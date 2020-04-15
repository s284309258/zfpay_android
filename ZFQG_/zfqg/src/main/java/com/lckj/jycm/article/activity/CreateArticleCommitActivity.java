package com.lckj.jycm.article.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lckj.base.MainApplication;
import com.lckj.custom.CustomPictureExternalPreviewActivity;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.CommonBean;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.article.picker.SelectTools;
import com.lckj.jycm.network.ArticleByURLResponse;
import com.lckj.jycm.network.CreateAdvArticleRequest;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.utils.ToastHelper;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.lckjlib.picker.PickerArticleSelector;
import com.lckj.lcwl.home.ActionUtils;
import com.lckj.utilslib.TextWatcherHelper;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;
import com.luck.picture.lib2.tools.DoubleUtils;
import com.lython.network.model.Const;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreateArticleCommitActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_brief_intro)
    EditText editBriefIntro;
    @BindView(R.id.tv_select_type)
    TextView tvSelectType;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_image_cover)
    TextView tvImageCover;
    private ArticleByURLResponse.DataBean dataBean = new ArticleByURLResponse.DataBean();

    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private List<LocalMedia> localMedia = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParams();
        setContentView(R.layout.activity_create_article_commit);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
    }

    private void getIntentParams() {
        Intent intent = getIntent();
        Serializable data = intent.getSerializableExtra("data");
        if (data instanceof ArticleByURLResponse.DataBean) {
            dataBean = (ArticleByURLResponse.DataBean) data;
        }
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        leftAction.setText("");
        customTitle.setText(R.string.创建文章);
        tvTitle.setText(dataBean.getArtTitle());
        editBriefIntro.setText(dataBean.getArtIntro());
        if (!TextUtils.isEmpty(dataBean.getArtCover())) {
            ivPhoto.setVisibility(View.VISIBLE);
            String img;
            if (dataBean.getArtCover().startsWith("http")) {
                img = dataBean.getArtCover();
                localMedia.add(new LocalMedia().setPath(dataBean.getArtCover()));
            } else {
                localMedia.add(new LocalMedia().setPath(ProviderModule.getDataManager(this).getQiniuDomain() + "/" + dataBean.getArtCover()));
                img = ProviderModule.getDataManager(this).getQiniuDomain() + "/" + dataBean.getArtCover();
            }
            tvImageCover.setText("ok");
            ImageLoader.loadImage(img, ivPhoto);
        } else {
            ivPhoto.setVisibility(View.GONE);
            tvImageCover.setText("");
        }
        tvSelectType.setText(getArticle_types().get(0));
        TextWatcherHelper.bindView(false, tvCommit, tvSelectType, tvTitle, editBriefIntro, tvImageCover);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.iv_add, R.id.iv_photo, R.id.ll_select_type, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.iv_add:
                if (!ActionUtils.hasPermission(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                })) {
                    ActionUtils.requestPermission(this, 948192, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    });
                    return;
                }
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
                        .withAspectRatio(2, 1)
                        .enableCrop(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            case R.id.iv_photo:
                if (!DoubleUtils.isFastDoubleClick()) {
                    Intent intent = new Intent(this, CustomPictureExternalPreviewActivity.class);
                    intent.putExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) localMedia);
                    intent.putExtra(PictureConfig.EXTRA_POSITION, 0);
                    intent.putExtra("tv", getString(R.string.update_img));
                    intent.putExtra("cropRadio", 2f);
                    startActivityForResult(intent, PictureConfig.CHOOSE_REQUEST);
                    overridePendingTransition(com.luck.picture.lib2.R.anim.a5, 0);
                }
//                PictureSelector.create(this).themeStyle(R.style.picture_QQ_style).openExternalPreview(0, this.localMedia);
                break;
            case R.id.ll_select_type:
                SelectTools.selectOne(getString(com.lckj.jycm.R.string.选择文章类型), this, getArticle_types(), new PickerArticleSelector.OnWheelChangeListenerAdapter() {
                    @Override
                    public void OnWheelChange(int position1, int position2, int position3, boolean isCancel) {
                        if (isCancel) return;
                        tvSelectType.setText(getArticle_types().get(position1));
                    }
                });
                break;
            case R.id.tv_commit:
                uploadImages();
                break;
        }
    }


    private void uploadImages() {
        if (localMedia == null || localMedia.size() == 0) return;
        if (localMedia.get(0).getPath() != null && !localMedia.get(0).getPath().startsWith("http")) {
            ProgressDlgHelper.openDialog(this);
            UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
            List<File> fileList = new ArrayList<>();
            fileList.add(new File(localMedia.get(0).getPath()));
            uploadImageToQnUtils.uploadImage(null, Const.UPLOAD_TOKEN_BIZ_COMMON, fileList, ProviderModule.getDataManager(this).getToken(), "0");
            uploadImageToQnUtils.setPicCallback(new UploadImageToQnUtils.QiniuTokenListener() {
                @Override
                public void onSuccessedcallBack(final List<String> imageUrlList, List<String> fileList) {
                    if (imageUrlList != null && imageUrlList.size() > 0) {
                        dataBean.setArtCover(imageUrlList.get(0));
                    }
                    create();
                }

                @Override
                public void onFailedcallBack() {
                    ProgressDlgHelper.closeDialog();
                    ToastHelper.showToast(getString(R.string.failed_to_upload_pictures));
                }
            });
        } else {
            create();
        }
    }

    private void create() {
        ProgressDlgHelper.openDialog(MainApplication.getInstance().getActivityContext());
        infoService.createAdvArticle(new CreateAdvArticleRequest(dataBean.getArtCover(), editBriefIntro.getText().toString(), tvTitle.getText().toString(), tvSelectType.getText().toString(), dataBean.getArtUrl(), dataBean.getSource(), dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<CommonBean>(getBaseContext()) {
                    @Override
                    public void onSuccessCall(CommonBean response) {
                        ProgressDlgHelper.closeDialog();
                        Toast.makeText(CreateArticleCommitActivity.this, R.string.创建成功, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), MyArticleActivity.class).putExtra("created", true));
                    }
                }, new ThrowableConsumer<Throwable>(getBaseContext()));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (localMedia != null && localMedia.size() > 0) {
                        String cutPath = localMedia.get(0).getCutPath();
                        this.localMedia.clear();
                        this.localMedia.add(new LocalMedia().setPath(cutPath));
                        ImageLoader.loadImage(cutPath, ivPhoto);
                        ivPhoto.setVisibility(View.VISIBLE);
                        tvImageCover.setText("ok");
                        ivAdd.setVisibility(View.GONE);
                    }
//                    this.localMedia = PictureSelector.obtainMultipleResult(data);
            }
        }
    }

    private List<String> getArticle_types() {
        return Arrays.asList(getResources().getStringArray(R.array.article_types));
    }
}
