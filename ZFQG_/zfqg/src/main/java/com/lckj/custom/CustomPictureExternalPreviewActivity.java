package com.lckj.custom;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.lckj.jycm.R;
import com.lckj.lcwl.home.ActionUtils;
import com.luck.picture.lib2.PictureBaseActivity;
import com.luck.picture.lib2.PictureSelectionModel;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.dialog.CustomDialog;
import com.luck.picture.lib2.entity.LocalMedia;
import com.luck.picture.lib2.permissions.RxPermissions;
import com.luck.picture.lib2.photoview.OnViewTapListener;
import com.luck.picture.lib2.photoview.PhotoView;
import com.luck.picture.lib2.tools.PictureFileUtils;
import com.luck.picture.lib2.tools.ScreenUtils;
import com.luck.picture.lib2.tools.ToastManage;
import com.luck.picture.lib2.widget.PreviewViewPager;
import com.luck.picture.lib2.widget.longimage.ImageSource;
import com.luck.picture.lib2.widget.longimage.ImageViewState;
import com.luck.picture.lib2.widget.longimage.SubsamplingScaleImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//import com.bumptech.glide.request.animation.GlideAnimation;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.ui
 * email：邮箱->893855882@qq.com
 * data：17/01/18
 */
public class CustomPictureExternalPreviewActivity extends PictureBaseActivity implements View.OnClickListener {
    @BindView(R.id.replace_icon)
    TextView replaceIcon;
    private ImageButton left_back;
    private TextView tv_title;
    private PreviewViewPager viewPager;
    private List<LocalMedia> images = new ArrayList<>();
    private int position = 0;
    private String directory_path;
    private SimpleFragmentAdapter adapter;
    private LayoutInflater inflater;
    private RxPermissions rxPermissions;
    private loadDataThread loadDataThread;
    private float cropRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParams();
        setContentView(R.layout.custom_picture_activity_external_preview);
        ButterKnife.bind(this);
        inflater = LayoutInflater.from(this);
        tv_title = (TextView) findViewById(com.luck.picture.lib2.R.id.picture_title);
        left_back = (ImageButton) findViewById(com.luck.picture.lib2.R.id.left_back);
//        left_back.setVisibility(View.GONE);
        viewPager = (PreviewViewPager) findViewById(com.luck.picture.lib2.R.id.preview_pager);
        position = getIntent().getIntExtra(PictureConfig.EXTRA_POSITION, 0);
        directory_path = getIntent().getStringExtra(PictureConfig.DIRECTORY_PATH);
        images = (List<LocalMedia>) getIntent().getSerializableExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST);
        replaceIcon.setText(getIntent().getStringExtra("tv"));
        left_back.setOnClickListener(this);
        initViewPageAdapterData();
    }

    private void getIntentParams() {
        Intent intent = getIntent();
        cropRadio = intent.getFloatExtra("cropRadio", 0);
    }

    private void initViewPageAdapterData() {
//        tv_title.setText(position + 1 + "/" + images.size());
        adapter = new SimpleFragmentAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                tv_title.setText(position + 1 + "/" + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
        overridePendingTransition(0, com.luck.picture.lib2.R.anim.a3);
    }

    @OnClick(R.id.replace_icon)
    public void onViewClicked() {
//        if (DoubleUtils.isFastDoubleClick()) return;
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
        PictureSelectionModel pictureSelectionModel = PictureSelector.create(this)
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
                .minimumCompressSize(100);// 小于100kb的图片不压缩
        if (cropRadio>0){
            pictureSelectionModel.enableCrop(true);
            pictureSelectionModel.withAspectRatio((int) cropRadio,1);
        }
        pictureSelectionModel.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    setResult(RESULT_OK, data);
                    finish();
                    overridePendingTransition(0, com.luck.picture.lib2.R.anim.a3);

            }
        }
    }


    public class SimpleFragmentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View contentView = inflater.inflate(com.luck.picture.lib2.R.layout.picture_image_preview, container, false);
            // 常规图控件
            final PhotoView imageView = (PhotoView) contentView.findViewById(com.luck.picture.lib2.R.id.preview_image);
            // 长图控件
            final SubsamplingScaleImageView longImg = (SubsamplingScaleImageView) contentView.findViewById(com.luck.picture.lib2.R.id.longImg);

            LocalMedia media = images.get(position);
            if (media != null) {
                final String pictureType = media.getPictureType();
                String path;
                if (media.isCut() && !media.isCompressed()) {
                    // 裁剪过
                    path = media.getCutPath();
                } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                    // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                    path = media.getCompressPath();
                } else {
                    path = media.getPath();
                }
                if (path != null && !path.contains("/"))
                    path = container.getContext().getSharedPreferences("lcwl", Context.MODE_PRIVATE).getString("pref_qiniu_domain", "") + "/" + path;
                boolean isHttp = PictureMimeType.isHttp(path);
                // 可以长按保存并且是网络图片显示一个对话框
                if (isHttp) {
                    showPleaseDialog();
                }
                boolean isGif = PictureMimeType.isGif(pictureType);
                final boolean eqLongImg = PictureMimeType.isLongImg(media);
                imageView.setVisibility(eqLongImg && !isGif ? View.GONE : View.VISIBLE);
                longImg.setVisibility(eqLongImg && !isGif ? View.VISIBLE : View.GONE);
                // 压缩过的gif就不是gif了
                if (isGif && !media.isCompressed()) {
                    Glide.with(CustomPictureExternalPreviewActivity.this).asGif()
                            .load(path).apply(new RequestOptions().override(ScreenUtils.getScreenWidth(CustomPictureExternalPreviewActivity.this), ScreenUtils.getScreenHeight(CustomPictureExternalPreviewActivity.this)).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL))
                            .listener(new RequestListener<GifDrawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }
                                /*@Override
                                public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    dismissDialog();
                                    return false;
                                }*/
                            })
                            .into(imageView);
                } else {
                    Glide.with(CustomPictureExternalPreviewActivity.this).asBitmap()
                            .load(path).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))

                            .into(new SimpleTarget<Bitmap>(ScreenUtils.getScreenWidth(CustomPictureExternalPreviewActivity.this), ScreenUtils.getScreenHeight(CustomPictureExternalPreviewActivity.this)) {
                                @Override
                                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                    super.onLoadFailed(errorDrawable);
                                    dismissDialog();
                                }

                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    dismissDialog();
                                    if (eqLongImg) {
                                        displayLongPic(resource, longImg);
                                    } else {
                                        imageView.setImageBitmap(resource);
                                    }
                                }

                                /*@Override
                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    super.onLoadFailed(e, errorDrawable);
                                    dismissDialog();
                                }*/


                            });
                }
                imageView.setOnViewTapListener(new OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        if (replaceIcon.getVisibility() == View.GONE) {
                            finish();
                        }
                        overridePendingTransition(0, com.luck.picture.lib2.R.anim.a3);
                    }
                });
                longImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (replaceIcon.getVisibility() == View.GONE) {
                            finish();
                        }
                        overridePendingTransition(0, com.luck.picture.lib2.R.anim.a3);
                    }
                });
                final String finalPath = path;
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (rxPermissions == null) {
                            rxPermissions = new RxPermissions(CustomPictureExternalPreviewActivity.this);
                        }
                        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }

                                    @Override
                                    public void onNext(Boolean aBoolean) {
                                        if (aBoolean) {
                                            showDownLoadDialog(finalPath);
                                        } else {
                                            ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_jurisdiction));
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                        return true;
                    }
                });
            }
            (container).addView(contentView, 0);
            return contentView;
        }
    }

    /**
     * 加载长图
     *
     * @param bmp
     * @param longImg
     */
    private void displayLongPic(Bitmap bmp, SubsamplingScaleImageView longImg) {
        longImg.setQuickScaleEnabled(true);
        longImg.setZoomEnabled(true);
        longImg.setPanEnabled(true);
        longImg.setDoubleTapZoomDuration(100);
        longImg.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
        longImg.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
        longImg.setImage(ImageSource.cachedBitmap(bmp), new ImageViewState(0, new PointF(0, 0), 0));
    }

    /**
     * 下载图片提示
     */
    private void showDownLoadDialog(final String path) {
        final CustomDialog dialog = new CustomDialog(CustomPictureExternalPreviewActivity.this,
                ScreenUtils.getScreenWidth(CustomPictureExternalPreviewActivity.this) * 3 / 4,
                ScreenUtils.getScreenHeight(CustomPictureExternalPreviewActivity.this) / 4,
                com.luck.picture.lib2.R.layout.picture_wind_base_dialog_xml, com.luck.picture.lib2.R.style.Theme_dialog);
        Button btn_cancel = (Button) dialog.findViewById(com.luck.picture.lib2.R.id.btn_cancel);
        Button btn_commit = (Button) dialog.findViewById(com.luck.picture.lib2.R.id.btn_commit);
        TextView tv_title = (TextView) dialog.findViewById(com.luck.picture.lib2.R.id.tv_title);
        TextView tv_content = (TextView) dialog.findViewById(com.luck.picture.lib2.R.id.tv_content);
        tv_title.setText(getString(com.luck.picture.lib2.R.string.picture_prompt));
        tv_content.setText(getString(com.luck.picture.lib2.R.string.picture_prompt_content));
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPleaseDialog();
                boolean isHttp = PictureMimeType.isHttp(path);
                if (isHttp) {
                    loadDataThread = new loadDataThread(path);
                    loadDataThread.start();
                } else {
                    // 有可能本地图片
                    try {
                        String dirPath = PictureFileUtils.createDir(CustomPictureExternalPreviewActivity.this,
                                System.currentTimeMillis() + ".png", directory_path);
                        PictureFileUtils.copyFile(path, dirPath);
                        ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_save_success) + "\n" + dirPath);
                        dismissDialog();
                    } catch (IOException e) {
                        ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_save_error) + "\n" + e.getMessage());
                        dismissDialog();
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    // 进度条线程
    public class loadDataThread extends Thread {
        private String path;

        public loadDataThread(String path) {
            super();
            this.path = path;
        }

        @Override
        public void run() {
            try {
                showLoadingImage(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 下载图片保存至手机
    public void showLoadingImage(String urlPath) {
        try {
            URL u = new URL(urlPath);
            String path = PictureFileUtils.createDir(CustomPictureExternalPreviewActivity.this,
                    System.currentTimeMillis() + ".png", directory_path);
            byte[] buffer = new byte[1024 * 8];
            int read;
            int ava = 0;
            long start = System.currentTimeMillis();
            BufferedInputStream bin;
            bin = new BufferedInputStream(u.openStream());
            BufferedOutputStream bout = new BufferedOutputStream(
                    new FileOutputStream(path));
            while ((read = bin.read(buffer)) > -1) {
                bout.write(buffer, 0, read);
                ava += read;
                long speed = ava / (System.currentTimeMillis() - start);
            }
            bout.flush();
            bout.close();
            Message message = handler.obtainMessage();
            message.what = 200;
            message.obj = path;
            handler.sendMessage(message);
        } catch (IOException e) {
            ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_save_error) + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    String path = (String) msg.obj;
                    ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_save_success) + "\n" + path);
                    dismissDialog();
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, com.luck.picture.lib2.R.anim.a3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadDataThread != null) {
            handler.removeCallbacks(loadDataThread);
            loadDataThread = null;
        }
    }
}
