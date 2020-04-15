package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
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
import com.lckj.jycm.zfqg_network.GetRecallTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.StatusRequest;
import com.lckj.zfqg.adapter.AgreeRefuseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
public class AgreeRefuseFragment extends BaseFragment {
    private final int mType;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private AgreeRefuseAdapter mAgreeRefuseAdapter;
    private int mWho;
    private List<GetRecallTraditionalPosListBean.DataBean.RecallTraditionalPosListBean> mData = new ArrayList<>();

    public AgreeRefuseFragment(int type) {
        super();
        mType = type;
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

    private void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    void initData() {
        if(getContext() == null)return;
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetRecallTraditionalPosListBean> request = null;
        if (mWho == 0) {
            request = mMyService.getRecallTraditionalPosList(new StatusRequest(dataManager.getToken(), mType == 1 ? "09" : "08"));
        } else if (mWho == 1) {
            request = mMyService.getRecallMposList(new StatusRequest(dataManager.getToken(), mType == 1 ? "09" : "08"));
        } else if (mWho == 2) {
            request = mMyService.getRecallTrafficCardList(new StatusRequest(dataManager.getToken(), mType == 1 ? "09" : "08"));
        }else if (mWho == 3) {
            request = mMyService.getRecallEposList(new StatusRequest(dataManager.getToken(), mType == 1 ? "09" : "08"));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetRecallTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetRecallTraditionalPosListBean response) {
                        refreshLayout.finishRefresh();
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        mAgreeRefuseAdapter.setWho(mWho);
                        switch (mWho){
                            case 0:
                                mData.addAll(response.getData().getRecallTraditionalPosList());
                                break;
                            case 1:
                                mData.addAll(response.getData().getRecallMposList());
                                break;
                            case 2:
                                mData.addAll(response.getData().getRecallTrafficCardList());
                                break;
                            case 3:
                                mData.addAll(response.getData().getRecallTraditionalPosList());
                                break;
                        }
                        mAgreeRefuseAdapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAgreeRefuseAdapter = new AgreeRefuseAdapter(getContext(), mData, mType);
        recyclerView.setAdapter(mAgreeRefuseAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    public void setWho(int who) {
        mWho = who;
        initData();
    }
}
