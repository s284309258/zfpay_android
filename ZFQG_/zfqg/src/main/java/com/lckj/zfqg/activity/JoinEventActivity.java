package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetTraditionalPosOnlineActivityDetailBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosOnlineActivityDetailRequest;
import com.lckj.jycm.zfqg_network.GetTraditionalPosPartActivityInfoBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SubmitTraditionalPosActivityApplyRequest;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.widget.GradeDialog;
import com.lckj.zfqg.widget.SnDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JoinEventActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.rl_grade)
    RelativeLayout rlGrade;
    @BindView(R.id.tv_sn)
    TextView tvSn;
    @BindView(R.id.rl_sn)
    RelativeLayout rlSn;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private GradeDialog mGradeDialog;
    private SnDialog mSnDialog;
    private int mSubsetType;
    private String mId;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosActivityRewardListBean> mRewardList = new ArrayList<>();
    List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosListBean> mSnList = new ArrayList<>();
    private String mReward_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mSubsetType = getIntent().getIntExtra("subsetType", 0);
        mId = getIntent().getStringExtra("id");
        customTitle.setText(getString(R.string.参与活动));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        Observable<GetTraditionalPosPartActivityInfoBean> request = null;
        if (mSubsetType == 0 || mSubsetType == 2) {
            request = mMyService.getTraditionalPosPartActivityInfo(new GetTraditionalPosOnlineActivityDetailRequest(dataManager.getToken(), mId, mSubsetType == 2 ? "epos" : null));
        } else {
            request = mMyService.getMposPartActivityInfo(new GetTraditionalPosOnlineActivityDetailRequest(dataManager.getToken(), mId));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosPartActivityInfoBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosPartActivityInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (mSubsetType == 0 || mSubsetType == 2) {
                            mRewardList.addAll(response.getData().getTraditionalPosActivityRewardList());
                            mSnList.addAll(response.getData().getTraditionalPosList());
                        } else {
                            mRewardList.addAll(response.getData().getMposActivityRewardList());
                            mSnList.addAll(response.getData().getMposList());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }

    @OnClick({R.id.left_action, R.id.rl_grade, R.id.rl_sn, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_grade:
                showGradeDialog();
                break;
            case R.id.rl_sn:
                showSnDialog();
                break;
            case R.id.btn_submit:
                if (TextUtils.isEmpty(tvGrade.getText().toString())) {
                    Utils.showMsg(this, tvGrade.getHint().toString());
                } else if (TextUtils.isEmpty(tvSn.getText().toString())) {
                    Utils.showMsg(this, tvSn.getHint().toString());
                } else {
                    submit();
                }
                break;
        }
    }

    private void submit() {
        ProgressDlgHelper.openDialog(this);
        Observable<HttpResponse> request = null;
        if (mSubsetType == 0 || mSubsetType == 2) {
            request = mMyService.submitTraditionalPosActivityApply(new SubmitTraditionalPosActivityApplyRequest(dataManager.getToken(), mReward_id, tvSn.getText().toString()));
        } else {
            request = mMyService.submitMposActivityApply(new SubmitTraditionalPosActivityApplyRequest(dataManager.getToken(), mReward_id, tvSn.getText().toString()));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        finish();
                        Utils.showMsg(JoinEventActivity.this, getString(R.string.参加活动成功));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void showSnDialog() {
        if (mSnDialog == null)
            mSnDialog = new SnDialog(this, R.style.BottomDialog2, mSnList);
        mSnDialog.show();
    }

    private void showGradeDialog() {
        if (mGradeDialog == null)
            mGradeDialog = new GradeDialog(this, R.style.BottomDialog2, mRewardList);
        mGradeDialog.show();
    }

    public void setGrade(String grade, String reward_id) {
        tvGrade.setText(grade);
        mReward_id = reward_id;
    }

    public void setSn(String sn) {
        tvSn.setText(sn);
    }
}
