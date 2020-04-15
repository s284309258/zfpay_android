package com.lckj.jycm.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.fragment.MyAdFragment;
import com.lckj.jycm.fragment.MyArticleFragment;
import com.lckj.jycm.home.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAdActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.radio_left)
    RadioButton radioLeft;
    @BindView(R.id.radio_center)
    RadioButton radioCenter;
    @BindView(R.id.radio_right)
    RadioButton radioRight;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.v_indicator1)
    View vIndicator1;
    @BindView(R.id.v_indicator2)
    View vIndicator2;
    @BindView(R.id.v_indicator3)
    View vIndicator3;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> fragments = new ArrayList<>();
    private MyAdFragment fragment1;
    private MyAdFragment fragment2;
    private MyAdFragment fragment3;
    private MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ad);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        leftAction.setText("");
        customTitle.setText(R.string.我的广告);
        rightAction.setText(R.string.toufangguanggao);
        toolBar.setBackgroundResource(R.color.yellow);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left:
                        viewpager.setCurrentItem(0, true);
                        changeIndicator(0);
                        break;
                    case R.id.radio_center:
                        viewpager.setCurrentItem(1, true);
                        changeIndicator(1);
                        break;
                    case R.id.radio_right:
                        viewpager.setCurrentItem(2, true);
                        changeIndicator(2);
                        break;
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        if (fragments.size() == 0) {
            fragment1 = new MyAdFragment();
            fragment2 = new MyAdFragment();
            fragment3 = new MyAdFragment();
            Bundle args1 = new Bundle();
            Bundle args2 = new Bundle();
            Bundle args3 = new Bundle();
            args1.putInt("status", 2);
            args2.putInt("status", 1);
            args3.putInt("status", 3);
            fragment1.setArguments(args1);
            fragment2.setArguments(args2);
            fragment3.setArguments(args3);
            fragments.add(fragment1);
            fragments.add(fragment2);
            fragments.add(fragment3);
        }
        adapter = new MyFragmentPagerAdapter(fm, fragments); //new myFragmentPagerAdater记得带上两个参数
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        changeIndicator(0);
                        radioLeft.setChecked(true);
                        break;
                    case 1:
                        changeIndicator(1);
                        radioCenter.setChecked(true);
                        break;
                    case 2:
                        changeIndicator(2);
                        radioRight.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void changeIndicator(int i) {
        vIndicator1.setVisibility(View.INVISIBLE);
        vIndicator2.setVisibility(View.INVISIBLE);
        vIndicator3.setVisibility(View.INVISIBLE);
        switch (i) {
            case 0:
                vIndicator1.setVisibility(View.VISIBLE);
                break;
            case 1:
                vIndicator2.setVisibility(View.VISIBLE);
                break;
            case 2:
                vIndicator3.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.right_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                startActivity(new Intent(this, InvestAdActivity.class));
                break;
        }
    }
}
