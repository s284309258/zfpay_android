package com.lckj.zfqg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetMposAllocationListBean;
import com.lckj.jycm.zfqg_network.GetMposRecallListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListRequest;
import com.lckj.jycm.zfqg_network.GetTraditionalPosRecallListBean;
import com.lckj.jycm.zfqg_network.GetTrafficCardAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTrafficCardRecallListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.RecallTrafficCardRequest;
import com.lckj.jycm.zfqg_network.SnListRequest;
import com.lckj.jycm.zfqg_network.SnRequest;
import com.lckj.zfqg.activity.CardAllocationActivity;
import com.lckj.zfqg.activity.MPOSAllocationActivity;
import com.lckj.zfqg.activity.POSAllocationActivity;
import com.lckj.zfqg.adapter.AllocationRecallAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllocationRecallFragment<E> extends BaseFragment {
    @BindView(R.id.tv_epos)
    TextView tvEpos;
    private int mType;
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.et_start)
    EditText etStart;
    @BindView(R.id.et_end)
    EditText etEnd;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.tv_all)
    TextView tvAll;
    private Unbinder mBind;
    private AllocationRecallAdapter mAllocationRecallAdapter;
    private int mSubtype;
    private boolean isAll;
    private String mStartKey;
    private String mEndKey;
    private List<E> mData = new ArrayList<>();
    private MyService mMyService;
    private DataManager dataManager;
    private String mSn;

    public void setType(int type, MyService myService, DataManager dataManager) {
        mType = type;
        mMyService = myService;
        this.dataManager = dataManager;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_allocation_recall);
        mBind = ButterKnife.bind(this, view);
        initView();
        initData();
        count();
    }

    //新增EPOS
    public void initData() {
        try {
            if (etStart == null) return;
            if (dataManager == null) return;
            mStartKey = etStart.getText().toString().trim();
            mEndKey = etEnd.getText().toString().trim();
            ProgressDlgHelper.openDialog(getContext());
            tvAll.setSelected(false);
            if (mType == 0) {
                switch (mSubtype) {
                    case 0:
                        mMyService.getMposAllocationList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetMposAllocationListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetMposAllocationListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getMposAllocationList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));

                        break;
                    case 1:
                        mMyService.getTraditionalPosAllocationList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTraditionalPosAllocationListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTraditionalPosAllocationListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTraditionalPosAllocationList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                    case 2:
                        mMyService.getTraditionalPosAllocationList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey, "epos"))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTraditionalPosAllocationListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTraditionalPosAllocationListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTraditionalPosAllocationList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                    case 3:
                        mMyService.getTrafficCardAllocationList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTrafficCardAllocationListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTrafficCardAllocationListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTrafficCardAllocationList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                }
            } else if (mType == 1) {
                switch (mSubtype) {
                    case 0:
                        mMyService.getMposRecallList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetMposRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetMposRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getMposRecallList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));

                        break;
                    case 1:
                        mMyService.getTraditionalPosRecallList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTraditionalPosRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTraditionalPosRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTraditionalPosRecallList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                    case 2:
                        mMyService.getTraditionalPosRecallList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey, "epos"))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTraditionalPosRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTraditionalPosRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTraditionalPosRecallList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                    case 3:
                        mMyService.getTrafficCardRecallList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTrafficCardRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTrafficCardRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTrafficCardRecallList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                }
            } else if (mType == 2) {
                switch (mSubtype) {
                    case 0:
                        mMyService.selectUnbindMpos(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetMposRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetMposRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getMposUnbindList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));

                        break;
                    case 1:
                        mMyService.selectUnbindTraditionalPos(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTraditionalPosRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTraditionalPosRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTraditionalPosUnbindList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                    case 2:
                        mMyService.selectUnbindTraditionalPos(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey, "epos"))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseConsumer<GetTraditionalPosRecallListBean>(getContext()) {
                                    @Override
                                    public void onSuccessCall(GetTraditionalPosRecallListBean response) {
                                        etStart.setText("");
                                        etEnd.setText("");
                                        ProgressDlgHelper.closeDialog();
                                        mData.clear();
                                        mAllocationRecallAdapter.mMap.clear();
                                        mData.addAll((Collection<? extends E>) response.getData().getTraditionalPosUnbindList());
                                        mAllocationRecallAdapter.notifyDataSetChanged();
                                        count();
                                    }
                                }, new ThrowableConsumer<Throwable>(this));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAllocationRecallAdapter = new AllocationRecallAdapter(getContext(), mData, mType, this);
//        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        recyclerView.setAdapter(mAllocationRecallAdapter);

        if (mType == 0) {
            btnConfirm.setText(getString(R.string.下一步));
        } else if (mType == 1) {
            btnConfirm.setText(getString(R.string.确认召回));
        } else if (mType == 2) {
            btnConfirm.setText(getString(R.string.提交申请));
            tvCard.setVisibility(View.INVISIBLE);
        }
        tvMpos.setSelected(true);
        mAllocationRecallAdapter.setSubtype(mSubtype);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.tv_mpos, R.id.tv_pos, R.id.tv_epos, R.id.tv_card, R.id.tv_search, R.id.tv_all, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mpos:
                setSelector(0);
                break;
            case R.id.tv_pos:
                setSelector(1);
                break;
            case R.id.tv_epos:
                setSelector(2);
                break;
            case R.id.tv_card:
                setSelector(3);
                break;
            case R.id.tv_search:
                initData();
                break;
            case R.id.tv_all:
                isAll = !isAll;
                tvAll.setSelected(isAll);
                mAllocationRecallAdapter.setAll(isAll);
                mAllocationRecallAdapter.notifyDataSetChanged();
                count();
                break;
            case R.id.btn_confirm:
                mSn = "";
                if (mType == 0) {
                    allocation();
                } else if (mType == 1) {
                    recall();
                } else if (mType == 2) {
                    unbind();
                }
                break;
        }
    }

    private void unbind() {
        switch (mSubtype) {
            case 0:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetMposRecallListBean.DataBean.MposRecallListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetMposRecallListBean.DataBean.MposRecallListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要解绑的MPOS));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.unbindMpos(new SnRequest(mSn, dataManager.getToken()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.申请解绑成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
            case 1:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要解绑的POS));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.unbindTraditionalPos(new SnRequest(mSn, dataManager.getToken()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.申请解绑成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
            case 2:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要解绑的EPOS));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.unbindTraditionalPos(new SnRequest(mSn, dataManager.getToken(), "", "epos"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.申请解绑成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
        }
    }

    public void count() {
        int count = 0;
        if (mAllocationRecallAdapter.mMap != null) {
            for (int i = 0; i < mAllocationRecallAdapter.mMap.size(); i++) {
                if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                    count++;
                }
            }
        }
        tvAll.setText(getString(R.string.全选) + "(" + count + ")");
    }

    private void recall() {
        switch (mSubtype) {
            case 0:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetMposRecallListBean.DataBean.MposRecallListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetMposRecallListBean.DataBean.MposRecallListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要召回的MPOS));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.recallMpos(new SnListRequest(mSn, dataManager.getToken()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.召回成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
            case 1:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要召回的POS));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.recallTraditionalPos(new SnListRequest(mSn, dataManager.getToken()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.召回成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
            case 2:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要召回的EPOS));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.recallTraditionalPos(new SnListRequest(mSn, dataManager.getToken(), "epos"))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.召回成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
            case 3:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTrafficCardRecallListBean.DataBean.TrafficCardRecallListBean) mData.get(i)).getCard_no();
                        } else {
                            mSn += "," + ((GetTrafficCardRecallListBean.DataBean.TrafficCardRecallListBean) mData.get(i)).getCard_no();
                        }
                    }
                }
                if (TextUtils.isEmpty(mSn)) {
                    Utils.showMsg(getContext(), getString(R.string.请选择需要召回的流量卡));
                    return;
                }
                ProgressDlgHelper.openDialog(getContext());
                mMyService.recallTrafficCard(new RecallTrafficCardRequest(mSn, dataManager.getToken()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseConsumer<HttpResponse>(this) {
                            @Override
                            public void onSuccessCall(HttpResponse response) {
                                ProgressDlgHelper.closeDialog();
                                Utils.showMsg(getContext(), getString(R.string.召回成功));
                                initData();
                            }
                        }, new ThrowableConsumer<Throwable>(this));
                break;
        }
    }

    private void allocation() {
        switch (mSubtype) {
            case 0:
                boolean isMposOne = true;
                GetMposAllocationListBean.DataBean.MposAllocationListBean mpos = null;
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (isMposOne) {
                            isMposOne = false;
                            mpos = (GetMposAllocationListBean.DataBean.MposAllocationListBean) mData.get(i);
                        }
                        GetMposAllocationListBean.DataBean.MposAllocationListBean bean1 = (GetMposAllocationListBean.DataBean.MposAllocationListBean) mData.get(i);
                        if (!mpos.getCard_settle_price().equals(bean1.getCard_settle_price()) ||
                                !mpos.getCash_back_rate().equals(bean1.getCash_back_rate()) ||
                                !mpos.getCloud_settle_price().equals(bean1.getCloud_settle_price()) ||
                                !mpos.getSingle_profit_rate().equals(bean1.getSingle_profit_rate())) {
                            Utils.showMsg(getContext(), getString(R.string.请选择参数统一的机具));
                            return;
                        }
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetMposAllocationListBean.DataBean.MposAllocationListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetMposAllocationListBean.DataBean.MposAllocationListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (!TextUtils.isEmpty(mSn))
                    getActivity().startActivityForResult(new Intent(getContext(), MPOSAllocationActivity.class).putExtra("sn", mSn), 1);
                else
                    Utils.showMsg(getContext(), getString(R.string.请选择需要分配的MPOS));
                break;
            case 1:
                boolean isPosOne = true;
                GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean pos = null;
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (isPosOne) {
                            isPosOne = false;
                            pos = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                        }
                        GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean bean1 = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                        if (!pos.getCard_settle_price().equals(bean1.getCard_settle_price()) ||
                                !pos.getCash_back_rate().equals(bean1.getCash_back_rate()) ||
                                !pos.getCloud_settle_price().equals(bean1.getCloud_settle_price()) ||
                                !pos.getSingle_profit_rate().equals(bean1.getSingle_profit_rate()) ||
                                !pos.getWeixin_settle_price().equals(bean1.getWeixin_settle_price()) ||
                                !pos.getZhifubao_settle_price().equals(bean1.getZhifubao_settle_price())||
                                !pos.getCard_settle_price_vip().equals(bean1.getCard_settle_price_vip())) {
                            Utils.showMsg(getContext(), getString(R.string.请选择参数统一的机具));
                            return;
                        }
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (!TextUtils.isEmpty(mSn))
                    getActivity().startActivityForResult(new Intent(getContext(), POSAllocationActivity.class).putExtra("sn", mSn).putExtra("isPos", true), 1);
                else
                    Utils.showMsg(getContext(), getString(R.string.请选择需要分配的POS));
                break;
            case 2:
                boolean isEposOne = true;
                GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean epos = null;
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (isEposOne) {
                            isEposOne = false;
                            epos = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                        }
                        GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean bean1 = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                        if (!epos.getCard_settle_price().equals(bean1.getCard_settle_price()) ||
                                !epos.getCash_back_rate().equals(bean1.getCash_back_rate()) ||
                                !epos.getCloud_settle_price().equals(bean1.getCloud_settle_price()) ||
                                !epos.getSingle_profit_rate().equals(bean1.getSingle_profit_rate()) ||
                                !epos.getWeixin_settle_price().equals(bean1.getWeixin_settle_price()) ||
                                !epos.getZhifubao_settle_price().equals(bean1.getZhifubao_settle_price())||
                                !epos.getCard_settle_price_vip().equals(bean1.getCard_settle_price_vip())) {
                            Utils.showMsg(getContext(), getString(R.string.请选择参数统一的机具));
                            return;
                        }
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i)).getSn();
                        } else {
                            mSn += "," + ((GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i)).getSn();
                        }
                    }
                }
                if (!TextUtils.isEmpty(mSn))
                    getActivity().startActivityForResult(new Intent(getContext(), POSAllocationActivity.class).putExtra("sn", mSn), 1);
                else
                    Utils.showMsg(getContext(), getString(R.string.请选择需要分配的EPOS));
                break;
            case 3:
                for (int i = 0; i < mData.size(); i++) {
                    if ((Boolean) mAllocationRecallAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += ((GetTrafficCardAllocationListBean.DataBean.TrafficCardAllocationListBean) mData.get(i)).getCard_no();
                        } else {
                            mSn += "," + ((GetTrafficCardAllocationListBean.DataBean.TrafficCardAllocationListBean) mData.get(i)).getCard_no();
                        }
                    }
                }
                if (!TextUtils.isEmpty(mSn))
                    getActivity().startActivityForResult(new Intent(getContext(), CardAllocationActivity.class).putExtra("sn", mSn), 1);
                else
                    Utils.showMsg(getContext(), getString(R.string.请选择需要分配的流量卡));
                break;
        }
    }

    private void setSelector(int subtype) {
        mSubtype = subtype;
        mData.clear();
        mAllocationRecallAdapter.notifyDataSetChanged();
        mAllocationRecallAdapter.mMap.clear();
        mAllocationRecallAdapter.setSubtype(mSubtype);
        mStartKey = "";
        mEndKey = "";
        isAll = false;
        tvAll.setSelected(false);
        switch (subtype) {
            case 0:
                tvMpos.setSelected(true);
                tvPos.setSelected(false);
                tvEpos.setSelected(false);
                tvCard.setSelected(false);
                break;
            case 1:
                tvMpos.setSelected(false);
                tvPos.setSelected(true);
                tvEpos.setSelected(false);
                tvCard.setSelected(false);
                break;
            case 2:
                tvMpos.setSelected(false);
                tvPos.setSelected(false);
                tvEpos.setSelected(true);
                tvCard.setSelected(false);
                break;
            case 3:
                tvMpos.setSelected(false);
                tvPos.setSelected(false);
                tvEpos.setSelected(false);
                tvCard.setSelected(true);
                break;
        }
        initData();
    }
}
