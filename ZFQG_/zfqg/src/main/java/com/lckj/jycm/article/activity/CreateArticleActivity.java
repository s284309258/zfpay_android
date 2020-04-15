package com.lckj.jycm.article.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.ArticleByURLRequest;
import com.lckj.jycm.network.ArticleByURLResponse;
import com.lckj.jycm.network.InfoService;
import com.lckj.utilslib.TextWatcherHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreateArticleActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.edit_)
    EditText edit;
    @BindView(R.id.tv_plate)
    TextView tvPlate;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_article);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();

    }

    @Override
    protected void initView() {
        leftAction.setText("");
        customTitle.setText(R.string.创建文章);
        toolBar.setBackgroundResource(R.color.yellow);
    }

    @Override
    protected void initEvents() {
        TextWatcherHelper.bindView(false,tvNext,edit);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.tv_plate, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_plate:
                plate();
                break;
            case R.id.tv_next:
                submit();
                //

                break;
        }
    }

    private void submit() {
        ProgressDlgHelper.openDialog(this);
        infoService.getArticleByURL(new ArticleByURLRequest(edit.getText().toString(),dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<ArticleByURLResponse>(this) {
                    @Override
                    public void onSuccessCall(ArticleByURLResponse response) {
                        ProgressDlgHelper.closeDialog();
                        startActivity(new Intent(CreateArticleActivity.this,CreateArticleCommitActivity.class).putExtra("data",response.getData()));
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }

    private void plate() {
        String content = "";
        try {
            ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData data = cm.getPrimaryClip();
            ClipData.Item item = data.getItemAt(0);
            String s = item.getText().toString();
            if (s!=null)
            content = s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        edit.setText(content);
    }
}
