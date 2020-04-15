package com.lckj.zfqg.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lckj.base.FragmentFactory;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.PwLoginActivity;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetAppImgListBean;
import com.lckj.jycm.zfqg_network.GetAppImgListRequest;
import com.lckj.jycm.zfqg_network.GetUnReadNewsBean;
import com.lckj.jycm.zfqg_network.GetUserNewInfoBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.widget.ConfirmDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends BaseActvity {
    private static HomeActivity sInstance;
    @BindView(R.id.main_bottom_menu)
    LinearLayout mainBottomMenu;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private FragmentManager mSupportFragmentManager;
    private ConfirmDialog mConfirmDialog;

    public static HomeActivity getInstance() {
        return sInstance != null ? sInstance : new HomeActivity();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        setContentView(R.layout.activity_home_);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    protected void initView() {
        setSelector(0);
        changeFragment(0);
        initBottomMenu();
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    //初始化底部菜单栏
    private void initBottomMenu() {
        for (int i = 0; i < mainBottomMenu.getChildCount(); i++) {
            final View childAt = mainBottomMenu.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = mainBottomMenu.indexOfChild(childAt);
                   /* if (TextUtils.isEmpty(dataManager.getToken())) {
                        if (index != 0) {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        }
                    } else {*/
                    changeFragment(index);
                    setSelector(index);
//                    }
                }
            });
        }
    }

    //切换Fragment
    int lastIndex = 0;

    @SuppressLint("RestrictedApi")
    public void changeFragment(int index) {
        if (mSupportFragmentManager == null) {
            mSupportFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();
        FragmentFactory fragmentFactory = FragmentFactory.getInstanes();
        Fragment LastFragment = fragmentFactory.createMainFragment(lastIndex);
        Fragment fragment = fragmentFactory.createMainFragment(index);
        if (mSupportFragmentManager.getFragments() == null) {
            if (!fragment.isAdded() && null == mSupportFragmentManager.findFragmentByTag(index + "")) {
                ft.add(R.id.frame_layout, fragment, index + "");
            }
        } else if (!mSupportFragmentManager.getFragments().contains(fragment)) {
            if (!fragment.isAdded() && null == mSupportFragmentManager.findFragmentByTag(index + "")) {
                ft.add(R.id.frame_layout, fragment, index + "");
            }
        }
        if (ft != null) {
            ft.hide(LastFragment);
            ft.show(fragment);
            ft.commitAllowingStateLoss();
            if (mSupportFragmentManager != null)
                mSupportFragmentManager.executePendingTransactions();
        }
        lastIndex = index;
    }

    //菜单选项选中处理
    public void setSelector(int index) {
        for (int i = 0; i < mainBottomMenu.getChildCount(); i++) {
            if (i == index) {
                mainBottomMenu.getChildAt(i).setSelected(true);
            } else {
                mainBottomMenu.getChildAt(i).setSelected(false);
            }
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void initData() {
        mMyService.getUserNewInfo(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserNewInfoBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserNewInfoBean response) {
                        MainApplication.getInstance().setUserNewInfo(response.getData().getUserNewInfo());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //页面销毁删除掉储存的fragment
        Map<Integer, Fragment> map = FragmentFactory.mMainMap;
        FragmentFactory.getInstanes().setNull();
        EventBus.getDefault().unregister(this);
        for (int i : map.keySet()) {
            mSupportFragmentManager.beginTransaction().remove(map.get(i));
            mSupportFragmentManager.beginTransaction().hide(map.get(i));
        }
        sInstance = null;
    }

    @Override
    public void onBackPressed() {
        if (mConfirmDialog == null)
            mConfirmDialog = new ConfirmDialog(this, R.style.BottomDialog2);
        mConfirmDialog.show(getString(R.string.确定退出中付钱柜吗), "");
    }
}
