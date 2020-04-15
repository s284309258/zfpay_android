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
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosRequest;
import com.lckj.jycm.zfqg_network.GetCreditCardRateListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SnListRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.ConfigAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpdateRateActivity extends BaseActvity {
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
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.rl_selecotr_rate)
    RelativeLayout rlSelecotrRate;
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
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    private HomePosAdapter mHomePosAdapter;
    private String mSn;
    List<String> mData = new ArrayList<>();
    private ConfigAdapter mConfigAdapter;
    private int mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rate);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.调整费率));
        mSn = getIntent().getStringExtra("sn");
        mType = getIntent().getIntExtra("type", 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mConfigAdapter = new ConfigAdapter(this, mData, 6);
        recyclerView.setAdapter(mConfigAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getCreditCardRateList(new TokenRequest(dataManager.getToken(), null, null, mType == 2 ? "epos" : null))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetCreditCardRateListBean>(this) {
                    @Override
                    public void onSuccessCall(GetCreditCardRateListBean response) {
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        mData.addAll(response.getData().getCreditCardRateList());
                        mConfigAdapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.rl_selecotr_rate, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_selecotr_rate:
                tvTitle.setText(getString(R.string.刷卡费率));
                rlSearch.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.END);
                break;
            case R.id.btn_submit:
                if (TextUtils.isEmpty(tvRate.getText().toString())) {
                    Utils.showMsg(this, tvRate.getHint().toString());
                } else {
                    submit();
                }
                break;
        }
    }

    private void submit() {
        ProgressDlgHelper.openDialog(this);
        String rate = tvRate.getText().toString().replace("%", "");
        Observable<HttpResponse> request = null;
        if (mType == 0) {
            request = mMyService.addApplyRateTraditionalPos(new GetApplyRateTraditionalPosRequest(mSn, rate, dataManager.getToken()));
        } else if (mType == 2) {
            request = mMyService.addApplyRateTraditionalPos(new GetApplyRateTraditionalPosRequest(mSn, rate, dataManager.getToken(), "epos"));
        } else {
            request = mMyService.addApplyRateMpos(new GetApplyRateTraditionalPosRequest(mSn, rate, dataManager.getToken()));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(getContext(), getString(R.string.申请成功));
                        setResult(RESULT_OK);
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.END)) {
                drawerLayout.closeDrawer(Gravity.END);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setData(String name) {
        tvRate.setText(name);
        drawerLayout.closeDrawer(Gravity.END);
    }
}
