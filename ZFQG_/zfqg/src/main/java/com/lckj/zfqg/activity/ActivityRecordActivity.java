package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetMposOnlineActivityListBean;
import com.lckj.jycm.zfqg_network.GetMposRewardRecordListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosActivityApplyListBean;
import com.lckj.jycm.zfqg_network.LastIdRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.ActivityRecordAdapter;
import com.lckj.zfqg.adapter.SpaceItemDecoration;
import com.lckj.zfqg.adapter.WithdrawalRecordAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActivityRecordActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ActivityRecordAdapter mActivityRecordAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<GetMposRewardRecordListBean.DataBean.MposRewardRecordListBean> mData = new ArrayList<>();
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private int mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.活动奖励明细));
        mType = getIntent().getIntExtra("type", 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mActivityRecordAdapter = new ActivityRecordAdapter(this, mData);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30, 0));
        recyclerView.setAdapter(mActivityRecordAdapter);
    }

    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refstatus = "down";
                last_id = "";
                initData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refstatus = "up";
                initData();
            }
        });
    }

    public void finishLoad() {
        if ("up".equals(refstatus)) refreshLayout.finishLoadMore();
        if ("down".equals(refstatus)) refreshLayout.finishRefresh();
    }

    @Override
    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        Observable<GetMposRewardRecordListBean> request = null;
        if (mType == 0 || mType == 2) {
            request = mMyService.getTraditionalPosRewardRecordList(new LastIdRequest(dataManager.getToken(), last_id, mType == 2 ? "epos" : null));
        } else {
            request = mMyService.getMposRewardRecordList(new LastIdRequest(dataManager.getToken(), last_id));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetMposRewardRecordListBean>(this) {
                    @Override
                    public void onSuccessCall(GetMposRewardRecordListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetMposRewardRecordListBean.DataBean.MposRewardRecordListBean> userFeedBackList = response.getData().getMposRewardRecordList();
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(userFeedBackList);
                        if (mData.size() > 0)
                            last_id = mData.get(mData.size() - 1).getRecord_id();
                        mActivityRecordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetMposRewardRecordListBean response) {
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

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
