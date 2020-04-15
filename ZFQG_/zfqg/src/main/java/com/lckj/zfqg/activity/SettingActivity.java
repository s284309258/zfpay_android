package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.lckj.jycm.zfqg_network.ModifyUserInfoRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.widget.ConfirmDialog;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SettingActivity extends BaseActvity {
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
    @BindView(R.id.tv_remark_status)
    TextView tvRemarkStatus;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_inform)
    ImageView ivInform;
    @BindView(R.id.iv_voice)
    ImageView ivVoice;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    private String link;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private List<LocalMedia> selectList;
    private boolean isInform;
    private boolean isVoice;
    private ConfirmDialog mConfirmDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updatePhone(String msg) {
        if (msg.equals(getString(R.string.修改手机号码))) {
            tvPhone.setText(dataManager.getUserTel());
        }
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.设置));
        tvPhone.setText(dataManager.getUserTel());
        tvVersion.setText(Utils.getVersionName(this));
        ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 25, 0);
        switch (dataManager.getAuthStatus()) {
            case "00":
                tvRemarkStatus.setText(getString(R.string.未实名认证));
                break;
            case "04":
                tvRemarkStatus.setText(getString(R.string.实名认证审核中));
                break;
            case "08":
                tvRemarkStatus.setText(getString(R.string.实名认证失败));
                break;
            case "09":
                tvRemarkStatus.setText(dataManager.getUserName());
                break;
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.left_action, R.id.rl_head_img, R.id.rl_remark_name, R.id.rl_phone, R.id.rl_login_pw, R.id.rl_pay_pw, R.id.rl_inform, R.id.rl_voice, R.id.rl_version, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_head_img:
                selecotrImg();
                break;
            case R.id.rl_remark_name:
//                startActivityForResult(new Intent(this, UpdateRemarkActivity.class), UPDATE_USERNAME);
                break;
            case R.id.rl_phone:
                startActivityForResult(new Intent(this, UpdatePhoneActivity.class), UPDATE_PHONE);
                break;
            case R.id.rl_login_pw:
                startActivity(new Intent(this, UpdateLoginPwActivity.class));
                break;
            case R.id.rl_pay_pw:
                startActivity(new Intent(this, UpdatePayPwActivity.class));
                break;
            case R.id.rl_inform:
                isInform = !isInform;
                ivInform.setSelected(isInform);
                break;
            case R.id.rl_voice:
                isVoice = !isVoice;
                ivVoice.setSelected(isVoice);
                break;
            case R.id.rl_version:
                break;
            case R.id.btn_logout:
                showLogoutDialog();
                break;
        }
    }

    private void showLogoutDialog() {
        if (mConfirmDialog == null)
            mConfirmDialog = new ConfirmDialog(this, R.style.BottomDialog2);
        mConfirmDialog.show(getString(R.string.确定退出当前账号吗),"");
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
                    tvRemarkStatus.setText(data.getStringExtra("username"));
                    break;
                case UPDATE_PHONE:
                    tvPhone.setText(data.getStringExtra("phone"));
                    break;
            }
        }
    }

    private void selecotrImg() {
        PictureSelector.create(SettingActivity.this)
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
//                .compress(true)
                .forResult(CHOOSE_REQUEST_HEAD_IMG);//结果回调onActivityResult code
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
                Utils.showMsg(SettingActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit() {
        mMyService.modifyUserInfo(new ModifyUserInfoRequest(dataManager.getToken(), link))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        dataManager.setHeadPhoto(link);
                        ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 25, 0);
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(getContext(), getString(R.string.修改头像成功));
                        EventBus.getDefault().post(getString(R.string.修改头像成功));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void logout() {
        ProgressDlgHelper.openDialog(this);
        mMyService.userLogOut(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(getContext(), getString(R.string.退出登录成功));
                        dataManager.setToken("");
                        MainApplication.getInstance().clearActivitys();
                        startActivity(new Intent(getContext(), LoginActivity.class));

                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
}
