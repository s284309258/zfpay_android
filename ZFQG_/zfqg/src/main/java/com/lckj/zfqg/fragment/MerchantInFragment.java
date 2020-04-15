package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosRecordListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosInstallListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosInstallListRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.MerchantInAdapter;
import com.lckj.zfqg.adapter.RateApplyFragmentAdapter;
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
public class MerchantInFragment extends BaseFragment {
    private boolean isPos;
    private String mType;
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
    private MerchantInAdapter mMerchantInAdapter;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    List<GetTraditionalPosInstallListBean.DataBean.TraditionalPosInstallListBean> mData = new ArrayList<>();

    public MerchantInFragment(String type, boolean isPos) {
        super();
        mType = type;
        this.isPos = isPos;
    }


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_merchant_in);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMerchantInAdapter = new MerchantInAdapter(getContext(), mData, mType);
        recyclerView.setAdapter(mMerchantInAdapter);
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
        mMyService.getTraditionalPosInstallList(new GetTraditionalPosInstallListRequest(mType, dataManager.getToken(), last_id, key_word, isPos ? null : "epos"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosInstallListBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosInstallListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetTraditionalPosInstallListBean.DataBean.TraditionalPosInstallListBean> bean = response.getData().getTraditionalPosInstallList();
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(bean);
                        if (mData.size() > 0) last_id = mData.get(mData.size() - 1).getInstall_id();
                        mMerchantInAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetTraditionalPosInstallListBean response) {
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
}
