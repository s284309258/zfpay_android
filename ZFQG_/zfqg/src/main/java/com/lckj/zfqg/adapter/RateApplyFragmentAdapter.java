package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosRecordListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RateApplyFragmentAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetApplyRateTraditionalPosRecordListBean.DataBean.ApplyRateTraditionalPosRecordListBean> mData;


    public RateApplyFragmentAdapter(Context context, List<GetApplyRateTraditionalPosRecordListBean.DataBean.ApplyRateTraditionalPosRecordListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rate_apply_fragment, viewGroup, false);
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
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_new_rate)
        TextView tvNewRate;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetApplyRateTraditionalPosRecordListBean.DataBean.ApplyRateTraditionalPosRecordListBean bean = mData.get(i);
            tvSn.setText(bean.getSn());
            tvTime.setText(bean.getCre_datetime());
            tvRate.setText(bean.getCredit_card_rate_old() + "%");
            tvNewRate.setText(bean.getCredit_card_rate_new() + "%");
            if (bean.getRemark() != null && !bean.getRemark().isEmpty()) {
                tvRemark.setText(bean.getRemark());
                tvRemark.setVisibility(View.VISIBLE);
            } else {
                tvRemark.setVisibility(View.GONE);
            }
            switch (bean.getStatus()) {
                case "00":
                    tvStatus.setText(mContext.getString(R.string.审核中));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.black));
                    break;
                case "08":
                    tvStatus.setText(mContext.getString(R.string.申请失败));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                case "09":
                    tvStatus.setText(mContext.getString(R.string.申请成功));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.green2));
                    break;
            }
        }
    }
}
