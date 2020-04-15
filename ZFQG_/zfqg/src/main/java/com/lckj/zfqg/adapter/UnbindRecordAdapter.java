package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosUnbindRecordListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnbindRecordAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetTraditionalPosUnbindRecordListBean.DataBean.TraditionalPosUnbindRecordListBean> mData;

    public UnbindRecordAdapter(Context context, List<GetTraditionalPosUnbindRecordListBean.DataBean.TraditionalPosUnbindRecordListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_unbind_record, viewGroup, false);
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
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_status)
        TextView tvStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetTraditionalPosUnbindRecordListBean.DataBean.TraditionalPosUnbindRecordListBean bean = mData.get(i);
            tvTitle.setText(mContext.getString(R.string.SN码2) + bean.getSn());
            tvTime.setText(bean.getCre_datetime());
            switch (bean.getStatus()) {
                case "00":
                    tvStatus.setText(mContext.getString(R.string.审核中));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.black));
                    break;
                case "08":
                    tvStatus.setText(mContext.getString(R.string.解绑失败));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                case "09":
                    tvStatus.setText(mContext.getString(R.string.申请解绑成功));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.green2));
                    break;
            }
        }
    }
}
