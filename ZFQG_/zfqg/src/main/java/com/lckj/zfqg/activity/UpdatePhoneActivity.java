package com.lckj.zfqg.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.lckj.jycm.utils.CountDownTimerUtils;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.CreateImgCodeBean;
import com.lckj.jycm.zfqg_network.CreateImgCodeRequest;
import com.lckj.jycm.zfqg_network.ModifyTelFirstBean;
import com.lckj.jycm.zfqg_network.ModifyTelFirstRequest;
import com.lckj.jycm.zfqg_network.ModifyTelSecondRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SendSmsCodeTokenRequest;
import com.lckj.lckjlib.widgets.ClearEditText;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpdatePhoneActivity extends BaseActvity {

    @Inject
    MyService mMyService;
    @Inject
    DataManager dataManager;
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
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.rl_tv_phone)
    RelativeLayout rlTvPhone;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.rl_et_phone)
    RelativeLayout rlEtPhone;
    private String mImg_id;
    private boolean isUpdate;
    private String mValid_flag;
    private CountDownTimerUtils mCountDownTimerUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        tvPhone.setText(dataManager.getUserTel());
        customTitle.setText(getString(R.string.修改手机号码));
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

    @OnClick({R.id.left_action, R.id.iv_vcode, R.id.btn_send_vcode, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.iv_vcode:
                initData();
                break;
            case R.id.btn_send_vcode:
                if (isUpdate) {
                    if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                        Utils.showMsg(this, getString(R.string.请输入新手机号码));
                    } else if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
                        Utils.showMsg(this, getString(R.string.请输入图形验证码));
                    } else {
                        sendVcode();
                    }
                } else {
                    if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
                        Utils.showMsg(this, getString(R.string.请输入图形验证码));
                    } else {
                        sendVcode();
                    }
                }

                break;
            case R.id.btn_next:
                if (checkEdit()) {
                    if (isUpdate) {
                        updatePhone();
                    } else {
                        next();
                    }
                }
                break;
        }
    }

    private void next() {
        ProgressDlgHelper.openDialog(this);
        String vcode = etVcode.getText().toString().trim();
        mMyService.modifyTelFirst(new ModifyTelFirstRequest(dataManager.getToken(), vcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<ModifyTelFirstBean>(this) {
                    @Override
                    public void onSuccessCall(ModifyTelFirstBean response) {
                        ProgressDlgHelper.closeDialog();
                        isUpdate = true;
                        rlTvPhone.setVisibility(View.GONE);
                        rlEtPhone.setVisibility(View.VISIBLE);
                        btnNext.setText(getString(R.string.确定绑定));
                        initData();
                        etVcode.setText("");
                        btnSendVcode.setClickable(true);//重新获得点击
                        mValid_flag = response.getData().getValid_flag();
                        mCountDownTimerUtils.onFinish();
                        mCountDownTimerUtils.cancel();
                    }

                    @Override
                    public void onFailedCall(ModifyTelFirstBean response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private boolean checkEdit() {
        if (isUpdate) {
            if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                Utils.showMsg(this, getString(R.string.请输入新手机号码));
                return false;
            }
        }
        if (TextUtils.isEmpty(etIvVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.请输入图形验证码));
            return false;
        } else if (TextUtils.isEmpty(etVcode.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.请输入短信验证码));
            return false;
        }
        return true;
    }

    private void updatePhone() {
        ProgressDlgHelper.openDialog(this);
        String vcode = etVcode.getText().toString().trim();
        final String phone = etPhone.getText().toString();
        mMyService.modifyTelSecond(new ModifyTelSecondRequest(dataManager.getToken(), phone, mValid_flag, vcode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(UpdatePhoneActivity.this, getString(R.string.修改成功));
                        dataManager.setUserTel(phone);
                        finish();
                        EventBus.getDefault().post(getString(R.string.修改手机号码));
                    }

                    @Override
                    public void onFailedCall(HttpResponse response) {
                        super.onFailedCall(response);
                        initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void sendVcode() {
        if (isUpdate) {
            String ivVcode = etIvVcode.getText().toString().trim();
            String phone = etPhone.getText().toString();
            ProgressDlgHelper.openDialog(this);
            mMyService.sendSmsCodeToken(new SendSmsCodeTokenRequest(dataManager.getToken(), "FrontModifyTelSecond", mImg_id, ivVcode, phone))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<HttpResponse>(this) {
                        @Override
                        public void onSuccessCall(HttpResponse response) {
                            ProgressDlgHelper.closeDialog();
                            mCountDownTimerUtils = new CountDownTimerUtils(UpdatePhoneActivity.this, btnSendVcode, 60000, 1000); //倒计时1分钟
                            mCountDownTimerUtils.start();
                            Utils.showMsg(UpdatePhoneActivity.this, getString(R.string.验证码发送成功));
                        }

                        @Override
                        public void onFailedCall(HttpResponse response) {
                            super.onFailedCall(response);
                            initData();
                        }
                    }, new ThrowableConsumer<Throwable>(this));
        } else {
            String ivVcode = etIvVcode.getText().toString().trim();
            ProgressDlgHelper.openDialog(this);
            mMyService.sendSmsCodeToken(new SendSmsCodeTokenRequest(dataManager.getToken(), "FrontModifyTelFirst", mImg_id, ivVcode))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<HttpResponse>(this) {
                        @Override
                        public void onSuccessCall(HttpResponse response) {
                            ProgressDlgHelper.closeDialog();
                            //倒计时1分钟
                            mCountDownTimerUtils = new CountDownTimerUtils(UpdatePhoneActivity.this, btnSendVcode, 60000, 1000);
                            mCountDownTimerUtils.start();
                            Utils.showMsg(UpdatePhoneActivity.this, getString(R.string.验证码发送成功));
                        }

                        @Override
                        public void onFailedCall(HttpResponse response) {
                            super.onFailedCall(response);
                            initData();
                        }
                    }, new ThrowableConsumer<Throwable>(this));
        }

    }
}
