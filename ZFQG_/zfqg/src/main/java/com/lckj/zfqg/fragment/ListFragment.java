package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.zfqg_network.DateRequest;
import com.lckj.jycm.zfqg_network.GetActivityRewardTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetDeductTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetMachineBackTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetShareBenefitTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.DetailRecordAdapter;
import com.lckj.zfqg.adapter.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("ValidFragment")
public class ListFragment<E> extends BaseFragment {
    private final MyService mMyService;
    private final DataManager dataManager;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    private DetailRecordAdapter mDetailRecordAdapter;
    private int mType;
    private String mDate;
    List<GetShareBenefitTraditionalPosListBean.DataBean.ShareBenefitTraditionalPosListBean> mData = new ArrayList<>();
    List<E> mBaseData = new ArrayList<>();
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private List<GetMachineBackTraditionalPosListBean.DataBean.MachineBackTraditionalPosListBean> mData2 = new ArrayList<>();
    private List<GetActivityRewardTraditionalPosListBean.DataBean.ActivityRewardTraditionalPosListBean> mData3 = new ArrayList<>();
    private List<GetDeductTraditionalPosListBean.DataBean.DeductTraditionalPosListBean> mData4 = new ArrayList<>();

    public ListFragment(MyService myService, DataManager dataManager, int type, String date) {
        mMyService = myService;
        this.dataManager = dataManager;
        mType = type;
        mDate = date;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.layout_recycler_view);
        mBind = ButterKnife.bind(this, view);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDetailRecordAdapter = new DetailRecordAdapter(getContext(), mBaseData, mType);
        recyclerView.addItemDecoration(new SpaceItemDecoration(30, 0));
        recyclerView.setAdapter(mDetailRecordAdapter);
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

    //新增EPOS
    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(getContext());
            isOpen = false;
        }
        if (mType == 0 || mType == 4 || mType == 8) {
            Observable<GetShareBenefitTraditionalPosListBean> request = null;
            if (mType == 0) {
                request = mMyService.getShareBenefitTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            } else if (mType == 8) {
                request = mMyService.getShareBenefitEposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            } else {
                request = mMyService.getShareBenefitMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            }
            request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetShareBenefitTraditionalPosListBean>(this) {
                        @Override
                        public void onSuccessCall(GetShareBenefitTraditionalPosListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetShareBenefitTraditionalPosListBean.DataBean.ShareBenefitTraditionalPosListBean> userFeedBackList;
                            if (mType == 0 || mType == 8) {
                                userFeedBackList = response.getData().getShareBenefitTraditionalPosList();
                            } else {
                                userFeedBackList = response.getData().getShareBenefitMposList();
                            }
                            if (TextUtils.isEmpty(last_id)) mData.clear();
                            mData.addAll(userFeedBackList);
                            mBaseData.clear();
                            mBaseData.addAll((Collection<? extends E>) mData);
                            if (mData.size() > 0)
                                last_id = mData.get(mData.size() - 1).getRecord_id();
                            mDetailRecordAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetShareBenefitTraditionalPosListBean response) {
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
        } else if (mType == 1 || mType == 5 || mType == 9) {
            Observable<GetMachineBackTraditionalPosListBean> request = null;
            if (mType == 1) {
                request = mMyService.getMachineBackTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            } else if (mType == 9) {
                request = mMyService.getMachineBackEposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            } else {
                request = mMyService.getMachineBackMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            }
            request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetMachineBackTraditionalPosListBean>(this) {
                        @Override
                        public void onSuccessCall(GetMachineBackTraditionalPosListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetMachineBackTraditionalPosListBean.DataBean.MachineBackTraditionalPosListBean> userFeedBackList;
                            if (mType == 1 || mType == 9) {
                                userFeedBackList = response.getData().getMachineBackTraditionalPosList();
                            } else {
                                userFeedBackList = response.getData().getMachineBackMposList();
                            }
                            if (TextUtils.isEmpty(last_id)) mData2.clear();
                            mData2.addAll(userFeedBackList);
                            mBaseData.clear();
                            mBaseData.addAll((Collection<? extends E>) mData2);
                            if (mData2.size() > 0)
                                last_id = mData2.get(mData2.size() - 1).getRecord_id();
                            mDetailRecordAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetMachineBackTraditionalPosListBean response) {
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
        } else if (mType == 2 || mType == 6 || mType == 10) {
            Observable<GetActivityRewardTraditionalPosListBean> request = null;
            if (mType == 2) {
                request = mMyService.getActivityRewardTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
            } else if (mType == 10) {
                request = mMyService.getActivityRewardEposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken()));
            } else {
                request = mMyService.getActivityRewardMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            }
            request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetActivityRewardTraditionalPosListBean>(this) {
                        @Override
                        public void onSuccessCall(GetActivityRewardTraditionalPosListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetActivityRewardTraditionalPosListBean.DataBean.ActivityRewardTraditionalPosListBean> userFeedBackList;
                            if (mType == 2 || mType == 10) {
                                userFeedBackList = response.getData().getActivityRewardTraditionalPosList();
                            } else {
                                userFeedBackList = response.getData().getActivityRewardMposList();
                            }
                            if (TextUtils.isEmpty(last_id)) mData3.clear();
                            mData3.addAll(userFeedBackList);
                            mBaseData.clear();
                            mBaseData.addAll((Collection<? extends E>) mData3);
                            if (mData3.size() > 0)
                                last_id = mData3.get(mData3.size() - 1).getRecord_id();
                            mDetailRecordAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetActivityRewardTraditionalPosListBean response) {
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
        } else if (mType == 3 || mType == 7 || mType == 11) {
            Observable<GetDeductTraditionalPosListBean> request = null;
            if (mType == 3) {
                request = mMyService.getDeductTraditionalPosList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            } else if (mType == 11) {
                request = mMyService.getDeductEposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            } else {
                request = mMyService.getDeductMposList(new DateRequest(mDate.replace("-", ""), dataManager.getToken(), last_id));
            }
            request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetDeductTraditionalPosListBean>(this) {
                        @Override
                        public void onSuccessCall(GetDeductTraditionalPosListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetDeductTraditionalPosListBean.DataBean.DeductTraditionalPosListBean> userFeedBackList;
                            if (mType == 3 || mType == 11) {
                                userFeedBackList = response.getData().getDeductTraditionalPosList();
                            } else {
                                userFeedBackList = response.getData().getDeductMposList();
                            }
                            if (TextUtils.isEmpty(last_id)) mData4.clear();
                            mData4.addAll(userFeedBackList);
                            mBaseData.clear();
                            mBaseData.addAll((Collection<? extends E>) mData4);
                            if (mData4.size() > 0)
                                last_id = mData4.get(mData4.size() - 1).getRecord_id();
                            mDetailRecordAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetDeductTraditionalPosListBean response) {
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
