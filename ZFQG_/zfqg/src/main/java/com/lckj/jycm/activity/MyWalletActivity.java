package com.lckj.jycm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.network.AccountRecordListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActvity {


    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tv_current_balance)
    TextView tvCurrentBalance;
    @BindView(R.id.tv_earnings_yesterday)
    TextView tvEarningsYesterday;
    @BindView(R.id.tv_accumulated_income)
    TextView tvAccumulatedIncome;
    @BindView(R.id.radio_left)
    RadioButton radioLeft;
    @BindView(R.id.radio_right)
    RadioButton radioRight;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_yesterday)
    TextView tvYesterday;
    @BindView(R.id.tv_accumulated)
    TextView tvAccumulated;
    private MyFragmentPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private WithdrawalRecordFragment withdrawalRecordFragment;
    private GainRecordFragment gainRecordFragment;
    private AccountRecordListBean.DataBean.FuAccountBean mFuAccount1;
    private AccountRecordListBean.DataBean.FuAccountBean mFuAccount2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        customTitle.setText(R.string.我的钱包);
        rightAction.setText(R.string.记录);
        rightAction.setVisibility(View.GONE);
        leftAction.setText("");
        viewpager.setCurrentItem(0, true);
        setRightAction(1);
        radioLeft.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_left:
                        viewpager.setCurrentItem(0, true);
                        setRightAction(1);
                        break;
                    case R.id.radio_right:
                        viewpager.setCurrentItem(1, true);
                        setRightAction(2);
                        break;
                }
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        if (fragments.size() == 0) {
            withdrawalRecordFragment = new WithdrawalRecordFragment();
            gainRecordFragment = new GainRecordFragment();
            fragments.add(withdrawalRecordFragment);
            fragments.add(gainRecordFragment);
        }
        adapter = new MyFragmentPagerAdapter(fm, fragments); //new myFragmentPagerAdater记得带上两个参数
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setRightAction(i + 1);
                if (i == 0) {
                    radioLeft.setChecked(true);
                } else {
                    radioRight.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setRightAction(int i) {
        if (i == 2) {
            rightAction.setVisibility(View.GONE);
            if (mFuAccount1 != null) {
                tvAccumulatedIncome.setText(mFuAccount1.getSumAmount());
                tvEarningsYesterday.setText(mFuAccount1.getYesdayEarn());
                tvMoney.setText(mFuAccount1.getAmount());
            }
        } else {
            rightAction.setVisibility(View.VISIBLE);
            if (mFuAccount2 != null) {
                tvAccumulatedIncome.setText(mFuAccount2.getSumAmount());
                tvEarningsYesterday.setText(mFuAccount2.getYesdayEarn());
                tvMoney.setText(mFuAccount2.getAmount());
            }
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.left_action, R.id.custom_title, R.id.right_action, R.id.tv_immediate_withdrawal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.custom_title:
                break;
            case R.id.right_action:
                Toast.makeText(this, R.string.暂未开放, Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_immediate_withdrawal:
                Toast.makeText(this, R.string.暂未开放, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setData(AccountRecordListBean.DataBean.FuAccountBean fuAccount, int type) {
        if (type == 1) {
            mFuAccount1 = fuAccount;
        } else {
            mFuAccount2 = fuAccount;
        }
    }
}
