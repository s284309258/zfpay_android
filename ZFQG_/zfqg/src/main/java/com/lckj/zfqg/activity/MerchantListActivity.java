package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetAllMerchantTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosRecordListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.MerchantAdapter;
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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MerchantListActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private MerchantAdapter mMerchantAdapter;
    private int mType;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    List<GetAllMerchantTraditionalPosListBean.DataBean.AllMerchantTraditionalPosListBean> mData = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
//        initData();
    }

    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("type", 0);
        customTitle.setText(getIntent().getStringExtra("title"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int isPos;
        if (mType <= 4) {
            isPos = 0;
        } else if (mType <= 10) {
            isPos = 1;
        } else {
            isPos = 2;
        }
        mMerchantAdapter = new MerchantAdapter(this, mData, isPos);
        recyclerView.setAdapter(mMerchantAdapter);
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
    protected void onResume() {
        super.onResume();
        /*refstatus = "down";
        last_id = "";*/
        initData();
    }

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

    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        Observable<GetAllMerchantTraditionalPosListBean> request = null;
        switch (mType) {
            case 0:
                request = mMyService.getAllMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 1:
                request = mMyService.getExcellentMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 2:
                request = mMyService.getActiveMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 3:
                request = mMyService.getDormantMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 4:
                request = mMyService.getDormantMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 6:
                request = mMyService.getAllMerchantMposList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 7:
                request = mMyService.getExcellentMerchantMposList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 8:
                request = mMyService.getActiveMerchantMposList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 9:
                request = mMyService.getActiveMerchantMposList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 10:
                request = mMyService.getDormantMerchantMposList(new TokenRequest(dataManager.getToken(), key_word, last_id));
                break;
            case 12:
                request = mMyService.getAllMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
                break;
            case 13:
                request = mMyService.getExcellentMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
                break;
            case 14:
                request = mMyService.getActiveMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
                break;
            case 15:
                request = mMyService.getDormantMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
                break;
            case 16:
                request = mMyService.getDormantMerchantTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id, "epos"));
                break;
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetAllMerchantTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetAllMerchantTraditionalPosListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetAllMerchantTraditionalPosListBean.DataBean.AllMerchantTraditionalPosListBean> bean = null;
                        switch (mType) {
                            case 0:
                                bean = response.getData().getAllMerchantTraditionalPosList();
                                break;
                            case 1:
                                bean = response.getData().getExcellentMerchantTraditionalPosList();
                                break;
                            case 2:
                                bean = response.getData().getActiveMerchantTraditionalPosList();
                                break;
                            case 3:
                                bean = response.getData().getDormantMerchantTraditionalPosList();
                                break;
                            case 4:
                                bean = response.getData().getDormantMerchantTraditionalPosList();
                                break;
                            case 6:
                                bean = response.getData().getAllMerchantMposList();
                                break;
                            case 7:
                                bean = response.getData().getExcellentMerchantMposList();
                                break;
                            case 8:
                                bean = response.getData().getActiveMerchantMposList();
                                break;
                            case 9:
                                bean = response.getData().getActiveMerchantMposList();
                                break;
                            case 10:
                                bean = response.getData().getDormantMerchantMposList();
                                break;
                            case 12:
                                bean = response.getData().getAllMerchantTraditionalPosList();
                                break;
                            case 13:
                                bean = response.getData().getExcellentMerchantTraditionalPosList();
                                break;
                            case 14:
                                bean = response.getData().getActiveMerchantTraditionalPosList();
                                break;
                            case 15:
                                bean = response.getData().getDormantMerchantTraditionalPosList();
                                break;
                            case 16:
                                bean = response.getData().getDormantMerchantTraditionalPosList();
                                break;
                        }
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(bean);
                        if (mType <= 4 || mType >=12) {
                            if (mData.size() > 0)
                                last_id = mData.get(mData.size() - 1).getTrapos_id();
                        } else {
                            if (mData.size() > 0)
                                last_id = mData.get(mData.size() - 1).getMpos_id();
                        }
                        mMerchantAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetAllMerchantTraditionalPosListBean response) {
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

    }

    public void finishLoad() {
        if ("up".equals(refstatus)) refreshLayout.finishLoadMore();
        if ("down".equals(refstatus)) refreshLayout.finishRefresh();
    }

    @OnClick({R.id.left_action, R.id.right_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                break;
        }
    }

}
