package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetNoticeDetailBean;
import com.lckj.jycm.zfqg_network.GetNoticeDetailRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AfficheDetailsActivity extends BaseActvity {
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String mId;
    private ImageAdapter mImageAdapter;
    List<String> mImageList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_details);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mId = getIntent().getStringExtra("id");
        customTitle.setText(getString(R.string.公告详情));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mImageAdapter = new ImageAdapter(this, mImageList);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        recyclerView.setAdapter(mImageAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getNoticeDetail(new GetNoticeDetailRequest(dataManager.getToken(), mId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetNoticeDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetNoticeDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        GetNoticeDetailBean.DataBean.NoticeInfoBean bean = response.getData().getNoticeInfo();
                        tvTitle.setText(bean.getNotice_title());
                        mImageList.clear();
                        mImageList.addAll(Arrays.asList(bean.getNotice_content().split(",")));
                        mImageAdapter.notifyDataSetChanged();
                        tvTime.setText(bean.getCre_date());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
