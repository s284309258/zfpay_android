package com.lckj.jycm.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lckj.base.FragmentFactory;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.PwLoginActivity;
import com.lckj.jycm.fragment.PersonCenterFragment;
import com.lckj.jycm.fragment.TaskFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@Deprecated
public class HomeActivity extends BaseActvity {


    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.xx)
    View xx;
    @BindView(R.id.main_bottom_menu)
    LinearLayout mainBottomMenu;
    @BindView(R.id.tab1)
    View tab1;
    @BindView(R.id.tab2)
    View tab2;
    @BindView(R.id.tab3)
    View tab3;
    @BindView(R.id.tab4)
    View tab4;
    @BindView(R.id.ll_login_tabs)
    LinearLayout llLoginTabs;
    public static HomeActivity instance = null;
    @Inject
    DataManager dataManager;
    private FragmentManager mSupportFragmentManager;

    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle arg0) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(arg0);
        getIntentParams();
        instance = this;
        setContentView(R.layout.activity_home);
        MainApplication.getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
        Log.d("tinker","right-what->》");
    }

    private void getIntentParams() {

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isMainActivityDestroy", true);
    }

    protected void initView() {
        setSelector(0);
        //切换Fragment
        changeFragment(0);
        initBottomMenu();
    }


    private void updateLoginStatus() {
        /*if (dataManager.islogin()){
            llLoginTabs.setVisibility(View.GONE);
        }else{
            llLoginTabs.setVisibility(View.VISIBLE);
        }*/
    }

    //初始化底部菜单栏
    private void initBottomMenu() {
        for (int i = 0; i < mainBottomMenu.getChildCount(); i++) {
            final View childAt = mainBottomMenu.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = mainBottomMenu.indexOfChild(childAt);
                    if (TextUtils.isEmpty(dataManager.getToken())) {
                        if (index != 0) {
                            startActivity(new Intent(HomeActivity.this, PwLoginActivity.class));
                        }
                    } else {
                        changeFragment(index);
                        setSelector(index);
                    }
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
    protected void initData() {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int whence = intent.getIntExtra("whence", 0);
        switch (whence) {
            case 0:
                setSelector(0);
                changeFragment(0);
                break;
            case 1:
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateLoginStatus();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
        instance = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
        }
    }


    @Override
    public void onBackPressed() {
        if (lastIndex==0) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.exit_dialog_msg)
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ArrayList<BaseActvity> activitys = MainApplication.getInstance().getActivitys();
                            for (int i = 0; i < activitys.size(); i++) {
                                activitys.get(i).finish();
                            }
                            activitys.clear();
                        }
                    }).show();
        }else{
            changeFragment(0);
            setSelector(0);
        }
    }

    @OnClick({R.id.tab2, R.id.tab3, R.id.tab4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab2:
            case R.id.tab3:
            case R.id.tab4:
                startActivity(new Intent(HomeActivity.this, PwLoginActivity.class));
                break;
        }
    }
}
