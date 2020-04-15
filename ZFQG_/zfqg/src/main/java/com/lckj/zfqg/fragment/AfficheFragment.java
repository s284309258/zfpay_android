package com.lckj.zfqg.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetMessageRecordListBean;
import com.lckj.jycm.zfqg_network.GetNewNoticeBean;
import com.lckj.jycm.zfqg_network.GetNoticeListBean;
import com.lckj.jycm.zfqg_network.LastIdRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.PageRequest;
import com.lckj.zfqg.adapter.AfficheAdapter;
import com.lckj.zfqg.adapter.InformAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AfficheFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private AfficheAdapter mAfficheAdapter;
    List<GetNewNoticeBean.DataBean.NoticeInfoListBean> mData = new ArrayList<>();
    private boolean isOpen = true;
    private int mPage = 1;
    private String refstatus = "down";

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.layout_recycler_view);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }


    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refstatus = "down";
                mPage = 1;
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

    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(getContext());
            isOpen = false;
        }
        mMyService.getNoticeList(new PageRequest(dataManager.getToken(), mPage + ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetNewNoticeBean>(this) {
                    @Override
                    public void onSuccessCall(GetNewNoticeBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        if (mPage == 1) mData.clear();
                        mPage++;
                        mData.addAll(response.getData().getNoticeInfoList());
                        mAfficheAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetNewNoticeBean response) {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAfficheAdapter = new AfficheAdapter(getContext(), mData);
        recyclerView.setAdapter(mAfficheAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
