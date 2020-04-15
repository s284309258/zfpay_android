package com.lckj.zfqg.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<String> mImageList;

    public ImageAdapter(Context context, List<String> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_img, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            ImageLoader.loadImage(mImageList.get(i), iv);

            String url = mImageList.get(i);
            if (url!=null&&!url.startsWith("http")) {
                url = ProviderModule.getDataManager(iv.getContext()).getQiniuDomain()+url;
            }
            final String finalUrl = url;
            Glide.with(iv.getContext())
                    .asBitmap()//强制Glide返回一个Bitmap对象
                    .apply(new RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                    .load(url)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            int displayWidth = Utils.getWidth(iv.getContext());
                            int displayHeight = height*displayWidth/width;
                            ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                            /*if (width<=displayWidth){
                                layoutParams.height = height;
                                layoutParams.width = width;
                            }else{
                                layoutParams.height = displayHeight;
                                layoutParams.width = displayWidth;
                            }*/
                            layoutParams.height = displayHeight;
                            layoutParams.width = displayWidth;
                            iv.setLayoutParams(layoutParams);
                            ImageLoader.loadImage565(finalUrl,iv);
                        }

                    /*@Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {

                    }*/
                    });
        }
    }
}
