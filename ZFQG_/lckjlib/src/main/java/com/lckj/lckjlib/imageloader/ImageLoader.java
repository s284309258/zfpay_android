package com.lckj.lckjlib.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.lckjlib.R;

public class ImageLoader {
    public static void loadImage(String url, ImageView imageView) {
        if (needAbort(imageView))return;
        if (url!=null&&!url.contains("/"))url = ProviderModule.getDataManager(imageView.getContext()).getQiniuDomain()+"/"+url;
        Glide.with(imageView.getContext())
                .load(url).apply(new RequestOptions().placeholder(R.mipmap.avatar_default_40)//图片加载出来前，显示的图片
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .error(R.mipmap.avatar_default_40))//图片加载失败后，显示的图片)

                .into(imageView);
    }

    public static void loadImage565(String url, ImageView imageView) {
        if (needAbort(imageView))return;
        if (url!=null&&!url.contains("/"))url = ProviderModule.getDataManager(imageView.getContext()).getQiniuDomain()+"/"+url;
        Glide.with(imageView.getContext())
                .load(url).apply(new RequestOptions().placeholder(R.mipmap.avatar_default_40)//图片加载出来前，显示的图片
                .apply(new RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.avatar_default_40))//图片加载失败后，显示的图片)
                .into(imageView);
    }

    public static void loadImageWithPlaceholder(String url, ImageView imageView,@DrawableRes int resourceId) {
        if (needAbort(imageView))return;
        if (url!=null&&!url.contains("/"))url = ProviderModule.getDataManager(imageView.getContext()).getQiniuDomain()+"/"+url;
        Glide.with(imageView.getContext())
                .load(url).apply(new RequestOptions().placeholder(resourceId)//图片加载出来前，显示的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(resourceId))//图片加载失败后，显示的图片)
                .into(imageView);
    }

    public static void loadImage(String url, ImageView imageView, int radius, int padding) {
        if (needAbort(imageView))return;
        if (url!=null&&!url.contains("/"))url = ProviderModule.getDataManager(imageView.getContext()).getQiniuDomain()+"/"+url;
        GlideRoundTransform transformation = new GlideRoundTransform(imageView.getContext(), radius, padding);
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url).apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).transform(transformation)/*.centerCrop()*/
                                                    .placeholder(R.mipmap.avatar_default_40)//图片加载出来前，显示的图片
                                                    .error(R.mipmap.avatar_default_40)//图片加载失败后，显示的图片
                                                    )

                .into(imageView);
    }

    public static void loadImageWithDefault(String url, ImageView imageView,int defaultPic) {
        if (needAbort(imageView))return;
        if (url!=null&&!url.contains("/"))url = ProviderModule.getDataManager(imageView.getContext()).getQiniuDomain()+"/"+url;
        Glide.with(imageView.getContext())
                .load(url).apply(new RequestOptions().placeholder(defaultPic)//图片加载出来前，显示的图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(defaultPic))//图片加载失败后，显示的图片)

                .into(imageView);
    }

    public static void loadImageWithoutDefault(String url, final ImageView imageView) {
        if (needAbort(imageView))return;
        if (url!=null&&!url.contains("/"))url = ProviderModule.getDataManager(imageView.getContext()).getQiniuDomain()+"/"+url;

        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }
                });
//        Glide.with(imageView.getContext())
//                .load(url).apply(new RequestOptions()
//                .diskCacheStrategy(DiskCacheStrategy.ALL))
//                .into(imageView);
    }

    public static boolean needAbort(ImageView imageView){
        if (imageView==null)return true;
        Context context = imageView.getContext();
        if (context == null) return true;
        if (context instanceof Activity){
            if (((Activity) context).isFinishing())return true;
        }
        return false;
    }

}
