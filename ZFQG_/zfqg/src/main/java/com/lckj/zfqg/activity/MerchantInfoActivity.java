package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosDetailBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SnRequest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MerchantInfoActivity extends BaseActvity {
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_shuaka_rate)
    TextView tvShuakaRate;
    @BindView(R.id.tv_yun_rate)
    TextView tvYunRate;
    @BindView(R.id.tv_wechat_rate)
    TextView tvWechatRate;
    @BindView(R.id.tv_alipay_rate)
    TextView tvAlipayRate;
    @BindView(R.id.tv_shuaka_price)
    TextView tvShuakaPrice;
    @BindView(R.id.tv_yun_price)
    TextView tvYunPrice;
    @BindView(R.id.tv_wechat_price)
    TextView tvWechatPrice;
    @BindView(R.id.tv_alipay_price)
    TextView tvAlipayPrice;
    @BindView(R.id.tv_single_rate)
    TextView tvSingleRate;
    @BindView(R.id.tv_machine_rate)
    TextView tvMachineRate;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_wechat_rate)
    RelativeLayout rlWechatRate;
    @BindView(R.id.rl_alipay_rate)
    RelativeLayout rlAlipayRate;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.rl_wechat)
    RelativeLayout rlWechat;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.tv_mer_cap_fee)
    TextView tvMerCapFee;
    @BindView(R.id.rl_mer_cap_fee)
    RelativeLayout rlMerCapFee;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv_type_number)
    TextView tvTypeNumber;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_deal_number)
    TextView tvDealNumber;
    @BindView(R.id.tv_deal_money)
    TextView tvDealMoney;
    @BindView(R.id.tv_policy)
    TextView tvPolicy;
    @BindView(R.id.rl_policy)
    RelativeLayout rlPolicy;
    @BindView(R.id.tv_realname)
    TextView tvRealname;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv6)
    TextView tv6;
    private String mSn;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private int isPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_info);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.详情));
        mSn = getIntent().getStringExtra("sn");
        isPos = getIntent().getIntExtra("isPos", 0);
        if (isPos == 0 || isPos == 2) {
            tvTypeName.setText(getString(R.string.商户名称2));
            tvTypeNumber.setText(getString(R.string.商户号2));
        } else {
            tvTypeName.setText(getString(R.string.商户名字2));
            tvTypeName.setVisibility(View.GONE);
            tvTypeNumber.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            tvPhone.setVisibility(View.GONE);
            tvTypeNumber.setText(getString(R.string.手机号码2));
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        Observable<GetTraditionalPosDetailBean> request = null;
        if (isPos == 0 || isPos == 2) {
            request = mMyService.getTraditionalPosDetail(new SnRequest(mSn, dataManager.getToken(), null, isPos == 2 ? "epos" : null));
        } else {
            request = mMyService.getMposDetail(new SnRequest(mSn, dataManager.getToken()));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (isPos == 0 || isPos == 2) {
                            rlAlipay.setVisibility(View.VISIBLE);
                            rlWechat.setVisibility(View.VISIBLE);
                            rlAlipayRate.setVisibility(View.VISIBLE);
                            rlWechatRate.setVisibility(View.VISIBLE);
                            rlMerCapFee.setVisibility(View.VISIBLE);
                            GetTraditionalPosDetailBean.DataBean.TraditionalPosDetailBean bean = response.getData().getTraditionalPosDetail();
                            if (!TextUtils.isEmpty(bean.getMer_name())) {
                                tvName.setText(bean.getMer_name());
                            } else {
                                tvName.setText("——");
                            }
                            if (!TextUtils.isEmpty(bean.getMer_id())) {
                                tvPhone.setText(bean.getMer_id());
                            } else {
                                tvPhone.setText("——");
                            }
                            if (TextUtils.isEmpty(bean.getExpire_day())) {
                                tv2.setVisibility(View.GONE);
                                tvDay.setVisibility(View.GONE);
                            } else {
                                tvDay.setText(bean.getExpire_day());
                            }
                            if (TextUtils.isEmpty(bean.getPolicy_name())) {
                                tv5.setVisibility(View.GONE);
                                tvPolicy.setVisibility(View.GONE);
                            } else {
                                tvPolicy.setText(bean.getPolicy_name());
                            }
                            tvCall.setText(bean.getTel());
                            tvRealname.setText(bean.getName());
                            tvNumber.setText(bean.getSn());
                            tvDealNumber.setText(bean.getNum());
                            tvDealMoney.setText(bean.getPerformance());
                            tvShuakaRate.setText(bean.getCredit_card_rate() + "%");
                            tvYunRate.setText(bean.getCloud_flash_rate() + "%");
                            tvWechatRate.setText(bean.getWeixin_rate() + "%");
                            tvAlipayRate.setText(bean.getZhifubao_rate() + "%");
                            tvShuakaPrice.setText(bean.getCard_settle_price() + "%");
                            tvYunPrice.setText(bean.getCloud_settle_price() + "%");
                            tvWechatPrice.setText(bean.getWeixin_settle_price() + "%");
                            tvAlipayPrice.setText(bean.getZhifubao_settle_price() + "%");
                            tvSingleRate.setText(bean.getSingle_profit_rate() + "%");
                            tvMachineRate.setText(bean.getCash_back_rate() + "%");
                            tvMerCapFee.setText(bean.getMer_cap_fee());
                            if (TextUtils.isEmpty(bean.getAct_date())) {
                                tvTime.setText("——");
                            } else {
                                tvTime.setText(bean.getAct_date());
                            }
                            switch (bean.getAct_status()) {
                                case "0":
                                    tvStatus.setText(getString(R.string.未激活));
                                    tvStatus.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                case "1":
                                    tvStatus.setText(getString(R.string.已激活));
                                    tvStatus.setTextColor(getResources().getColor(R.color.green2));
                                    break;
                            }
                            switch (bean.getCash_back_status()) {
                                case "0":
                                    tvState.setText(getString(R.string.未返现));
                                    tvState.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                case "1":
                                    tvState.setText(getString(R.string.已返现));
                                    tvState.setTextColor(getResources().getColor(R.color.green2));
                                    break;
                            }
                            if (TextUtils.isEmpty(bean.getCard_settle_price_vip())) {
                                tvVip.setText("——");
                            } else {
                                tvVip.setText(bean.getCard_settle_price_vip() + "%");
                            }
                        } else {
                            GetTraditionalPosDetailBean.DataBean.TraditionalPosDetailBean bean = response.getData().getMposDetail();
                            if (!TextUtils.isEmpty(bean.getName())) {
                                tvRealname.setText(bean.getName());
                            } else {
                                tvRealname.setText("——");
                            }
                            if (!TextUtils.isEmpty(bean.getTel())) {
                                tvCall.setText(bean.getTel());
                            } else {
                                tvCall.setText("——");
                            }
                            if (TextUtils.isEmpty(bean.getExpire_day())) {
                                tv2.setVisibility(View.GONE);
                                tvDay.setVisibility(View.GONE);
                            } else {
                                tvDay.setText(bean.getExpire_day());
                            }
                            if (TextUtils.isEmpty(bean.getPolicy_name())) {
                                tv5.setVisibility(View.GONE);
                                tvPolicy.setVisibility(View.GONE);
                            } else {
                                tvPolicy.setText(bean.getPolicy_name());
                            }
                            tvNumber.setText(bean.getSn());
                            tvDealNumber.setText(bean.getNum());
                            tvDealMoney.setText(bean.getPerformance());
                            tvShuakaRate.setText(bean.getCredit_card_rate() + "%");
                            tvYunRate.setText(bean.getCloud_flash_rate() + "%");
                            tvShuakaPrice.setText(bean.getCard_settle_price() + "%");
                            tvYunPrice.setText(bean.getCloud_settle_price() + "%");
                            tvSingleRate.setText(bean.getSingle_profit_rate() + "%");
                            tvMachineRate.setText(bean.getCash_back_rate() + "%");
                            if (TextUtils.isEmpty(bean.getAct_date())) {
                                tvTime.setText("——");
                            } else {
                                tvTime.setText(bean.getAct_date());
                            }
                            switch (bean.getAct_status()) {
                                case "0":
                                    tvStatus.setText(getString(R.string.未激活));
                                    tvStatus.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                case "1":
                                    tvStatus.setText(getString(R.string.已激活));
                                    tvStatus.setTextColor(getResources().getColor(R.color.green2));
                                    break;
                            }
                            switch (bean.getCash_back_status()) {
                                case "0":
                                    tvState.setText(getString(R.string.未返现));
                                    tvState.setTextColor(getResources().getColor(R.color.red));
                                    break;
                                case "1":
                                    tvState.setText(getString(R.string.已返现));
                                    tvState.setTextColor(getResources().getColor(R.color.green2));
                                    break;
                            }
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.tv_deal_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_deal_money:
                startActivity(new Intent(this, MerchantDealInfoActivity.class).putExtra("sn", mSn));
                break;
        }
    }
}
