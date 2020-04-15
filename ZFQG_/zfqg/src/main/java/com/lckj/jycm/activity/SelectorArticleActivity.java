package com.lckj.jycm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.fragment.MyArticleFragment;
import com.lckj.jycm.home.MyFragmentPagerAdapter;
import com.lckj.jycm.network.AdvArticleListBean;
import com.lckj.lckjlib.share.ShareDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectorArticleActivity extends BaseActvity {
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
    @BindView(R.id.tv_platform)
    TextView tvPlatform;
    @BindView(R.id.xx)
    TextView xx;
    @BindView(R.id.rl_platform)
    RelativeLayout rlPlatform;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.xx2)
    TextView xx2;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.rl_share)
    RelativeLayout rlShare;
    private int mWho;
    private MyArticleFragment mFragment;
    private MyArticleFragment mFragment2;
    private String mImgUrl;
    private int mI;
    private int mAdId;
    private String mAdUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_article);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        mWho = getIntent().getIntExtra("who", 0);
        mAdId = getIntent().getIntExtra("adId", 0);
        mImgUrl = getIntent().getStringExtra("imgUrl");
        mAdUrl = getIntent().getStringExtra("adUrl");
        if (mWho == 2) {
            btnShare.setText(getString(R.string.下一步));
        } else {
            btnShare.setText(getString(R.string.fenxiangjizhuan));
        }
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.selector_article));
        leftAction.setText("");
        mFragment = new MyArticleFragment();
        Bundle args = new Bundle();
        args.putInt("status", 2);
        args.putInt("type", 2);
        args.putInt("who", mWho);
        mFragment.setArguments(args);
        mFragment2 = new MyArticleFragment();
        Bundle args2 = new Bundle();
        args2.putInt("status", 2);
        args2.putInt("type", 1);
        args2.putInt("who", mWho);
        mFragment2.setArguments(args2);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(mFragment);
        fragments.add(mFragment2);
        setMenuSelector(0);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setMenuSelector(i);
                mI = i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setMenuSelector(int i) {
        if (i == 0) {
            xx.setVisibility(View.VISIBLE);
            tvPlatform.setSelected(true);
            xx2.setVisibility(View.GONE);
            tvMy.setSelected(false);
        } else {
            xx.setVisibility(View.GONE);
            tvPlatform.setSelected(false);
            xx2.setVisibility(View.VISIBLE);
            tvMy.setSelected(true);
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.rl_platform, R.id.rl_my, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_platform:
                viewPager.setCurrentItem(0);
                setMenuSelector(0);
                break;
            case R.id.rl_my:
                viewPager.setCurrentItem(1);
                setMenuSelector(1);
                break;
            case R.id.btn_share:
                share();
                break;
        }
    }

    private void share() {
        if (mWho == 3) {
            AdvArticleListBean.DataBean.ListBean selectItem = mFragment.adapter.getSelectItem();
            if (selectItem != null) {
                String artUrl = selectItem.getArtUrl();
                String artTitle = selectItem.getArtTitle();
                String artCover = selectItem.getArtCover();
                if(!mImgUrl.startsWith("http")){
                    mImgUrl = ProviderModule.getDataManager(this).getQiniuDomain()+"/"+ mImgUrl;
                }
                String shareUrl = String.format("http://www.rxhwl.com/shareAdv.html?str1=%1$s&str2=%2$s&str3=%3$s", artUrl, mImgUrl,mAdUrl);
                ShareDialog.createDialog(this, shareUrl, artTitle, artCover, selectItem.getId(), mAdId).show();
            }
        } else {
            AdvArticleListBean.DataBean.ListBean selectItem = null;
            if(mI == 0){
                 selectItem = mFragment.adapter.getSelectItem();
            }else {
                 selectItem = mFragment2.adapter.getSelectItem();
            }
            if (selectItem != null) {
                Intent intent = new Intent(this, InvestAdActivity.class);
                intent.putExtra("id", selectItem.getId() + "");
                intent.putExtra("title", selectItem.getArtTitle());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
