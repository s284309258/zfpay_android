package com.lckj.zfqg.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetUserAuthStatusBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SubmitUserAuthInfoRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RealNameActivity extends BaseActvity {
    private static final int FRONT_ID_CARD_IMG = 301;
    private static final int BACK_ID_CARD_IMG = 302;
    private static final int HAND_ID_CARD_IMG = 303;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_real_name)
    ClearEditText etRealName;
    @BindView(R.id.et_id_card)
    ClearEditText etIdCard;
    @BindView(R.id.tv_add_front)
    TextView tvAddFront;
    @BindView(R.id.tv_add_back)
    TextView tvAddBack;
    @BindView(R.id.tv_add_hand)
    TextView tvAddHand;
    @BindView(R.id.iv_front)
    ImageView ivFront;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_hand)
    ImageView ivHand;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.ll_audit)
    LinearLayout llAudit;
    @BindView(R.id.btn_anew_apply)
    Button btnAnewApply;
    @BindView(R.id.ll_error)
    LinearLayout llError;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.ll_succeed)
    LinearLayout llSucceed;
    @BindView(R.id.tv_error_msg)
    TextView tvErrorMsg;
    private String link;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.实名认证));
        selectList.add(null);
        selectList.add(null);
        selectList.add(null);

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getUserAuthStatus(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserAuthStatusBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserAuthStatusBean response) {
                        ProgressDlgHelper.closeDialog();
                        switch (response.getData().getUserAuthStatus().getAuth_status()) {
                            case "00":
                                llNoData.setVisibility(View.VISIBLE);
                                llAudit.setVisibility(View.GONE);
                                llError.setVisibility(View.GONE);
                                llSucceed.setVisibility(View.GONE);
                                break;
                            case "04":
                                llNoData.setVisibility(View.GONE);
                                llAudit.setVisibility(View.VISIBLE);
                                llError.setVisibility(View.GONE);
                                llSucceed.setVisibility(View.GONE);
                                break;
                            case "08":
                                llNoData.setVisibility(View.GONE);
                                llAudit.setVisibility(View.GONE);
                                llError.setVisibility(View.VISIBLE);
                                llSucceed.setVisibility(View.GONE);
                                tvErrorMsg.setText(response.getData().getUserAuthStatus().getAuth_remark());
                                break;
                            case "09":
                                llNoData.setVisibility(View.GONE);
                                llAudit.setVisibility(View.GONE);
                                llError.setVisibility(View.GONE);
                                llSucceed.setVisibility(View.VISIBLE);
                                tvIdCard.setText(response.getData().getUserAuthStatus().getId_card());
                                tvRealName.setText(response.getData().getUserAuthStatus().getReal_name());
                                break;
                        }
                        dataManager.setUserName(response.getData().getUserAuthStatus().getReal_name());
                        dataManager.setAuthStatus(response.getData().getUserAuthStatus().getAuth_status());
                        EventBus.getDefault().post(getString(R.string.实名认证));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.iv_front, R.id.iv_back, R.id.iv_hand, R.id.btn_submit, R.id.btn_anew_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.iv_front:
                selecotrImg(FRONT_ID_CARD_IMG);
                break;
            case R.id.iv_back:
                selecotrImg(BACK_ID_CARD_IMG);
                break;
            case R.id.iv_hand:
                selecotrImg(HAND_ID_CARD_IMG);
                break;
            case R.id.btn_submit:
                if (checkEdit()) {
                    uploadImages();
                }
                break;
            case R.id.btn_anew_apply:
                llNoData.setVisibility(View.VISIBLE);
                llAudit.setVisibility(View.GONE);
                llError.setVisibility(View.GONE);
                llSucceed.setVisibility(View.GONE);
                break;
        }
    }

    private boolean checkEdit() {
        if (TextUtils.isEmpty(etRealName.getText().toString())) {
            Utils.showMsg(this, etRealName.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etIdCard.getText().toString())) {
            Utils.showMsg(this, etIdCard.getHint().toString());
            return false;
        } else if (selectList.get(0) == null) {
            Utils.showMsg(this, getString(R.string.身份证正面照));
            return false;
        } else if (selectList.get(1) == null) {
            Utils.showMsg(this, getString(R.string.身份证反面照));
            return false;
        } else if (selectList.get(2) == null) {
            Utils.showMsg(this, getString(R.string.手持身份证照));
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FRONT_ID_CARD_IMG:
                    selectList.remove(0);
                    selectList.add(0, PictureSelector.obtainMultipleResult(data).get(0));
                    ivFront.setImageBitmap(BitmapFactory.decodeFile(selectList.get(0).getCompressPath()));
                    break;
                case BACK_ID_CARD_IMG:
                    selectList.remove(1);
                    selectList.add(1, PictureSelector.obtainMultipleResult(data).get(0));
                    ivBack.setImageBitmap(BitmapFactory.decodeFile(selectList.get(1).getCompressPath()));
                    break;
                case HAND_ID_CARD_IMG:
                    selectList.remove(2);
                    selectList.add(2, PictureSelector.obtainMultipleResult(data).get(0));
                    ivHand.setImageBitmap(BitmapFactory.decodeFile(selectList.get(2).getCompressPath()));
                    break;
            }
        }
    }

    private void selecotrImg(int code) {
        PictureSelector.create(this)
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
                .compress(true)
                .forResult(code);//结果回调onActivityResult code
    }

    private void uploadImages() {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        List<String> pictureTypes = new ArrayList<>();
        for (LocalMedia localMedia : selectList) {
            if (!TextUtils.isEmpty(localMedia.getCompressPath()) && !localMedia.getCompressPath().startsWith("http")) {
                fileList.add(new File(localMedia.getCompressPath()));
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
                submit();

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(RealNameActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit() {
        String idCard = etIdCard.getText().toString();
        String realname = etRealName.getText().toString();
        mMyService.submitUserAuthInfo(new SubmitUserAuthInfoRequest(dataManager.getToken(), idCard, realname, link))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(RealNameActivity.this, getString(R.string.上传成功));
                        EventBus.getDefault().post(getString(R.string.实名认证));
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
}
