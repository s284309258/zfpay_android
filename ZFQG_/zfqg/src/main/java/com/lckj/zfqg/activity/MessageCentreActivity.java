package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.zfqg.fragment.AfficheFragment;
import com.lckj.zfqg.fragment.InformFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageCentreActivity extends BaseActvity {

    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_inform)
    TextView tvInform;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.tv_inform_status)
    TextView tvInformStatus;
    @BindView(R.id.rl_inform)
    RelativeLayout rlInform;
    @BindView(R.id.tv_affiche)
    TextView tvAffiche;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.tv_affiche_status)
    TextView tvAfficheStatus;
    @BindView(R.id.rl_affiche)
    RelativeLayout rlAffiche;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_centre);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.消息中心));
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
            tvInform.setSelected(true);
            tvAffiche.setSelected(false);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
        } else {
            tvInform.setSelected(false);
            tvAffiche.setSelected(true);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        mFragmentList.add(new InformFragment());
        mFragmentList.add(new AfficheFragment());
    }

    @OnClick({R.id.left_action, R.id.rl_inform, R.id.rl_affiche})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_inform:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.rl_affiche:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
