package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetReferAgencyTraditionalPosListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.MerchantInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgencyInfoAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetReferAgencyTraditionalPosListBean.DataBean.ReferAgencyTraditionalPosListBean> mData;
    private int isPos;

    public AgencyInfoAdapter(Context context, List<GetReferAgencyTraditionalPosListBean.DataBean.ReferAgencyTraditionalPosListBean> data, int isPos) {
        mContext = context;
        mData = data;
        this.isPos = isPos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_agency_info, viewGroup, false);
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

    public void setIsPos(int isPos) {
        this.isPos = isPos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_belong)
        TextView tvBelong;
        private String mSn;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MerchantInfoActivity.class).putExtra("sn", mSn).putExtra("isPos", isPos));
                }
            });
        }

        private void setData(int i) {
            mSn = mData.get(i).getSn();
            ImageLoader.loadImage(mData.get(i).getPerformance(), ivHeadImg, 20, 0);
            if (isPos == 0 || isPos == 2) {
                tvName.setText(mContext.getString(R.string.商户名称2) + mData.get(i).getMer_name());
            } else {
                tvName.setText(mContext.getString(R.string.商户名字2) + mData.get(i).getName());
            }
            tvSn.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
            tvCount.setText(mContext.getString(R.string.交易额2) + mData.get(i).getPerformance());
            tvBelong.setVisibility(mData.get(i).getState_status().equals("1") ? View.GONE : View.VISIBLE);
        }
    }
}
