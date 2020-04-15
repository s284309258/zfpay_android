package com.lckj.jycm.article.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.TaskAdatper;
import com.lckj.jycm.network.AdvInfoListBean;
import com.lckj.jycm.network.AdvInfoListRequest;
import com.lckj.jycm.network.InfoService;
import com.lckj.lckjlib.share.ShareDialog;
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

public class SelectTaskActivity extends BaseActvity {
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_share)
    TextView tvShare;
    private TaskAdatper adapter;
    String url;
    String title;
    String img;
    private String advUrl = "https://mp.weixin.qq.com/s/m7vWM9wq6zYXqJF0KqWafeffeffw";
    private List<AdvInfoListBean.DataBean.ListBean> mDatas = new ArrayList<>();

    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private int page = 1;
    private int arid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParams();
        setContentView(R.layout.activity_select_task);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        getDatas();
    }

    private void getDatas() {
        ProgressDlgHelper.openDialog(this);
        infoService.showAppAdvInfoList(new AdvInfoListRequest(page,2,2,dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<AdvInfoListBean>(this) {
                    @Override
                    public void onSuccessCall(AdvInfoListBean response) {
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                        List<AdvInfoListBean.DataBean.ListBean> list = response.getData().getList();
                        if (page ==1) {
                            mDatas.clear();
                        }
                        mDatas.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(AdvInfoListBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                }, new ThrowableConsumer<Throwable>(this){
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadMore();
                    }
                });

    }

    private void getIntentParams() {
        Intent intent = getIntent();
         url = intent.getStringExtra("url");
         title = intent.getStringExtra("title");
         img = intent.getStringExtra("img");
        arid = intent.getIntExtra("arid", 0);
    }

    @Override
    protected void initView() {
        customTitle.setText(R.string.选择任务);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter  = new TaskAdatper(this,mDatas);
        adapter.setSelect(true);
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                getDatas();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
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

    @OnClick({R.id.left_action, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_share:
                AdvInfoListBean.DataBean.ListBean selectItem = adapter.getSelectItem();
                if (selectItem!=null){
                    String adimgUrl;
                    if (!selectItem.getAdvImg().startsWith("http")) {
                        adimgUrl = ProviderModule.getDataManager(this).getQiniuDomain() + "/" + selectItem.getAdvImg();
                    } else {
                        adimgUrl = selectItem.getAdvImg();
                    }
                    String shareUrl = String.format("http://www.rxhwl.com/shareAdv.html?str1=%1$s&str2=%2$s&str3=%3$s", url, adimgUrl, selectItem.getAdvUrl());
                    ShareDialog.createDialog(this,shareUrl,title,img, arid, selectItem.getId()).show();
                }
                break;
        }
    }
}
