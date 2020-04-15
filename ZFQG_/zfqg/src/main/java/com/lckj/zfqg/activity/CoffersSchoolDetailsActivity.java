package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetMoneyLockerCollegeDetailBean;
import com.lckj.jycm.zfqg_network.GetMoneyLockerCollegeDetailRequest;
import com.lckj.jycm.zfqg_network.GetNewsDetailBean;
import com.lckj.jycm.zfqg_network.GetNewsDetailRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.imageloader.ImageLoader;
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

public class CoffersSchoolDetailsActivity extends BaseActvity {
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
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv)
    TextView tv;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String mId;
    private String mUrl;
    private boolean mWho;
    private ImageAdapter mImageAdapter;
    List<String> mImageList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffers_school_details);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mId = getIntent().getStringExtra("id");
        mWho = getIntent().getBooleanExtra("who", false);
        customTitle.setText(getString(R.string.详情));
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
        if (mWho) {
            mMyService.getNewsDetail(new GetNewsDetailRequest(dataManager.getToken(), mId))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetNewsDetailBean>(this) {
                        @Override
                        public void onSuccessCall(GetNewsDetailBean response) {
                            ProgressDlgHelper.closeDialog();
                            GetNewsDetailBean.DataBean.NewsInfoBean bean = response.getData().getNewsInfo();

                            if (bean.getNews_nav().contains(".mp4") || bean.getNews_nav().contains(".MP4")) {
                                ivPlay.setVisibility(View.VISIBLE);
                                mUrl = bean.getNews_nav();
                                ImageLoader.loadImage(bean.getNews_nav(), ivImg);
                            } else {
                                ivPlay.setVisibility(View.GONE);
                                ImageLoader.loadImage(bean.getNews_nav(), ivImg);
                            }
                            mImageList.clear();
                            mImageList.addAll(Arrays.asList(bean.getNews_content().split(",")));
                            mImageAdapter.notifyDataSetChanged();
                            tvTitle.setText(bean.getNews_title());
                            tvCount.setVisibility(View.VISIBLE);
                            tvCount.setText(bean.getBrowse_num());
                            tvTime.setText(bean.getCre_date());
                        }
                    }, new ThrowableConsumer<Throwable>(this));
        } else {
            mMyService.getMoneyLockerCollegeDetail(new GetMoneyLockerCollegeDetailRequest(mId, dataManager.getToken()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetMoneyLockerCollegeDetailBean>(this) {
                        @Override
                        public void onSuccessCall(GetMoneyLockerCollegeDetailBean response) {
                            ProgressDlgHelper.closeDialog();
                            GetMoneyLockerCollegeDetailBean.DataBean.MoneyLockerCollegeBean bean = response.getData().getMoneyLockerCollege();

                            if (bean.getMoney_locker_nav().contains(".mp4") || bean.getMoney_locker_nav().contains(".MP4")) {
                                ivPlay.setVisibility(View.VISIBLE);
                                mUrl = bean.getMoney_locker_nav();
                                ImageLoader.loadImage(bean.getMoney_locker_cover(), ivImg);
                            } else {
                                ivPlay.setVisibility(View.GONE);
                                ImageLoader.loadImage(bean.getMoney_locker_nav(), ivImg);
                            }
                            mImageList.clear();
                            mImageList.addAll(Arrays.asList(bean.getMoney_locker_content().split(",")));
                            mImageAdapter.notifyDataSetChanged();
                            tvTitle.setText(bean.getMoney_locker_title());
                            tvTime.setText(bean.getCre_datetime());
                        }
                    }, new ThrowableConsumer<Throwable>(this));
        }
    }

    @OnClick({R.id.left_action, R.id.iv_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.iv_img:
                if (ivPlay.getVisibility() == View.VISIBLE) {
                    startActivity(new Intent(this, VideoActivity.class).putExtra("url", mUrl));
                }
                break;
        }
    }
}
