package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetMessageRecordDetailBean;
import com.lckj.jycm.zfqg_network.GetMessageRecordDetailRequest;
import com.lckj.jycm.zfqg_network.GetMoneyLockerCollegeDetailBean;
import com.lckj.jycm.zfqg_network.GetMoneyLockerCollegeDetailRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InformDetailsActivity extends BaseActvity {
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
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_sn)
    TextView tvSn;
    @BindView(R.id.tv_earnings_type)
    TextView tvEarningsType;
    @BindView(R.id.tv_earnings_time)
    TextView tvEarningsTime;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_details);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mId = getIntent().getStringExtra("id");
        customTitle.setText(getString(R.string.通知详情));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getMessageRecordDetail(new GetMessageRecordDetailRequest(dataManager.getToken(), mId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetMessageRecordDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetMessageRecordDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        GetMessageRecordDetailBean.DataBean.MessageRecordBean bean = response.getData().getMessageRecord();
                        tvSn.setText(bean.getSn());
                        tvEarningsTime.setText(bean.getCre_datetime());
                        switch (bean.getOp_type()){
                            case "01":
                                tvEarningsType.setText(getString(R.string.结算分润));
                                break;
                            case "02":
                                tvEarningsType.setText(getString(R.string.单笔分润));
                                break;
                            case "03":
                                tvEarningsType.setText(getString(R.string.机器返现));
                                break;
                            case "04":
                                tvEarningsType.setText(getString(R.string.活动奖励));
                                break;
                        }
                        tvMoney.setText("￥" + bean.getMoney());
                        tvOrderNumber.setText(bean.getOrder_id());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}