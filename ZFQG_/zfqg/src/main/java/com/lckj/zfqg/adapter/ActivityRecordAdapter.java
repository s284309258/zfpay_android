package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetMposRewardRecordListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRecordAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetMposRewardRecordListBean.DataBean.MposRewardRecordListBean> mData;

    public ActivityRecordAdapter(Context context, List<GetMposRewardRecordListBean.DataBean.MposRewardRecordListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_activity_record, viewGroup, false);
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
        @BindView(R.id.tv_real_count)
        TextView tvRealCount;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_period)
        TextView tvPeriod;
        @BindView(R.id.tv_item)
        TextView tvItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetMposRewardRecordListBean.DataBean.MposRewardRecordListBean bean = mData.get(i);
            tvOrderNumber.setText(bean.getOrder_id());
            tvRealCount.setText("￥" + bean.getMoney());
            tvPeriod.setText(bean.getStart_date() + "-" + bean.getEnd_date());
            tvItem.setText(bean.getCre_datetime());
            tvType.setText(bean.getActivity_name());
            tvSn.setText(bean.getSn());
        }
    }
}
