package com.lckj.zfqg.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.DateRequest;
import com.lckj.jycm.zfqg_network.GetBenefitCentreMposDetailBean;
import com.lckj.jycm.zfqg_network.GetHeaderInformationBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.activity.DetailRecordActivity;
import com.lckj.zfqg.activity.WithdrawalActivity;
import com.lckj.zfqg.adapter.EarningsAdapter;
import com.lckj.zfqg.adapter.EarningsPVAdapter;
import com.lckj.zfqg.bean.CardInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EarningsFragment extends BaseFragment {
    @BindView(R.id.tv_today_earnings)
    TextView tvTodayEarnings;
    @BindView(R.id.tv_withdrawal_count)
    TextView tvWithdrawalCount;
    @BindView(R.id.tv_withdrawal)
    TextView tvWithdrawal;
    @BindView(R.id.tv_sum_earnings)
    TextView tvSumEarnings;
    @BindView(R.id.tv_withdrawal_sum)
    TextView tvWithdrawalSum;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_fenlun)
    TextView tvFenlun;
    @BindView(R.id.rl_fenlun)
    RelativeLayout rlFenlun;
    @BindView(R.id.tv_fanxian)
    TextView tvFanxian;
    @BindView(R.id.rl_fanxian)
    RelativeLayout rlFanxian;
    @BindView(R.id.tv_huodong)
    TextView tvHuodong;
    @BindView(R.id.rl_huodong)
    RelativeLayout rlHuodong;
    @BindView(R.id.tv_kouchu)
    TextView tvKouchu;
    @BindView(R.id.rl_kouchu)
    RelativeLayout rlKouchu;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private EarningsPVAdapter mEarningsPVAdapter;
    private List<CardView> mViews = new ArrayList<>();
    private List<CardInfo> mCardList = new ArrayList<>();
    private List<GetBenefitCentreMposDetailBean.DataBean.MposBenefitDeatilBean> mData = new ArrayList<>();
    private TimePickerView mTimePickerView;
    private String mDate;
    private boolean isPos;
    private int mCount;
    private boolean isMpos = true;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_earnings);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initTime();
        initView();
        initEvents();
        initHeadData();
        initData();
    }

    private void initData() {
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetBenefitCentreMposDetailBean> request = null;
        mMyService.getBenefitCentreTraditionalPosDetail(new DateRequest(mDate.replace("-", ""), dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetBenefitCentreMposDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetBenefitCentreMposDetailBean response) {
                        mCount++;
                        GetBenefitCentreMposDetailBean.DataBean.MposBenefitDeatilBean bean = response.getData().getTraditionalPosBenefitDeatil();
                        mCardList.remove(1);
                        mCardList.add(1, new CardInfo(getString(R.string.传统POS收益元), R.drawable.bg_blue_shape2, R.mipmap.bg_zhexian2, bean.getBenefit(), bean.getMerchant_benefit(), bean.getAgency_benefit(), mDate));
                        mData.remove(1);
                        mData.add(1, bean);
                        if (viewPager.getCurrentItem() == 1) {
                            tvFenlun.setText(bean.getShare_benefit());
                            tvFanxian.setText(bean.getReturn_benefit());
                            tvHuodong.setText(bean.getActivity_benefit());
                            tvKouchu.setText(bean.getDeduct_money());
                        }
                        if (mCount == 3) {
                            mCount = 0;
                            ProgressDlgHelper.closeDialog();
                            refreshLayout.finishRefresh();
                            mEarningsPVAdapter.notifyDataSetChanged();
//                            viewPager.setAdapter(new EarningsPVAdapter(getContext(), mViews, mCardList, EarningsFragment.this, mDate));
                        }
                    }

                    @Override
                    public void onFailedCall(GetBenefitCentreMposDetailBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });

        //新增EPOS
        mMyService.getBenefitCentreTraditionalPosDetail(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), null, "epos"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetBenefitCentreMposDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetBenefitCentreMposDetailBean response) {
                        mCount++;
                        GetBenefitCentreMposDetailBean.DataBean.MposBenefitDeatilBean bean = response.getData().getTraditionalPosBenefitDeatil();
                        mCardList.remove(2);
                        mCardList.add(2, new CardInfo(getString(R.string.电签EPOS收益元), R.drawable.bg_yellow_shape4, R.mipmap.bg_zhexian3, bean.getBenefit(), bean.getMerchant_benefit(), bean.getAgency_benefit(), mDate));
                        mData.remove(2);
                        mData.add(2, bean);
                        if (viewPager.getCurrentItem() == 2) {
                            tvFenlun.setText(bean.getShare_benefit());
                            tvFanxian.setText(bean.getReturn_benefit());
                            tvHuodong.setText(bean.getActivity_benefit());
                            tvKouchu.setText(bean.getDeduct_money());
                        }
                        if (mCount == 3) {
                            mCount = 0;
                            ProgressDlgHelper.closeDialog();
                            refreshLayout.finishRefresh();
                            mEarningsPVAdapter.notifyDataSetChanged();
//                            viewPager.setAdapter(new EarningsPVAdapter(getContext(), mViews, mCardList, EarningsFragment.this, mDate));
                        }
                    }

                    @Override
                    public void onFailedCall(GetBenefitCentreMposDetailBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });

        mMyService.getBenefitCentreMposDetail(new DateRequest(mDate.replace("-", ""), dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetBenefitCentreMposDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetBenefitCentreMposDetailBean response) {
                        mCount++;
                        GetBenefitCentreMposDetailBean.DataBean.MposBenefitDeatilBean bean = response.getData().getMposBenefitDeatil();
                        mCardList.remove(0);
                        mCardList.add(0, new CardInfo(getString(R.string.MPOS收益元), R.drawable.bg_green_shape3, R.mipmap.bg_zhexian, bean.getBenefit(), bean.getMerchant_benefit(), bean.getAgency_benefit(), mDate));
                        mData.remove(0);
                        mData.add(0, bean);
                        if (viewPager.getCurrentItem() == 0) {
                            tvFenlun.setText(bean.getShare_benefit());
                            tvFanxian.setText(bean.getReturn_benefit());
                            tvHuodong.setText(bean.getActivity_benefit());
                            tvKouchu.setText(bean.getDeduct_money());
                        }
                        if (mCount == 3) {
                            mCount = 0;
                            ProgressDlgHelper.closeDialog();
                            refreshLayout.finishRefresh();
                            mEarningsPVAdapter.notifyDataSetChanged();
//                            viewPager.setAdapter(new EarningsPVAdapter(getContext(), mViews, mCardList, EarningsFragment.this, mDate));
                        }
                    }

                    @Override
                    public void onFailedCall(GetBenefitCentreMposDetailBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });
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
        mTimePickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                mDate = simpleDateFormat.format(date);
                initData();
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

    public void showTimeDialog() {
        mTimePickerView.show();
    }

    private void initHeadData() {
        mMyService.getHeaderInformation(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetHeaderInformationBean>(this) {
                    @Override
                    public void onSuccessCall(GetHeaderInformationBean response) {
                        refreshLayout.finishRefresh();
                        ProgressDlgHelper.closeDialog();
                        tvSumEarnings.setText(response.getData().getHeaderInfo().getTotal_benefit());
                        tvWithdrawalSum.setText(response.getData().getHeaderInfo().getSettle_money());
                        tvWithdrawalCount.setText(response.getData().getHeaderInfo().getWithdraw_money());
                        tvTodayEarnings.setText(response.getData().getHeaderInfo().getToday_benefit());
                    }

                    @Override
                    public void onFailedCall(GetHeaderInformationBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });

    }

    private void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initHeadData();
                initData();
            }
        });
    }

    @Override
    protected void initView() {
        mCardList.clear();
        if (mCardList.size() == 0) {
            mViews.add(null);
            mViews.add(null);
            mViews.add(null);
            mData.add(null);
            mData.add(null);
            mData.add(null);
            mCardList.add(new CardInfo(getString(R.string.MPOS收益元), R.drawable.bg_green_shape3, R.mipmap.bg_zhexian, "0.00", "0.00", "0.00", mDate));
            mCardList.add(new CardInfo(getString(R.string.传统POS收益元), R.drawable.bg_blue_shape2, R.mipmap.bg_zhexian2, "0.00", "0.00", "0.00", mDate));
            mCardList.add(new CardInfo(getString(R.string.电签EPOS收益元), R.drawable.bg_yellow_shape4, R.mipmap.bg_zhexian3, "0.00", "0.00", "0.00", mDate));
        }
        mEarningsPVAdapter = new EarningsPVAdapter(getContext(), mViews, mCardList, this);
        viewPager.setAdapter(mEarningsPVAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(Utils.dp2px(getContext(), 10));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                isPos = i == 1;
                isMpos = i == 0;
                GetBenefitCentreMposDetailBean.DataBean.MposBenefitDeatilBean bean = mData.get(i);
                if (bean != null) {
                    tvFenlun.setText(bean.getShare_benefit());
                    tvFanxian.setText(bean.getReturn_benefit());
                    tvHuodong.setText(bean.getActivity_benefit());
                    tvKouchu.setText(bean.getDeduct_money());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        if (dataManager.getAlgebra().equals("1")) {
            rlKouchu.setVisibility(View.VISIBLE);
        } else {
            rlKouchu.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick(R.id.tv_withdrawal)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), WithdrawalActivity.class));
    }

    @OnClick({R.id.rl_fenlun, R.id.rl_fanxian, R.id.rl_huodong, R.id.rl_kouchu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_fenlun:
                startActivity(new Intent(getContext(), DetailRecordActivity.class).putExtra("type", isPos ? 0 : isMpos ? 4 : 8).putExtra("date", mDate));
                break;
            case R.id.rl_fanxian:
                startActivity(new Intent(getContext(), DetailRecordActivity.class).putExtra("type", isPos ? 1 : isMpos ? 5 : 9).putExtra("date", mDate));
                break;
            case R.id.rl_huodong:
                startActivity(new Intent(getContext(), DetailRecordActivity.class).putExtra("type", isPos ? 2 : isMpos ? 6 : 10).putExtra("date", mDate));
                break;
            case R.id.rl_kouchu:
                startActivity(new Intent(getContext(), DetailRecordActivity.class).putExtra("type", isPos ? 3 : isMpos ? 7 : 11).putExtra("date", mDate));
                break;
        }
    }
}
