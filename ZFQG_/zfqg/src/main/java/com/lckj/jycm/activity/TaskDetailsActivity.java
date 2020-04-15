package com.lckj.jycm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.article.activity.ArticleShareActivity;
import com.lckj.jycm.network.AdvInfoListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.lckjlib.share.ShareDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailsActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sum_money)
    TextView tvSumMoney;
    @BindView(R.id.iv_ad_img)
    ImageView ivAdImg;
    @BindView(R.id.tv_ad_title)
    TextView tvAdTitle;
    @BindView(R.id.tv_ad_content)
    TextView tvAdContent;
    @BindView(R.id.rl_ad)
    RelativeLayout rlAd;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_article_img)
    ImageView ivArticleImg;
    @BindView(R.id.tv_article_content)
    TextView tvArticleContent;
    @BindView(R.id.rl_article)
    RelativeLayout rlArticle;
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private AdvInfoListBean.DataBean.ListBean mData;
    private String title;
    private String img;
    private String url;
    private int mAdId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.ad_details));
        leftAction.setText("");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        mData = (AdvInfoListBean.DataBean.ListBean) getIntent().getSerializableExtra("data");
        url = mData.getAdvImg();
        mAdId = mData.getId();
        ImageLoader.loadImage(mData.getAdvImg(), ivAdImg, 10, 0);
        tvAdContent.setText(mData.getAdvIntro());
        tvAdTitle.setText(mData.getAdvTitle());
        tvTitle.setText(mData.getAdvTitle());
        ImageLoader.loadImage(mData.getHeadPhoto(), ivHeadImg, 10, 0);
        tvSumMoney.setText(getString(R.string.总金额) + " : " + mData.getPutBudget() + "金币");
        tvMoney.setText(getString(R.string.share_task_money, mData.getRevShare() + ""));
        tvTime.setText(mData.getCreateDate());
        if (mData.getAdvArt() != null) {
            ImageLoader.loadImage(mData.getAdvArt().getArtCover(), ivArticleImg, 10, 0);
            tvArticleContent.setText(mData.getAdvArt().getArtTitle());
        } else {
            rlArticle.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.left_action, R.id.rl_ad, R.id.rl_article, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_ad:
                startActivity(new Intent(this, ArticleShareActivity.class).putExtra("url", mData.getAdvUrl()));
                break;
            case R.id.rl_article:
                startActivity(new Intent(this, ArticleShareActivity.class).putExtra("url", mData.getAdvArt().getArtUrl()));
                break;
            case R.id.btn_share:
                if (mData.getStatus() == 2) {
                    share();
                }
                break;
        }
    }

    private void share() {
        if (mData.getAdvArt() == null) {
            Intent intent = new Intent(this, SelectorArticleActivity.class);
            intent.putExtra("who", 3);
            intent.putExtra("imgUrl", url);
            intent.putExtra("adId", mAdId);
            intent.putExtra("adUrl", mData.getAdvUrl());
            startActivity(intent);
        } else {
            String adimgUrl;
            if (!mData.getAdvImg().startsWith("http")) {
                adimgUrl = ProviderModule.getDataManager(this).getQiniuDomain() + "/" + mData.getAdvImg();
            } else {
                adimgUrl = mData.getAdvImg();
            }
            String shareUrl = String.format("http://www.rxhwl.com/shareAdv.html?str1=%1$s&str2=%2$s&str3=%3$s", mData.getAdvArt().getArtUrl(), adimgUrl, mData.getAdvUrl());
            ShareDialog.createDialog(this, shareUrl, mData.getAdvArt().getArtTitle(), mData.getAdvArt().getArtCover(), mData.getAdvArt().getId(), mData.getId()).show();
        }
    }
}
