package com.lckj.jycm.article.activity;

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
import com.lckj.jycm.article.activity.CreateArticleActivity;
import com.lckj.jycm.fragment.MyArticleFragment;
import com.lckj.jycm.home.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyArticleActivity extends BaseActvity {
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
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.v_indicator1)
    View vIndicator1;
    @BindView(R.id.v_indicator2)
    View vIndicator2;
    @BindView(R.id.v_indicator3)
    View vIndicator3;
    private MyFragmentPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private MyArticleFragment fragment1;
    private MyArticleFragment fragment2;
    private MyArticleFragment fragment3;
    private boolean created;
    private int mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_article);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        created = intent.getBooleanExtra("created", false);
        if (viewpager != null && created && fragments.size() > 1) {
            viewpager.setCurrentItem(1, true);
            fragment2.setPage(1);
            fragment2.getData(true);
            created = false;
        }
    }

    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("type", 0);
        leftAction.setText("");
        rightAction.setText(R.string.创建文章);
        toolBar.setBackgroundResource(R.color.yellow);
        Drawable left = getDrawable(R.mipmap.icon_create_article);
        left.setBounds(0, 0, (int) (left.getIntrinsicWidth() * 0.8), (int) (left.getIntrinsicHeight() * 0.8));
        rightAction.setCompoundDrawables(left, null, null, null);
        rightAction.setCompoundDrawablePadding(dip2px(5));
        if (mType == 0) {
            customTitle.setText(R.string.我的文章);
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
            if (fragments.size() == 0) {
                fragment1 = new MyArticleFragment();
                fragment2 = new MyArticleFragment();
                fragment3 = new MyArticleFragment();
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
        } else {
            customTitle.setText(R.string.home_news);
            radioGroup.setVisibility(View.GONE);
            if (fragments.size() == 0) {
                fragment1 = new MyArticleFragment();
                Bundle args1 = new Bundle();
                args1.putInt("status", 2);
                args1.putInt("type", 2);
                fragment1.setArguments(args1);
                fragments.add(fragment1);
            }
        }
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments); //new myFragmentPagerAdater记得带上两个参数
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
                startActivity(new Intent(this, CreateArticleActivity.class));
                break;
        }
    }
}
