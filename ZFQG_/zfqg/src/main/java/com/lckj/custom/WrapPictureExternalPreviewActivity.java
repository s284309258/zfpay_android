package com.lckj.custom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.lckj.jycm.R;
import com.lckj.lckjlib.widgets.ActionSheet;
import com.luck.picture.lib2.PictureExternalPreviewActivity;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.tools.PictureFileUtils;
import com.luck.picture.lib2.tools.ToastManage;

import java.io.IOException;


public class WrapPictureExternalPreviewActivity extends PictureExternalPreviewActivity implements ActionSheet.MenuItemClickListener {
    private String result;
    private String finalPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View.OnLongClickListener getOnLongClickListner() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Object tag = v.getTag(R.id.image_view_tag_path);
                String path = "";
                if (tag!=null){
                    path = (String) tag;
                }
                finalPath = path;
                showMenu("");
                return true;
            }
        };
    }

    private Bitmap getBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    private void showMenu(String result){
        this.result=result;
        setTheme(R.style.ActionSheetStyleIOS7);
        ActionSheet menuView = new ActionSheet(this);
        menuView.setCancelButtonTitle(getString(R.string.cancel));
        menuView.setCurrentItems("");
        if(TextUtils.isEmpty(result)){
            menuView.addItems("保存到手机");
        }else{
            menuView.addItems("保存到手机","识别图中二维码");
        }
        menuView.setItemClickListener(this);
        menuView.setCancelableOnTouchMenuOutside(true);
        menuView.showMenu();
    }

    @Override
    public void onActionSheetItemClick(View v, int itemPosition, String itemText) {
        if("保存到手机".equals(itemText)){
            showPleaseDialog();
            boolean isHttp = PictureMimeType.isHttp(finalPath);
            if (isHttp) {
                loadDataThread = new LoadDataThread(finalPath);
                loadDataThread.start();
            } else {
                // 有可能本地图片
                try {
                    String dirPath = PictureFileUtils.createDir(WrapPictureExternalPreviewActivity.this,
                            System.currentTimeMillis() + ".png", directory_path);
                    PictureFileUtils.copyFile(finalPath, dirPath);
                    ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_save_success) + "\n" + dirPath);
                    dismissDialog();
                } catch (IOException e) {
                    ToastManage.s(mContext, getString(com.luck.picture.lib2.R.string.picture_save_error) + "\n" + e.getMessage());
                    dismissDialog();
                    e.printStackTrace();
                }
            }
        }

    }
}
