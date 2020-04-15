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
import com.lckj.jycm.zfqg_network.AllocationTrafficCardRequest;
import com.lckj.jycm.zfqg_network.GetRefererAgencyBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CardAllocationActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_agency)
    TextView tvAgency;
    @BindView(R.id.rl_agency)
    RelativeLayout rlAgency;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private HomePosAdapter mHomePosAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    private List<GetRefererAgencyBean.DataBean.RefererAgencyListBean> mAgencyData = new ArrayList<>();
    private String key_word;
    private String mUser_id;
    private String mSn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_allocation);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.流量卡分配));
        mSn = getIntent().getStringExtra("sn");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHomePosAdapter = new HomePosAdapter(this);
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
                        ProgressDlgHelper.closeDialog();
                        mAgencyData.clear();
                        mAgencyData.addAll(response.getData().getRefererAgencyList());
                        mHomePosAdapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.rl_agency, R.id.btn_submit})
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
            case R.id.btn_submit:
                if (checkEidt()) {
                    submit();
                }
                break;
        }
    }

    private boolean checkEidt() {
        if (TextUtils.isEmpty(tvAgency.getText().toString())) {
            Utils.showMsg(this, tvAgency.getHint().toString());
            return false;
        }
        return true;
    }

    private void submit() {
        String agency = tvAgency.getText().toString();
        ProgressDlgHelper.openDialog(this);
        mMyService.allocationTrafficCard(new AllocationTrafficCardRequest(mSn, mUser_id, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(CardAllocationActivity.this, getString(R.string.申请分配成功));
                        setResult(RESULT_OK);
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void setName(String name, String user_id) {
        tvAgency.setText(name);
        mUser_id = user_id;
        drawerLayout.closeDrawer(Gravity.END);
    }
}
