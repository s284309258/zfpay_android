package com.lckj.jycm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.home.HomeActivity;
import com.lckj.jycm.network.NewVersionInfoBean;
import com.lckj.jycm.network.NewVersionInfoRequest;
import com.lckj.jycm.network.SysVersionService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActvity {
    @Inject
    SysVersionService sysVersionService;
    @Inject
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication.getInjectGraph().inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    public void hello(View view) {
        ProgressDlgHelper.openDialog(this);
        sysVersionService.getNewVersionInfo(new NewVersionInfoRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<NewVersionInfoBean>(this) {
                    @Override
                    public void onSuccessCall(NewVersionInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        Toast.makeText(MainActivity.this, response.toJson(), Toast.LENGTH_SHORT).show();
                    }
                }, new ThrowableConsumer<Throwable>(this));
        startActivity(new Intent(this, HomeActivity.class));
    }
}
