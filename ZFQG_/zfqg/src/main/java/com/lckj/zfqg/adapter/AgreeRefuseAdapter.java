package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetRecallTraditionalPosListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgreeRefuseAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetRecallTraditionalPosListBean.DataBean.RecallTraditionalPosListBean> mData;
    private final int mType;
    private int mWho;

    public AgreeRefuseAdapter(Context context, List<GetRecallTraditionalPosListBean.DataBean.RecallTraditionalPosListBean> data, int type) {
        mContext = context;
        mData = data;
        mType = type;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_agree_refuse, viewGroup, false);
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

    public void setWho(int who) {
        mWho = who;
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
            switch (mWho) {
                case 0:
                    tvTitle.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
                    break;
                case 1:
                    tvTitle.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
                    break;
                case 2:
                    tvTitle.setText(mContext.getString(R.string.流量卡号2) + mData.get(i).getCard_no());
                    break;
                case 3:
                    tvTitle.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
                    break;
            }
            switch (mType) {
                case 1:
                    tvStatus.setText(mContext.getString(R.string.已同意));
                    break;
                case 2:
                    tvStatus.setText(mContext.getString(R.string.已拒绝));
                    break;
            }
            tvTime.setText(mData.get(i).getCre_datetime());
        }
    }
}
