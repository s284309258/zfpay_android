package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.MyService;
import com.qtopay.agentlibrary.APIProcxy;
import com.qtopay.agentlibrary.entity.response.SdkResponseModel;
import com.qtopay.agentlibrary.present.listener.AddMerchantListener;
import com.qtopay.agentlibrary.present.listener.QtopaySdkInterface;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePOSActivity extends BaseActvity {

    @BindView(R.id.custom_title)
    TextView customTitle;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    @BindView(R.id.rl_qrcode_pay)
    RelativeLayout rlQrcodePay;
    private boolean isPos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pos);
        ButterKnife.bind(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        isPos = getIntent().getBooleanExtra("isPos", false);
        customTitle.setText(isPos ? getString(R.string.传统POS) : "EPOS");
        rlQrcodePay.setVisibility(isPos ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.rl_new, R.id.rl_merchant_in, R.id.rl_qrcode_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_new:
//                addStore();
                startActivity(new Intent(this, HomeMPOSActivity.class).putExtra("type", isPos ? 2 : 3));
                break;
            case R.id.rl_merchant_in:
                startActivity(new Intent(this, MerchantInActivity.class).putExtra("isPos", isPos));
//                updateStore();
                break;
            case R.id.rl_qrcode_pay:
                startActivity(new Intent(this, ApplyQrcodePayActivity.class));
                break;
        }
    }


    private void addStore() {
        QtopaySdkInterface qtopaysdkInterface = APIProcxy.Companion.getQtopayImpl();
        Map<String, String> map = new TreeMap<>();
        map.put("other", "sdk name");
        map.put("account", dataManager.getAppId());
        qtopaysdkInterface.sdkDatas(this, map, new AddMerchantListener() {
            @Override
            public void _onAccept(SdkResponseModel sdkResponseModel) {
                //新增商户回调
                Utils.showMsg(HomePOSActivity.this, sdkResponseModel.getReturnMsg());
            }
        });
    }
}
