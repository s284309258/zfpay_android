package com.lckj.jycm.center.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.center.adapter.MyTeamAdapter;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.TeamListBean;
import com.lckj.jycm.network.TeamListRequest;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyTeamActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.custom_title)
    TextView customTitle;
    private ArrayList<TeamListBean.DataBean.ListBean> mDatas = new ArrayList<>();
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;
    private int page = 1;
    private String refstatus = "down";
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }
    @Override
    protected void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyTeamAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvents() {
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                page++;
                initData();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                page = 1;
                initData();
            }
        });
    }

    public void finishLoad() {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mFrontUserService.getTeamList(new TeamListRequest(page, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<TeamListBean>(this) {
                    @Override
                    public void onSuccessCall(TeamListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<TeamListBean.DataBean.ListBean> list = response.getData().getList();

                        customTitle.setText(String.format("我的团队（%s人）", response.getData().getCount() + ""));
                        response.getData().getList();
                        if (page == 1) mDatas.clear();
                        mDatas.addAll(list);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(TeamListBean response) {
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

    @OnClick({R.id.left_action, R.id.tv_invate_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_invate_friend:
                invateFriend();
                break;
        }
    }

    private void invateFriend() {
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        Platform.ShareParams shareParams = new  Platform.ShareParams();
        shareParams.setText("帮我点一下吧\uD83D\uDE4F\uD83C\uDFFB你也有钱拿\uD83D\uDCB0\uD83D\uDCB0\uD83D\uDCB0 http://www.rxhwl.com/share/indexs.html?invtCode="+dataManager.getInvitationCode());
        shareParams.setTitle("");
        shareParams.setShareType(Platform.SHARE_TEXT);
        platform.setPlatformActionListener(new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                arg2.printStackTrace();
            }

            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                Toast.makeText(MyTeamActivity.this, "分享成功~", Toast.LENGTH_SHORT).show();
            }

            public void onCancel(Platform arg0, int arg1) {
                Toast.makeText(MyTeamActivity.this, "分享取消~", Toast.LENGTH_SHORT).show();
            }
        });
        platform.share(shareParams);
    }
}
