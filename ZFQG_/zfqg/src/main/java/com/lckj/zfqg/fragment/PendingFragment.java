package com.lckj.zfqg.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetMposRecallListBean;
import com.lckj.jycm.zfqg_network.GetRecallTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.StatusRequest;
import com.lckj.zfqg.activity.RecallActivity;
import com.lckj.zfqg.adapter.PendingAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PendingFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.tv_refuse)
    TextView tvRefuse;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isAll;
    private PendingAdapter mPendingAdapter;
    private int mWho;
    private List<GetRecallTraditionalPosListBean.DataBean.RecallTraditionalPosListBean> mData = new ArrayList<>();
    private String mSn = "";

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_pending);
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

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPendingAdapter = new PendingAdapter(getContext(), mData);
        recyclerView.setAdapter(mPendingAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.tv_all, R.id.tv_agree, R.id.tv_refuse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                isAll = !isAll;
                tvAll.setSelected(isAll);
                mPendingAdapter.setAll(isAll);
                mPendingAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_agree:
                dispose("09");
                break;
            case R.id.tv_refuse:
                dispose("08");
                break;
        }
    }

    private void dispose(String status) {
        mSn = "";
        for (int i = 0; i < mData.size(); i++) {
            if (mPendingAdapter.mMap.get(i)) {
                if (TextUtils.isEmpty(mSn)) {
                    mSn += mData.get(i).getRecall_id();
                } else {
                    mSn += "," + mData.get(i).getRecall_id();
                }
            }
        }
        if (TextUtils.isEmpty(mSn)) {
            switch (mWho) {
                case 0:
                    Utils.showMsg(getContext(), getString(R.string.请选择需要处理的POS));
                    break;
                case 1:
                    Utils.showMsg(getContext(), getString(R.string.请选择需要处理的MPOS));
                    break;
                case 2:
                    Utils.showMsg(getContext(), getString(R.string.请选择需要处理的流量卡));
                    break;
            }
            return;
        }
        ProgressDlgHelper.openDialog(getContext());
        Observable<HttpResponse> request = null;
        if (mWho == 0) {
            request = mMyService.dealRecallTraditionalPos(new StatusRequest(dataManager.getToken(), status, mSn));
        } else if (mWho == 1) {
            request = mMyService.dealRecallMpos(new StatusRequest(dataManager.getToken(), status, mSn));
        } else if (mWho == 2) {
            request = mMyService.dealRecallTrafficCard(new StatusRequest(dataManager.getToken(), status, mSn));
        } else if (mWho == 3) {
            request = mMyService.dealRecallTraditionalPos(new StatusRequest(dataManager.getToken(), status, mSn, "epos"));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        initData();
                        RecallActivity activity = (RecallActivity) getActivity();
                        activity.mAgreeRefuseFragment.initData();
                        activity.mAgreeRefuseFragment1.initData();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void setWho(int who) {
        mWho = who;
        initData();
    }

    private void initData() {
        if (getContext() == null) return;
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetRecallTraditionalPosListBean> request = null;
        if (mWho == 0) {
            request = mMyService.getRecallTraditionalPosList(new StatusRequest(dataManager.getToken(), "00"));
        } else if (mWho == 1) {
            request = mMyService.getRecallMposList(new StatusRequest(dataManager.getToken(), "00"));
        } else if (mWho == 2) {
            request = mMyService.getRecallTrafficCardList(new StatusRequest(dataManager.getToken(), "00"));
        } else if (mWho == 3) {
            request = mMyService.getRecallEposList(new StatusRequest(dataManager.getToken(), "00"));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetRecallTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetRecallTraditionalPosListBean response) {
                        refreshLayout.finishRefresh();
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        mPendingAdapter.mMap.clear();
                        tvAll.setSelected(isAll = false);
                        mPendingAdapter.setWho(mWho);
                        switch (mWho) {
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
                        mPendingAdapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
}
