package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosInstallDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantInDetailsAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetTraditionalPosInstallDetailBean.DataBean.TerminalListBean> mData;

    public MerchantInDetailsAdapter(Context context, List<GetTraditionalPosInstallDetailBean.DataBean.TerminalListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_merchant_in_details, viewGroup, false);
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
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_sim)
        TextView tvSim;
        @BindView(R.id.tv_net)
        TextView tvNet;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetTraditionalPosInstallDetailBean.DataBean.TerminalListBean bean = mData.get(i);

            if (!TextUtils.isEmpty(bean.getTerminal()))
                tvSn.setText(bean.getTerminal());
            if (!TextUtils.isEmpty(bean.getMachine_id()))
                tvId.setText(bean.getMachine_id());
            if (!TextUtils.isEmpty(bean.getSim_card()))
                tvSim.setText(bean.getSim_card());
            if (!TextUtils.isEmpty(bean.getIs_take_machi()))
                tvNet.setText(bean.getIs_take_machi().equals("1") ? mContext.getString(R.string.是) : mContext.getString(R.string.否));
        }
    }
}
