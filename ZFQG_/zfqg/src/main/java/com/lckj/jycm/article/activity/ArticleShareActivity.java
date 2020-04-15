package com.lckj.jycm.article.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.PwLoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleShareActivity extends BaseActvity {

    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tv_share)
    TextView tvShare;
    private String url;
    private String title;
    private String img;
    private int arid;
    private int mStatus;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentParamas();
        setContentView(R.layout.activity_article_share);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
    }

    private void getIntentParamas() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        title = intent.getStringExtra("title");
        img = intent.getStringExtra("img");
        arid = intent.getIntExtra("arId", 0);
        mStatus = intent.getIntExtra("status", 0);
    }

    @Override
    protected void initView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        ProgressDlgHelper.openDialog(this);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                ProgressDlgHelper.closeDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("http"))
                    return true;
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_close, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_share:
                if (mStatus == 2 && !TextUtils.isEmpty(dataManager.getToken())) {
                    startActivity(new Intent(this, SelectTaskActivity.class)
                            .putExtra("url", url)
                            .putExtra("title", title)
                            .putExtra("arId", arid)
                            .putExtra("img", img));
                } else if (TextUtils.isEmpty(dataManager.getToken())) {
                    startActivity(new Intent(this, PwLoginActivity.class));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }

            webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            webView.destroy();

        }
        super.onDestroy();
    }


}
