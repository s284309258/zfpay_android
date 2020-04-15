package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.ACache;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetAppImgListBean;
import com.lckj.jycm.zfqg_network.GetAppImgListRequest;
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosRecordListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.MaterialAdapter;
import com.lckj.zfqg.adapter.SpaceItemDecoration;
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
public class MaterialFragment extends BaseFragment {

    private final String mType;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private MaterialAdapter mMaterialAdapter;
    private Unbinder mBind;
    private List<String> mData = new ArrayList<>();

    public MaterialFragment(String type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.layout_recycler_view);
        MainApplication.getInjectGraph().inject(this);
        mBind = ButterKnife.bind(this, view);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mMaterialAdapter = new MaterialAdapter(getActivity(), mData, mType);
        recyclerView.addItemDecoration(new SpaceItemDecoration(Utils.dp2px(getContext(), 15), 3));
        recyclerView.setAdapter(mMaterialAdapter);
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
        mMyService.getAppImgList(new GetAppImgListRequest(dataManager.getToken(), mType))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetAppImgListBean>(this) {
                    @Override
                    public void onSuccessCall(GetAppImgListBean response) {
                        finishLoad();
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        for (int i = 0; i < response.getData().getAppImgList().size(); i++) {
                            mData.add(response.getData().getAppImgList().get(i).getImg_url());
                        }
                        mMaterialAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetAppImgListBean response) {
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
