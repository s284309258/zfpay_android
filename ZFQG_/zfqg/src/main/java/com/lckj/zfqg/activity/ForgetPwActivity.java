package com.lckj.zfqg.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.CountDownTimerUtils;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.CreateImgCodeBean;
import com.lckj.jycm.zfqg_network.CreateImgCodeRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SendSmsCodeOnlyRequest;
import com.lckj.jycm.zfqg_network.UserForgetPassRequest;
import com.lckj.lckjlib.widgets.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForgetPwActivity extends BaseActvity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_sms_vcode)
    ClearEditText etSmsVcode;
    @BindView(R.id.btn_send_vcode)
    Button btnSendVcode;
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_verify_pw)
    ClearEditText etVerifyPw;
    @BindView(R.id.iv_eye2)
    ImageView ivEye2;
    @BindView(R.id.et_img_vcode)
    ClearEditText etImgVcode;
    @BindView(R.id.iv_vcode)
    ImageView ivVcode;
    private boolean isEye;
    private boolean isEye2;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mImg_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        etImgVcode.setText("");
        mMyService.createImgCode(new CreateImgCodeRequest("createImgCode"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<CreateImgCodeBean>(this) {
                    @Override
                    public void onSuccessCall(CreateImgCodeBean response) {
                        mImg_id = response.getData().getImg_id();
                        Bitmap bitmap = base64ToBitmap(response.getData().getImg_io());
                        ivVcode.setImageBitmap(bitmap);
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @OnClick({R.id.tv_back, R.id.iv_vcode, R.id.btn_send_vcode, R.id.btn_confirm, R.id.iv_eye, R.id.iv_eye2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_send_vcode:
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    Utils.showMsg(this, getString(R.string.手机号码));
                } else if (TextUtils.isEmpty(etImgVcode.getText().toString())) {
                    Utils.showMsg(this, getString(R.string.图形验证码));
                } else {
                    sendVcode();
                }
                break;
            case R.id.btn_confirm:
                if (checkEdit())
                    confirm();
                break;
            case R.id.iv_eye:
                pwShowHide(1);
                break;
            case R.id.iv_eye2:
                pwShowHide(2);
                break;
            case R.id.iv_vcode:
                initData();
                break;
        }
    }

    private boolean checkEdit() {
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Utils.showMsg(this, etPhone.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etImgVcode.getText().toString())) {
            Utils.showMsg(this, etImgVcode.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etSmsVcode.getText().toString())) {
            Utils.showMsg(this, etSmsVcode.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etPw.getText().toString())) {
            Utils.showMsg(this, etPw.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etVerifyPw.getText().toString())) {
            Utils.showMsg(this, etVerifyPw.getHint().toString());
            return false;
        } else if (!etPw.getText().toString().equals(etVerifyPw.getText().toString())) {
            Utils.showMsg(this, getString(R.string.密码不一致));
            return false;
        }
        return true;
    }

    private void pwShowHide(int who) {
        if (who == 1) {
            isEye = !isEye;
            if (isEye) {
                etPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            ivEye.setSelected(isEye);
            etPw.setSelection(etPw.length());
        } else {
            isEye2 = !isEye2;
            if (isEye2) {
                etVerifyPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etVerifyPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            ivEye2.setSelected(isEye2);
            etVerifyPw.setSelection(etVerifyPw.length());
        }
    }

    private void confirm() {
        String phone = etPhone.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        String vocde = etSmsVcode.getText().toString().trim();
        mMyService.userForgetPass(new UserForgetPassRequest(phone, pw, vocde))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        Utils.showMsg(ForgetPwActivity.this, getString(R.string.重置密码成功));
                        finish();
                    }

                    @Override
                    public void onFailedCall(HttpResponse response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void sendVcode() {
        String phone = etPhone.getText().toString().trim();
        String ivVcode = etImgVcode.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mMyService.sendSmsCodeOnly(new SendSmsCodeOnlyRequest(phone, "FrontForgetPass", mImg_id, ivVcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(ForgetPwActivity.this, btnSendVcode, 60000, 1000); //倒计时1分钟
                        mCountDownTimerUtils.start();
                        Utils.showMsg(ForgetPwActivity.this, getString(R.string.验证码发送成功));
                    }

                    @Override
                    public void onFailedCall(HttpResponse response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }
}
