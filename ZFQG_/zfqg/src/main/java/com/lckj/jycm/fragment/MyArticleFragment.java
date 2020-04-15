package com.lckj.jycm.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.article.adapter.MyArticleAdapter;
import com.lckj.jycm.article.bean.MyarticleData;
import com.lckj.jycm.network.AdvArticleListBean;
import com.lckj.jycm.network.AdvArticleListRequest;
import com.lckj.jycm.network.InfoService;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyArticleFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder bind;
    private ArrayList<AdvArticleListBean.DataBean.ListBean> mDatas = new ArrayList<AdvArticleListBean.DataBean.ListBean>();
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private int status;
    private int page = 1;
    public MyArticleAdapter adapter;
    private String refstatus;
    private int type = 1;
    private int mWho;

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        getIntentParams();
        View view = setContentView(R.layout.frg_my_article);
        bind = ButterKnife.bind(this, view);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        getData(true);
    }



    private void getIntentParams() {
        Bundle arguments = getArguments();
        if (arguments!=null){
            status = arguments.getInt("status",1);
            type = arguments.getInt("type",1);
            mWho = arguments.getInt("who", 1);
        }
    }

    public void getData(boolean loading) {
        if (loading)ProgressDlgHelper.openDialog(getContext());
        infoService.showAdvArticleList(new AdvArticleListRequest(page,status,dataManager.getToken(),type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<AdvArticleListBean>(this) {
                    @Override
                    public void onSuccessCall(AdvArticleListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<AdvArticleListBean.DataBean.ListBean> list = response.getData().getList();
                        if (page == 1)mDatas.clear();
                        mDatas.addAll(list);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(AdvArticleListBean response) {
                        super.onFailedCall(response);
                        finishLoad();
                    }
                }, new ThrowableConsumer<Throwable>(this){
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyArticleAdapter(mDatas, mWho,getActivity());
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refstatus = "down";
                page = 1;
                getData(false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refstatus = "up";
                page++;
                getData(false);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
