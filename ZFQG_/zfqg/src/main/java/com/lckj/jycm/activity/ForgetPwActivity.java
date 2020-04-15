package com.lckj.jycm.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.SelllValidateCodeRequest;
import com.lckj.jycm.network.UpdatePasswordRequest;
import com.lckj.jycm.utils.CountDownTimerUtils;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.widgets.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class ForgetPwActivity extends BaseActvity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
   /* @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.et_vcode)
    ClearEditText etVcode;
    @BindView(R.id.btn_send_vcode)
    Button btnSendVcode;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.btn_modification)
    Button btnModification;
    private boolean isEye = false;
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);
        MainApplication.getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        leftAction.setText("");
        customTitle.setText(R.string.忘记密码);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.btn_send_vcode, R.id.iv_eye, R.id.btn_modification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.btn_send_vcode:
                if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                    Utils.showMsg(this, getString(R.string.toast_et_phone));
                } else {
                    sendVcode();
                }
                break;
            case R.id.iv_eye:
                pwShowHide();
                break;
            case R.id.btn_modification:
                if (checkEdit()) {
                    updatePassword();
                }
                break;
        }
    }

    private void sendVcode() {
        String phone = etPhone.getText().toString().trim();
        mFrontUserService.selllvalidateCode(new SelllValidateCodeRequest(phone, "SetLoginPass", "", ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        if (response.isSuccess()) {
                            CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(ForgetPwActivity.this, btnSendVcode, 60000, 1000); //倒计时1分钟
                            mCountDownTimerUtils.start();
                            Utils.showMsg(ForgetPwActivity.this, getString(R.string.toast_send_vcode_success));
                        } else {
                            Utils.showMsg(ForgetPwActivity.this, response.getMsg());
                        }
                    }

                }, new ThrowableConsumer<Throwable>(this));
    }

    private void updatePassword() {
        String phone = etPhone.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        String vocde = etVcode.getText().toString().trim();
        mFrontUserService.updatePassword(new UpdatePasswordRequest("", pw, vocde, phone, "SetLoginPass"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        if (response.isSuccess()) {
                            Utils.showMsg(ForgetPwActivity.this, getString(R.string.toast_update_pw_success));
                            finish();
                        } else {
                            Utils.showMsg(ForgetPwActivity.this, response.getMsg());
                        }
                    }

                }, new ThrowableConsumer<Throwable>(this));
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
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_phone));
            return false;
        } else if (TextUtils.isEmpty(etPw.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_pw));
            return false;
        } else if (TextUtils.isEmpty(etVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_vcode));
            return false;
        } else if (!Utils.isPhone(etPhone.getText().toString().trim(), this)) {
            return false;
        }
        return true;
    }*/
}
