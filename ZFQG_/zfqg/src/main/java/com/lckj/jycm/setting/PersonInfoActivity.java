package com.lckj.jycm.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.zfqg.activity.UpdateRemarkActivity;
import com.lckj.zfqg.activity.UpdatePhoneActivity;
import com.lckj.jycm.article.picker.SelectTools;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.UpdatePersonalRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.lckjlib.picker.PickerArticleSelector;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonInfoActivity extends BaseActvity {
    private static final int UPDATE_USERNAME = 100;
    private static final int CHOOSE_REQUEST_HEAD_IMG = 101;
    private static final int UPDATE_PHONE = 102;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.ll_head_img)
    LinearLayout llHeadImg;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rl_username)
    RelativeLayout rlUsername;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_invitation_code)
    TextView tvInvitationCode;

    private String link;
    private List<LocalMedia> selectList;
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;
    private int mPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvPhone.setText(dataManager.getUserTel());
        tvUsername.setText(dataManager.getUserName());
        tvSex.setText(getSexList().get(dataManager.getSex()));
        ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 10, 1);
        customTitle.setText(getString(R.string.person_info));
        tvInvitationCode.setText(dataManager.getInvitationCode());
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.ll_head_img, R.id.rl_username, R.id.rl_sex, R.id.rl_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.ll_head_img:
                selecotrImg();
                break;
            case R.id.rl_username:
                Intent intent = new Intent(this, UpdateRemarkActivity.class);
                intent.putExtra("title", getString(R.string.update_username));
                startActivityForResult(intent, UPDATE_USERNAME);
                break;
            case R.id.rl_sex:
                editSex();
                break;
            case R.id.rl_phone:
                startActivityForResult(new Intent(this, UpdatePhoneActivity.class), UPDATE_PHONE);
                break;
        }
    }

    private void uploadImages() {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        List<String> pictureTypes = new ArrayList<>();
        for (LocalMedia localMedia : selectList) {
            if (!TextUtils.isEmpty(localMedia.getPath()) && !localMedia.getPath().startsWith("http")) {
                fileList.add(new File(localMedia.getPath()));
                pictureTypes.add(localMedia.getPictureType());
            }
        }
        String biz = Const.UPLOAD_TOKEN_BIZ_COMMON;
        uploadImageToQnUtils.uploadImage(pictureTypes, biz, fileList, dataManager.getToken(), "0");
        uploadImageToQnUtils.setPicCallback(new UploadImageToQnUtils.QiniuTokenListener() {
            @Override
            public void onSuccessedcallBack(List<String> imageUrlList, List<String> fileList) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < imageUrlList.size(); i++) {
                    String s = imageUrlList.get(i);
                    if (i == 0) {
                        stringBuilder.append(s);
                    } else {
                        stringBuilder.append(",").append(s);
                    }
                }
                link = stringBuilder.toString();
                submit(1);

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(PersonInfoActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit(int type) {
        switch (type) {
            case 0:
                ProgressDlgHelper.openDialog(this);
                mFrontUserService.updatePersonal(new UpdatePersonalRequest("", mPosition + "", "", "", "", dataManager.getToken(), "", ""))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                if (response.isSuccess()) {
                                    Utils.showMsg(PersonInfoActivity.this, getString(R.string.toast_update_success));
                                    dataManager.setSex(mPosition);
                                    tvSex.setText(getSexList().get(mPosition));
                                } else {
                                    Utils.showMsg(PersonInfoActivity.this, response.getMsg());
                                }

                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
            case 1:
                mFrontUserService.updatePersonal(new UpdatePersonalRequest("", "", "", "", link, dataManager.getToken(), "", ""))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                if (response.isSuccess()) {
                                    Utils.showMsg(PersonInfoActivity.this, getString(R.string.toast_update_success));
                                    dataManager.setHeadPhoto(link);
                                    ImageLoader.loadImage(link, ivHeadImg, 10, 1);
                                } else {
                                    Utils.showMsg(PersonInfoActivity.this, response.getMsg());
                                }

                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_REQUEST_HEAD_IMG:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    uploadImages();
                    break;
                case UPDATE_USERNAME:
                    tvUsername.setText(data.getStringExtra("username"));
                    break;
                case UPDATE_PHONE:
                    tvPhone.setText(data.getStringExtra("phone"));
                    break;
            }
        }
    }

    private void selecotrImg() {
        PictureSelector.create(PersonInfoActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                            .selectionMedia(selectList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(CHOOSE_REQUEST_HEAD_IMG);//结果回调onActivityResult code
    }

    private void editSex() {
        SelectTools.selectOne(getString(R.string.selector_sex), this, getSexList(), new PickerArticleSelector.OnWheelChangeListenerAdapter() {
            @Override
            public void OnWheelChange(int position1, int position2, int position3, boolean isCancel) {
                if (isCancel) return;
                mPosition = position1;
                if (position1 == dataManager.getSex()) {
                } else {
                    submit(0);
                }
            }
        });
    }

    private List<String> getSexList() {
        final List<String> list = new ArrayList<>();
        list.add(getString(R.string.mam));
        list.add(getString(R.string.woman));
        return list;
    }
}
