package com.lckj.jycm.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.InvestAdActivity;
import com.lckj.jycm.home.TaskAdatper;
import com.lckj.jycm.home.TaskSignAdatper;
import com.lckj.jycm.network.AdvInfoListBean;
import com.lckj.jycm.network.AdvInfoListRequest;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.network.SignInfoBean;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.widget.CustomDialog;
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
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TaskFragment extends BaseFragment {
    @BindView(R.id.tv_invest_ad)
    TextView tvInvestAd;
    @BindView(R.id.tv_sign_days)
    TextView tvSignDays;
    @BindView(R.id.rv_sign)
    RecyclerView rvSign;
    @BindView(R.id.rv_task)
    RecyclerView rvTask;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    InfoService mInfoService;
    @Inject
    DataManager dataManager;
    private int mPage = 1;
    List<AdvInfoListBean.DataBean.ListBean> mData = new ArrayList<>();
    List<SignInfoBean.DataBean.SignListBean> mSignList = new ArrayList<>();
    private TaskSignAdatper mTaskSignAdatper;
    private TaskAdatper mTaskAdatper;
    List<SignInfoBean.DataBean> mSign = new ArrayList<>();
    private String refstatus = "down";

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frg_task);
        MainApplication.getInstance().getInjectGraph().inject(this);
        mBind = ButterKnife.bind(this, view);
        initView();
        initEvents();
        initData();
    }

    private void initEvents() {
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

    private void initData() {
        if (refstatus.equals("down")) {
            mFrontUserService.showSignInfo(new TokenRequest(dataManager.getToken(), "showSignInfo"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<SignInfoBean>(this) {
                        @Override
                        public void onSuccessCall(SignInfoBean response) {
                            finishLoad();
                            if (response.isSuccess()) {
                                if (dataManager.getSignInfo() != response.getData().getSignSum()) {
                                    dataManager.setSignInfo(response.getData().getSignSum());
                                    String gold;
                                    if (response.getData().getSignSum() + 1 > response.getData().getSignList().size()) {
                                        gold = response.getData().getSignList().get(response.getData().getSignList().size() - 1).getGold();
                                    } else {
                                        gold = response.getData().getSignList().get(response.getData().getSignSum()).getGold();
                                    }
                                    showDialog(gold);
                                }
                                if (mPage == 1) {
                                    mSign.clear();
                                    mSignList.clear();
                                }
                                mSign.add(response.getData());
                                tvSignDays.setText(getString(R.string.task_sign_days, response.getData().getSignSum() + 1 + ""));
                                mSignList.addAll(response.getData().getSignList());
                                mTaskSignAdatper.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailedCall(SignInfoBean response) {
                            super.onFailedCall(response);
                            refreshLayout.finishRefresh();
                        }
                    }, new ThrowableConsumer<Throwable>(this) {
                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
                            finishLoad();
                        }
                    });
        }

        mInfoService.showAppAdvInfoList(new AdvInfoListRequest(mPage, 2, 2, dataManager.getToken()))
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
                            mTaskAdatper.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailedCall(AdvInfoListBean response) {
                        super.onFailedCall(response);
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSign.setLayoutManager(linearLayoutManager);
        mTaskSignAdatper = new TaskSignAdatper(getContext(), mSignList, mSign);
        rvSign.setAdapter(mTaskSignAdatper);
        rvSign.addItemDecoration(new SpaceItemDecoration(60));
        rvTask.setLayoutManager(new LinearLayoutManager(getContext()));
        mTaskAdatper = new TaskAdatper(getContext(), mData);
        rvTask.setAdapter(mTaskAdatper);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick(R.id.tv_invest_ad)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), InvestAdActivity.class));
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildPosition(view) != 0)
                outRect.left = space;
        }
    }

    private void showDialog(String money) {
        final CustomDialog customDialog = new CustomDialog(R.layout.dialog_sign, getActivity(), R.style.BottomDialog2);
        customDialog.show();
        customDialog.setData(money);
        new Handler() {
            public void handleMessage(Message msg) {
                customDialog.dismiss();
            }
        }.sendEmptyMessageDelayed(0, 1500);
    }
}
