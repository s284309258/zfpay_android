package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosOnlineActivityDetailBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosOnlineActivityDetailRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.imageloader.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActivityDetailsActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.btn_join)
    Button btnJoin;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    private int mSubsetType;
    private String mId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activity);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.活动详情));
        mSubsetType = getIntent().getIntExtra("subsetType", 0);
        mId = getIntent().getStringExtra("id");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        Observable<GetTraditionalPosOnlineActivityDetailBean> request = null;
        if (mSubsetType == 0 || mSubsetType== 2) {
            request = mMyService.getTraditionalPosOnlineActivityDetail(new GetTraditionalPosOnlineActivityDetailRequest(dataManager.getToken(), mId));
        } else {
            request = mMyService.getMposOnlineActivityDetail(new GetTraditionalPosOnlineActivityDetailRequest(dataManager.getToken(), mId));
        }
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTraditionalPosOnlineActivityDetailBean>(this) {
                    @Override
                    public void onSuccessCall(GetTraditionalPosOnlineActivityDetailBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (mSubsetType == 0 || mSubsetType== 2) {
                            ImageLoader.loadImage(response.getData().getTraditionalPosOnlineActivity().getDetail_url(), ivImg);
                        } else {
                            ImageLoader.loadImage(response.getData().getMposOnlineActivity().getDetail_url(), ivImg);
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.btn_join})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.btn_join:
                startActivity(new Intent(this, JoinEventActivity.class).putExtra("id", mId).putExtra("subsetType", mSubsetType));
                break;
        }
    }
}
