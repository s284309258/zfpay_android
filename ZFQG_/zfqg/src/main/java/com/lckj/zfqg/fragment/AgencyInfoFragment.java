package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
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
import com.lckj.jycm.zfqg_network.GetCashRecordListBean;
import com.lckj.jycm.zfqg_network.GetReferAgencyTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UserRequest;
import com.lckj.zfqg.adapter.AgencyInfoAdapter;
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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("ValidFragment")
public class AgencyInfoFragment extends BaseFragment {
    private final String mId;
    private int isPos;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private AgencyInfoAdapter mAgencyInfoAdapter;
    List<GetReferAgencyTraditionalPosListBean.DataBean.ReferAgencyTraditionalPosListBean> mData = new ArrayList<>();
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String mType;

    public AgencyInfoFragment(int type, String id) {
        super();
        switch (type) {
            case 0:
                mType = null;
                break;
            case 1:
                mType = "1";
                break;
            case 2:
                mType = "0";
                break;

        }
        mId = id;
    }

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

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAgencyInfoAdapter = new AgencyInfoAdapter(getContext(), mData, isPos);
        recyclerView.setAdapter(mAgencyInfoAdapter);
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

    public void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(getContext());
            isOpen = false;
        }
        Observable<GetReferAgencyTraditionalPosListBean> request = null;
        if (isPos == 0 || isPos == 2) {
            request = mMyService.getReferAgencyTraditionalPosList(new UserRequest(dataManager.getToken(), mId, last_id, mType, isPos == 2 ? "epos" : null));
        } else {
            request = mMyService.getReferAgencyMposList(new UserRequest(dataManager.getToken(), mId, last_id, mType));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetReferAgencyTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetReferAgencyTraditionalPosListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetReferAgencyTraditionalPosListBean.DataBean.ReferAgencyTraditionalPosListBean> userFeedBackList = null;
                        if (isPos == 0 || isPos == 2) {
                            userFeedBackList = response.getData().getReferAgencyTraditionalPosList();
                        } else {
                            userFeedBackList = response.getData().getReferAgencyMposList();
                        }
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(userFeedBackList);
                        if (mData.size() > 0) last_id = mData.get(mData.size() - 1).getTrapos_id();
                        mAgencyInfoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetReferAgencyTraditionalPosListBean response) {
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
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    public void setIsPos(int isPos) {
        this.isPos = isPos;
        last_id = "";
        initData();
        mAgencyInfoAdapter.setIsPos(isPos);
    }
}
