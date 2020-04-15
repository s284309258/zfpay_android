package com.lckj.zfqg.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetMposListBean;
import com.lckj.jycm.zfqg_network.GetPosActivityApplyListDetailBean;
import com.lckj.jycm.zfqg_network.GetPosActivityApplyListDetailRequest;
import com.lckj.jycm.zfqg_network.GetTraditionalPosActivityApplyListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.ActivityOrderDetailsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActivityOrderDetailsActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_type)
    TextView tvOrderType;
    @BindView(R.id.tv_award_type)
    TextView tvAwardType;
    @BindView(R.id.tv_period)
    TextView tvPeriod;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_open)
    TextView tvOpen;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isOpen;
    private ActivityOrderDetailsAdapter mActivityOrderDetailsAdapter;
    private int mSubsetType;
    private String mId;
    private List<String> mSnList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.活动订单详情));
        mSubsetType = getIntent().getIntExtra("subsetType", 0);
        mId = getIntent().getStringExtra("id");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mActivityOrderDetailsAdapter = new ActivityOrderDetailsAdapter(this, mSnList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mActivityOrderDetailsAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        Observable<GetPosActivityApplyListDetailBean> request = null;
        if (mSubsetType == 0 || mSubsetType == 2) {
            request = mMyService.getTraditionalPosActivityApplyDetail(new GetPosActivityApplyListDetailRequest(dataManager.getToken(), mId));
        } else {
            request = mMyService.getMposActivityApplyDetail(new GetPosActivityApplyListDetailRequest(dataManager.getToken(), mId));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetPosActivityApplyListDetailBean>(this) {
                    @SuppressLint("StringFormatInvalid")
                    @Override
                    public void onSuccessCall(GetPosActivityApplyListDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (mSubsetType == 0 || mSubsetType == 2) {
                            GetPosActivityApplyListDetailBean.DataBean.TraditionalPosActivityApplyBean bean = response.getData().getTraditionalPosActivityApply();
                            tvOrderNumber.setText(bean.getOrder_id());
                            tvAwardType.setText(getString(R.string.库存台交易额达到万返现元, bean.getPos_num(), bean.getExpenditure(), bean.getReward_money()));
                            switch (bean.getStatus()) {
                                case "00":
                                    tvStatus.setText(getString(R.string.审核中));
                                    btnCancel.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenhezhong);
                                    break;
                                case "04":
                                    tvStatus.setText(getString(R.string.取消活动));
                                    btnCancel.setVisibility(View.GONE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenheshibai);
                                    break;
                                case "08":
                                    tvStatus.setText(getString(R.string.审核失败));
                                    btnCancel.setVisibility(View.GONE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenheshibai);
                                    break;
                                case "09":
                                    tvStatus.setText(getString(R.string.已通过));
                                    btnCancel.setVisibility(View.GONE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenhechenggong);
                                    break;
                            }
                            tvOrderType.setText(bean.getActivity_name());
                            tvTime.setText(bean.getCre_datetime());
                            tvPeriod.setText(bean.getStart_date() + "-" + bean.getEnd_date());
                            String[] split = bean.getSn_list().split(",");
                            mSnList.addAll(Arrays.asList(split));
                            tvNumber.setText(getString(R.string.参与活动的SN码, mSnList.size() + ""));
                        } else {
                            GetPosActivityApplyListDetailBean.DataBean.TraditionalPosActivityApplyBean bean = response.getData().getMposActivityApply();
                            tvOrderNumber.setText(bean.getOrder_id());
                            tvAwardType.setText(getString(R.string.库存台交易额达到万返现元, bean.getPos_num(), bean.getExpenditure(), bean.getReward_money()));
                            switch (bean.getStatus()) {
                                case "00":
                                    tvStatus.setText(getString(R.string.审核中));
                                    btnCancel.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenhezhong);
                                    break;
                                case "04":
                                    tvStatus.setText(getString(R.string.取消活动));
                                    btnCancel.setVisibility(View.GONE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenheshibai);
                                    break;
                                case "08":
                                    tvStatus.setText(getString(R.string.审核失败));
                                    btnCancel.setVisibility(View.GONE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenheshibai);
                                    break;
                                case "09":
                                    tvStatus.setText(getString(R.string.已通过));
                                    btnCancel.setVisibility(View.GONE);
                                    ivStatus.setImageResource(R.mipmap.icon_shenhechenggong);
                                    break;
                            }
                            tvOrderType.setText(bean.getActivity_name());
                            tvTime.setText(bean.getCre_datetime());
                            tvPeriod.setText(bean.getStart_date() + "-" + bean.getEnd_date());
                            String[] split = bean.getSn_list().split(",");
                            mSnList.addAll(Arrays.asList(split));
                            tvNumber.setText(getString(R.string.参与活动的SN码, mSnList.size() + ""));
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.tv_open, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_open:
                isOpen = !isOpen;
                if (isOpen) {
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_cancel:
                cancel();
                break;
        }
    }

    private void cancel() {
        ProgressDlgHelper.openDialog(this);
        Observable<HttpResponse> request = null;
        if (mSubsetType == 0 || mSubsetType == 2) {
            request = mMyService.cancelTraditionalPosActivityApply(new GetPosActivityApplyListDetailRequest(dataManager.getToken(), mId));
        } else {
            request = mMyService.cancelMposActivityApply(new GetPosActivityApplyListDetailRequest(dataManager.getToken(), mId));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        Utils.showMsg(ActivityOrderDetailsActivity.this, getString(R.string.取消活动成功));
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
}
