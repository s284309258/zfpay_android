package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.lckj.jycm.zfqg_network.GetSummaryTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.activity.MerchantListActivity;
import com.lckj.zfqg.activity.StandardMerchantListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("ValidFragment")
public class DirectFragment extends BaseFragment {
    private final int mType;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_all_count)
    TextView tvAllCount;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv_quality_count)
    TextView tvQualityCount;
    @BindView(R.id.rl_quality)
    RelativeLayout rlQuality;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.tv_active_count)
    TextView tvActiveCount;
    @BindView(R.id.rl_active)
    RelativeLayout rlActive;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.tv_unverified_count)
    TextView tvUnverifiedCount;
    @BindView(R.id.rl_unverified)
    RelativeLayout rlUnverified;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.tv_sleep_count)
    TextView tvSleepCount;
    @BindView(R.id.rl_sleep)
    RelativeLayout rlSleep;
    @BindView(R.id.tv_standard_count)
    TextView tvStandardCount;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;

    public DirectFragment(int type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_direct);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initData();
    }

    private void initData() {
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetSummaryTraditionalPosListBean> request = null;
        if (mType == 0) {
            request = mMyService.getSummaryTraditionalPosList(new TokenRequest(dataManager.getToken()));
        } else if (mType == 2) {
            request = mMyService.getSummaryTraditionalPosList(new TokenRequest(dataManager.getToken(), null, null, "epos"));
        } else {
            request = mMyService.getSummaryMposList(new TokenRequest(dataManager.getToken()));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetSummaryTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetSummaryTraditionalPosListBean response) {
                        ProgressDlgHelper.closeDialog();
                        tvAllCount.setText(response.getData().getAll_merchant());
                        tvQualityCount.setText(response.getData().getExcellent_merchant());
                        tvActiveCount.setText(response.getData().getActive_merchant());
                        tvSleepCount.setText(response.getData().getDormant_merchant());
                        tvStandardCount.setText(response.getData().getStandard_merchant());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.rl_all, R.id.rl_quality, R.id.rl_active, R.id.rl_unverified, R.id.rl_sleep, R.id.rl_standard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_all:
                if (mType == 0) {
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 0).putExtra("title", getString(R.string.全部商户)));
                } else if(mType == 1){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 6).putExtra("title", getString(R.string.全部商户)));
                }else if(mType == 2){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 12).putExtra("title", getString(R.string.全部商户)));
                }
                break;
            case R.id.rl_quality:
                if (mType == 0) {
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 1).putExtra("title", getString(R.string.优质商户)));
                } else if(mType == 1){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 7).putExtra("title", getString(R.string.优质商户)));
                }else if(mType == 2){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 13).putExtra("title", getString(R.string.优质商户)));
                }
                break;
            case R.id.rl_active:
                if (mType == 0) {
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 2).putExtra("title", getString(R.string.活跃商户)));
                } else if(mType == 1){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 8).putExtra("title", getString(R.string.活跃商户)));
                } else if(mType == 2){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 14).putExtra("title", getString(R.string.活跃商户)));
                }
                break;
            case R.id.rl_unverified:
                if (mType == 0) {
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 3).putExtra("title", getString(R.string.需认证商户)));
                } else if(mType == 1){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 9).putExtra("title", getString(R.string.需认证商户)));
                }else if(mType == 2){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 15).putExtra("title", getString(R.string.需认证商户)));
                }
                break;
            case R.id.rl_sleep:
                if (mType == 0) {
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 4).putExtra("title", getString(R.string.休眠商户)));
                } else if(mType == 1){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 10).putExtra("title", getString(R.string.休眠商户)));
                }else if(mType == 2){
                    startActivity(new Intent(getContext(), MerchantListActivity.class).putExtra("type", 16).putExtra("title", getString(R.string.休眠商户)));
                }
                break;
            case R.id.rl_standard:
                if (mType == 0) {
                    startActivity(new Intent(getContext(), StandardMerchantListActivity.class).putExtra("type", 5).putExtra("title", getString(R.string.达标商户)));
                } else if(mType == 1) {
                    startActivity(new Intent(getContext(), StandardMerchantListActivity.class).putExtra("type", 11).putExtra("title", getString(R.string.达标商户)));
                }else if(mType == 2) {
                    startActivity(new Intent(getContext(), StandardMerchantListActivity.class).putExtra("type", 17).putExtra("title", getString(R.string.达标商户)));
                }
                break;
        }
    }
}
