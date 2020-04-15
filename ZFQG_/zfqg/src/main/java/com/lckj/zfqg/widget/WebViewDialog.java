package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewDialog extends Dialog {
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    public WebViewDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_web_view);
        ButterKnife.bind(this);
        WebSettings settings = webView.getSettings();
        settings.setBlockNetworkImage(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setDefaultFontSize(16);
    }

    public void show(String url, String title) {
        show();
        webView.loadUrl(url);
        tvTitle.setText(title);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        dismiss();
    }
}
