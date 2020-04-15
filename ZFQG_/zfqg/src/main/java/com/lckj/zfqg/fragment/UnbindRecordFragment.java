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
import com.lckj.jycm.zfqg_network.GetMposListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosUnbindRecordListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.UnbindRecordAdapter;
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
public class UnbindRecordFragment extends BaseFragment {
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
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    private UnbindRecordAdapter mUnbindRecordAdapter;
    List<GetTraditionalPosUnbindRecordListBean.DataBean.TraditionalPosUnbindRecordListBean> mData = new ArrayList<>();

    public UnbindRecordFragment(int type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_unbind_record);
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
        Observable<GetTraditionalPosUnbindRecordListBean> request;
        if (mType == 0) {
            request = mMyService.getMposUnbindRecordList(new TokenRequest(dataManager.getToken(), key_word, last_id));
        } else if (mType == 1) {
            request = mMyService.getTraditionalPosUnbindRecordList(new TokenRequest(dataManager.getToken(), key_word, last_id));
        } else {
            request = mMyService.getTraditionalPosUnbindRecordList(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosUnbindRecordListBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosUnbindRecordListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetTraditionalPosUnbindRecordListBean.DataBean.TraditionalPosUnbindRecordListBean> userFeedBackList;
                        if (mType == 0) {
                            userFeedBackList = response.getData().getMposUnbindRecordList();
                        } else {
                            userFeedBackList = response.getData().getTraditionalPosUnbindRecordList();
                        }
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(userFeedBackList);
                        if (mData.size() > 0) last_id = mData.get(mData.size() - 1).getUnbind_id();
                        mUnbindRecordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetTraditionalPosUnbindRecordListBean response) {
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
        mUnbindRecordAdapter = new UnbindRecordAdapter(getContext(), mData);
        recyclerView.setAdapter(mUnbindRecordAdapter);
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
