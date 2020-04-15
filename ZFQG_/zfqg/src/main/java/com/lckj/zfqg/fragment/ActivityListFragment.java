package com.lckj.zfqg.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.activity.OnlineActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class ActivityListFragment extends BaseFragment {
    private final int mType;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.ll_pos)
    LinearLayout llPos;
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.ll_mpos)
    LinearLayout llMpos;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_epos)
    TextView tvEpos;
    @BindView(R.id.xx3)
    TextView xx3;
    @BindView(R.id.ll_epos)
    LinearLayout llEpos;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    List<Fragment> mFragmentList = new ArrayList<>();
    public int mIndex;
    public PosActivityListFragment mPosActivityListFragment;
    public PosActivityListFragment mPosActivityListFragment1;
    private PosActivityListFragment mPosActivityListFragment2;

    public ActivityListFragment(int type) {
        super();
        mType = type;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.frgment_activity_list);
        mBind = ButterKnife.bind(this, view);
        MainApplication.getInjectGraph().inject(this);
        initData();
        initView();
    }

    protected void initData() {
        mPosActivityListFragment = new PosActivityListFragment(mType, 0);
        mPosActivityListFragment1 = new PosActivityListFragment(mType, 1);
        mPosActivityListFragment2 = new PosActivityListFragment(mType, 2);
        mFragmentList.add(mPosActivityListFragment);
        mFragmentList.add(mPosActivityListFragment1);
        mFragmentList.add(mPosActivityListFragment2);
    }

    @Override
    protected void initView() {
        setSelector(0);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), mFragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setSelector(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setSelector(int index) {
        mIndex = index;
        OnlineActivity activity = (OnlineActivity) getActivity();
        activity.mCurrent = mIndex;
        if (index == 0) {
            tvPos.setSelected(true);
            tvMpos.setSelected(false);
            tvEpos.setSelected(false);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.VISIBLE);
            xx3.setVisibility(View.INVISIBLE);
        } else if(index == 1){
            tvPos.setSelected(false);
            tvMpos.setSelected(true);
            tvEpos.setSelected(false);
            xx2.setVisibility(View.VISIBLE);
            xx.setVisibility(View.INVISIBLE);
            xx3.setVisibility(View.INVISIBLE);
        }else {
            tvPos.setSelected(false);
            tvMpos.setSelected(false);
            tvEpos.setSelected(true);
            xx2.setVisibility(View.INVISIBLE);
            xx.setVisibility(View.INVISIBLE);
            xx3.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.ll_pos, R.id.ll_mpos, R.id.ll_epos})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_pos:
                setSelector(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_mpos:
                setSelector(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_epos:
                setSelector(2);
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
