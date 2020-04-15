package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetReferAgencyListBean;
import com.lckj.jycm.zfqg_network.GetReferAgencyNumBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.AgencyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AgencyActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_pos_number)
    TextView tvPosNumber;
    @BindView(R.id.tv_mpos_number)
    TextView tvMposNumber;
    @BindView(R.id.tv_epos_number)
    TextView tvEposNumber;
    private AgencyAdapter mAgencyAdapter;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    List<GetReferAgencyListBean.DataBean.ReferAgencyListBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.我的代理商));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAgencyAdapter = new AgencyAdapter(this, mData);
        recyclerView.setAdapter(mAgencyAdapter);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    key_word = etSearch.getText().toString().trim();
                    last_id = "";
                    Utils.hideKeyboard(etSearch);
                    initData();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refstatus = "down";
                last_id = "";
                key_word = "";
                initData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refstatus = "up";
                initData();
            }
        });
    }


    @Override
    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        mMyService.getReferAgencyList(new TokenRequest(dataManager.getToken(), key_word, last_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetReferAgencyListBean>(this) {
                    @Override
                    public void onSuccessCall(GetReferAgencyListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetReferAgencyListBean.DataBean.ReferAgencyListBean> bean = response.getData().getReferAgencyList();
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(bean);
                        if (mData.size() > 0)
                            last_id = mData.get(mData.size() - 1).getUser_id();
                        mAgencyAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetReferAgencyListBean response) {
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
        mMyService.getReferAgencyNum(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetReferAgencyNumBean>(this) {
                    @Override
                    public void onSuccessCall(GetReferAgencyNumBean response) {
                        ProgressDlgHelper.closeDialog();
                        tvCount.setText(getString(R.string.总人数人, response.getData().getReferer_num()));
                        String pos = "<font color='#000000'>传统POS共计<big> " + response.getData().getTra_pos_num() + "</big>台</font> 已激活 <font color='#1CCC9A'><big>" + response.getData().getTra_act_num() + "</big></font> 未激活 <font color='#FF0000'><big>" + response.getData().getTra_inact_num() + "</big></font> ";
                        String mpos = "<font color='#000000'>MPOS共计<big> " + response.getData().getM_pos_num() + "</big>台</font> 已激活 <font color='#1CCC9A'><big>" + response.getData().getM_act_num() + "</big></font> 未激活 <font color='#FF0000'><big>" + response.getData().getM_inact_num() + "</big></font> ";
                        String epos = "<font color='#000000'>EPOS共计<big> " + response.getData().getE_pos_num() + "</big>台</font> 已激活 <font color='#1CCC9A'><big>" + response.getData().getE_act_num() + "</big></font> 未激活 <font color='#FF0000'><big>" + response.getData().getE_inact_num() + "</big></font> ";
                        tvPosNumber.setText(Html.fromHtml(pos));
                        tvMposNumber.setText(Html.fromHtml(mpos));
                        tvEposNumber.setText(Html.fromHtml(epos));
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }

    public void finishLoad() {
        if ("up".equals(refstatus)) refreshLayout.finishLoadMore();
        if ("down".equals(refstatus)) refreshLayout.finishRefresh();
    }

    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
