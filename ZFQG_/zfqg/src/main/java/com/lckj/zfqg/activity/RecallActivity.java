package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.zfqg.fragment.AgreeRefuseFragment;
import com.lckj.zfqg.fragment.PendingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecallActivity extends BaseActvity {

    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_pending)
    TextView tvPending;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.tv_pending_status)
    TextView tvPendingStatus;
    @BindView(R.id.rl_pending)
    RelativeLayout rlPending;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.rl_agree)
    RelativeLayout rlAgree;
    @BindView(R.id.tv_refuse)
    TextView tvRefuse;
    @BindView(R.id.xx3)
    TextView xx3;
    @BindView(R.id.rl_refuse)
    RelativeLayout rlRefuse;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_epos)
    TextView tvEpos;
    private PendingFragment mPendingFragment;
    public AgreeRefuseFragment mAgreeRefuseFragment;
    public AgreeRefuseFragment mAgreeRefuseFragment1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
//        customTitle.setText(getString(R.string.召回意见));
        setSelector(0);
        tvPos.setSelected(true);
        tvMpos.setSelected(false);
        tvCard.setSelected(false);
        viewPager.setOffscreenPageLimit(4);
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
            tvPending.setSelected(true);
            tvAgree.setSelected(false);
            tvRefuse.setSelected(false);
            xx3.setVisibility(View.INVISIBLE);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
        } else if (index == 1) {
            tvPending.setSelected(false);
            tvAgree.setSelected(true);
            tvRefuse.setSelected(false);
            xx3.setVisibility(View.INVISIBLE);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
        } else if (index == 2) {
            tvPending.setSelected(false);
            tvAgree.setSelected(false);
            tvRefuse.setSelected(true);
            xx3.setVisibility(View.VISIBLE);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        mPendingFragment = new PendingFragment();
        mAgreeRefuseFragment = new AgreeRefuseFragment(1);
        mAgreeRefuseFragment1 = new AgreeRefuseFragment(2);
        mFragmentList.add(mPendingFragment);
        mFragmentList.add(mAgreeRefuseFragment);
        mFragmentList.add(mAgreeRefuseFragment1);
    }

    @OnClick({R.id.left_action, R.id.rl_pending, R.id.rl_agree, R.id.rl_refuse, R.id.tv_pos, R.id.tv_epos, R.id.tv_mpos, R.id.tv_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_pending:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.rl_agree:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.rl_refuse:
                setSelector(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_pos:
                tvPos.setSelected(true);
                tvMpos.setSelected(false);
                tvCard.setSelected(false);
                tvEpos.setSelected(false);
                mPendingFragment.setWho(0);
                mAgreeRefuseFragment.setWho(0);
                mAgreeRefuseFragment1.setWho(0);
                break;
            case R.id.tv_epos:
                tvPos.setSelected(false);
                tvMpos.setSelected(false);
                tvCard.setSelected(false);
                tvEpos.setSelected(true);
                mPendingFragment.setWho(3);
                mAgreeRefuseFragment.setWho(3);
                mAgreeRefuseFragment1.setWho(3);
                break;
            case R.id.tv_mpos:
                tvPos.setSelected(false);
                tvMpos.setSelected(true);
                tvCard.setSelected(false);
                tvEpos.setSelected(false);
                mPendingFragment.setWho(1);
                mAgreeRefuseFragment.setWho(1);
                mAgreeRefuseFragment1.setWho(1);
                break;
            case R.id.tv_card:
                tvPos.setSelected(false);
                tvMpos.setSelected(false);
                tvCard.setSelected(true);
                tvEpos.setSelected(false);
                mPendingFragment.setWho(2);
                mAgreeRefuseFragment.setWho(2);
                mAgreeRefuseFragment1.setWho(2);
                break;
        }
    }
}
