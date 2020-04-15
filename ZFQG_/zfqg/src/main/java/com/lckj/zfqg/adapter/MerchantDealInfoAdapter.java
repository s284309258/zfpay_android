package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosTradeDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantDealInfoAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetTraditionalPosTradeDetailBean.DataBean.TraditionalPosTradeDetailBean> mData;

    public MerchantDealInfoAdapter(Context context, List<GetTraditionalPosTradeDetailBean.DataBean.TraditionalPosTradeDetailBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_material_deal, viewGroup, false);
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
        @BindView(R.id.tv_deal)
        TextView tvDeal;
        @BindView(R.id.tv_profit)
        TextView tvProfit;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetTraditionalPosTradeDetailBean.DataBean.TraditionalPosTradeDetailBean bean = mData.get(i);
            tvDeal.setText(bean.getTrans_amount());
            tvProfit.setText(bean.getBenefit_money());
            tvTime.setText(bean.getTrans_time());
        }
    }
}
