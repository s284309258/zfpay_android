package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.zfqg_network.GetHomePageInfoBean;
import com.lckj.jycm.zfqg_network.GetUserAuthStatusBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.PerformanceActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReportFragment extends BaseFragment {
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_pos_sum)
    TextView tvPosSum;
    @BindView(R.id.tv_user_count)
    TextView tvUserCount;
    @BindView(R.id.tv_user_sum)
    TextView tvUserSum;
    @BindView(R.id.rl_pos)
    RelativeLayout rlPos;
    @BindView(R.id.tv_mpos_sum)
    TextView tvMposSum;
    @BindView(R.id.tv_muser_count)
    TextView tvMuserCount;
    @BindView(R.id.tv_muser_sum)
    TextView tvMuserSum;
    @BindView(R.id.rl_mpos)
    RelativeLayout rlMpos;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_pos_average)
    TextView tvPosAverage;
    @BindView(R.id.tv_mpos_average)
    TextView tvMposAverage;
    @BindView(R.id.tv_today_deal)
    TextView tvTodayDeal;
    @BindView(R.id.tv_sum_deal)
    TextView tvSumDeal;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_pos_day)
    TextView tvPosDay;
    @BindView(R.id.tv_mpos_day)
    TextView tvMposDay;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv_epos_sum)
    TextView tvEposSum;
    @BindView(R.id.tv_epos_day)
    TextView tvEposDay;
    @BindView(R.id.tv_euser_count)
    TextView tvEuserCount;
    @BindView(R.id.tv_euser_sum)
    TextView tvEuserSum;
    @BindView(R.id.rl_epos)
    RelativeLayout rlEpos;
    @BindView(R.id.tv_epos_average)
    TextView tvEposAverage;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isDown;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_report);
        mBind = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateImg(String msg) {
        if (msg.equals(getString(R.string.修改头像成功))) {
            ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 30, 0);
        } else if (msg.equals(getString(R.string.修改手机号码))) {
            tvPhone.setText(dataManager.getUserTel());
        } else if (msg.equals(getString(R.string.实名认证))) {
            tvName.setText(dataManager.getUserName());
        }
    }

    private void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    //新增EPOS
    @SuppressLint("CheckResult")
    private void initData() {
        ProgressDlgHelper.openDialog(getContext());
        mMyService.getHomePageInfo(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetHomePageInfoBean>(this) {
                    @Override
                    public void onSuccessCall(GetHomePageInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
                        tvPosSum.setText(response.getData().getTraposDetail().getPerformance());
                        tvUserCount.setText(response.getData().getTraposDetail().getAct_num());
                        tvUserSum.setText(response.getData().getTraposDetail().getPos_num());
                        tvPosAverage.setText(response.getData().getTraposDetail().getPos_avg());
                        tvPosDay.setText(response.getData().getTraposDetail().getAct_num_day());
                        tvMposSum.setText(response.getData().getMposDetail().getPerformance());
                        tvMuserCount.setText(response.getData().getMposDetail().getAct_num());
                        tvMuserSum.setText(response.getData().getMposDetail().getPos_num());
                        tvMposAverage.setText(response.getData().getMposDetail().getPos_avg());
                        tvMposDay.setText(response.getData().getMposDetail().getAct_num_day());
                        tvEposSum.setText(response.getData().getEposDetail().getPerformance());
                        tvEuserCount.setText(response.getData().getEposDetail().getAct_num());
                        tvEuserSum.setText(response.getData().getEposDetail().getPos_num());
                        tvEposAverage.setText(response.getData().getEposDetail().getPos_avg());
                        tvEposDay.setText(response.getData().getEposDetail().getAct_num_day());
                    }

                    @Override
                    public void onFailedCall(GetHomePageInfoBean response) {
                        super.onFailedCall(response);
                        refreshLayout.finishRefresh();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        refreshLayout.finishRefresh();
                    }
                });

        mMyService.getUserAuthStatus(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserAuthStatusBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserAuthStatusBean response) {
                        dataManager.setUserName(response.getData().getUserAuthStatus().getReal_name());
                        dataManager.setAuthStatus(response.getData().getUserAuthStatus().getAuth_status());
                        tvTodayDeal.setText("今日交易额：" + response.getData().getUserAuthStatus().getTradeAmountDay());
                        tvSumDeal.setText("累计交易额：" + response.getData().getUserAuthStatus().getTradeAmountAll());
                        EventBus.getDefault().post(getString(R.string.实名认证));
                    }
                }, new ThrowableConsumer<Throwable>(this));

        /*mMyService.getUserAuthStatus(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserAuthStatusBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserAuthStatusBean response) {
                        dataManager.setUserName(response.getData().getUserAuthStatus().getReal_name());
                        dataManager.setAuthStatus(response.getData().getUserAuthStatus().getAuth_status());
                        EventBus.getDefault().post(getString(R.string.实名认证));
                    }
                }, new ThrowableConsumer<Throwable>(this));*/
    }

    @Override
    protected void initView() {
        ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 30, 0);
        tvPhone.setText(dataManager.getUserTel());
        tvName.setText(dataManager.getUserName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.rl_pos, R.id.rl_mpos, R.id.rl_epos, R.id.iv_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_pos:
                startActivity(new Intent(getContext(), PerformanceActivity.class).putExtra("title", getString(R.string.传统POS)));
                break;
            case R.id.rl_mpos:
                startActivity(new Intent(getContext(), PerformanceActivity.class).putExtra("title", "MPOS"));
                break;
            case R.id.rl_epos:
                startActivity(new Intent(getContext(), PerformanceActivity.class).putExtra("title", "EPOS"));
                break;
            case R.id.iv_down:
                isDown = !isDown;
                if (isDown) {
                    ll.setVisibility(View.VISIBLE);
                } else {
                    ll.setVisibility(View.GONE);
                }
                break;
        }
    }
}
