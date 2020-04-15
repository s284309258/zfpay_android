package com.lckj.jycm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.widgets.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetLoginPwActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.btn_set)
    Button btnSet;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.ll_set_login)
    LinearLayout llSetLogin;
    private boolean isEye = false;
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_login_pw);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.set_login_pw));
        tvPhone.setText(dataManager.getUserTel());
        leftAction.setText("");

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    private void pwShowHide() {
        isEye = !isEye;
        if (isEye) {
            etPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            etPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        ivEye.setSelected(isEye);
        etPw.setSelection(etPw.length());
    }

    @OnClick({R.id.left_action, R.id.btn_set, R.id.iv_eye, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.btn_set:
                llNoData.setVisibility(View.GONE);
                llSetLogin.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_eye:
                pwShowHide();
                break;
            case R.id.btn_confirm:
                if (CheckEdit()) {

                }
                break;
        }
    }

    private boolean CheckEdit() {
        if (TextUtils.isEmpty(etPw.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_pw));
            return false;
        }
        return true;
    }
}
