package com.lckj.zfqg.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.ApplyCashRequest;
import com.lckj.jycm.zfqg_network.GetCashInfoBean;
import com.lckj.jycm.zfqg_network.GetUserCardListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.zfqg.widget.BankDialog;
import com.lckj.zfqg.widget.PayDialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WithdrawalActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_bank_logo)
    ImageView ivBankLogo;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_number)
    TextView tvBankNumber;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.tv_usable_count)
    TextView tvUsableCount;
    @BindView(R.id.tv_service_count)
    TextView tvServiceCount;
    @BindView(R.id.tv_real_count)
    TextView tvRealCount;
    @BindView(R.id.tv_deduct_count)
    TextView tvDeductCount;
    @BindView(R.id.btn_apply)
    Button btnApply;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv4)
    TextView tv4;
    private PayDialog mPayDialog;
    private BankDialog mBankDialog;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isAdd = true;
    private GetCashInfoBean.DataBean.CashInfoBean mBean;
    List<GetUserCardListBean.DataBean.UserCardListBean> mData = new ArrayList<>();
    private String mCard_id;

    private double mDeductCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initView() {
        if (dataManager.getAlgebra().equals("1")) {
            tvDeductCount.setVisibility(View.VISIBLE);
            tv4.setVisibility(View.VISIBLE);
        } else {
            tvDeductCount.setVisibility(View.GONE);
            tv4.setVisibility(View.GONE);
        }
        customTitle.setText(getString(R.string.提现));
        rightAction.setText(getString(R.string.提现记录));
    }

    @Override
    protected void initEvents() {
    }

    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //删除“.”后面超过2位后的数据
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 2 + 1);
                    etNumber.setText(s);
                    etNumber.setSelection(s.length()); //光标移到最后
                }
            }
            //如果"."在起始位置,则起始位置自动补0
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                etNumber.setText(s);
                etNumber.setSelection(2);
            }

            //如果起始位置为0,且第二位跟的不是".",则无法后续输入
            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    etNumber.setText(s.subSequence(0, 1));
                    etNumber.setSelection(1);
                    return;
                }
            }
            if (!TextUtils.isEmpty(s)) {
                double etCount = Double.parseDouble(s.toString());
                double cashSingleFeet = Double.parseDouble(mBean.getCashSingleFeet());
                double cashFeetRate = Double.parseDouble(mBean.getCashFeetRate());
                double deductMoney = Double.parseDouble(mBean.getDeduct_money());
                mDeductCount = new BigDecimal(etCount).subtract(new BigDecimal(deductMoney)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double sumService = cashSingleFeet + new BigDecimal(mDeductCount).multiply(new BigDecimal(cashFeetRate)).doubleValue();
                double sumDeduct = sumService + deductMoney;
                double realCount = new BigDecimal(etCount).subtract(new BigDecimal(sumDeduct)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                tvRealCount.setText(realCount + "");
                tvServiceCount.setText(new BigDecimal(sumService).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            } else {
                tvRealCount.setText("0.00");
                tvServiceCount.setText("0.00");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mMyService.getCashInfo(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetCashInfoBean>(this) {
                    @SuppressLint("StringFormatMatches")
                    @Override
                    public void onSuccessCall(GetCashInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (isAdd) {
                            isAdd = false;
                            etNumber.addTextChangedListener(mTextWatcher);
                        }
                        mBean = response.getData().getCashInfo();
                        tvDeductCount.setText(mBean.getDeduct_money());
                        tvUsableCount.setText(getString(R.string.可提现金额, mBean.getCan_cash_money()));
                        tvHint.setText(getString(R.string.提现温馨提示, mBean.getCashMinNum(), (new BigDecimal(mBean.getCashFeetRate()).multiply(new BigDecimal("100")) + "%"), mBean.getCashSingleFeet()));
                    }
                }, new ThrowableConsumer<Throwable>(this));

        mMyService.getUserValidCardList(new TokenRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetUserCardListBean>(this) {
                    @Override
                    public void onSuccessCall(GetUserCardListBean response) {
                        ProgressDlgHelper.closeDialog();
                        mData.clear();
                        mData.addAll(response.getData().getUserCardList());
                        if (mData.size() != 0) {
                            mCard_id = mData.get(0).getCard_id();
                            tvBankName.setText(mData.get(0).getBank_name());
                            tvBankNumber.setText(mData.get(0).getAccount());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.right_action, R.id.rl_bank, R.id.btn_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                startActivity(new Intent(this, WithdrawalRecordActivity.class));
                break;
            case R.id.rl_bank:
                showBankDialog();
                break;
            case R.id.btn_apply:
                if (checkEidt()) {
                    showPayDialog();
                }
                break;
        }
    }

    private boolean checkEidt() {
        if (TextUtils.isEmpty(tvBankName.getText().toString())) {
            Utils.showMsg(this, getString(R.string.选择银行卡));
            return false;
        } else if (TextUtils.isEmpty(etNumber.getText().toString())) {
            Utils.showMsg(this, getString(R.string.请输入提现金额));
            return false;
        } else if (mDeductCount < Double.parseDouble(mBean.getCashMinNum())) {
            Utils.showMsg(this, getString(R.string.提现金额不得低于, mBean.getCashMinNum()));
            return false;
        }else if (Double.parseDouble(etNumber.getText().toString()) > mBean.getCan_cash_money()) {
            Utils.showMsg(this, getString(R.string.提现金额不得高于可提现金额));
            return false;
        }
        return true;
    }

    private void showBankDialog() {
        if (mBankDialog == null)
            mBankDialog = new BankDialog(this, R.style.BottomDialog2);
        mBankDialog.show(mData);
    }

    private void showPayDialog() {
        if (mPayDialog == null) {
            mPayDialog = new PayDialog(this);
            mPayDialog.setOnResult(new PayDialog.OnResult() {
                @Override
                public void success(String password) {
                    submit(password);
                }
            });
        }
        mPayDialog.show(etNumber.getText().toString(), tvBankName.getText().toString(), tvBankNumber.getText().toString());
    }

    private void submit(String password) {
        ProgressDlgHelper.openDialog(this);
        mMyService.applyCash(new ApplyCashRequest(dataManager.getToken(), etNumber.getText().toString(), mCard_id, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        startActivity(new Intent(WithdrawalActivity.this, WithdrawalRecordActivity.class));
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void setData(GetUserCardListBean.DataBean.UserCardListBean bean) {
        mCard_id = bean.getCard_id();
        tvBankNumber.setText(bean.getAccount());
        tvBankName.setText(bean.getBank_name());
    }
}
