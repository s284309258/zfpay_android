package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetAllocationTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosUnbindRecordListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SelectPosBatchAllocateBean;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.AllocationRecordAdapter;
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
public class AllocationRecordFragment extends BaseFragment {
    private final int mType;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private AllocationRecordAdapter mAllocationRecordAdapter;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    List<SelectPosBatchAllocateBean.DataBean.AllocateListBean> mData = new ArrayList<>();

    public AllocationRecordFragment(int type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_allocation_record);
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

    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(getContext());
            isOpen = false;
        }
        Observable<SelectPosBatchAllocateBean> request = null;
        if (mType == 0) {
            request = mMyService.selectPosBatchAllocate(new TokenRequest(dataManager.getToken(), key_word, last_id, "MPOS"));
        } else if (mType == 1) {
            request = mMyService.selectPosBatchAllocate(new TokenRequest(dataManager.getToken(), key_word, last_id, "TraditionalPOS"));
        } else if (mType == 2) {
            request = mMyService.selectPosBatchAllocate(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
        } else {
            request = mMyService.selectPosBatchAllocate(new TokenRequest(dataManager.getToken(), key_word, last_id, "TrafficCard"));

        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<SelectPosBatchAllocateBean>(this) {
                    @Override
                    public void onSuccessCall(SelectPosBatchAllocateBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<SelectPosBatchAllocateBean.DataBean.AllocateListBean> userFeedBackList;
                        if (mType == 0) {
                            userFeedBackList = response.getData().getAllocateList();
                        } else if (mType == 1) {
                            userFeedBackList = response.getData().getAllocateList();
                        } else {
                            userFeedBackList = response.getData().getAllocateList();
                        }
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(userFeedBackList);
                        if (mData.size() > 0)
                            last_id = mData.get(mData.size() - 1).getId();
                        mAllocationRecordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(SelectPosBatchAllocateBean response) {
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
        mAllocationRecordAdapter = new AllocationRecordAdapter(getContext(), mType, mData);
        recyclerView.setAdapter(mAllocationRecordAdapter);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    key_word = etSearch.getText().toString().trim();
                    last_id = "";
                    Utils.hideKeyboard(etSearch);
                    initData();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}