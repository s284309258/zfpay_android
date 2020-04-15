package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.ACache;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetMposAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.activity.POSAllocationActivity;
import com.lckj.zfqg.activity.UpdateRateActivity;
import com.lckj.zfqg.adapter.RateApplyAdapter;
import com.mob.tools.RxMob;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@SuppressLint("ValidFragment")
public class RateApplyFragment extends BaseFragment {
    private final int mType;
    @BindView(R.id.et_start)
    EditText etStart;
    @BindView(R.id.et_end)
    EditText etEnd;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_next)
    Button btnNext;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private RateApplyAdapter mRateApplyAdapter;
    private boolean isAll;
    private String mStartKey;
    private String mEndKey;
    private String mSn;
    private List<GetApplyRateTraditionalPosListBean.DataBean.ApplyRateTraditionalPosListBean> mData = new ArrayList<>();

    public RateApplyFragment(int type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_rate_apply);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initData();
    }

    public void initData() {
        if (etStart == null) return;
        mStartKey = etStart.getText().toString().trim();
        mEndKey = etEnd.getText().toString().trim();
        ProgressDlgHelper.openDialog(getContext());
        Observable<GetApplyRateTraditionalPosListBean> request = null;
        if (mType == 0) {
            request = mMyService.getApplyRateTraditionalPosList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey));
        } else if (mType == 2) {
            request = mMyService.getApplyRateTraditionalPosList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey, "epos"));
        } else {
            request = mMyService.getApplyRateMposList(new GetTraditionalPosAllocationListRequest(dataManager.getToken(), mStartKey, mEndKey));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetApplyRateTraditionalPosListBean>(getContext()) {
                    @Override
                    public void onSuccessCall(GetApplyRateTraditionalPosListBean response) {
                        etStart.setText("");
                        etEnd.setText("");
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        mRateApplyAdapter.mMap.clear();
                        if (mType == 0 || mType == 2) {
                            mData.addAll(response.getData().getApplyRateTraditionalPosList());
                        } else {
                            mData.addAll(response.getData().getApplyRateMposList());
                        }
                        mRateApplyAdapter.notifyDataSetChanged();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRateApplyAdapter = new RateApplyAdapter(getContext(), mData, mType);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        recyclerView.setAdapter(mRateApplyAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.tv_search, R.id.tv_all, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                initData();
                break;
            case R.id.tv_all:
                isAll = !isAll;
                tvAll.setSelected(isAll);
                mRateApplyAdapter.setAll(isAll);
                mRateApplyAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_next:
                mSn = "";
                for (int i = 0; i < mData.size(); i++) {
                    if (mRateApplyAdapter.mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += mData.get(i).getSn();
                        } else {
                            mSn += "," + mData.get(i).getSn();
                        }
                    }
                }
                if (mType == 0) {
                    if (!TextUtils.isEmpty(mSn))
                        getActivity().startActivityForResult(new Intent(getContext(), UpdateRateActivity.class).putExtra("sn", mSn).putExtra("type", mType), 2);
                    else
                        Utils.showMsg(getContext(), getString(R.string.请选择需要申请的SN码));
                } else {
                    if (!TextUtils.isEmpty(mSn))
                        getActivity().startActivityForResult(new Intent(getContext(), UpdateRateActivity.class).putExtra("sn", mSn).putExtra("type", mType), 2);
                    else
                        Utils.showMsg(getContext(), getString(R.string.请选择需要申请的SN码));
                }
                break;
        }
    }
}
