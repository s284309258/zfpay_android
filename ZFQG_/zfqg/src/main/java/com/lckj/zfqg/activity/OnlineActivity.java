package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.zfqg.fragment.ActivityListFragment;
import com.lckj.zfqg.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlineActivity extends BaseActvity {
    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.iv_back)
    TextView ivBack;
    @BindView(R.id.tv_list)
    TextView tvList;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.view_pager)
    MyViewPager viewPager;
    public int mCurrent;
    private ActivityListFragment mActivityListFragment;
    private ActivityListFragment mActivityListFragment1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mActivityListFragment1.mPosActivityListFragment1 != null) {
            mActivityListFragment1.mPosActivityListFragment1.last_id = "";
            mActivityListFragment1.mPosActivityListFragment1.initData();
            mActivityListFragment1.mPosActivityListFragment.last_id = "";
            mActivityListFragment1.mPosActivityListFragment.initData();
        }
    }

    @Override
    protected void initView() {
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
            tvRecord.setVisibility(View.VISIBLE);
            tvList.setSelected(true);
            tvOrder.setSelected(false);
            mCurrent = mActivityListFragment.mIndex;
        } else {
            tvRecord.setVisibility(View.GONE);
            tvOrder.setSelected(true);
            tvList.setSelected(false);
            mCurrent = mActivityListFragment1.mIndex;
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        mActivityListFragment = new ActivityListFragment(0);
        mActivityListFragment1 = new ActivityListFragment(1);
        mFragmentList.add(mActivityListFragment);
        mFragmentList.add(mActivityListFragment1);

    }


    @OnClick({R.id.iv_back, R.id.tv_list, R.id.tv_order, R.id.tv_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_list:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_order:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_record:
                startActivity(new Intent(this, ActivityRecordActivity.class).putExtra("type", mCurrent));
                break;
        }
    }
}
