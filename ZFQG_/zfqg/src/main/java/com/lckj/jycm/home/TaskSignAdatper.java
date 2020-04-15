package com.lckj.jycm.home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.network.SignInfoBean;
import com.lckj.jycm.widget.CustomDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskSignAdatper extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<SignInfoBean.DataBean.SignListBean> mSignList;
    private final List<SignInfoBean.DataBean> mSign;

    public TaskSignAdatper(Context context, List<SignInfoBean.DataBean.SignListBean> signList, List<SignInfoBean.DataBean> sign) {
        mContext = context;
        mSignList = signList;
        mSign = sign;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_task_sign, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return mSignList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_sign_days)
        TextView tvSignDays;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
           /* view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!tvSignDays.getText().toString().equals(mContext.getString(R.string.yiqian))) {
                        tvMoney.setTextColor(mContext.getResources().getColor(R.color.gray));
                        tvMoney.setBackgroundResource(R.mipmap.icon_my_huibi);
                        showDialog(tvMoney.getText().toString());
                        tvSignDays.setText(mContext.getString(R.string.yiqian));
                        tvSignDays.setTextColor(mContext.getResources().getColor(R.color.gray));
                    }
                }
            });*/
        }

        private void setData(int i) {
            SignInfoBean.DataBean.SignListBean signListBean = mSignList.get(i);
            tvSignDays.setText(i + 1 + "天");
            tvMoney.setText(signListBean.getGold() + "");
            if (mSign.size() != 0) {
                if (i + 1 <= mSign.get(0).getSignSum() + 1) {
                    tvMoney.setTextColor(mContext.getResources().getColor(R.color.gray));
                    tvMoney.setBackgroundResource(R.mipmap.icon_my_huibi);
                    tvSignDays.setText(mContext.getString(R.string.yiqian));
                    tvSignDays.setTextColor(mContext.getResources().getColor(R.color.gray));
                }
            }
        }
    }
}
