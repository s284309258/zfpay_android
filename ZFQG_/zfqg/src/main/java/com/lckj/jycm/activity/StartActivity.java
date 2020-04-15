package com.lckj.jycm.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.WithoutLoginPage;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetAppImgListBean;
import com.lckj.jycm.zfqg_network.GetAppImgListRequest;
import com.lckj.jycm.zfqg_network.GetNewVersionBean;
import com.lckj.jycm.zfqg_network.GetNewVersionRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UserLoginBean;
import com.lckj.jycm.zfqg_network.UserLoginRequest;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.HomeActivity;
import com.lckj.zfqg.activity.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StartActivity extends BaseActvity implements WithoutLoginPage {
    @Inject
    MyService mMyService;
    @Inject
    DataManager dataManager;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.iv)
    ImageView iv;
    private int count = 5;
    private AlertDialog.Builder mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initData();
        mHandler.sendEmptyMessageDelayed(0, 1000);
        tvTime.setText("跳过(" + count + ")");
    }

    @OnClick({R.id.tv_time, R.id.rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                if (TextUtils.isEmpty(dataManager.getToken())) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    startActivity(new Intent(this, HomeActivity.class));
                }
                finish();
                break;
            case R.id.rl:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
        mHandler = null;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count--;
            tvTime.setText("跳过(" + count + ")");
            if (count == 0) {
                if (mDialog == null) {
                    count = 5;
                    if (TextUtils.isEmpty(dataManager.getToken())) {
                        startActivity(new Intent(StartActivity.this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(StartActivity.this, HomeActivity.class));
                    }
                    finish();
                }
            } else {
                mHandler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvents() {

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        mMyService.getAppImgList(new GetAppImgListRequest(dataManager.getToken(), "04"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetAppImgListBean>(this) {
                    @Override
                    public void onSuccessCall(GetAppImgListBean response) {
                        dataManager.setStartImage(response.getData().getAppImgList().get(0).getImg_url());
                        ImageLoader.loadImage(dataManager.getStartImage(), iv);
                    }

                    @Override
                    public void onFailedCall(GetAppImgListBean response) {
                        super.onFailedCall(response);
                        ImageLoader.loadImage(dataManager.getStartImage(), iv);

                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        ImageLoader.loadImage(dataManager.getStartImage(), iv);
                    }
                });

        mMyService.getNewVersion(new GetNewVersionRequest(dataManager.getToken(), "android"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetNewVersionBean>(this) {
                    @Override
                    public void onSuccessCall(GetNewVersionBean response) {
                        ProgressDlgHelper.closeDialog();
                        String version_no = response.getData().getVersionInfo().getVersion_no();
                        String oldVersion = Utils.getVersionName(getContext());
                        String status = response.getData().getVersionInfo().getStatus();
                        final String version_url = response.getData().getVersionInfo().getVersion_url();
                        String text = response.getData().getVersionInfo().getNote();
                        boolean hasNew = version_no.compareTo(oldVersion) > 0;

                        if (hasNew) {
                            if ("1".equals(status)) {
                                mDialog = new AlertDialog.Builder(getContext())
                                        .setTitle(getString(R.string.检测到新版本))
                                        .setMessage(text)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                Uri uri = Uri.parse(version_url);
                                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                getContext().startActivity(intent);
                                                finish();
                                            }
                                        });
                                mDialog.setCancelable(false);
                                mDialog.show();
                            } else {
                                autoLogin();
                            }
                        } else {
                            autoLogin();
                        }
                    }

                    @Override
                    public void onFailedCall(GetNewVersionBean response) {
                        super.onFailedCall(response);
                        autoLogin();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                        autoLogin();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void autoLogin() {
        if (TextUtils.isEmpty(dataManager.getToken())) {
//            mHandler.sendEmptyMessageDelayed(0, 1000);
        } else {
            mMyService.userLogin(new UserLoginRequest("token", "", "", dataManager.getToken(), "android", Utils.getVersionName(this), Utils.getDevice(this)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<UserLoginBean>(this) {
                        @Override
                        public void onSuccessCall(UserLoginBean response) {
//                            startActivity(new Intent(StartActivity.this, HomeActivity.class));
//                        dataManager.setToken(response.getData().getToken());
                            setData(response);
//                            finish();
                        }

                        @Override
                        public void onFailedCall(UserLoginBean response) {
                            super.onFailedCall(response);
//                            startActivity(new Intent(StartActivity.this, LoginActivity.class));
                            dataManager.setToken("");
//                            finish();
                        }
                    }, new ThrowableConsumer<Throwable>(this) {
                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
//                            startActivity(new Intent(StartActivity.this, LoginActivity.class));
                            dataManager.setToken("");
//                            finish();
                        }
                    });
        }
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
