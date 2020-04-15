package com.lckj.zfqg.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.GetUserAuthStatusBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UpdateNewsReadFlagRequest;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.lckjlib.widgets.ActionSheet;
import com.lckj.zfqg.activity.ClearingCentreActivity;
import com.lckj.zfqg.activity.FeedbackActivity;
import com.lckj.zfqg.activity.MessageCentreActivity;
import com.lckj.zfqg.activity.RealNameActivity;
import com.lckj.zfqg.activity.RecallActivity;
import com.lckj.zfqg.activity.SettingActivity;
import com.lckj.zfqg.activity.WithdrawalRecordActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyFragment extends BaseFragment implements ActionSheet.MenuItemClickListener {
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_bank_count)
    TextView tvBankCount;
    @BindView(R.id.tv_feedback_count)
    TextView tvFeedbackCount;
    @BindView(R.id.tv_msg_count)
    TextView tvMsgCount;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rl_feedback)
    RelativeLayout rlFeedback;
    @BindView(R.id.rl_out_record)
    RelativeLayout rlOutRecord;
    @BindView(R.id.rl_real_name)
    RelativeLayout rlRealName;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.rl_msg)
    RelativeLayout rlMsg;
    @BindView(R.id.rl_service)
    RelativeLayout rlService;
    @BindView(R.id.rl_feed_back)
    RelativeLayout rlFeedBack;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_recall_red)
    TextView tvRecallRed;
    @BindView(R.id.tv_bank_red)
    TextView tvBankRed;
    private Unbinder mBind;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        View view = setContentView(R.layout.fragment_my);
        mBind = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        MainApplication.getInjectGraph().inject(this);
        initView();
        initEvents();
    }

    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    private void initData() {
        mMyService.getUserAuthStatus(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserAuthStatusBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserAuthStatusBean response) {
                        ProgressDlgHelper.closeDialog();
                        refreshLayout.finishRefresh();
                        dataManager.setUserName(response.getData().getUserAuthStatus().getReal_name());
                        dataManager.setAuthStatus(response.getData().getUserAuthStatus().getAuth_status());
                        EventBus.getDefault().post(getString(R.string.实名认证));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateImg(String msg) {
        if (msg.equals(getString(R.string.修改头像成功))) {
            ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 30, 0);
        } else if (msg.equals(getString(R.string.实名认证))) {
            tvName.setText(dataManager.getUserName());
        } else if (msg.equals(getString(R.string.修改手机号码))) {
            tvPhone.setText(dataManager.getUserTel());
        }
    }

    @Override
    protected void initView() {
        ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 30, 0);
        tvPhone.setText(dataManager.getUserTel());
        tvName.setText(dataManager.getUserName());
        switch (dataManager.getAuthStatus()) {
            case "00":
                tvRealName.setText(getString(R.string.未实名));
                break;
            case "04":
                tvRealName.setText(getString(R.string.实名审核中));
                break;
            case "08":
                tvRealName.setText(getString(R.string.实名失败));
                break;
            case "09":
                tvRealName.setText(getString(R.string.已实名));
                break;
        }
        if (MainApplication.getInstance().getUnReadNews() != null) {
            tvRecallRed.setVisibility(MainApplication.getInstance().getUnReadNews().getRecallFlag().equals("0") ? View.VISIBLE : View.GONE);
            tvBankRed.setVisibility(MainApplication.getInstance().getUnReadNews().getCardFlag().equals("0") ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_setting, R.id.iv_head_img, R.id.rl_info, R.id.rl_bank, R.id.rl_feedback, R.id.rl_out_record, R.id.rl_real_name, R.id.rl_msg, R.id.rl_service, R.id.rl_feed_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.iv_head_img:
                break;
            case R.id.rl_info:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.rl_bank:
                startActivity(new Intent(getContext(), ClearingCentreActivity.class));
                updateRed("cardFlag");
                break;
            case R.id.rl_feedback:
                startActivity(new Intent(getContext(), RecallActivity.class));
                updateRed("recallFlag");
                break;
            case R.id.rl_out_record:
                startActivity(new Intent(getContext(), WithdrawalRecordActivity.class));
                break;
            case R.id.rl_real_name:
                startActivity(new Intent(getContext(), RealNameActivity.class));
                break;
            case R.id.rl_msg:
                startActivity(new Intent(getContext(), MessageCentreActivity.class));
                break;
            case R.id.rl_service:
                /*版本1，打开QQ聊天*/
//                skipQQ();
                showMenu();

                break;
            case R.id.rl_feed_back:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
        }
    }

    private void updateRed(String type) {
        mMyService.updateNewsReadFlag(new UpdateNewsReadFlagRequest(dataManager.getToken(), type, "1"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        if (MainApplication.getInstance().getUnReadNews() != null) {
                            if (type.equals("recallFlag")) {
                                MainApplication.getInstance().getUnReadNews().setRecallFlag("1");
                                tvRecallRed.setVisibility(View.GONE);
                            } else {
                                MainApplication.getInstance().getUnReadNews().setCardFlag("1");
                                tvBankRed.setVisibility(View.GONE);
                            }
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private void showMenu() {
        getActivity().setTheme(R.style.ActionSheetStyleIOS7);
        ActionSheet menuView = new ActionSheet(getContext());
        menuView.setCancelButtonTitle(getString(R.string.cancel));
        menuView.setCurrentItems("");
        menuView.addItems("传统POS客服：4007006889", "MPOS客服电话：4007700070");
        menuView.setItemClickListener(this);
        menuView.setCancelableOnTouchMenuOutside(true);
        menuView.showMenu();
    }

    @Override
    public void onActionSheetItemClick(View v, int itemPosition, String itemText) {
        String[] split = itemText.split("：");
        if (split.length == 2) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + split[1])));
        }
    }

    private void skipQQ() {
        // 跳转之前，可以先判断手机是否安装QQ
        if (Utils.isQQClientAvailable(getContext())) {
            // 跳转到客服的QQ
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=3061335451";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            // 跳转前先判断Uri是否存在，如果打开一个不存在的Uri，App可能会崩溃
            if (Utils.isValidIntent(getContext(), intent)) {
                startActivity(intent);
            }
        } else {
            Utils.showMsg(getContext(), getString(R.string.请安装QQ以便联系客服));
        }
    }
}
