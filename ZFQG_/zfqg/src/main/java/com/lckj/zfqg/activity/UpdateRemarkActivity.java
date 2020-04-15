package com.lckj.zfqg.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateRemarkActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_content)
    ClearEditText etContent;
    @Inject
    MyService mMyService;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_remark);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.修改昵称));

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.btn_confirm:
                submit();
                break;
        }
    }

    private void submit() {
        /*final String username = etContent.getText().toString().trim();
        ProgressDlgHelper.openDialog(this);
        mFrontUserService.updatePersonal(new UpdatePersonalRequest(username,"", "", "", "", dataManager.getToken(), "", ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            Utils.showMsg(UpdateRemarkActivity.this, getString(R.string.toast_update_success));
                            dataManager.setUserName(username);
                            Intent intent = new Intent(UpdateRemarkActivity.this, PersonInfoActivity.class);
                            intent.putExtra("username", username);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Utils.showMsg(UpdateRemarkActivity.this, response.getMsg());
                        }

                    }
                }, new ThrowableConsumer<Throwable>(this));*/
    }
}
