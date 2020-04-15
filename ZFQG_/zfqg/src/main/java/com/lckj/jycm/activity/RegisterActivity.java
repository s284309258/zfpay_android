package com.lckj.jycm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.WithoutLoginPage;

import javax.inject.Inject;

public class RegisterActivity extends BaseActvity implements WithoutLoginPage {
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
}
