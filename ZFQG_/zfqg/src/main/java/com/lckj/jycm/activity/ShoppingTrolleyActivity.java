package com.lckj.jycm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.adapter.ShoppingTrolleyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingTrolleyActivity extends BaseActvity {

    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.cb_edit)
    CheckBox cbEdit;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.rl_edit_menu)
    RelativeLayout rlEditMenu;
    @BindView(R.id.cb_invest)
    CheckBox cbInvest;
    @BindView(R.id.tv_sum_money)
    TextView tvSumMoney;
    @BindView(R.id.btn_invest)
    Button btnInvest;
    @BindView(R.id.rl_invest_menu)
    RelativeLayout rlInvestMenu;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /*在暂不开放*/
        finish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_trolley);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        leftAction.setText("");
        customTitle.setText(getString(R.string.toufangjihua));
        rightAction.setText(getString(R.string.bianji));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShoppingTrolleyAdapter shoppingTrolleyAdapter = new ShoppingTrolleyAdapter(this);
        recyclerView.setAdapter(shoppingTrolleyAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    private boolean isType = false;

    @OnClick({R.id.left_action, R.id.right_action, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                isType = !isType;
                if (isType) {
                    rightAction.setText(getString(R.string.wancheng));
                    rlEditMenu.setVisibility(View.VISIBLE);
                    rlInvestMenu.setVisibility(View.GONE);
                } else {
                    rightAction.setText(getString(R.string.bianji));
                    rlEditMenu.setVisibility(View.GONE);
                    rlInvestMenu.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_delete:
                break;
        }
    }
}
