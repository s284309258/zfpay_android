package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosActivityApplyListBean;
import com.lckj.zfqg.activity.ActivityOrderDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityOrderListAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetTraditionalPosActivityApplyListBean.DataBean.TraditionalPosActivityApplyListBean> mData;
    private final int mSubsetType;

    public ActivityOrderListAdapter(Context context, List<GetTraditionalPosActivityApplyListBean.DataBean.TraditionalPosActivityApplyListBean> data2, int subsetType) {
        mContext = context;
        mData = data2;
        mSubsetType = subsetType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_activity_order_list, viewGroup, false);
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
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_order_type)
        TextView tvOrderType;
        @BindView(R.id.tv_award_type)
        TextView tvAwardType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        private GetTraditionalPosActivityApplyListBean.DataBean.TraditionalPosActivityApplyListBean mBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, ActivityOrderDetailsActivity.class).putExtra("id", mBean.getApply_id()).putExtra("subsetType",mSubsetType));
                }
            });
        }

        private void setData(int i) {
            mBean = mData.get(i);
            tvOrderNumber.setText(mBean.getOrder_id());
            tvTime.setText(mBean.getCre_datetime());
            tvOrderType.setText(mBean.getActivity_name());
            tvAwardType.setText(mContext.getString(R.string.库存台交易额达到万返现元, mBean.getPos_num(), mBean.getExpenditure(), mBean.getReward_money()));
            switch (mBean.getStatus()) {
                case "00":
                    tvStatus.setText(mContext.getString(R.string.审核中));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                case "04":
                    tvStatus.setText(mContext.getString(R.string.取消活动));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.gray));
                    break;
                case "08":
                    tvStatus.setText(mContext.getString(R.string.审核失败));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.gray));
                    break;
                case "09":
                    tvStatus.setText(mContext.getString(R.string.已通过));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.green2));
                    break;
            }
        }
    }
}
