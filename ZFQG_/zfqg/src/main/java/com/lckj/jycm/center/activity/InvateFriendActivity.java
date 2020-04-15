package com.lckj.jycm.center.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.zxing.qrcode.util.QRCodeUtil;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvateFriendActivity extends BaseActvity {
    @BindView(R.id.rl_qr_holder)
    RelativeLayout rlQrHolder;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    private int width;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invate_friend);
        ButterKnife.bind(this);
        WindowManager windowManager = getWindowManager();
        width = windowManager.getDefaultDisplay().getWidth();
        initView();
        getDatas();
    }

    private void getDatas() {
        try {

            Bitmap code = QRCodeUtil.createImage("appDownloadUrl", 200, 900, null);
//            Bitmap code = QRCodeTools.createCode("appDownloadUrl",900, true);
            if (code!=null) {
                ivQrCode.setImageBitmap(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) rlQrHolder.getLayoutParams();
        layoutParams.topMargin = width / 2 - dip2px(25);
        rlQrHolder.setLayoutParams(layoutParams);

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
