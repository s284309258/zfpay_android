package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetCashRecordListBean;
import com.lckj.jycm.zfqg_network.GetMoneyLockerCollegeListBean;
import com.lckj.jycm.zfqg_network.LastIdRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.CoffersSchoolAdapter;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CoffersSchoolActivity extends BaseActvity {
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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private CoffersSchoolAdapter mCoffersSchoolAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<GetMoneyLockerCollegeListBean.DataBean.MoneyLockerCollegeListBean> mData = new ArrayList<>();
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffers_school);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.钱柜学院));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCoffersSchoolAdapter = new CoffersSchoolAdapter(this, mData);
        recyclerView.setAdapter(mCoffersSchoolAdapter);

    }

    @Override
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

    @Override
    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        mMyService.getMoneyLockerCollegeList(new LastIdRequest(dataManager.getToken(), last_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetMoneyLockerCollegeListBean>(this) {
                    @Override
                    public void onSuccessCall(GetMoneyLockerCollegeListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetMoneyLockerCollegeListBean.DataBean.MoneyLockerCollegeListBean> userFeedBackList = response.getData().getMoneyLockerCollegeList();
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(userFeedBackList);
                        if (mData.size() > 0)
                            last_id = mData.get(mData.size() - 1).getMoney_locker_id();
                        mCoffersSchoolAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetMoneyLockerCollegeListBean response) {
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
