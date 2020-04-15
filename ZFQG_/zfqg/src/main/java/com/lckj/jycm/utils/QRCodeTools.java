package com.lckj.jycm.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRCodeTools {
    private static final int IMAGE_HALFWIDTH = 40;
    private static final int QR_IMAGE_WIDTH = 300;
    public final static String QR_HEAD = "https://bill.s1.natapp.cc/givinggift/userCrowd/twoDimensionaCodes/";
    /**
     * Check if this device has a camera
     */
    public static boolean checkCameraHardware(Context context) {
        if (android.os.Build.MODEL.equals("vivo")) {//不检查vivo手机是否支持camera
            return true;
        }
        if (context == null) {
            return false;
        }
        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT)) {
            // this device has a front camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    public static Bitmap decodeFile(final String path) {
        Bitmap bmp = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        int scale = 1;
        if (opts.outWidth > 1080) {
            scale = opts.outWidth / 1080;
        }
        if (opts.outHeight > 1080) {
            scale = opts.outHeight / 1080 > scale ? opts.outHeight / 1080
                    : scale;
        }
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = scale;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            bmp = BitmapFactory.decodeFile(path, opts);
        } catch (OutOfMemoryError e) {
            System.gc();
        }
        return bmp;
    }

    /**
     * 生成二维码
     *
     * @param string  二维码中包含的文本信息
     * @param mBitmap logo图片
     *                [url=home.php?mod=space&uid=309376]@return[/url] Bitmap 位图
     */
    public static Bitmap createCode(String string, Bitmap mBitmap)
            throws WriterException {
        Matrix m = new Matrix();
        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
        float sy = (float) 2 * IMAGE_HALFWIDTH
                / mBitmap.getHeight();
        m.setScale(sx, sy);//设置缩放信息
        //将logo图片按martix设置的信息缩放
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                mBitmap.getWidth(), mBitmap.getHeight(), m, false);
        MultiFormatWriter writer = new MultiFormatWriter();
        Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");//设置字符编码
        BitMatrix matrix = writer.encode(string, BarcodeFormat.QR_CODE, QR_IMAGE_WIDTH, QR_IMAGE_WIDTH, hst);//生成二维码矩阵信息
        int width = matrix.getWidth();//矩阵高度
        int height = matrix.getHeight();//矩阵宽度
        int halfW = width / 2;
        int halfH = height / 2;
        int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
        for (int y = 0; y < height; y++) {//从行开始迭代矩阵
            for (int x = 0; x < width; x++) {//迭代列
                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
                        && y > halfH - IMAGE_HALFWIDTH
                        && y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息
//记录图片每个像素信息
                    pixels[y * width + x] = mBitmap.getPixel(x - halfW
                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
                } else {
                    if (matrix.get(x, y)) {//如果有黑块点，记录信息
                        pixels[y * width + x] = 0xff000000;//记录黑块信息
                    }
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 生成二维码
     *
     * @param string 二维码中包含的文本信息
     * @param deleteWhite
     */
    public static Bitmap createQRImage(String content, int widthPix, int heightPix) {
        if (!TextUtils.isEmpty(content)) {
            try {
                //配置参数
                Map<EncodeHintType, Object> hints = new HashMap<>();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                //容错级别
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                //设置空白边距的宽度
                 hints.put(EncodeHintType.MARGIN, 0); //啊啊啊

                // 图像数据转换，使用了矩阵转换
                BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
                int[] pixels = new int[widthPix * heightPix];
                // 下面这里按照二维码的算法，逐个生成二维码的图片，
                // 两个for循环是图片横列扫描的结果
                for (int y = 0; y < heightPix; y++) {
                    for (int x = 0; x < widthPix; x++) {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * widthPix + x] = 0xff000000;
                        } else {
                            pixels[y * widthPix + x] = 0xffffffff;
                        }
                    }
                }

                // 生成二维码图片的格式，使用ARGB_8888
                Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 在二维码中间添加Logo图案
     */
    public static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();
        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }
        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }
        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }
        return bitmap;
    }

    public static Bitmap createCode(String string, int size, boolean deleteWhite)
            throws Exception {
        int width = QR_IMAGE_WIDTH;//矩阵高度
        int height = QR_IMAGE_WIDTH;//矩阵宽度
        if (size>0){
            width=size;
            height=size;
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        Hashtable<EncodeHintType, Object> hst = new Hashtable<EncodeHintType, Object>();
        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");//设置字符编码
        //容错级别
        hst.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置空白边距的宽度
        hst.put(EncodeHintType.MARGIN, 0); //啊啊啊
        BitMatrix matrix = writer.encode(string, BarcodeFormat.QR_CODE, width, height, hst);//生成二维码矩阵信息
        if(deleteWhite){
            matrix = deleteWhite(matrix);
            width = matrix.getWidth();
            height = matrix.getHeight();
        }
        int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                } else {
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
}
