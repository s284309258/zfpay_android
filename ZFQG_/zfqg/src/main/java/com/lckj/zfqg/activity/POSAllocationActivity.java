package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.AllocationTraditionalPosRequest;
import com.lckj.jycm.zfqg_network.GetRefererAgencyBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosSysParamRateListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SnRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.ConfigAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class POSAllocationActivity extends BaseActvity {

    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_agency)
    TextView tvAgency;
    @BindView(R.id.rl_agency)
    RelativeLayout rlAgency;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_offline)
    TextView tvOffline;
    @BindView(R.id.rl_offline)
    RelativeLayout rlOffline;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.rl_online)
    RelativeLayout rlOnline;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv_alipay)
    TextView tvAlipay;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.rl_wechat)
    RelativeLayout rlWechat;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_single)
    TextView tvSingle;
    @BindView(R.id.rl_single)
    RelativeLayout rlSingle;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv_return_money)
    TextView tvReturnMoney;
    @BindView(R.id.rl_return_money)
    RelativeLayout rlReturnMoney;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
    @BindView(R.id.rl_limit)
    RelativeLayout rlLimit;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.rl_vip)
    RelativeLayout rlVip;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.iv_deal_number)
    ImageView ivDealNumber;
    @BindView(R.id.rl_deal_number)
    RelativeLayout rlDealNumber;
    private HomePosAdapter mHomePosAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private List<GetRefererAgencyBean.DataBean.RefererAgencyListBean> mAgencyData = new ArrayList<>();
    private GetTraditionalPosSysParamRateListBean.DataBean.TraditionalPosSysParamRateBean mConfigData;
    private ConfigAdapter mConfigAdapter;
    private String key_word;
    private String mUser_id;
    private String mSn;
    boolean isPos;
    private boolean isSelector = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos_allocation);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
//        ivDealNumber.setSelected(isSelector);
        isPos = getIntent().getBooleanExtra("isPos", false);
        customTitle.setText(getString(isPos ? R.string.传统POS分配 : R.string.EPOS分配));
        mSn = getIntent().getStringExtra("sn");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHomePosAdapter = new HomePosAdapter(this);
        mConfigAdapter = new ConfigAdapter(this);
    }

    @Override
    protected void initEvents() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    key_word = etSearch.getText().toString().trim();
                    Utils.hideKeyboard(etSearch);
                    initData();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getRefererAgency(new TokenRequest(dataManager.getToken(), key_word))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetRefererAgencyBean>(this) {
                    @Override
                    public void onSuccessCall(GetRefererAgencyBean response) {
                        mAgencyData.clear();
                        mAgencyData.addAll(response.getData().getRefererAgencyList());
                        mHomePosAdapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this));
        mMyService.getTraditionalPosSysParamRateList(new SnRequest(mSn, dataManager.getToken(), null, isPos ? null : "epos"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosSysParamRateListBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosSysParamRateListBean response) {
                        ProgressDlgHelper.closeDialog();
                        mConfigData = response.getData().getTraditionalPosSysParamRate();
                        if (response.getData().getTraditionalPosSysParamRate().getIs_reward() == null) {
                            rlDealNumber.setVisibility(View.GONE);
                            isSelector = true;
                            ivDealNumber.setSelected(isSelector);
                        } else if (response.getData().getTraditionalPosSysParamRate().getIs_reward().equals("0")) {
                            rlDealNumber.setVisibility(View.GONE);
                            isSelector = false;
                            ivDealNumber.setSelected(isSelector);
                        } else if (response.getData().getTraditionalPosSysParamRate().getIs_reward().equals("1")) {
                            rlDealNumber.setVisibility(View.VISIBLE);
                            isSelector = true;
                            ivDealNumber.setSelected(isSelector);
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.rl_limit, R.id.left_action, R.id.rl_agency, R.id.rl_offline, R.id.rl_online, R.id.rl_single, R.id.rl_return_money, R.id.rl_wechat, R.id.rl_alipay, R.id.btn_submit, R.id.rl_vip, R.id.rl_deal_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_agency:
                tvTitle.setText(getString(R.string.代理伙伴));
                rlSearch.setVisibility(View.VISIBLE);
                drawerLayout.openDrawer(Gravity.END);
                recyclerView.setAdapter(mHomePosAdapter);
                mHomePosAdapter.setData(mAgencyData);
                mHomePosAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_offline:
                tvTitle.setText(getString(R.string.刷卡结算价));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(0);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_online:
                tvTitle.setText(getString(R.string.云闪付结算价));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(1);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_single:
                tvTitle.setText(getString(R.string.单笔分润));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(2);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_return_money:
                tvTitle.setText(getString(R.string.机器返现));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(3);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_wechat:
                tvTitle.setText(getString(R.string.微信结算价));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(4);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_alipay:
                tvTitle.setText(getString(R.string.支付宝结算价));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(5);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_submit:
                if (checkEidt()) {
                    submit();
                }
                break;
            case R.id.rl_limit:
                tvTitle.setText(getString(R.string.商户封顶费));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(7);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_vip:
                tvTitle.setText(getString(R.string.VIP刷卡结算价));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                mConfigAdapter.setType(8);
                recyclerView.setAdapter(mConfigAdapter);
                mConfigAdapter.setData(mConfigData);
                mConfigAdapter.notifyDataSetChanged();
                break;
            case R.id.rl_deal_number:
                isSelector = !isSelector;
                ivDealNumber.setSelected(isSelector);
                break;
        }
    }

    private boolean checkEidt() {
        if (TextUtils.isEmpty(tvAgency.getText().toString())) {
            Utils.showMsg(this, tvAgency.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvOffline.getText().toString())) {
            Utils.showMsg(this, tvOffline.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvOnline.getText().toString())) {
            Utils.showMsg(this, tvOnline.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvWechat.getText().toString())) {
            Utils.showMsg(this, tvWechat.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvAlipay.getText().toString())) {
            Utils.showMsg(this, tvAlipay.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvSingle.getText().toString())) {
            Utils.showMsg(this, tvSingle.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvReturnMoney.getText().toString())) {
            Utils.showMsg(this, tvReturnMoney.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvLimit.getText().toString())) {
            Utils.showMsg(this, tvLimit.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(tvVip.getText().toString())) {
            Utils.showMsg(this, tvVip.getHint().toString());
            return false;
        }
        return true;
    }

    private void submit() {
        String agency = tvAgency.getText().toString();
        String offline = tvOffline.getText().toString().replace("%", "");
        String online = tvOnline.getText().toString().replace("%", "");
        String single = tvSingle.getText().toString().replace("%", "");
        String returnMoney = tvReturnMoney.getText().toString().replace("%", "");
        String wechat = tvWechat.getText().toString().replace("%", "");
        String alipay = tvAlipay.getText().toString().replace("%", "");
        String limit = tvLimit.getText().toString();
        String vip = tvVip.getText().toString().replace("%", "");
        ProgressDlgHelper.openDialog(this);
        mMyService.allocationTraditionalPos(new AllocationTraditionalPosRequest(limit, dataManager.getToken(), mUser_id, offline, returnMoney, online, single, mSn, wechat, alipay, vip, "", "", "", isPos ? null : "epos", isSelector ? "1" : "0"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(POSAllocationActivity.this, getString(R.string.申请分配成功));
                        setResult(RESULT_OK);
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void setData(String data, int type) {
        switch (type) {
            case 0:
                tvOffline.setText(data);
                break;
            case 1:
                tvOnline.setText(data);
                break;
            case 2:
                tvSingle.setText(data);
                break;
            case 3:
                tvReturnMoney.setText(data);
                break;
            case 4:
                tvWechat.setText(data);
                break;
            case 5:
                tvAlipay.setText(data);
                break;
            case 7:
                tvLimit.setText(data);
                break;
            case 8:
                tvVip.setText(data);
                break;
        }
        drawerLayout.closeDrawer(Gravity.END);
    }

    public void setName(String name, String user_id) {
        tvAgency.setText(name);
        mUser_id = user_id;
        drawerLayout.closeDrawer(Gravity.END);
    }
}
