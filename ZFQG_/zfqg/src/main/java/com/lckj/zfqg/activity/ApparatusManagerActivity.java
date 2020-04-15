package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.fragment.AllocationRecallFragment;
import com.lckj.zfqg.fragment.UnbindFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApparatusManagerActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_allocation)
    TextView tvAllocation;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.ll_allocation)
    LinearLayout llAllocation;
    @BindView(R.id.tv_recall)
    TextView tvRecall;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.ll_recall)
    LinearLayout llRecall;
    @BindView(R.id.tv_unbind)
    TextView tvUnbind;
    @BindView(R.id.xx3)
    TextView xx3;
    @BindView(R.id.ll_unbind)
    LinearLayout llUnbind;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<Fragment> mFragmentList = new ArrayList<>();
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private AllocationRecallFragment mAllocationRecallFragment;
    private AllocationRecallFragment mAllocationRecallFragment2;
    private AllocationRecallFragment mAllocationRecallFragment3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apparatus_manager);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.机具管理));
        setSelector(0);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setSelector(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setSelector(int index) {
        if (index == 0) {
            tvAllocation.setSelected(true);
            tvRecall.setSelected(false);
            tvUnbind.setSelected(false);
            xx3.setVisibility(View.INVISIBLE);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
            rightAction.setText(getString(R.string.分配记录));
            rightAction.setVisibility(View.VISIBLE);
        } else if(index == 1){
            tvAllocation.setSelected(false);
            tvRecall.setSelected(true);
            tvUnbind.setSelected(false);
            xx3.setVisibility(View.INVISIBLE);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
            rightAction.setVisibility(View.GONE);
        }else if(index == 2){
            tvAllocation.setSelected(false);
            tvRecall.setSelected(false);
            tvUnbind.setSelected(true);
            xx3.setVisibility(View.VISIBLE);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.INVISIBLE);
            rightAction.setText(getString(R.string.申请记录));
            rightAction.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initEvents() {

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            mAllocationRecallFragment.initData();
            mAllocationRecallFragment2.initData();
        }
    }

    @Override
    protected void initData() {
        mAllocationRecallFragment = new AllocationRecallFragment();
        mAllocationRecallFragment.setType(0, mMyService, dataManager);
        mAllocationRecallFragment2 = new AllocationRecallFragment();
        mAllocationRecallFragment2.setType(1, mMyService, dataManager);
        mAllocationRecallFragment3 = new AllocationRecallFragment();
        mAllocationRecallFragment3.setType(2, mMyService, dataManager);
        mFragmentList.add(mAllocationRecallFragment);
        mFragmentList.add(mAllocationRecallFragment2);
        mFragmentList.add(mAllocationRecallFragment3);
    }

    @OnClick({R.id.left_action, R.id.right_action, R.id.ll_allocation, R.id.ll_recall, R.id.ll_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                startActivity(new Intent(this, rightAction.getText().toString().equals(getString(R.string.分配记录))?AllocationRecordActivity.class:UnbindRecordActivity.class));
                break;
            case R.id.ll_allocation:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_recall:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_unbind:
                setSelector(2);
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
