package com.lckj.jycm.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("CheckResult")
public class InvestAdActivity extends BaseActvity {
    private static final int CHOOSE_REQUEST_AD_IMG = 10;
    private static final int SELECTOR_ARTICLE = 100;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rl_img)
    RelativeLayout rlImg;
    @BindView(R.id.et_title)
    ClearEditText etTitle;
    @BindView(R.id.et_url)
    ClearEditText etUrl;
    @BindView(R.id.btn_copy)
    Button btnCopy;
    @BindView(R.id.et_intro)
    ClearEditText etIntro;
    @BindView(R.id.tv_article)
    TextView tvArticle;
    @BindView(R.id.rl_selecotr_article)
    RelativeLayout rlSelecotrArticle;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tv_img)
    TextView tvImg;
    @BindView(R.id.iv_ad_img)
    ImageView ivAdImg;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.xx3)
    TextView xx3;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.xx4)
    TextView xx4;
    @BindView(R.id.tv4)
    TextView tv4;
    private String mId;
    private String link;
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_ad);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.toufangguanggao));
        leftAction.setText("");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.rl_img, R.id.btn_copy, R.id.rl_selecotr_article, R.id.btn_next,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_img:
                selecotrImg();
                break;
            case R.id.btn_copy:
                copy_url();
                break;
            case R.id.rl_selecotr_article:
                Intent intent = new Intent(this, SelectorArticleActivity.class);
                intent.putExtra("who", 2);
                startActivityForResult(intent, SELECTOR_ARTICLE);
                break;
            case R.id.btn_next:
                if (CheckEidt()) {
                    next();
                }
                break;
        }
    }

    private void next() {
        String title = etTitle.getText().toString();
        String url = etUrl.getText().toString();
        String intro = etIntro.getText().toString();
        Intent intent = new Intent(this, InvestAdPayActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("intro", intro);
        intent.putExtra("link", link);
        intent.putExtra("id", mId);
        intent.putExtra("image", selectList.get(0));
        startActivity(intent);
    }

    /*private void uploadImages() {
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
                next();

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(InvestAdActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }*/

    private void selecotrImg() {
        PictureSelector.create(InvestAdActivity.this)
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
                .enableCrop(true)
                .withAspectRatio(3,1)
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                            .selectionMedia(selectList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(CHOOSE_REQUEST_AD_IMG);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_REQUEST_AD_IMG:
                    List<LocalMedia> localMedia1 = PictureSelector.obtainMultipleResult(data);
                    if (localMedia1!=null&&localMedia1.size()>0){
                        LocalMedia localMedia2 = localMedia1.get(0);
                        localMedia2.setPath(localMedia2.getCutPath());
                        selectList.clear();
                        selectList.add(localMedia2);
                        File file = new File(selectList.get(0).getPath());
                        if (file.exists()) {
                            Uri uri = Uri.fromFile(file);
                            ivAdImg.setImageURI(uri);
                            rl.setVisibility(View.GONE);
                        }
                    }
                case SELECTOR_ARTICLE:
                    mId = data.getStringExtra("id");
                    mTitle = data.getStringExtra("title");
                    tvArticle.setText(mTitle);
                    break;
            }
        }
    }

    private boolean CheckEidt() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            Utils.showMsg(this, getString(R.string.toast_ad_title));
            return false;
        } else if (selectList == null && selectList.size() == 0) {
            Utils.showMsg(this, getString(R.string.toast_ad_img));
            return false;
        } else if (TextUtils.isEmpty(etUrl.getText().toString())) {
            Utils.showMsg(this, getString(R.string.toast_ad_url));
            return false;
        } else if (TextUtils.isEmpty(etIntro.getText().toString())) {
            Utils.showMsg(this, getString(R.string.toast_ad_intro));
            return false;
        } /*else if (TextUtils.isEmpty(tvArticle.getText().toString())) {
            Utils.showMsg(this, getString(R.string.toast_select_article));
            return false;
        }*/
        return true;
    }

    private void copy_url() {
        try {
            //GET贴板是否有内容
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = clipboardManager.getPrimaryClip();
            //获取到内容
            ClipData.Item item = clipData.getItemAt(0);
            String text = item.getText().toString();
            etUrl.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
