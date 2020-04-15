package com.lckj.jycm.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.HomeActivity;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.LoginBean;
import com.lckj.jycm.network.LoginRequest;
import com.lckj.jycm.network.RegisterBean;
import com.lckj.jycm.network.RegisterRequest;
import com.lckj.jycm.network.SelllValidateCodeRequest;
import com.lckj.jycm.network.SendPictureCodeBean;
import com.lckj.jycm.network.WithoutLoginPage;
import com.lckj.jycm.utils.CountDownTimerUtils;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.networkbench.agent.impl.NBSAppAgent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExemptPwLoginActivity extends BaseActvity implements WithoutLoginPage {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.v)
    ImageView v;
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
    @BindView(R.id.et_iv_vcode)
    ClearEditText etIvVcode;
    @BindView(R.id.iv_vcode)
    ImageView ivVcode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_pw_login)
    TextView tvPwLogin;
    @BindView(R.id.ll)
    LinearLayout ll;
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;
    private String mImg_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exempt_pw_login);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
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
        mFrontUserService.sendPictureCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<SendPictureCodeBean>(this) {
                    @Override
                    public void onSuccessCall(SendPictureCodeBean response) {
                        if (response.isSuccess()) {
                            mImg_id = response.getData().getImg_id();
                            Bitmap bitmap = base64ToBitmap(response.getData().getImg_io());
                            ivVcode.setImageBitmap(bitmap);
                        } else {
                            Utils.showMsg(ExemptPwLoginActivity.this, response.getMsg());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

        private boolean checkEdit() {
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_phone));
            return false;
        } else if (TextUtils.isEmpty(etVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_vcode));
            return false;
        } else if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_iv_vcode));
            return false;
        } else if (!Utils.isPhone(etPhone.getText().toString().trim(), this)) {
            return false;
        }
        return true;
    }

    @OnClick({R.id.iv_back, R.id.btn_send_vcode, R.id.iv_vcode, R.id.btn_login, R.id.tv_pw_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_send_vcode:
                sendVcode();
                break;
            case R.id.iv_vcode:
                initData();
                break;
            case R.id.btn_login:
                if (checkEdit()) {
                    login();
                }
                break;
            case R.id.tv_pw_login:
                finish();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void login() {
        String phone = etPhone.getText().toString().trim();
        String ivVcode = etIvVcode.getText().toString().trim();
        String vcode = etVcode.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mFrontUserService.register(new RegisterRequest(phone, "register", "", vcode, ivVcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<RegisterBean>(this) {
                    @Override
                    public void onSuccessCall(RegisterBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            ProgressDlgHelper.closeDialog();
                            setData(response);
                            Utils.showMsg(ExemptPwLoginActivity.this, getString(R.string.toast_login_success));
                            Intent intent = new Intent(ExemptPwLoginActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            initData();
                            Utils.showMsg(ExemptPwLoginActivity.this, response.getMsg());
                        }
                    }

                    @Override
                    public void onFailedCall(RegisterBean response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void setData(RegisterBean response) {
        dataManager.setLoin(true);
        dataManager.setToken(response.getToken());
        dataManager.setUserId(response.getData().getUserId() + "");
        dataManager.setUserName(response.getData().getUserName());
        dataManager.setAge(response.getData().getAge());
        dataManager.setSex(response.getData().getSex());
        dataManager.setHeadPhoto(response.getData().getHeadPhoto());
        dataManager.setUserTel(response.getData().getUserTel());
        dataManager.setInvitationCode(response.getData().getInvitationCode());
        dataManager.setGold(response.getData().getFrontUserAccountDO().getGold());
        dataManager.setAmount(response.getData().getFrontUserAccountDO().getAmount());
        dataManager.setTeamMember(response.getData().getFrontUserAccountDO().getTeamMember());
        if(MainApplication.getInstance().isUseTingyun()) {
            NBSAppAgent.setUserCrashMessage("to", response.getToken());
            NBSAppAgent.setUserCrashMessage("n", response.getData().getUserName());
            NBSAppAgent.setUserCrashMessage("i", String.valueOf(response.getData().getUserId()));
            NBSAppAgent.setUserCrashMessage("t", response.getData().getUserTel());
        }
    }

    private void sendVcode() {
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_phone));
        } else if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_et_iv_vcode));
        } else if (!Utils.isPhone(etPhone.getText().toString().trim(), this)) {
        } else {
            String phone = etPhone.getText().toString().trim();
            String ivVcode = etIvVcode.getText().toString().trim();
            mFrontUserService.selllvalidateCode(new SelllValidateCodeRequest(phone, "register", mImg_id, ivVcode))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<HttpResponse>(this) {
                        @Override
                        public void onSuccessCall(HttpResponse response) {
                            if (response.isSuccess()) {
                                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(ExemptPwLoginActivity.this, btnSendVcode, 60000, 1000); //倒计时1分钟
                                mCountDownTimerUtils.start();
                                Utils.showMsg(ExemptPwLoginActivity.this, getString(R.string.toast_send_vcode_success));
                            } else {
                                Utils.showMsg(ExemptPwLoginActivity.this, response.getMsg());
                            }
                        }

                    }, new ThrowableConsumer<Throwable>(this));

        }
    }
}
