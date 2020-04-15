package com.lckj.zfqg.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetAppImgListBean;
import com.lckj.jycm.zfqg_network.GetAppImgListRequest;
import com.lckj.jycm.zfqg_network.GetTraditionalPosTradeDetailBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SnRequest;
import com.lckj.zfqg.adapter.MerchantDealInfoAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MerchantDealInfoActivity extends BaseActvity {
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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MerchantDealInfoAdapter mMerchantDealInfoAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mSn;
    private List<GetTraditionalPosTradeDetailBean.DataBean.TraditionalPosTradeDetailBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_deal_info);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText("交易信息");
        mSn = getIntent().getStringExtra("sn");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMerchantDealInfoAdapter = new MerchantDealInfoAdapter(this, mData);
        recyclerView.setAdapter(mMerchantDealInfoAdapter);
    }

    @Override
    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getTraditionalPosTradeDetail(new SnRequest(mSn, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosTradeDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosTradeDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
                        mData.clear();
                        mData.addAll(response.getData().getTraditionalPosTradeDetail());
                        mMerchantDealInfoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetTraditionalPosTradeDetailBean response) {
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

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
