package com.lckj.zfqg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SnListRequest;
import com.lckj.jycm.zfqg_network.SnRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.activity.UnbindRecordActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UnbindFragment extends BaseFragment {
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.et_sn)
    ClearEditText etSn;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_store)
    ClearEditText etStore;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private int mType;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_unbind);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initView();
    }

    @Override
    protected void initView() {
        tvMpos.setSelected(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.tv_mpos, R.id.tv_pos, R.id.btn_submit, R.id.btn_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mpos:
                mType = 0;
                etSn.setText("");
                etStore.setText("");
                tvMpos.setSelected(true);
                tvPos.setSelected(false);
                tv.setVisibility(View.GONE);
                etStore.setVisibility(View.GONE);
                break;
            case R.id.tv_pos:
                mType = 1;
                etSn.setText("");
                etStore.setText("");
                tvMpos.setSelected(false);
                tvPos.setSelected(true);
                tv.setVisibility(View.VISIBLE);
                etStore.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_submit:
                if(TextUtils.isEmpty(etSn.getText().toString())){
                    Utils.showMsg(getContext(), etSn.getHint().toString());
                }else {
                    submit();
                }
                break;
            case R.id.btn_record:
                startActivity(new Intent(getContext(), UnbindRecordActivity.class).putExtra("type", mType));
                break;
        }
    }

    private void submit() {
       /* String sn = etSn.getText().toString().trim();
        String store = etStore.getText().toString().trim();
        if(mType == 0){
            ProgressDlgHelper.openDialog(getContext());
            mMyService.unbindMpos(new SnListRequest(sn,dataManager.getToken()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<HttpResponse>(this) {
                        @Override
                        public void onSuccessCall(HttpResponse response) {
                            ProgressDlgHelper.closeDialog();
                            Utils.showMsg(getContext(), getString(R.string.申请解绑成功));
                        }
                    }, new ThrowableConsumer<Throwable>(this));
        }else {
            ProgressDlgHelper.openDialog(getContext());
            mMyService.unbindTraditionalPos(new SnListRequest(sn,dataManager.getToken(), store))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseConsumer<HttpResponse>(this) {
                        @Override
                        public void onSuccessCall(HttpResponse response) {
                            ProgressDlgHelper.closeDialog();
                            Utils.showMsg(getContext(), getString(R.string.申请解绑成功));
                        }
                    }, new ThrowableConsumer<Throwable>(this));
        }*/
    }
}