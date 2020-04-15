package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.zfqg_network.DateRequest;
import com.lckj.jycm.zfqg_network.GetActivityRewardTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetDeductTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetMachineBackTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetShareBenefitTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.adapter.DetailRecordAdapter;
import com.lckj.zfqg.adapter.SpaceItemDecoration;
import com.lckj.zfqg.fragment.ListFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailRecordActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<Fragment> mFragmentList = new ArrayList<>();
    private int mType;
    private String mDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_record);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("type", -1);
        switch (mType){
            case 0:
                customTitle.setText(getString(R.string.传统POS分润记录));
                break;
            case 1:
                customTitle.setText(getString(R.string.传统POS激活返现记录));
                break;
            case 2:
                customTitle.setText(getString(R.string.传统POS活动奖励记录));
                break;
            case 3:
                customTitle.setText(getString(R.string.传统POS考核未达标扣除记录));
                break;
            case 4:
                customTitle.setText(getString(R.string.MPOS分润记录));
                break;
            case 5:
                customTitle.setText(getString(R.string.MPOS激活返现记录));
                break;
            case 6:
                customTitle.setText(getString(R.string.MPOS活动奖励记录));
                break;
            case 7:
                customTitle.setText(getString(R.string.MPOS考核未达标扣除记录));
                break;
            case 8:
                customTitle.setText(getString(R.string.EPOS分润记录));
                break;
            case 9:
                customTitle.setText(getString(R.string.EPOS激活返现记录));
                break;
            case 10:
                customTitle.setText(getString(R.string.EPOS活动奖励记录));
                break;
            case 11:
                customTitle.setText(getString(R.string.EPOS考核未达标扣除记录));
                break;
        }
        mDate = getIntent().getStringExtra("date");
        mFragmentList.add(new ListFragment(mMyService, dataManager, mType, mDate));
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.left_action)
    public void onViewClicked() {
        finish();
    }
}
