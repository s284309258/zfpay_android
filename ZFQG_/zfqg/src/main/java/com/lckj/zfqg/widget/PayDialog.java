package com.lckj.zfqg.widget;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.maning.pswedittextlibrary.MNPasswordEditText;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;

public class PayDialog {
    private Activity context;
    private Animation mEnterAnim;
    private Animation mExitAnim;
    private MNPasswordEditText mEtPassword;
    private Dialog mRedDialog;
    private GridView mGridView;
    private OnResult onResult;
    private RelativeLayout mRlBank;
    private TextView mTvTitle;
    private TextView mTvMoney;
    private TextView mTvBank;
    private TextView mTvHint;
    private LinearLayout mLlMoney;
    private View mXx;

    public PayDialog(Activity context) {
        this.context = context;
        mEnterAnim = AnimationUtils.loadAnimation(context, R.anim.dialog_enter);
        mExitAnim = AnimationUtils.loadAnimation(context, R.anim.dialog_exit);
        mRedDialog = new Dialog(context, R.style.BottomDialog2);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_passwrod, null);
        //获得dialog的window窗口
        Window window = mRedDialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.BottomDialog);
        mRedDialog.setContentView(contentView);
        mRedDialog.show();
        mRedDialog.setCanceledOnTouchOutside(false);
        initDialog();
    }

    public void show() {
        mRedDialog.show();
        mLlMoney.setVisibility(View.GONE);
        mTvHint.setVisibility(View.GONE);
        mXx.setVisibility(View.GONE);
        mRlBank.setVisibility(View.INVISIBLE);
    }


    private void initDialog() {
        mEtPassword = (MNPasswordEditText) mRedDialog.findViewById(R.id.et_password);
        mRlBank = (RelativeLayout) mRedDialog.findViewById(R.id.rl_bank);
        mTvTitle = (TextView) mRedDialog.findViewById(R.id.tv_title);
        mTvMoney = (TextView) mRedDialog.findViewById(R.id.tv_money);
        mTvBank = (TextView) mRedDialog.findViewById(R.id.tv_bank);
        mLlMoney = (LinearLayout) mRedDialog.findViewById(R.id.ll_money);
        mTvHint = (TextView) mRedDialog.findViewById(R.id.tv_hint);
        mXx = (View) mRedDialog.findViewById(R.id.xx);

        mEtPassword.setInputType(InputType.TYPE_NULL);
       /* // 设置不调用系统键盘
        if (Build.VERSION.SDK_INT <= 10) {
            mEtPassword.setInputType(InputType.TYPE_NULL);
        } else {
            context.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(mEtPassword, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        final VirtualKeyboardView virtualKeyboardView = (VirtualKeyboardView) mRedDialog.findViewById(R.id.virtualKeyboardView);
        ImageView bark = (ImageView) mRedDialog.findViewById(R.id.iv_back);
        bark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRedDialog.dismiss();
                mEtPassword.setText("");
            }
        });
        valueList = virtualKeyboardView.getValueList();

        virtualKeyboardView.getLayoutBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virtualKeyboardView.startAnimation(mExitAnim);
                virtualKeyboardView.setVisibility(View.GONE);
            }
        });
        mGridView = virtualKeyboardView.getGridView();
        mGridView.setOnItemClickListener(onItemClickListener);
        mEtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virtualKeyboardView.setFocusable(true);
                virtualKeyboardView.setFocusableInTouchMode(true);
                virtualKeyboardView.startAnimation(mEnterAnim);
                virtualKeyboardView.setVisibility(View.VISIBLE);
            }
        });

        mEtPassword.setOnTextChangeListener(new MNPasswordEditText.OnTextChangeListener() {
            @Override
            public void onTextChange(String text, boolean isComplete) {
                if (isComplete) {
                    mRedDialog.dismiss();
                    mEtPassword.setText("");
                    if (onResult != null) {
                        onResult.success(text);
                    }
                }
            }
        });
    }

    private ArrayList<Map<String, String>> valueList;
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (position < 11 && position != 9) {    //点击0~9按钮

                String amount = mEtPassword.getText().toString().trim();
                amount = amount + valueList.get(position).get("name");

                mEtPassword.setText(amount);

                Editable ea = mEtPassword.getText();
                mEtPassword.setSelection(ea.length());
            } else {
                if (position == 9) {      //点击退格键
                    String amount = mEtPassword.getText().toString().trim();
                    if (!amount.contains(".")) {
                        amount = amount + valueList.get(position).get("name");
                        mEtPassword.setText(amount);
                        Editable ea = mEtPassword.getText();
                        mEtPassword.setSelection(ea.length());
                    }
                }

                if (position == 11) {      //点击退格键
                    String amount = mEtPassword.getText().toString().trim();
                    if (amount.length() > 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        mEtPassword.setText(amount);

                        Editable ea = mEtPassword.getText();
                        mEtPassword.setSelection(ea.length());
                    }
                }
            }
        }
    };

    public void setOnResult(OnResult onPWDresult) {
        this.onResult = onPWDresult;
    }

    public void show(String money, String bankName, String bankNumber) {
        show();
        mLlMoney.setVisibility(View.VISIBLE);
        mTvHint.setVisibility(View.VISIBLE);
        mXx.setVisibility(View.VISIBLE);
        mRlBank.setVisibility(View.VISIBLE);
        mTvBank.setText(bankName + "（" + context.getString(R.string.尾号) + bankNumber.substring(bankNumber.length() - 4, bankNumber.length()) + ")");
        mTvMoney.setText(money);
    }

    public interface OnResult {
        void success(String password);
    }
}
