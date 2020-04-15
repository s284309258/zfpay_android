package com.lckj.jycm.center.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.custom.WrapPictureExternalPreviewActivity;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.CoupleBackActivity;
import com.lckj.jycm.center.activity.ApplyMerchantFunctionActivity;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.lcwl.home.ActionUtils;
import com.lckj.utilslib.view.RatioImage;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyMerchantFunctionAdapter extends RecyclerView.Adapter<ApplyMerchantFunctionAdapter.ViewHolder> {
    private final List<LocalMedia> datas;
    private final int maxCount;
    private final TextView dataWatcher;
    private final Activity mActivity;
    private boolean enabled = true;

    public ApplyMerchantFunctionAdapter(Activity activity, List<LocalMedia> mDatas1, int maxCount, TextView data1Watcher) {
        datas = mDatas1;
        mActivity = activity;
        this.maxCount = maxCount;
        this.dataWatcher = data1Watcher;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_apply_merchant_img, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        if (i > datas.size() - 1) {
            holder.rivImg.setImageResource(R.mipmap.icon_add_picture);
            holder.rivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ActionUtils.hasPermission(MainApplication.getInstance().getLastActivity(), new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    })) {
                        ActionUtils.requestPermission(MainApplication.getInstance().getLastActivity(), 948192, new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        });
                        return;
                    }
                    PictureSelector.create(MainApplication.getInstance().getLastActivity())
                            .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                            .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                            .maxSelectNum(3 - datas.size())// 最大图片选择数量
                            .minSelectNum(1)// 最小选择数量
                            .imageSpanCount(4)// 每行显示个数
                            .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                            .previewImage(true)// 是否可预览图片
                            .previewVideo(true)// 是否可预览视频
                            .isCamera(true)// 是否显示拍照按钮
                            .enableCrop(true)
                            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                            .synOrAsy(true)//同步true或异步false 压缩 默认同步
                            .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                            .selectionMedia(selectList)// 是否传入已选图片
                            .minimumCompressSize(100)// 小于100kb的图片不压缩
                            .enableCrop(true)
                            .withAspectRatio(4,3)
                            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    if (mActivity instanceof ApplyMerchantFunctionActivity) {
                        ApplyMerchantFunctionActivity.instance.operatingList = datas;
                        ApplyMerchantFunctionActivity.instance.operatingAdapter = ApplyMerchantFunctionAdapter.this;
                    }else if(mActivity instanceof CoupleBackActivity) {
                        CoupleBackActivity.instance.operatingList = datas;
                        CoupleBackActivity.instance.operatingAdapter = ApplyMerchantFunctionAdapter.this;
                    }
                }
            });
            holder.ivDelete.setVisibility(View.GONE);
        } else {
            final LocalMedia localMedia = datas.get(i);
            ImageLoader.loadImage(localMedia.getPath(), holder.rivImg, 2, 0);
            holder.rivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), WrapPictureExternalPreviewActivity.class);
                    intent.putExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) datas);
                    intent.putExtra(PictureConfig.EXTRA_POSITION, i);
                    MainApplication.getInstance().getLastActivity().startActivityForResult(intent, PictureConfig.CHOOSE_REQUEST);
                    MainApplication.getInstance().getLastActivity().overridePendingTransition(com.luck.picture.lib2.R.anim.a5, 0);
                }
            });
            if (enabled) {
                holder.ivDelete.setVisibility(View.VISIBLE);
            } else {
                holder.ivDelete.setVisibility(View.GONE);
            }
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(localMedia);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (!enabled) return datas.size();
        dataWatcher.setText(datas.size() > 0 ? String.valueOf(datas.size()) : "");
        if (datas.size() < maxCount) return datas.size() + 1;
        return datas.size();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        notifyDataSetChanged();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.riv_img)
        RatioImage rivImg;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int left;
        int right;
        int top;
        int bottom;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = left;
            outRect.right = right;
            outRect.top = top;
            outRect.bottom = bottom;

        }

        public SpaceItemDecoration(int space) {
            this.left = space;
            this.right = space;
            this.top = space;
        }

        public SpaceItemDecoration(int left, int top) {
            this.left = left;
            this.right = left;
            this.top = top;
            this.bottom = top;
        }
    }


}
