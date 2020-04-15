package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
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
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.EditAllocationMPosBatchRequest;
import com.lckj.jycm.zfqg_network.GetTraditionalPosSysParamRateListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SelectPosSettlePriceBySNBean;
import com.lckj.jycm.zfqg_network.SnRequest;
import com.lckj.jycm.zfqg_network.TokenRequest2;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.ActivityOrderDetailsAdapter;
import com.lckj.zfqg.adapter.ConfigAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MPOSAllocationUpdateActivity extends BaseActvity {
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
    @BindView(R.id.tv_agency)
    TextView tvAgency;
    @BindView(R.id.rl_agency)
    RelativeLayout rlAgency;
    @BindView(R.id.tv_sn)
    TextView tvSn;
    @BindView(R.id.rl_sn)
    RelativeLayout rlSn;
    @BindView(R.id.tv_offline)
    TextView tvOffline;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rl_offline)
    RelativeLayout rlOffline;
    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.rl_online)
    RelativeLayout rlOnline;
    @BindView(R.id.tv_single)
    TextView tvSingle;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.rl_single)
    RelativeLayout rlSingle;
    @BindView(R.id.tv_return_money)
    TextView tvReturnMoney;
    @BindView(R.id.iv6)
    ImageView iv6;
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
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_open)
    TextView tvOpen;
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;
    @BindView(R.id.tv_policy)
    TextView tvPolicy;
    @BindView(R.id.rl_policy)
    RelativeLayout rlPolicy;
    @BindView(R.id.iv_deal_number)
    ImageView ivDealNumber;
    @BindView(R.id.rl_deal_number)
    RelativeLayout rlDealNumber;
    private String mUser_id;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private GetTraditionalPosSysParamRateListBean.DataBean.TraditionalPosSysParamRateBean mConfigData;
    private ConfigAdapter mConfigAdapter;
    private String mBatch_no;
    private String mSn;
    private List<String> mSnList = new ArrayList<>();
    private ActivityOrderDetailsAdapter mActivityOrderDetailsAdapter;
    private boolean isOpen;
    private String mMax_sn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpos_allocation_update);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.分配修改));
        mUser_id = getIntent().getStringExtra("user_id");
        mBatch_no = getIntent().getStringExtra("batch_no");
        mSn = getIntent().getStringExtra("sn");
        mMax_sn = getIntent().getStringExtra("max_sn");
        tvSn.setText(mSn + "\n至\n" + mMax_sn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mConfigAdapter = new ConfigAdapter(this);
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        mActivityOrderDetailsAdapter = new ActivityOrderDetailsAdapter(this, mSnList);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setAdapter(mActivityOrderDetailsAdapter);

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.selectPosSettlePriceBySN(new TokenRequest2("MPOS", dataManager.getToken(), mSn, mUser_id, mBatch_no))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<SelectPosSettlePriceBySNBean>(this) {
                    @Override
                    public void onSuccessCall(SelectPosSettlePriceBySNBean response) {
                        ProgressDlgHelper.closeDialog();
                        SelectPosSettlePriceBySNBean.DataBean.AllocationPosBean bean = response.getData().getAllocationPos();
                        tvAgency.setText(bean.getReal_name());
                        tvOffline.setText(bean.getCard_settle_price() + "%");
                        tvOnline.setText(bean.getCloud_settle_price() + "%");
                        tvSingle.setText(bean.getSingle_profit_rate() + "%");
                        tvReturnMoney.setText(bean.getCash_back_rate() + "%");
                        String[] split = bean.getSns().split(",");
                        mSnList.addAll(Arrays.asList(split));
                        tvNumber.setText(getString(R.string.分配的SN码, mSnList.size() + ""));
                        if (TextUtils.isEmpty(bean.getPolicy_name())) {
                            rlPolicy.setVisibility(View.GONE);
                            tvPolicy.setText(bean.getPolicy_name());
                        } else {
                            rlPolicy.setVisibility(View.VISIBLE);
                            tvPolicy.setText(bean.getPolicy_name());
                        }
                        getData();
                        if(bean.getIs_reward() != null&& bean.getIs_reward().equals("1")) {
                            rlDealNumber.setVisibility(View.VISIBLE);
                            ivDealNumber.setSelected(bean.getIs_reward().equals("1"));
                        }else {
                            rlDealNumber.setVisibility(View.GONE);
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void getData() {
        mMyService.getMposSysParamRateList(new SnRequest(mSn, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosSysParamRateListBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosSysParamRateListBean response) {
                        ProgressDlgHelper.closeDialog();
                        mConfigData = response.getData().getMposSysParamRate();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.rl_offline, R.id.rl_online, R.id.rl_single, R.id.rl_return_money, R.id.btn_submit, R.id.tv_open})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
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
            case R.id.btn_submit:
                submit();
                break;
            case R.id.tv_open:
                isOpen = !isOpen;
                if (isOpen) {
                    recyclerView2.setVisibility(View.VISIBLE);
                } else {
                    recyclerView2.setVisibility(View.GONE);
                }
        }
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
        }
        drawerLayout.closeDrawer(Gravity.END);
    }

    private void submit() {
        ProgressDlgHelper.openDialog(this);
        String online = tvOnline.getText().toString().replace("%", "");
        String returnMoney = tvReturnMoney.getText().toString().replace("%", "");
        String offline = tvOffline.getText().toString().replace("%", "");
        String single = tvSingle.getText().toString().replace("%", "");
        mMyService.editAllocationMPosBatch(new EditAllocationMPosBatchRequest(online, mBatch_no, returnMoney, offline, single, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        finish();
                        Utils.showMsg(MPOSAllocationUpdateActivity.this, getString(R.string.分配修改成功));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
}

