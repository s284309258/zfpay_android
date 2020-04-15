package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetAllocationTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.SelectPosBatchAllocateBean;
import com.lckj.zfqg.activity.MPOSAllocationActivity;
import com.lckj.zfqg.activity.MPOSAllocationUpdateActivity;
import com.lckj.zfqg.activity.POSAllocationUpdateActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllocationRecordAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final int mType;
    private final List<SelectPosBatchAllocateBean.DataBean.AllocateListBean> mData;

    public AllocationRecordAdapter(Context context, int type, List<SelectPosBatchAllocateBean.DataBean.AllocateListBean> data) {
        mContext = context;
        mType = type;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_allocation_record, viewGroup, false);
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
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            final SelectPosBatchAllocateBean.DataBean.AllocateListBean bean = mData.get(i);
            tvName.setText(mContext.getString(R.string.代理2, bean.getReal_name()));
            tvTime.setText(bean.getAllocate_date());
            tvNumber.setText(mContext.getString(R.string.共台, bean.getCnt()));
            if (mType == 0) {
                tv.setText(mContext.getString(R.string.SN码2));
                iv.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, MPOSAllocationUpdateActivity.class).putExtra("user_id", bean.getUser_id()).putExtra("batch_no", bean.getBatch_no()).putExtra("sn", bean.getMin_sn()).putExtra("max_sn", bean.getMax_sn()));
                    }
                });
                tvSn.setText(bean.getMin_sn() + "\n至\n" + bean.getMax_sn());
            } else if (mType == 1) {
                tv.setText(mContext.getString(R.string.SN码2));
                iv.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, POSAllocationUpdateActivity.class).putExtra("user_id", bean.getUser_id()).putExtra("batch_no", bean.getBatch_no()).putExtra("sn", bean.getMin_sn()).putExtra("max_sn", bean.getMax_sn()).putExtra("isPos", true));
                    }
                });
                tvSn.setText(bean.getMin_sn() + "\n至\n" + bean.getMax_sn());
            } else if (mType == 2) {
                tv.setText(mContext.getString(R.string.SN码2));
                iv.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, POSAllocationUpdateActivity.class).putExtra("user_id", bean.getUser_id()).putExtra("batch_no", bean.getBatch_no()).putExtra("sn", bean.getMin_sn()).putExtra("max_sn", bean.getMax_sn()));
                    }
                });
                tvSn.setText(bean.getMin_sn() + "\n至\n" + bean.getMax_sn());
            } else if (mType == 3) {
                tv.setText(mContext.getString(R.string.流量卡号2));
                iv.setVisibility(View.INVISIBLE);
                tvName.setVisibility(View.GONE);
                tvSn.setText(bean.getMin_sn() + "\n至\n" + bean.getMax_sn());
            }
        }
    }
}
