package com.lckj.zfqg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreQueryActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tv_direct_count)
    TextView tvDirectCount;
    @BindView(R.id.rl_direct)
    RelativeLayout rlDirect;
    @BindView(R.id.tv_agency_count)
    TextView tvAgencyCount;
    @BindView(R.id.rl_agency)
    RelativeLayout rlAgency;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_query);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.商户查询));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.rl_direct, R.id.rl_agency})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_direct:
                startActivity(new Intent(this, DirectActivity.class));
                break;
            case R.id.rl_agency:
                startActivity(new Intent(this, AgencyActivity.class));
                break;
        }
    }
}
