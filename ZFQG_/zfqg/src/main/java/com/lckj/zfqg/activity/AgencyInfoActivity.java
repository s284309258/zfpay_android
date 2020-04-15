package com.lckj.zfqg.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.zfqg_network.GetReferAgencyHeadTraditionalPosInfoBean;
import com.lckj.jycm.zfqg_network.MonthRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.ReferAgencyPosAmountAvgBean;
import com.lckj.jycm.zfqg_network.UserRequest;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.fragment.AgencyInfoFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AgencyInfoActivity extends BaseActvity {

    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_already_deal)
    TextView tvAlreadyDeal;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.ll_already_deal)
    LinearLayout llAlreadyDeal;
    @BindView(R.id.tv_no_deal)
    TextView tvNoDeal;
    @BindView(R.id.xx3)
    TextView xx3;
    @BindView(R.id.ll_no_deal)
    LinearLayout llNoDeal;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_pos_number)
    TextView tvPosNumber;
    @BindView(R.id.tv_month_deal)
    TextView tvMonthDeal;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_epos)
    TextView tvEpos;
    private String mId;
    private int isPos;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mPhone;
    private String mHead_img;
    private AgencyInfoFragment mAgencyInfoFragment;
    private AgencyInfoFragment mAgencyInfoFragment1;
    private AgencyInfoFragment mAgencyInfoFragment2;
    private TimePickerView mTimePickerView;
    private String mDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_info);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initFragment();
        initTime();
        initView();
        initEvents();
        initData();
        getData();
    }

    private void initFragment() {
        mId = getIntent().getStringExtra("id");
        mPhone = getIntent().getStringExtra("phone");
        mHead_img = getIntent().getStringExtra("head_img");
        mAgencyInfoFragment = new AgencyInfoFragment(0, mId);
        mAgencyInfoFragment1 = new AgencyInfoFragment(1, mId);
        mAgencyInfoFragment2 = new AgencyInfoFragment(2, mId);
        mFragmentList.add(mAgencyInfoFragment);
        mFragmentList.add(mAgencyInfoFragment1);
        mFragmentList.add(mAgencyInfoFragment2);
    }

    @Override
    protected void initView() {
        tvPhone.setText(mPhone);
        tvTime.setText(mDate);
        ImageLoader.loadImage(mHead_img, ivHeadImg, 30, 0);
        setSelector(0);
        tvPos.setSelected(true);
        tvMpos.setSelected(false);
        viewPager.setOffscreenPageLimit(3);
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
            tvAll.setSelected(true);
            tvAlreadyDeal.setSelected(false);
            tvNoDeal.setSelected(false);
            xx3.setVisibility(View.INVISIBLE);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
        } else if (index == 1) {
            tvAll.setSelected(false);
            tvAlreadyDeal.setSelected(true);
            tvNoDeal.setSelected(false);
            xx3.setVisibility(View.INVISIBLE);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
        } else if (index == 2) {
            tvAll.setSelected(false);
            tvAlreadyDeal.setSelected(false);
            tvNoDeal.setSelected(true);
            xx3.setVisibility(View.VISIBLE);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initEvents() {

    }

    private void initTime() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
        mDate = df.format(new Date());// new Date()为获取当前系统时间
        //正确设置方式 原因：注意事项有说明
        startDate.set(2010, 1, 1);
        endDate.set(2030, 12, 31);
        mTimePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                mDate = simpleDateFormat.format(date);
                getData();
            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})// 默认全部显示
                .setCancelText(getString(R.string.取消))//取消按钮文字
                .setCancelColor(getResources().getColor(R.color.gray))
                .setSubmitText(getString(R.string.确定))//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(getResources().getColor(R.color.green2))//确定按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.white))//标题背景颜色 Night mode
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    private void getData() {
        ProgressDlgHelper.openDialog(this);
        Observable<ReferAgencyPosAmountAvgBean> request = null;
        if (isPos == 0 || isPos == 2) {
            request = mMyService.getReferAgencyTraditionalPosTradeAmountAvg(new MonthRequest(dataManager.getToken(), mDate.replace("-", ""), mId,isPos == 2 ? "epos" : null));
        } else {
            request = mMyService.getReferAgencyMPosTradeAmountAvg(new MonthRequest(dataManager.getToken(), mDate.replace("-", ""), mId));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<ReferAgencyPosAmountAvgBean>(this) {
                    @Override
                    public void onSuccessCall(ReferAgencyPosAmountAvgBean response) {
                        ProgressDlgHelper.closeDialog();
                        tvTime.setText(mDate);
                        tvMonthDeal.setText("月交易额：¥" + response.getData().getMerchant_performance() + " | 月台均" + response.getData().getAvg_performance());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void showTimeDialog() {
        mTimePickerView.show();
    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        Observable<GetReferAgencyHeadTraditionalPosInfoBean> request = null;
        if (isPos == 0 || isPos == 2) {
            request = mMyService.getReferAgencyHeadTraditionalPosInfo(new UserRequest(dataManager.getToken(), mId, null, null, isPos == 2 ? "epos" : null));
        } else {
            request = mMyService.getReferAgencyHeadMposInfo(new UserRequest(dataManager.getToken(), mId));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetReferAgencyHeadTraditionalPosInfoBean>(this) {
                    @Override
                    public void onSuccessCall(GetReferAgencyHeadTraditionalPosInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (isPos == 0) {
                            GetReferAgencyHeadTraditionalPosInfoBean.DataBean.ReferAgencyHeadTraditionalPosInfoBean bean = response.getData().getReferAgencyHeadTraditionalPosInfo();
                            tvCount.setText(getString(R.string.商户2) + bean.getPos_num());
                            tvMoney.setText("总交易额：¥" + bean.getPerformance());
                            String pos = "<font color='#000000'>传统POS共计 " + bean.getTra_pos_num() + "台</font> 已激活 <font color='#1CCC9A'>" + bean.getTra_act_num() + "</font> 未激活 <font color='#FF0000'>" + bean.getTra_inact_num() + "</font> ";
                            tvPosNumber.setText(Html.fromHtml(pos));
                            tvName.setText(bean.getReal_name());
                        } else if (isPos == 2) {
                            GetReferAgencyHeadTraditionalPosInfoBean.DataBean.ReferAgencyHeadTraditionalPosInfoBean bean = response.getData().getReferAgencyHeadTraditionalPosInfo();
                            tvCount.setText(getString(R.string.商户2) + bean.getPos_num());
                            tvMoney.setText("总交易额：¥" + bean.getPerformance());
                            String pos = "<font color='#000000'>EPOS共计 " + bean.getE_pos_num() + "台</font> 已激活 <font color='#1CCC9A'>" + bean.getE_act_num() + "</font> 未激活 <font color='#FF0000'>" + bean.getE_inact_num() + "</font> ";
                            tvPosNumber.setText(Html.fromHtml(pos));
                            tvName.setText(bean.getReal_name());
                        } else {
                            GetReferAgencyHeadTraditionalPosInfoBean.DataBean.ReferAgencyHeadTraditionalPosInfoBean bean = response.getData().getReferAgencyHeadMposInfo();
                            tvCount.setText(getString(R.string.商户2) + bean.getPos_num());
                            tvMoney.setText("总交易额：¥" + bean.getPerformance());
                            String mpos = "<font color='#000000'>MPOS共计 " + bean.getM_pos_num() + "台</font> 已激活 <font color='#1CCC9A'>" + bean.getM_act_num() + "</font> 未激活 <font color='#FF0000'>" + bean.getM_inact_num() + "</font> ";
                            tvPosNumber.setText(Html.fromHtml(mpos));
                            tvName.setText(bean.getReal_name());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.tv_time, R.id.iv_call, R.id.ll_all, R.id.ll_already_deal, R.id.ll_no_deal, R.id.tv_pos, R.id.tv_mpos, R.id.tv_epos})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_time:
                showTimeDialog();
                break;
            case R.id.iv_call:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPhone));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
                break;
            case R.id.ll_all:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_already_deal:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_no_deal:
                setSelector(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_pos:
                isPos = 0;
                initData();
                getData();
                mAgencyInfoFragment.setIsPos(isPos);
                mAgencyInfoFragment1.setIsPos(isPos);
                mAgencyInfoFragment2.setIsPos(isPos);
                tvPos.setSelected(true);
                tvMpos.setSelected(false);
                tvEpos.setSelected(false);
                break;
            case R.id.tv_mpos:
                isPos = 1;
                initData();
                getData();
                mAgencyInfoFragment.setIsPos(isPos);
                mAgencyInfoFragment1.setIsPos(isPos);
                mAgencyInfoFragment2.setIsPos(isPos);
                tvPos.setSelected(false);
                tvMpos.setSelected(true);
                tvEpos.setSelected(false);
                break;
            case R.id.tv_epos:
                isPos = 2;
                initData();
                getData();
                mAgencyInfoFragment.setIsPos(isPos);
                mAgencyInfoFragment1.setIsPos(isPos);
                mAgencyInfoFragment2.setIsPos(isPos);
                tvPos.setSelected(false);
                tvMpos.setSelected(false);
                tvEpos.setSelected(true);
                break;
        }
    }
}
