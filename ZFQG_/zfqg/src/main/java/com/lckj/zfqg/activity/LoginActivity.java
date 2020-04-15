package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.PwLoginActivity;
import com.lckj.jycm.network.LoginBean;
import com.lckj.jycm.network.LoginRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UserLoginBean;
import com.lckj.jycm.zfqg_network.UserLoginRequest;
import com.lckj.lckjlib.widgets.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActvity {
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.cb_save_pw)
    CheckBox cbSavePw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    private boolean isEye;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        etPhone.setText(dataManager.getAccount());
        etPw.setText(dataManager.getPw());
        cbSavePw.setChecked(dataManager.getPwChecked());
        cbSavePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.setPwChecked(cbSavePw.isChecked());
                if (!cbSavePw.isChecked()) {
                    dataManager.setPw("");
                    dataManager.setAccount("");
                    etPhone.setText("");
                    etPw.setText("");
                }
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.tv_forget_pw, R.id.iv_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkEdit()) {
                    login();
                }
//                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forget_pw:
                startActivity(new Intent(this, ForgetPwActivity.class));
                break;
            case R.id.iv_eye:
                pwShowHide();
                break;
        }
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

    private boolean checkEdit() {
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Utils.showMsg(this, getString(R.string.手机号码));
            return false;
        } else if (TextUtils.isEmpty(etPw.getText().toString())) {
            Utils.showMsg(this, getString(R.string.登录密码));
            return false;
        }
        return true;
    }

    private void login() {
        String phone = etPhone.getText().toString();
        String pw = etPw.getText().toString();
        ProgressDlgHelper.openDialog(this);
        mMyService.userLogin(new UserLoginRequest("account", phone, pw, null, "android", Utils.getVersionName(this), Utils.getDevice(this)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<UserLoginBean>(this) {
                    @Override
                    public void onSuccessCall(UserLoginBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (cbSavePw.isChecked()) {
                            dataManager.setAccount(phone);
                            dataManager.setPw(pw);
                        }
                        setData(response);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        Utils.showMsg(LoginActivity.this, getString(R.string.登录成功));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void setData(UserLoginBean response) {
        dataManager.setToken(response.getData().getToken());
        dataManager.setHeadPhoto(response.getData().getHead_photo());
        dataManager.setUserId(response.getData().getUser_id());
        dataManager.setUserTel(response.getData().getUser_tel());
        dataManager.setUserName(response.getData().getReal_name());
        dataManager.setPayPw(TextUtils.isEmpty(response.getData().getPay_password()));
        dataManager.setQiniuDomain(response.getData().getQiniu_domain());
        dataManager.setQrCodeUrl(response.getData().getQr_code_url());
        dataManager.setAuthStatus(response.getData().getAuth_status());
        dataManager.setAlgebra(response.getData().getAlgebra());
        dataManager.setAppId(response.getData().getApp_id());
    }
}
