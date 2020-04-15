package com.lckj.jycm.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.AccountRecordListBean;
import com.lckj.jycm.network.AccountRecordListRequest;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.wallet.GainRecordAdapter;
import com.lckj.jycm.wallet.WithdrawalRecordAdapter;
import com.lckj.jycm.wallet.bean.WithdrawalRecordData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GainRecordFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Unbinder bind;
    List<AccountRecordListBean.DataBean.ListBean> mData = new ArrayList<>();
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;
    private GainRecordAdapter mAdatper;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frg_withdrawal_record);
        MainApplication.getInstance().getInjectGraph().inject(this);
        bind = ButterKnife.bind(this, view);
        initView();
        getData();
    }

    private void getData() {
        ProgressDlgHelper.openDialog(getActivity());
        mFrontUserService.showAccountRecordList(new AccountRecordListRequest(1, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<AccountRecordListBean>(this) {
                    @Override
                    public void onSuccessCall(AccountRecordListBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            MyWalletActivity activity = (MyWalletActivity) getActivity();
                            activity.setData(response.getData().getFuAccount(), 1);
                            mData.addAll(response.getData().getList());
                            mAdatper.notifyDataSetChanged();
                        } else {
                            Utils.showMsg(getActivity(), response.getMsg());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdatper = new GainRecordAdapter(mData);
        recyclerView.setAdapter(mAdatper);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
