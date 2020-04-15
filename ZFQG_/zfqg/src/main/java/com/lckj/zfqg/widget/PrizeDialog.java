package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.ChooseAwardRequest;
import com.lckj.jycm.zfqg_network.EditAllocationMPosBatchRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SelectPolicy3RecordBean;
import com.lckj.zfqg.activity.MPOSAllocationUpdateActivity;
import com.lckj.zfqg.activity.StandardMerchantListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PrizeDialog extends Dialog {
    private final Context mContext;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mId;
    private String mMer_id;
    private String mMer_name;

    public PrizeDialog(Context context, int dialog) {
        super(context, dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_prize);
        MainApplication.getInjectGraph().inject(this);
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                confirm();
                break;
        }
    }

    private void confirm() {
        ProgressDlgHelper.openDialog(mContext);
        mMyService.chooseAward(new ChooseAwardRequest(mId, dataManager.getToken(), mMer_id, mMer_name))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(mContext) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        dismiss();
                        Utils.showMsg(mContext, mContext.getString(R.string.领取成功));
                        ((StandardMerchantListActivity) mContext).initData();
                    }
                }, new ThrowableConsumer<Throwable>(mContext));
    }


    public void show(String id, String policyName, String mer_id, String mer_name) {
        show();
        mId = id;
        tvTitle.setText(policyName);
        mMer_id = mer_id;
        mMer_name = mer_name;
    }
}
