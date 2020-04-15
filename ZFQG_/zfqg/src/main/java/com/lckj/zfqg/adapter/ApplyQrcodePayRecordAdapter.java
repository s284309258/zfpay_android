package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetApplyScanRecordListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyQrcodePayRecordAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetApplyScanRecordListBean.DataBean.ApplyScanRecordListBean> mData;

    public ApplyQrcodePayRecordAdapter(Context context, List<GetApplyScanRecordListBean.DataBean.ApplyScanRecordListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_apply_qrcode_pay_record, viewGroup, false);
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
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetApplyScanRecordListBean.DataBean.ApplyScanRecordListBean bean = mData.get(i);
            tvSn.setText(bean.getSn());
            tvTime.setText(bean.getCre_datetime());
            switch (bean.getStatus()){
                case "00":
                    tvStatus.setText(mContext.getString(R.string.申请中));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                case "08":
                    tvStatus.setText(mContext.getString(R.string.申请失败));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.gray));
                    break;
                case "09":
                    tvStatus.setText(mContext.getString(R.string.申请成功));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.green2));
                    break;
            }
        }
    }
}
