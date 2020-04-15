package com.lckj.jycm.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.adapter.MyAdAdatper;
import com.lckj.jycm.network.AdvInfoListBean;
import com.lckj.jycm.network.AdvInfoListRequest;
import com.lckj.jycm.network.InfoService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyAdFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder bind;
    @Inject
    InfoService mInfoService;
    @Inject
    DataManager dataManager;
    private int status;
    private MyAdAdatper mMyAdAdatper;
    private String refstatus;
    private int type;
    private int mPage = 1;
    private ArrayList<AdvInfoListBean.DataBean.ListBean> mData = new ArrayList<>();

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        getIntentParams();
        View view = setContentView(R.layout.frg_my_article);
        bind = ButterKnife.bind(this, view);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        getData();
    }

    private void getIntentParams() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            status = arguments.getInt("status", 1);
            type = arguments.getInt("type", 0);
        }
    }

    private void getData() {
        mInfoService.showAppAdvInfoList(new AdvInfoListRequest(mPage, 1, status, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<AdvInfoListBean>(this) {
                    @Override
                    public void onSuccessCall(AdvInfoListBean response) {
                        finishLoad();
                        if (response.isSuccess() && response.getData().getList() != null) {
                            if (mPage == 1) mData.clear();
                            mPage = response.getData().getPage().getCurrentPageNo();
                            mPage++;
                            mData.addAll(response.getData().getList());
                            mMyAdAdatper.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailedCall(AdvInfoListBean response) {
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

    @Override
    protected void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyAdAdatper = new MyAdAdatper(mData, type, getActivity());
        recyclerView.setAdapter(mMyAdAdatper);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refstatus = "down";
                mPage = 1;
                getData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refstatus = "up";
                getData();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}