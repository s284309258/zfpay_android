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
import android.view.MotionEvent;
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
import com.lckj.jycm.zfqg_network.EditMposMerInfoRequest;
import com.lckj.jycm.zfqg_network.GetMposListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UpdateMerchantNameAndTelRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.qtopay.agentlibrary.APIProcxy;
import com.qtopay.agentlibrary.entity.response.SdkResponseModel;
import com.qtopay.agentlibrary.present.listener.AddMerchantListener;
import com.qtopay.agentlibrary.present.listener.QtopaySdkInterface;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeMPOSActivity extends BaseActvity {

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
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_sn)
    TextView tvSn;
    @BindView(R.id.rl_sn)
    RelativeLayout rlSn;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_pay_pw)
    ClearEditText tvPayPw;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    private HomePosAdapter mHomePosAdapter;
    private String mSn;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    List<GetMposListBean.DataBean.MposListBean> mData = new ArrayList<>();
    List<GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean> mData2 = new ArrayList<>();
    private String mUpdateSn;
    private int mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mpos);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        if (TextUtils.isEmpty(mUpdateSn))
            initData();
    }

    @Override
    protected void initView() {
        customTitle.setText("MPOS商户登记");
        mType = getIntent().getIntExtra("type", 0);
        mUpdateSn = getIntent().getStringExtra("sn");
        if (mType == 1) {
            tvSn.setText(mUpdateSn);
            customTitle.setText("修改");
            tvHint.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        } else if (mType == 2) {
            customTitle.setText("传统POS商户登记");
            tvHint.setVisibility(View.VISIBLE);
            btnSkip.setVisibility(View.VISIBLE);
        } else if (mType == 3) {
            customTitle.setText("电签EPOS商户登记");
            tvHint.setVisibility(View.VISIBLE);
            btnSkip.setVisibility(View.VISIBLE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHomePosAdapter = new HomePosAdapter(this, mType == 0 ? mData : mData2);
        recyclerView.setAdapter(mHomePosAdapter);
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
        if (mType == 0) {
            mMyService.getMposList(new TokenRequest(dataManager.getToken(), key_word, last_id))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetMposListBean>(this) {
                        @Override
                        public void onSuccessCall(GetMposListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetMposListBean.DataBean.MposListBean> userFeedBackList = response.getData().getMposList();
                            if (TextUtils.isEmpty(last_id)) mData.clear();
                            mData.addAll(userFeedBackList);
                            if (mData.size() > 0)
                                last_id = mData.get(mData.size() - 1).getMpos_id();
                            mHomePosAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetMposListBean response) {
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
        } else if (mType == 2) {
            mMyService.getTraditionalPosAllocationList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), null, null))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetTraditionalPosAllocationListBean>(this) {
                        @Override
                        public void onSuccessCall(GetTraditionalPosAllocationListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean> userFeedBackList = response.getData().getTraditionalPosAllocationList();
                            mData2.clear();
                            mData2.addAll(userFeedBackList);
                            mHomePosAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetTraditionalPosAllocationListBean response) {
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
        } else if (mType == 3) {
            mMyService.getTraditionalPosAllocationList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), null, null, "epos"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<GetTraditionalPosAllocationListBean>(this) {
                        @Override
                        public void onSuccessCall(GetTraditionalPosAllocationListBean response) {
                            ProgressDlgHelper.closeDialog();
                            finishLoad();
                            List<GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean> userFeedBackList = response.getData().getTraditionalPosAllocationList();
                            mData2.clear();
                            mData2.addAll(userFeedBackList);
                            mHomePosAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailedCall(GetTraditionalPosAllocationListBean response) {
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

    }

    public void finishLoad() {
        if ("up".equals(refstatus)) refreshLayout.finishLoadMore();
        if ("down".equals(refstatus)) refreshLayout.finishRefresh();
    }

    @OnClick({R.id.left_action, R.id.rl_sn, R.id.btn_submit, R.id.btn_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_sn:
                if (mType == 0 || mType == 2 || mType == 3)
                    drawerLayout.openDrawer(Gravity.END);
                break;
            case R.id.btn_submit:
                if (checkEdit()) {
                    submit();
                }
                break;
            case R.id.btn_skip:
                addStore();
                break;
        }
    }

    private void addStore() {
        QtopaySdkInterface qtopaysdkInterface = APIProcxy.Companion.getQtopayImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("other", "android");
        map.put("account", dataManager.getAppId());
//      map.put("account", "dlhe");
        qtopaysdkInterface.sdkDatas(this, map, new AddMerchantListener() {
            @Override
            public void _onAccept(SdkResponseModel sdkResponseModel) {
                //新增商户回调
                Utils.showMsg(HomeMPOSActivity.this, sdkResponseModel.getReturnMsg());
            }
        });
    }

    private boolean checkEdit() {
        if (TextUtils.isEmpty(tvSn.getText().toString())) {
            Utils.showMsg(this, tvSn.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etName.getText().toString())) {
            Utils.showMsg(this, etName.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Utils.showMsg(this, etPhone.getHint().toString());
            return false;
        }
        return true;
    }

    private void submit() {
        ProgressDlgHelper.openDialog(this);
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        Observable<HttpResponse> observable = null;
        if (mType == 0) {
            observable = mMyService.editMposMerInfo(new EditMposMerInfoRequest(dataManager.getToken(), name, phone, mSn));
        } else if (mType == 1 || mType == 2 || mType == 3) {
            observable = mMyService.updateMerchantNameAndTel(new UpdateMerchantNameAndTelRequest(dataManager.getToken(),  name, phone,mType == 1 ? mUpdateSn : mSn, mType == 3 ? "epos" : null));
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(HomeMPOSActivity.this, getString(R.string.提交成功));
                        addStore();
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }

    public void setSn(String sn) {
        mSn = sn;
        tvSn.setText(sn);
        drawerLayout.closeDrawer(Gravity.END);
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
}
