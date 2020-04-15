package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UpdateUserCardRequest;
import com.lckj.zfqg.activity.ClearingCentreActivity;
import com.lckj.zfqg.activity.HomeActivity;
import com.lckj.zfqg.activity.SettingActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ConfirmDialog extends Dialog {

    private final Context mContext;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private String mId;

    public ConfirmDialog( Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
    }

    public void show(String content, String id) {
        show();
        tvContent.setText(content);
        mId = id;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_confirm:
                if (mContext instanceof ClearingCentreActivity) {
                    mMyService.updateUserCard(new UpdateUserCardRequest(dataManager.getToken(), "user_card_set", "1", mId))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BaseConsumer<HttpResponse>(mContext) {
                                @Override
                                public void onSuccessCall(HttpResponse response) {
                                    ProgressDlgHelper.closeDialog();
                                    ClearingCentreActivity activity = (ClearingCentreActivity) mContext;
                                    activity.initData();
                                    dismiss();
                                }
                            }, new ThrowableConsumer<Throwable>(mContext));
                } else if (mContext instanceof SettingActivity) {
                    dismiss();
                    SettingActivity activity = (SettingActivity) mContext;
                    activity.logout();
                }else if(mContext instanceof HomeActivity){
                    dismiss();
                    MainApplication.getInstance().clearActivitys();
                }
                break;
        }
    }
}
