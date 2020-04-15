package com.lckj.zfqg.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.lckj.jycm.zfqg_network.UserLoginBean;
import com.lckj.jycm.zfqg_network.UserLoginRequest;
import com.lckj.jycm.zfqg_network.UserRegisterBean;
import com.lckj.jycm.zfqg_network.UserRegisterRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.widget.WebViewDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActvity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_id)
    ClearEditText etId;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.et_verify_pw)
    ClearEditText etVerifyPw;
    @BindView(R.id.et_pay_pw)
    ClearEditText etPayPw;
    @BindView(R.id.et_img_vcode)
    ClearEditText etImgVcode;
    @BindView(R.id.iv_vcode)
    ImageView ivVcode;
    @BindView(R.id.et_sms_vcode)
    ClearEditText etSmsVcode;
    @BindView(R.id.btn_send_vcode)
    Button btnSendVcode;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.iv_eye2)
    ImageView ivEye2;
    @BindView(R.id.iv_eye3)
    ImageView ivEye3;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    private boolean isEye;
    private boolean isEye2;
    private boolean isEye3;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mImg_id;
    private WebViewDialog mWebViewDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        tvLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        SpannableString start = new SpannableString(getString(R.string.我已阅读并同意));
        SpannableString user = new SpannableString(getString(R.string.用户服务协议));
        SpannableString and = new SpannableString(getString(R.string.和));
        SpannableString privacy = new SpannableString(getString(R.string.隐私政策));
        user.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showAgreementDialog("file:///android_asset/agreement.html", getString(R.string.用户服务协议));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.green));
            }
        }, 0, user.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        privacy.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showAgreementDialog("file:///android_asset/privacy.html",getString(R.string.隐私政策));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.green));
            }
        }, 0, privacy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAgreement.append(start);
        tvAgreement.append(user);
        tvAgreement.append(and);
        tvAgreement.append(privacy);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void showAgreementDialog(String url,String title) {
        if (mWebViewDialog == null)
            mWebViewDialog = new WebViewDialog(this, R.style.dialog);
        mWebViewDialog.show(url, title);
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

    @OnClick({R.id.tv_back, R.id.iv_vcode, R.id.btn_send_vcode, R.id.btn_register, R.id.tv_login, R.id.iv_eye, R.id.iv_eye2, R.id.iv_eye3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_vcode:
                initData();
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
            case R.id.btn_register:
                if (checkEdit()) {
                    register();
                }
                break;
            case R.id.tv_login:
                finish();
                break;
            case R.id.iv_eye:
                pwShowHide(1);
                break;
            case R.id.iv_eye2:
                pwShowHide(2);
                break;
            case R.id.iv_eye3:
                pwShowHide(3);
                break;
        }
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
        } else if (who == 2) {
            isEye2 = !isEye2;
            if (isEye2) {
                etVerifyPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etVerifyPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            ivEye2.setSelected(isEye2);
            etVerifyPw.setSelection(etVerifyPw.length());
        } else {
            isEye3 = !isEye3;
            if (isEye3) {
                etPayPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                etPayPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            ivEye3.setSelected(isEye3);
            etPayPw.setSelection(etPayPw.length());

        }
    }

    private boolean checkEdit() {
        if (TextUtils.isEmpty(etId.getText().toString())) {
            Utils.showMsg(this, getString(R.string.推荐人手机号));
            return false;
        } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Utils.showMsg(this, getString(R.string.手机号码));
            return false;
        } else if (TextUtils.isEmpty(etPw.getText().toString())) {
            Utils.showMsg(this, getString(R.string.登录密码616位数字字母组合));
            return false;
        } else if (TextUtils.isEmpty(etVerifyPw.getText().toString())) {
            Utils.showMsg(this, getString(R.string.确认密码));
            return false;
        } else if (TextUtils.isEmpty(etPayPw.getText().toString())) {
            Utils.showMsg(this, getString(R.string.支付密码6位数字));
            return false;
        } else if (TextUtils.isEmpty(etImgVcode.getText().toString())) {
            Utils.showMsg(this, getString(R.string.图形验证码));
            return false;
        } else if (TextUtils.isEmpty(etSmsVcode.getText().toString())) {
            Utils.showMsg(this, getString(R.string.短信验证码));
            return false;
        } else if (!etPw.getText().toString().equals(etVerifyPw.getText().toString())) {
            Utils.showMsg(this, getString(R.string.登录密码不一致));
            return false;
        } else if (!cbAgreement.isChecked()) {
            Utils.showMsg(this, getString(R.string.请同意用户服务协议和隐私政策));
            return false;
        }
        return true;
    }

    private void sendVcode() {
        String phone = etPhone.getText().toString().trim();
        String ivVcode = etImgVcode.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mMyService.sendSmsCodeOnly(new SendSmsCodeOnlyRequest(phone, "FrontRegister", mImg_id, ivVcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(RegisterActivity.this, btnSendVcode, 60000, 1000); //倒计时1分钟
                        mCountDownTimerUtils.start();
                        Utils.showMsg(RegisterActivity.this, getString(R.string.验证码发送成功));
                    }

                    @Override
                    public void onFailedCall(HttpResponse response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }

    private void register() {
        String phone = etPhone.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        String vcode = etSmsVcode.getText().toString().trim();
        String invateCode = etId.getText().toString().trim();
        String payPw = etPayPw.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mMyService.userRegister(new UserRegisterRequest(phone, pw, payPw, invateCode, vcode, "android", Utils.getVersionName(this)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<UserRegisterBean>(this) {
                    @Override
                    public void onSuccessCall(UserRegisterBean response) {
                        ProgressDlgHelper.closeDialog();
                        dataManager.setToken(response.getData().getToken());
                        autoLogin();
                    }

                    @Override
                    public void onFailedCall(UserRegisterBean response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
    /*
                           _ooOoo_
                          o8888888o
                          88" . "88
                          (| -_- |)
                          O\  =  /O
                       ____/`---'\____
                     .'  \\|     |//  `.
                    /  \\|||  :  |||//  \
                   /  _||||| -:- |||||-  \
                   |   | \\\  -  /// |   |
                   | \_|  ''\---/''  |   |
                   \  .-\__  `-`  ___/-. /
                 ___`. .'  /--.--\  `. . __
              ."" '<  `.___\_<|>_/___.'  >'"".
             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
             \  \ `-.   \_ __\ /__ _/   .-` /  /
        ======`-.____`-.___\_____/___.-`____.-'======
                           `=---='
        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 佛祖保佑       永无BUG
        */
    private void autoLogin() {
        mMyService.userLogin(new UserLoginRequest("token", "", "", dataManager.getToken(), "android", Utils.getVersionName(this), Utils.getDevice(this)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<UserLoginBean>(this) {
                    @Override
                    public void onSuccessCall(UserLoginBean response) {
                        ProgressDlgHelper.closeDialog();
                        setData(response);
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        Utils.showMsg(RegisterActivity.this, getString(R.string.登录成功));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    /*
     *
     *          ┌─┐       ┌─┐
     *       ┌──┘ ┴───────┘ ┴──┐
     *       │                 │
     *       │       ───       │
     *       │  ─┬┘       └┬─  │
     *       │                 │
     *       │       ─┴─       │
     *       │                 │
     *       └───┐         ┌───┘
     *           │         │
     *           │         │
     *           │         │
     *           │         └──────────────┐
     *           │                        │
     *           │                        ├─┐
     *           │                        ┌─┘
     *           │                        │
     *           └─┐  ┐  ┌───────┬──┐  ┌──┘
     *             │ ─┤ ─┤       │ ─┤ ─┤
     *             └──┴──┘       └──┴──┘
     *                 神兽保佑
     *                 代码无BUG!
     */
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
