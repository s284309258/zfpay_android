package com.lckj.zfqg.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetUserFeedBackListBean;
import com.lckj.jycm.zfqg_network.LastIdRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.FeedbackRecordAdapter;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FeedbackRecordActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Inject
    DataManager dataManager;
    @Inject
    MyService myService;
    private String last_id;
    private ArrayList<GetUserFeedBackListBean.DataBean.UserFeedBackListBean> mDatas = new ArrayList();
    private FeedbackRecordAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_record);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        ProgressDlgHelper.openDialog(this);
        getDatas();
    }

    private void getDatas() {
        myService.getUserFeedBackList(new LastIdRequest(dataManager.getToken(),last_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserFeedBackListBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserFeedBackListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoading();
                        List<GetUserFeedBackListBean.DataBean.UserFeedBackListBean> userFeedBackList = response.getData().getUserFeedBackList();
                        if (TextUtils.isEmpty(last_id))mDatas.clear();
                        mDatas.addAll(userFeedBackList);
                        if (mDatas.size()>0)last_id = mDatas.get(mDatas.size()-1).getId();
                        adapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this){
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        finishLoading();

                    }
                });

    }

    private void finishLoading() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    protected void initView() {
        customTitle.setText(R.string.反馈记录);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FeedbackRecordAdapter(mDatas);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = dip2px(10);
            }
        });
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                last_id = null;
                getDatas();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getDatas();
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
