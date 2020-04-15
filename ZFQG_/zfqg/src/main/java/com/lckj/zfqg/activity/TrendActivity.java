package com.lckj.zfqg.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.MainActivity;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.DateRequest;
import com.lckj.jycm.zfqg_network.GetDayAgencyTraditionalPosDetailBean;
import com.lckj.jycm.zfqg_network.GetDayAgencyTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.widget.XFormattedValue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TrendActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_deal)
    TextView tvDeal;
    @BindView(R.id.tv_agency)
    TextView tvAgency;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    private int mType;
    private String mDate;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isMonth;
    List<GetDayAgencyTraditionalPosListBean.DataBean.DayAgencyMposListBean> mData = new ArrayList<>();
    private int mChildType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("title");
        mType = getIntent().getIntExtra("type", 0);
        mDate = getIntent().getStringExtra("date");
        isMonth = getIntent().getBooleanExtra("isMonth", false);
        customTitle.setText(title);
        tvDeal.setSelected(true);
        initBarChart();
    }

    private void initBarChart() {
        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        xAxis.setValueFormatter(new XFormattedValue());

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setStartAtZero(true);
        barChart.getAxisLeft().setDrawGridLines(true);

        // add a nice and smooth animation
        barChart.animateY(1500);

        barChart.getLegend().setEnabled(false);
    }

    @Override
    protected void initEvents() {

    }
    //新增EPOS
    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        Observable<GetDayAgencyTraditionalPosListBean> request = null;
        if (isMonth) {
            switch (mType) {
                case 0:
                    request = mMyService.getMonthAgencyTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 1:
                    request = mMyService.getMonthMerchantTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 2:
                    request = mMyService.getMonthAgencyMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 3:
                    request = mMyService.getMonthMerchantMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 4:
                    request = mMyService.getMonthAgencyTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), null, "epos"));
                    break;
                case 5:
                    request = mMyService.getMonthMerchantTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), null, "epos"));
                    break;
            }
        } else {
            switch (mType) {
                case 0:
                    request = mMyService.getDayAgencyTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 1:
                    request = mMyService.getDayMerchantTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 2:
                    request = mMyService.getDayAgencyMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 3:
                    request = mMyService.getDayMerchantMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
                    break;
                case 4:
                    request = mMyService.getDayAgencyTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), null, "epos"));
                    break;
                case 5:
                    request = mMyService.getDayMerchantTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), null, "epos"));
                    break;
            }
        }

        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetDayAgencyTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetDayAgencyTraditionalPosListBean response) {
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        if (isMonth) {
                            switch (mType) {
                                case 0:
                                    mData.addAll(response.getData().getMonthAgencyTraditionalPosList());
                                    break;
                                case 1:
                                    mData.addAll(response.getData().getMonthMerchantTraditionalPosList());
                                    break;
                                case 2:
                                    mData.addAll(response.getData().getMonthAgencyMposList());
                                    break;
                                case 3:
                                    mData.addAll(response.getData().getMonthMerchantMposList());
                                    break;
                                case 4:
                                    mData.addAll(response.getData().getMonthAgencyTraditionalPosList());
                                    break;
                                case 5:
                                    mData.addAll(response.getData().getMonthMerchantTraditionalPosList());
                                    break;
                            }
                        } else {
                            switch (mType) {
                                case 0:
                                    mData.addAll(response.getData().getDayAgencyTraditionalPosList());
                                    break;
                                case 1:
                                    mData.addAll(response.getData().getDayMerchantTraditionalPosList());
                                    break;
                                case 2:
                                    mData.addAll(response.getData().getDayAgencyMposList());
                                    break;
                                case 3:
                                    mData.addAll(response.getData().getDayMerchantMposList());
                                    break;
                                case 4:
                                    mData.addAll(response.getData().getDayAgencyTraditionalPosList());
                                    break;
                                case 5:
                                    mData.addAll(response.getData().getDayMerchantTraditionalPosList());
                                    break;
                            }
                        }
                        initChartData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void initChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        String[] arr = new String[mData.size()];
        switch (mChildType) {
            case 0:
                for (int i = 0; i < mData.size(); i++) {
                    float count = Float.parseFloat(mData.get(i).getPerformance());
                    String time = mData.get(i).getDate().substring(mData.get(i).getDate().length() - 2, mData.get(i).getDate().length());
                    arr[i] = time + (isMonth ? "月":"日");
                    values.add(new BarEntry(i, count));
                }
                break;
            case 1:
                for (int i = 0; i < mData.size(); i++) {
                    float count = Float.parseFloat(mData.get(i).getUser_num());
                    String time = mData.get(i).getDate().substring(mData.get(i).getDate().length() - 2, mData.get(i).getDate().length());
                    arr[i] = time + (isMonth ? "月":"日");
                    values.add(new BarEntry(i, count));
                }
                break;
            case 2:
                for (int i = 0; i < mData.size(); i++) {
                    float count = Float.parseFloat(mData.get(i).getAct_num());
                    String time = mData.get(i).getDate().substring(mData.get(i).getDate().length() - 2, mData.get(i).getDate().length());
                    arr[i] = time + (isMonth ? "月":"日");
                    values.add(new BarEntry(i, count));
                }
                break;
        }
        BarDataSet set1;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "The year 2017");
            set1.setColor(getResources().getColor(R.color.green2));
            set1.setDrawValues(true);
            set1.setValueTextColor(getResources().getColor(R.color.green2));
            set1.setValueTextSize(10);
            set1.setFormLineWidth(5);


            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.4f);
            barChart.setData(data);
            barChart.setFitBars(true);
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new XFormattedValue(arr));
        barChart.invalidate();
    }

    @OnClick({R.id.left_action, R.id.tv_deal, R.id.tv_agency, R.id.tv_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_deal:
                mChildType = 0;
                initChartData();
                barChart.animateY(1500);
                tvDeal.setSelected(true);
                tvAgency.setSelected(false);
                tvStore.setSelected(false);
                break;
            case R.id.tv_agency:
                mChildType = 1;
                initChartData();
                barChart.animateY(1500);
                tvDeal.setSelected(false);
                tvAgency.setSelected(true);
                tvStore.setSelected(false);
                break;
            case R.id.tv_store:
                mChildType = 2;
                initChartData();
                barChart.animateY(1500);
                tvDeal.setSelected(false);
                tvAgency.setSelected(false);
                tvStore.setSelected(true);
                break;
        }
    }
}
