package com.lckj.zfqg.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
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
import com.lckj.jycm.zfqg_network.ModifyLoginPassRequest;
import com.lckj.jycm.zfqg_network.ModifyPayPassRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SendSmsCodeTokenRequest;
import com.lckj.lckjlib.widgets.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpdatePayPwActivity extends BaseActvity {

    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_iv_vcode)
    ClearEditText etIvVcode;
    @BindView(R.id.iv_vcode)
    ImageView ivVcode;
    @BindView(R.id.et_vcode)
    ClearEditText etVcode;
    @BindView(R.id.btn_send_vcode)
    TextView btnSendVcode;
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_pw_reg)
    ClearEditText etPwReg;
    @BindView(R.id.iv_eye2)
    ImageView ivEye2;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private String mImg_id;
    private boolean isEye;
    private boolean isEye2;
    @Inject
    MyService mMyService;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pay_pw);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        tvPhone.setText(dataManager.getUserTel());
        customTitle.setText(getString(R.string.修改交易密码));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        etIvVcode.setText("");
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

    @OnClick({R.id.left_action, R.id.iv_vcode, R.id.btn_send_vcode, R.id.iv_eye, R.id.iv_eye2, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.iv_vcode:
                initData();
                break;
            case R.id.btn_send_vcode:
                if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
                    Utils.showMsg(this, getString(R.string.请输入图形验证码));
                } else {
                    sendVcode();
                }
                break;
            case R.id.iv_eye:
                pwShowHide();
                break;
            case R.id.iv_eye2:
                pwShowHide2();
                break;
            case R.id.btn_confirm:
                if (checkEdit()) {
                    update();
                }
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

    private void pwShowHide2() {
        isEye2 = !isEye2;
        if (isEye2) {
            etPwReg.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            etPwReg.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        ivEye2.setSelected(isEye2);
        etPwReg.setSelection(etPwReg.length());
    }

    private void sendVcode() {
        String ivVcode = etIvVcode.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mMyService.sendSmsCodeToken(new SendSmsCodeTokenRequest(dataManager.getToken(), "FrontModifyPayPass", mImg_id, ivVcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(UpdatePayPwActivity.this, btnSendVcode, 60000, 1000); //倒计时1分钟
                        mCountDownTimerUtils.start();
                        Utils.showMsg(UpdatePayPwActivity.this, getString(R.string.验证码发送成功));
                    }

                    @Override
                    public void onFailedCall(HttpResponse response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }

    private void update() {
        ProgressDlgHelper.openDialog(this);
        String pw = etPw.getText().toString().trim();
        String vcode = etVcode.getText().toString().trim();
        mMyService.modifyPayPass(new ModifyPayPassRequest(dataManager.getToken(), pw, vcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        finish();
                        Utils.showMsg(UpdatePayPwActivity.this, getString(R.string.修改成功));
                    }

                    @Override
                    public void onFailedCall(HttpResponse response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private boolean checkEdit() {
        /*if (TextUtils.isEmpty(tvPhone.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.手机号码));
            return false;
        } else*/ if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.请输入图形验证码));
            return false;
        } else if (TextUtils.isEmpty(etVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.请输入短信验证码));
            return false;
        } else if (TextUtils.isEmpty(etPw.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.请输入交易密码));
            return false;
        } else if (TextUtils.isEmpty(etPwReg.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.请输入交易密码));
            return false;
        } else if (!etPw.getText().toString().equals(etPwReg.getText().toString())) {
            Utils.showMsg(this, getString(R.string.密码不一致));
            return false;
        }
        return true;
    }
}
