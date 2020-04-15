package com.lckj.zfqg.activity;

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
import com.lckj.zfqg.fragment.MerchantInFragment;
import com.lckj.zfqg.fragment.RateApplyRecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MerchantInActivity extends BaseActvity {
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
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.ll_mpos)
    LinearLayout llMpos;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.ll_pos)
    LinearLayout llPos;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<Fragment> mFragmentList = new ArrayList<>();
    private boolean isPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_in);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.商户进件查询));
        isPos = getIntent().getBooleanExtra("isPos", false);
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

    @Override
    protected void initData() {
        mFragmentList.add(new MerchantInFragment("00", isPos));
        mFragmentList.add(new MerchantInFragment("01", isPos));
    }

    private void setSelector(int index) {
        if (index == 1) {
            tvPos.setSelected(true);
            tvMpos.setSelected(false);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
        } else {
            tvPos.setSelected(false);
            tvMpos.setSelected(true);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick({R.id.left_action, R.id.ll_pos, R.id.ll_mpos})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.ll_pos:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_mpos:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
        }
    }
}