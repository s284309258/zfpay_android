package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetTraditionalPosInstallDetailBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosInstallDetailRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.MerchantInDetailsAdapter;
import com.qtopay.agentlibrary.APIProcxy;
import com.qtopay.agentlibrary.entity.response.SdkResponseModel;
import com.qtopay.agentlibrary.present.listener.ModifyMerchantListener;
import com.qtopay.agentlibrary.present.listener.QueryDetailsSdkInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MerchantInDetailsActivity extends BaseActvity {
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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_cause)
    TextView tvCause;
    @BindView(R.id.rl_cause)
    RelativeLayout rlCause;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.xx)
    View xx;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private String mId;
    private int mType;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<GetTraditionalPosInstallDetailBean.DataBean.TerminalListBean> mData = new ArrayList<>();
    private MerchantInDetailsAdapter mMerchantInDetailsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_in_details);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.详情));
        mId = getIntent().getStringExtra("id");
        mType = getIntent().getIntExtra("type", 0);
        if (mType == 0) {
            rlCause.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);
        } else {
            rlCause.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.VISIBLE);
            rl.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMerchantInDetailsAdapter = new MerchantInDetailsAdapter(this, mData);
        recyclerView.setAdapter(mMerchantInDetailsAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getTraditionalPosInstallDetail(new GetTraditionalPosInstallDetailRequest(mId, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosInstallDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosInstallDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        ll.setVisibility(View.VISIBLE);
                        GetTraditionalPosInstallDetailBean.DataBean.TraditionalPosInstallDetailBean bean = response.getData().getTraditionalPosInstallDetail();
                        mData.clear();
                        mData.addAll(response.getData().getTerminalList());
                        mMerchantInDetailsAdapter.notifyDataSetChanged();
                        if (!TextUtils.isEmpty(bean.getMerchant_name()))
                            tvName.setText(bean.getMerchant_name());
                        if (bean.getBiz_code().equals("00")) {
                            tvStatus.setText(getString(R.string.审核通过));
                            tvStatus.setTextColor(getResources().getColor(R.color.green2));
                        } else {
                            tvStatus.setText(getString(R.string.审核不通过));
                            tvStatus.setTextColor(getResources().getColor(R.color.red));
                        }
                        if (!TextUtils.isEmpty(bean.getBiz_msg()))
                            tvCause.setText(bean.getBiz_msg().replace("审核不通过：", ""));
                        if (!TextUtils.isEmpty(bean.getMer_code()))
                            tvId.setText(bean.getMer_code());
                        if (!TextUtils.isEmpty(bean.getAgent_id()))
                            tvUsername.setText(bean.getAgent_id());
                        if (!TextUtils.isEmpty(bean.getSettle_flag()))
                            tvTag.setText(bean.getSettle_flag() + getString(R.string.到账));
                        if (!TextUtils.isEmpty(bean.getCre_datetime()))
                            tvTime.setText(bean.getCre_datetime());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.btn_confirm:
                updateStore();
                break;
        }
    }

    private void updateStore() {
        QueryDetailsSdkInterface qtopayDetailsImpl = APIProcxy.Companion.getQtopayDetailsImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("other", "sdk name");
        map.put("account", dataManager.getAppId());
        map.put("merchantName", tvName.getText().toString());
        qtopayDetailsImpl.queryDetailsDatas(this, map, new ModifyMerchantListener() {
            @Override
            public void _onAccept(SdkResponseModel sdkResponseModel) {
                //修改商户回调
                Utils.showMsg(MerchantInDetailsActivity.this,sdkResponseModel.getReturnMsg());
            }
        });
    }
}
