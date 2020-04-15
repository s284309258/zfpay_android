package com.lckj.jycm.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.MainActivity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.HomeActivity;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.LoginBean;
import com.lckj.jycm.network.LoginRequest;
import com.lckj.jycm.network.SendPictureCodeBean;
import com.lckj.jycm.network.SendPictureCodeRequest;
import com.lckj.jycm.network.WithoutLoginPage;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.networkbench.agent.impl.NBSAppAgent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PwLoginActivity extends BaseActvity implements WithoutLoginPage {

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
    @BindView(R.id.et_pw)
    ClearEditText etPw;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.et_vcode)
    ClearEditText etVcode;
    @BindView(R.id.iv_vcode)
    ImageView ivVcode;
    @BindView(R.id.tv_forget_pw)
    TextView tvForgetPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_mian_pw)
    TextView tvMianPw;
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
        setContentView(R.layout.activity_pw_login);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        dataManager.setToken("");
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

    private boolean isEye = false;

    @OnClick({R.id.iv_back, R.id.iv_eye, R.id.iv_vcode, R.id.tv_forget_pw, R.id.btn_login, R.id.tv_mian_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_eye:
                pwShowHide();
                break;
            case R.id.iv_vcode:
                initData();
                break;
            case R.id.tv_forget_pw:
                startActivity(new Intent(this, ForgetPwActivity.class));
                break;
            case R.id.btn_login:
                if (checkEdit()) {
                    login();
                }
                break;
            case R.id.tv_mian_pw:
                startActivity(new Intent(this, ExemptPwLoginActivity.class));
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void login() {
        String phone = etPhone.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        String vcode = etVcode.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mFrontUserService.login(new LoginRequest(phone, pw, vcode, mImg_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<LoginBean>(this) {
                    @Override
                    public void onSuccessCall(LoginBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            setData(response);
                            Intent intent = new Intent(PwLoginActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            initData();
                        }
                        Utils.showMsg(PwLoginActivity.this, response.getMsg());
                    }

                    @Override
                    public void onFailedCall(LoginBean response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void setData(LoginBean response) {
        dataManager.setLoin(true);
        dataManager.setToken(response.getToken());
        dataManager.setUserId(response.getData().getUserId() + "");
        dataManager.setUserName(response.getData().getUserName());
        dataManager.setAge(response.getData().getAge());
        dataManager.setSex(response.getData().getSex());
        dataManager.setHeadPhoto(response.getData().getHeadPhoto());
        dataManager.setUserTel(response.getData().getUserTel());
        if(MainApplication.getInstance().isUseTingyun()) {
            NBSAppAgent.setUserCrashMessage( "to", response.getToken());
            NBSAppAgent.setUserCrashMessage( "n", response.getData().getUserName());
            NBSAppAgent.setUserCrashMessage( "i", String.valueOf(response.getData().getUserId()));
            NBSAppAgent.setUserCrashMessage( "t", response.getData().getUserTel());
        }
        dataManager.setInvitationCode(response.getData().getInvitationCode());
        dataManager.setGold(response.getData().getFrontUserAccountDO().getGold());
        dataManager.setAmount(response.getData().getFrontUserAccountDO().getAmount());
        dataManager.setTeamMember(response.getData().getFrontUserAccountDO().getTeamMember());
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
            Utils.showMsg(this, getString(R.string.toast_et_iv_vcode));
            return false;
        } else if (!Utils.isPhone(etPhone.getText().toString().trim(), this)) {
            return false;
        }
        return true;
    }
}
