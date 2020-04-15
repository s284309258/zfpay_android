package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetUserCardListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.ClearingCentreAdapter;
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

public class ClearingCentreActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClearingCentreAdapter mClearingCentreAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<GetUserCardListBean.DataBean.UserCardListBean> mData = new ArrayList<>();
    private boolean isOpen = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearing_centre);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        rightAction.setText(getString(R.string.添加));
        rightAction.setTextColor(getResources().getColor(R.color.green2));
        customTitle.setText(getString(R.string.结算卡中心));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mClearingCentreAdapter = new ClearingCentreAdapter(this, mData);
        recyclerView.setAdapter(mClearingCentreAdapter);
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
    public void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        mMyService.getUserCardList(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserCardListBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserCardListBean response) {
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
                        mData.clear();
                        mData.addAll(response.getData().getUserCardList());
                        if (mData.size() == 0) {
                            llNoData.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        } else {
                            llNoData.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                        mClearingCentreAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetUserCardListBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this){
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            initData();
        }
    }

    @OnClick({R.id.left_action, R.id.right_action, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                startActivityForResult(new Intent(this, AddBankCardActivity.class), 1);
                break;
            case R.id.tv_add:
                startActivityForResult(new Intent(this, AddBankCardActivity.class), 1);
                break;
        }
    }
}
