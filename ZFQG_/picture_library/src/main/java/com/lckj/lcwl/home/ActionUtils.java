package com.lckj.lcwl.home;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.R;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActionUtils {
    /**点击添加tup*/
    public static void onClickAddPhoto1(Activity activity, ArrayList<LocalMedia> dataList) {
        if (dataList.size()==0)dataList.add(new LocalMedia());
        int selectLimit = 1;
        selectLimit = 9 - dataList.size() + 1;
        if (checkPhotoPermission(activity)) return;
        int gallery = PictureMimeType.ofAll();
        if (dataList.size()>0&&dataList.get(0).getPictureType().startsWith(PictureConfig.IMAGE)){
            gallery = PictureMimeType.ofImage();
        }
        PictureSelector pictureSelector = PictureSelector.create(activity);
        open(selectLimit, gallery, pictureSelector);
    }

    public static void onClickAddPhoto1(Fragment fragment, ArrayList<LocalMedia> dataList) {
        if (dataList.size()==0)dataList.add(new LocalMedia());
        int selectLimit = 1;
        selectLimit = 9 - dataList.size() + 1;
        if (checkPhotoPermission(fragment.getActivity())) return;
        int gallery = PictureMimeType.ofAll();
        if (dataList.size()>0&&dataList.get(0).getPictureType().startsWith(PictureConfig.IMAGE)){
            gallery = PictureMimeType.ofImage();
        }
        PictureSelector pictureSelector = PictureSelector.create(fragment);
        open(selectLimit, gallery, pictureSelector);
    }

    public static boolean checkPhotoPermission(Activity activity) {
        if (!hasPermission(activity, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        })) {
            requestPermission(activity, 948192,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            });
            return true;
        }
        return false;
    }

    public static void open(int selectLimit, int gallery, PictureSelector pictureSelector) {
        pictureSelector.openGallery(gallery)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .isGif(true)
                .maxSelectNum(selectLimit)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(1)
                .recordVideoSecond(15)//视频秒数录制 默认60s int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(Context context, String[]... permissions) {
        if (isVersionM()) {
            for (String[] permissionGroup : permissions) {
                for (String permission : permissionGroup) {
                    if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public static void requestPermission(Activity activity, int requestCode, String permission) {
        requestPermission(activity, requestCode, new String[]{permission});
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Activity activity, int requestCode, String[]... permissions) {
        if (isVersionM()) {
            int count = 0;
            List<String> allPermission = new ArrayList<>();
            for (String[] permissionGroup : permissions) {
                count += permissionGroup.length;
            }
            for (String[] permissionGroup : permissions) {
                for (String permission : permissionGroup) {
                    if (!hasPermission(activity, permission)) {
                        allPermission.add(permission);
                    }
                }
            }

            String[] permisionArray = new String[allPermission.size()];
            for (int i = 0; i < allPermission.size(); i++) {
                permisionArray[i] = allPermission.get(i);
            }
            activity.requestPermissions(permisionArray, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasPermission(Context context, String permission) {
        if (isVersionM()
                && context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static boolean isVersionM() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        return false;
    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(Context context, String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(context,dir);
    }

    public static void deleteDirWihtFile(Context context, File dir) {
        if (!hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return;
        }
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(context,file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
    /**查看图片*/
    public static  void onClickPhoto(Activity activity, ArrayList<LocalMedia> dataList, int position) {
        if (TextUtils.isEmpty(dataList.get(dataList.size()-1).getPath())){
            PictureSelector.create(activity).themeStyle(R.style.picture_QQ_style).openExternalPreview(position, getArrayList(dataList.subList(0, dataList.size()-1)));
        }else{
            PictureSelector.create(activity).themeStyle(R.style.picture_QQ_style).openExternalPreview(position, dataList);
        }
    }

    public static  List<LocalMedia> getArrayList(List<LocalMedia> localMedia) {
        ArrayList<LocalMedia> list = new ArrayList<>();
        list.addAll(localMedia);
        return list;
    }
}
