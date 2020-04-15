package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetUserCardListBean;
import com.lckj.zfqg.activity.AddBankCardActivity;
import com.lckj.zfqg.activity.WithdrawalActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankDialog extends Dialog {
    private final Context mContext;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private BankAdapter mBankAdapter;
    List<GetUserCardListBean.DataBean.UserCardListBean> mData = new ArrayList<>();

    public BankDialog(Context context, int dialog) {
        super(context, dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bank, null);
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_dialog_anim);
        setContentView(contentView);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBankAdapter = new BankAdapter(mData);
        recyclerView.setAdapter(mBankAdapter);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        getContext().startActivity(new Intent(getContext(), AddBankCardActivity.class));
        dismiss();
    }

    public void show(List<GetUserCardListBean.DataBean.UserCardListBean> data) {
        show();
        mData.clear();
        mData.addAll(data);
        mBankAdapter.notifyDataSetChanged();
    }

    public class BankAdapter extends RecyclerView.Adapter {
        private final List<GetUserCardListBean.DataBean.UserCardListBean> mData;
        private ImageView mLastIvCheck;

        public BankAdapter(List<GetUserCardListBean.DataBean.UserCardListBean> data) {
            mData = data;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_bank, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.setData(i);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_bank_name)
            TextView tvBankName;
            @BindView(R.id.tv_bank_number)
            TextView tvBankNumber;
            @BindView(R.id.iv_check)
            ImageView ivCheck;
            @BindView(R.id.rl)
            RelativeLayout rl;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }

            private void setData(int i) {
                final GetUserCardListBean.DataBean.UserCardListBean bean = mData.get(i);
                tvBankName.setText(bean.getBank_name());
                tvBankNumber.setText(bean.getAccount());
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivCheck.setVisibility(View.VISIBLE);
                        if (mLastIvCheck != null) {
                            mLastIvCheck.setVisibility(View.GONE);
                        }
                        mLastIvCheck = ivCheck;
                        dismiss();
                        WithdrawalActivity activity = (WithdrawalActivity) mContext;
                        activity.setData(bean);
                    }
                });
            }
        }
    }
}
