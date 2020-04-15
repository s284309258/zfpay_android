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
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetMposOnlineActivityListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosActivityApplyListBean;
import com.lckj.jycm.zfqg_network.LastIdRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.ActivityOrderListAdapter;
import com.lckj.zfqg.adapter.PosActivityListAdapter;
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
public class PosActivityListFragment extends BaseFragment {
    private final int mType;
    private final int mSubsetType;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private RecyclerView.Adapter mAdapter;
    List<GetMposOnlineActivityListBean.DataBean.MposOnlineActivityListBean> mData = new ArrayList<>();
    List<GetTraditionalPosActivityApplyListBean.DataBean.TraditionalPosActivityApplyListBean> mData2 = new ArrayList<>();
    private boolean isOpen = true;
    public String last_id;
    private String refstatus = "down";

    public PosActivityListFragment(int type, int subsetType) {
        super();
        mType = type;
        mSubsetType = subsetType;
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

    public void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(getContext());
            isOpen = false;
        }
        if (mType == 0) {
            Observable<GetMposOnlineActivityListBean> request = null;
            if (mSubsetType == 0) {
                request = mMyService.getTraditionalPosOnlineActivityList(new TokenRequest(dataManager.getToken()));
            } else  if (mSubsetType == 1) {
                request = mMyService.getMposOnlineActivityList(new TokenRequest(dataManager.getToken()));
            }else  if (mSubsetType ==2) {
                request = mMyService.getTraditionalPosOnlineActivityList(new TokenRequest(dataManager.getToken(), null, null, "epos"));
            }
            request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetMposOnlineActivityListBean>(getContext()) {
                        @Override
                        public void onSuccessCall(GetMposOnlineActivityListBean response) {
                            ProgressDlgHelper.closeDialog();
                            refreshLayout.finishRefresh();
                            mData.clear();
                            mData.addAll(response.getData().getMposOnlineActivityList());
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetMposOnlineActivityListBean response) {
                            super.onFailedCall(response);
                            refreshLayout.finishRefresh();
                        }
                    }, new ThrowableConsumer<Throwable>(this) {
                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
                            refreshLayout.finishRefresh();
                        }
                    });
        } else {
            Observable<GetTraditionalPosActivityApplyListBean> request = null;
            if (mSubsetType == 0) {
                request = mMyService.getTraditionalPosActivityApplyList(new LastIdRequest(dataManager.getToken(), last_id));
            } else if (mSubsetType == 1) {
                request = mMyService.getMposActivityApplyList(new LastIdRequest(dataManager.getToken(), last_id));
            }else if (mSubsetType == 2) {
                request = mMyService.getTraditionalPosActivityApplyList(new LastIdRequest(dataManager.getToken(), last_id, "epos"));
            }
            request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetTraditionalPosActivityApplyListBean>(this) {
                        @Override
                        public void onSuccessCall(GetTraditionalPosActivityApplyListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetTraditionalPosActivityApplyListBean.DataBean.TraditionalPosActivityApplyListBean> userFeedBackList = response.getData().getTraditionalPosActivityApplyList();
                            if (TextUtils.isEmpty(last_id)) mData2.clear();
                            mData2.addAll(userFeedBackList);
                            if (mData2.size() > 0)
                                last_id = mData2.get(mData2.size() - 1).getApply_id();
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetTraditionalPosActivityApplyListBean response) {
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
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mType == 0) {
            mAdapter = new PosActivityListAdapter(getContext(), mData, mSubsetType);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter = new ActivityOrderListAdapter(getContext(), mData2,mSubsetType);
            recyclerView.setAdapter(mAdapter);
        }
    }

    protected void initEvents() {
        if (mType == 0) {
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    initData();
                }
            });
        } else {
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
}
