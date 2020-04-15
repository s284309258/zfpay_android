package com.lckj.lckjlib.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

public class GlideRoundTransform extends BitmapTransformation {
 
    private float radius = 0f;
    private float padding  = 0f;
 
    public GlideRoundTransform(Context context) {
        this(context, 4, 0);
    }
 
    public GlideRoundTransform(Context context, int dp, int padding) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        this.padding = Resources.getSystem().getDisplayMetrics().density*padding;
    }
 
    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, bitmap);
    }
 
    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
 
        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }


 
        Canvas canvas = new Canvas(result);
        if (padding>0){
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
        }
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f+padding, 0f+padding, source.getWidth()-padding, source.getHeight()-padding);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }
 
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
 
    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(getClass().getName().getBytes(CHARSET));
    }
 
}