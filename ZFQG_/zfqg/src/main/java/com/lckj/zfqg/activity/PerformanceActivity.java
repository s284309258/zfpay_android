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

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.zfqg.fragment.PerformanceFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerformanceActivity extends BaseActvity {

    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_agency)
    TextView tvAgency;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.ll_agency)
    LinearLayout llAgency;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.ll_my)
    LinearLayout llMy;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<Fragment> mFragmentList;
    private String mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        customTitle.setText(mTitle);
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

    @Override
    protected void initEvents() {

    }
    //新增EPOS
    @Override
    protected void initData() {
        mTitle = getIntent().getStringExtra("title");
        mFragmentList = new ArrayList<>();
        if (mTitle.equals(getString(R.string.传统POS))) {
            mFragmentList.add(new PerformanceFragment(0));
            mFragmentList.add(new PerformanceFragment(1));
        } else if (mTitle.equals("MPOS")) {
            mFragmentList.add(new PerformanceFragment(2));
            mFragmentList.add(new PerformanceFragment(3));
        }else if (mTitle.equals("EPOS")) {
            mFragmentList.add(new PerformanceFragment(4));
            mFragmentList.add(new PerformanceFragment(5));
        }
    }

    @OnClick({R.id.left_action, R.id.ll_agency, R.id.ll_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.ll_agency:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_my:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
        }
    }

    private void setSelector(int index) {
        if (index == 0) {
            tvAgency.setSelected(true);
            tvMy.setSelected(false);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
        } else {
            tvAgency.setSelected(false);
            tvMy.setSelected(true);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
        }
    }
}
