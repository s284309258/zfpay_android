package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetReferAgencyListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SelectPolicy3RecordBean;
import com.lckj.jycm.zfqg_network.TokenRequest2;
import com.lckj.zfqg.adapter.StandardMerchantListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StandardMerchantListActivity extends BaseActvity {
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
    private StandardMerchantListAdapter mStandardMerchantListAdapter;
    private int mType;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isOpen = true;
    private String refstatus = "down";
    List<SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_merchant_list);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("type", 0);
        customTitle.setText(getString(R.string.交易达标商户));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStandardMerchantListAdapter = new StandardMerchantListAdapter(this, mData);
        recyclerView.setAdapter(mStandardMerchantListAdapter);
    }

    @Override
    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refstatus = "down";
                initData();
            }
        });
    }

    @Override
    public void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        String pos_type = null;
        switch (mType) {
            case 5:
                pos_type = "01";
                break;
            case 11:
                pos_type = "02";
                break;
            case 17:
                pos_type = "03";
                break;
        }
        mMyService.selectPolicy3Record(new TokenRequest2(pos_type, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<SelectPolicy3RecordBean>(this) {
                    @Override
                    public void onSuccessCall(SelectPolicy3RecordBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean> bean = response.getData().getMachinesPolicy3Record();
                        mData.clear();
                        mData.addAll(bean);
                        mStandardMerchantListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(SelectPolicy3RecordBean response) {
                        super.onFailedCall(response);
                        finishLoad();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        finishLoad();
                    }
                });
    }

    public void finishLoad() {
        if ("up".equals(refstatus)) refreshLayout.finishLoadMore();
        if ("down".equals(refstatus)) refreshLayout.finishRefresh();
    }


    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
