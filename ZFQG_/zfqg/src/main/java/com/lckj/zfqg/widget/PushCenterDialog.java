package com.lckj.zfqg.widget;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.QRCodeTools;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.share.ShareDialog;
import com.lckj.lcwl.home.ActionUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PushCenterDialog extends Dialog {
    private final Context mContext;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.rl)
    RelativeLayout rl;
    List<String> mImgList = new ArrayList<>();
    @BindView(R.id.ll)
    LinearLayout ll;
    private String mType;

    public PushCenterDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_push_center);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
    }

    @OnClick({R.id.btn_share, R.id.btn_save, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                ShareDialog.createDialog((Activity) mContext, mImgList.get(0), mType).show();
                break;
            case R.id.btn_save:
                save();
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    private void save() {
        if (!ActionUtils.hasPermission(MainApplication.getInstance().getLastActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        })) {
            ActionUtils.requestPermission(MainApplication.getInstance().getLastActivity(), 948192, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            });
            return;
        }
        Utils.saveToSystemGallery(getContext(), getBitmapByView(rl));
    }

    public static Bitmap getBitmapByView(RelativeLayout scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    public void show(List<String> imgs2, String type) {
        mType = type;
        mImgList.clear();
        mImgList.addAll(imgs2);
        show();
        banner.setImages(imgs2);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.lckj.lckjlib.imageloader.ImageLoader.loadImage(String.valueOf(path), imageView);
            }
        });
        banner.start();
        try {
            Bitmap bitmap = QRCodeTools.addLogo(QRCodeTools.createCode(new DataManager(getContext()).getQrCodeUrl(), 200, false), BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.icon_qr_logo));
            ivQrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvCode.setText(new DataManager(getContext()).getUserTel());
        if (type.equals("02")) {
            ivQrCode.setVisibility(View.GONE);
            ll.setVisibility(View.GONE);
        } else {
            ivQrCode.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
        }
    }
}
