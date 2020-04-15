package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetCashRecordListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ProgressAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetCashRecordListBean.DataBean.CashRecordListBean.CashRecordDetailListBean> mData;

    public ProgressAdapter(Context context, List<GetCashRecordListBean.DataBean.CashRecordListBean.CashRecordDetailListBean> data) {
        mContext = context;
        mData = data;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_progress, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
       /* if (mData.size() != 0)
            return mData.size();
        else*/
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_oval)
        TextView tvOval;
        @BindView(R.id.tv_line)
        TextView tvLine;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            if (mData.size() > i) {
                GetCashRecordListBean.DataBean.CashRecordListBean.CashRecordDetailListBean bean = mData.get(i);
                tvTime.setText(bean.getCre_date());
                switch (bean.getCash_status()) {
                    case "00":
                        tvStatus.setText(mContext.getString(R.string.申请提现));
                        tvStatus.setTextColor(mContext.getResources().getColor(R.color.gray));
                        tvLine.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
                        tvOval.setBackgroundResource(R.drawable.oval_progress3);
                        break;
                    case "02":
                        tvStatus.setText(mContext.getString(R.string.处理中));
                        tvStatus.setTextColor(mContext.getResources().getColor(R.color.green2));
                        tvLine.setBackgroundColor(mContext.getResources().getColor(R.color.green2));
                        tvOval.setBackgroundResource(R.drawable.oval_progress);
                        break;
                    case "04":
                        tvStatus.setText(mContext.getString(R.string.已撤销));
                        tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                        tvLine.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                        tvOval.setBackgroundResource(R.drawable.oval_progress2);
                        break;
                    case "08":
                        tvStatus.setText(mContext.getString(R.string.处理失败));
                        tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                        tvLine.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                        tvOval.setBackgroundResource(R.drawable.oval_progress2);
                        break;
                    case "09":
                        tvStatus.setText(mContext.getString(R.string.处理成功));
                        tvStatus.setTextColor(mContext.getResources().getColor(R.color.green2));
                        tvLine.setBackgroundColor(mContext.getResources().getColor(R.color.green2));
                        tvOval.setBackgroundResource(R.drawable.oval_progress);
                        break;
                }
            }
            if (i == 2) {
                tvLine.setVisibility(View.GONE);
            }
           /* switch (i) {
                case 0:
                    tvStatus.setText(mContext.getString(R.string.申请提现));
                    break;
                case 1:
                    tvStatus.setText(mContext.getString(R.string.处理中));
                    break;
                case 2:
                    tvLine.setVisibility(View.GONE);
                    tvOval.setBackgroundResource(R.drawable.oval_progress3);
                    tvStatus.setText(mContext.getString(R.string.处理失败));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
            }*/
        }
    }
}
