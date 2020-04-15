package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetAllMerchantTraditionalPosListBean;
import com.lckj.zfqg.activity.HomeMPOSActivity;
import com.lckj.zfqg.activity.MerchantInfoActivity;
import com.lckj.zfqg.activity.MerchantListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetAllMerchantTraditionalPosListBean.DataBean.AllMerchantTraditionalPosListBean> mData;
    private final int isPos;

    public MerchantAdapter(Context context, List<GetAllMerchantTraditionalPosListBean.DataBean.AllMerchantTraditionalPosListBean> data, int isPos) {
        mContext = context;
        mData = data;
        this.isPos = isPos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_merhant, viewGroup, false);
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
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.tv_type_name)
        TextView tvTypeName;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type_number)
        TextView tvTypeNumber;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_mer_name)
        TextView tvMerName;
        @BindView(R.id.tv_mer_id)
        TextView tvMerId;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.iv_edit)
        ImageView ivEdit;
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
            if (isPos == 0||isPos == 2) {
                rl.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(mData.get(i).getMer_name())) {
                    tvMerName.setText(mData.get(i).getMer_name());
                } else {
                    tvMerName.setText("——");
                }
                if (!TextUtils.isEmpty(mData.get(i).getMer_id())) {
                    tvMerId.setText(mData.get(i).getMer_id());
                } else {
                    tvMerId.setText("——");
                }
            } else {
                rl.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(mData.get(i).getName())) {
                tvName.setText(mData.get(i).getName());
            } else {
                tvName.setText("——");
            }
            if (!TextUtils.isEmpty(mData.get(i).getTel())) {
                tvPhone.setText(mData.get(i).getTel());
            } else {
                tvPhone.setText("——");
            }
            tvNumber.setText(mData.get(i).getSn());
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, HomeMPOSActivity.class).putExtra("sn", mSn).putExtra("type", 1));
                }
            });
        }
    }
}
