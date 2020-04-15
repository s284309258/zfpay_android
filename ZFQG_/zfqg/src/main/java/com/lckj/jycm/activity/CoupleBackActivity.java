package com.lckj.jycm.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.lckj.custom.view.InnerRecyclerView;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.adapter.GridViewAdapter;
import com.lckj.jycm.center.adapter.ApplyMerchantFunctionAdapter;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoupleBackActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.et_content)
    ClearEditText etContent;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.recycler_view)
    InnerRecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private ApplyMerchantFunctionAdapter adapter;
    private ArrayList<LocalMedia> mDatas1 = new ArrayList<>();
    private TextView data1Watcher;
    public List<LocalMedia> operatingList;
    public ApplyMerchantFunctionAdapter operatingAdapter;
    public static CoupleBackActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_couple_back);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.意见反馈));
        leftAction.setText("");
        Drawable left = getDrawable(R.mipmap.icon_create_article);
        left.setBounds(0, 0, left.getIntrinsicWidth(), left.getIntrinsicHeight());
        rightAction.setCompoundDrawables(left, null, null, null);
        rightAction.setCompoundDrawablePadding(dip2px(5));
        data1Watcher = new TextView(this);
        adapter = new ApplyMerchantFunctionAdapter(this, mDatas1, 3, data1Watcher);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ApplyMerchantFunctionAdapter.SpaceItemDecoration(Utils.dp2px(this, 6), Utils.dp2px(this, 4)));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        gridView.setAdapter(new GridViewAdapter(this, mTypeList));
        gridView.setSelection(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (operatingAdapter != null && operatingList != null) {
                        operatingList.addAll(localMedia);
                        operatingAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    @Override
    protected void initEvents() {

    }

    List<String> mTypeList = new ArrayList<>();
    @Override
    protected void initData() {
        mTypeList.add("登录注册问题");
        mTypeList.add("闪退问题");
        mTypeList.add("用户体验");
        mTypeList.add("提现问题");
        mTypeList.add("提建议");
        mTypeList.add("其它");
    }

    @OnClick({R.id.left_action, R.id.right_action, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                break;
            case R.id.btn_submit:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}
