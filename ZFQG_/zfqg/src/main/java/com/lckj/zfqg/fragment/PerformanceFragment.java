package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.lckj.jycm.zfqg_network.DateRequest;
import com.lckj.jycm.zfqg_network.GetDayAgencyTraditionalPosDetailBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.activity.TrendActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("ValidFragment")
public class PerformanceFragment extends BaseFragment {
    private boolean isMonth;
    private final int mType;
    @BindView(R.id.tv_month_money)
    TextView tvMonthMoney;
    @BindView(R.id.tv_month_agency)
    TextView tvMonthAgency;
    @BindView(R.id.tv_month_store)
    TextView tvMonthStore;
    @BindView(R.id.btn_month_trend)
    Button btnMonthTrend;
    @BindView(R.id.tv_day_money)
    TextView tvDayMoney;
    @BindView(R.id.tv_day_agency)
    TextView tvDayAgency;
    @BindView(R.id.tv_day_store)
    TextView tvDayStore;
    @BindView(R.id.btn_day_trend)
    Button btnDayTrend;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_day)
    TextView tvDay;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mMonthDate;
    private TimePickerView mMonthPickerView;
    private String mDayDate;
    private TimePickerView mDayPickerView;

    public PerformanceFragment(int type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_performance);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initMonthTime();
        initDayTime();
        initView();
        getDayData();
        getMonthData();
    }


    private void initDayTime() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        mDayDate = df.format(new Date());// new Date()为获取当前系统时间
        tvDay.setText(mDayDate);
        //正确设置方式 原因：注意事项有说明
        startDate.set(2010, 1, 1);
        endDate.set(2030, 12, 31);
        mDayPickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                mDayDate = simpleDateFormat.format(date);
                tvDay.setText(mDayDate);
                getDayData();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
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

    private void initMonthTime() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
        mMonthDate = df.format(new Date());// new Date()为获取当前系统时间
        tvMonth.setText(mMonthDate);
        //正确设置方式 原因：注意事项有说明
        startDate.set(2010, 1, 1);
        endDate.set(2030, 12, 31);
        mMonthPickerView = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                mMonthDate = simpleDateFormat.format(date);
                tvMonth.setText(mMonthDate);
                getMonthData();
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
    //新增EPOS
    private void getMonthData() {
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetDayAgencyTraditionalPosDetailBean> request = null;
        switch (mType) {
            case 0:
                request = mMyService.getMonthAgencyTraditionalPosDetail(new DateRequest(mMonthDate.replace("-", ""), dataManager.getToken()));
                break;
            case 1:
                request = mMyService.getMonthMerchantTraditionalPosDetail(new DateRequest(mMonthDate.replace("-", ""), dataManager.getToken()));
                break;
            case 2:
                request = mMyService.getMonthAgencyMposDetail(new DateRequest(mMonthDate.replace("-", ""), dataManager.getToken()));
                break;
            case 3:
                request = mMyService.getMonthMerchantMposDetail(new DateRequest(mMonthDate.replace("-", ""), dataManager.getToken()));
                break;
            case 4:
                request = mMyService.getMonthAgencyEposDetail(new DateRequest(mMonthDate.replace("-", ""), dataManager.getToken()));
                break;
            case 5:
                request = mMyService.getMonthMerchantEposDetail(new DateRequest(mMonthDate.replace("-", ""), dataManager.getToken()));
                break;
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetDayAgencyTraditionalPosDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetDayAgencyTraditionalPosDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        GetDayAgencyTraditionalPosDetailBean.DataBean.DayAgencyTraditionalPosDetailBean bean = null;
                        switch (mType) {
                            case 0:
                                bean = response.getData().getMonthAgencyTraditionalPosDetail();
                                break;
                            case 1:
                                bean = response.getData().getMonthMerchantTraditionalPosDetail();
                                break;
                            case 2:
                                bean = response.getData().getMonthAgencyMposDetail();
                                break;
                            case 3:
                                bean = response.getData().getMonthMerchantMposDetail();
                                break;
                            case 4:
                                bean = response.getData().getMonthAgencyTraditionalPosDetail();
                                break;
                            case 5:
                                bean = response.getData().getMonthMerchantTraditionalPosDetail();
                                break;
                        }
                        tvMonthMoney.setText(bean.getPerformance());
                        tvMonthAgency.setText(bean.getUser_num());
                        tvMonthStore.setText(bean.getAct_num());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void getDayData() {
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetDayAgencyTraditionalPosDetailBean> request = null;
        switch (mType) {
            case 0:
                request = mMyService.getDayAgencyTraditionalPosDetail(new DateRequest(mDayDate.replace("-", ""), dataManager.getToken()));
                break;
            case 1:
                request = mMyService.getDayMerchantTraditionalPosDetail(new DateRequest(mDayDate.replace("-", ""), dataManager.getToken()));
                break;
            case 2:
                request = mMyService.getDayAgencyMposDetail(new DateRequest(mDayDate.replace("-", ""), dataManager.getToken()));
                break;
            case 3:
                request = mMyService.getDayMerchantMposDetail(new DateRequest(mDayDate.replace("-", ""), dataManager.getToken()));
                break;
            case 4:
                request = mMyService.getDayAgencyEposDetail(new DateRequest(mDayDate.replace("-", ""), dataManager.getToken()));
                break;
            case 5:
                request = mMyService.getDayMerchantEposDetail(new DateRequest(mDayDate.replace("-", ""), dataManager.getToken()));
                break;
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetDayAgencyTraditionalPosDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetDayAgencyTraditionalPosDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        GetDayAgencyTraditionalPosDetailBean.DataBean.DayAgencyTraditionalPosDetailBean bean = null;
                        switch (mType) {
                            case 0:
                                bean = response.getData().getDayAgencyTraditionalPosDetail();
                                break;
                            case 1:
                                bean = response.getData().getDayMerchantTraditionalPosDetail();
                                break;
                            case 2:
                                bean = response.getData().getDayAgencyMposDetail();
                                break;
                            case 3:
                                bean = response.getData().getDayMerchantMposDetail();
                                break;
                            case 4:
                                bean = response.getData().getDayAgencyTraditionalPosDetail();
                                break;
                            case 5:
                                bean = response.getData().getDayMerchantTraditionalPosDetail();
                                break;
                        }
                        tvDayMoney.setText(bean.getPerformance());
                        tvDayAgency.setText(bean.getUser_num());
                        tvDayStore.setText(bean.getAct_num());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.tv_month, R.id.btn_month_trend, R.id.tv_day, R.id.btn_day_trend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_month:
                isMonth = true;
                mMonthPickerView.show();
                break;
            case R.id.tv_day:
                isMonth = false;
                mDayPickerView.show();
                break;
            case R.id.btn_month_trend:
                Intent intent = new Intent(getContext(), TrendActivity.class);
                intent.putExtra("title", getString(R.string.业绩走势月));
                intent.putExtra("type", mType);
                intent.putExtra("date", mMonthDate);
                intent.putExtra("isMonth", true);

                startActivity(intent);
                break;
            case R.id.btn_day_trend:
                Intent intent2 = new Intent(getContext(), TrendActivity.class);
                intent2.putExtra("title", getString(R.string.业绩走势日));
                intent2.putExtra("type", mType);
                intent2.putExtra("date", mDayDate);
                intent2.putExtra("isMonth", false);
                startActivity(intent2);
                break;
        }
    }
}
