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
import com.lckj.jycm.zfqg_network.GetReferAgencyListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.AgencyInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgencyAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetReferAgencyListBean.DataBean.ReferAgencyListBean> mData;

    public AgencyAdapter(Context context, List<GetReferAgencyListBean.DataBean.ReferAgencyListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_agency, viewGroup, false);
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
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        private String mUser_id;
        private String mUser_tel;
        private String mHead_photo;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, AgencyInfoActivity.class).putExtra("id", mUser_id).putExtra("phone", mUser_tel).putExtra("head_img", mHead_photo));
                }
            });
        }

        private void setData(int i) {
            GetReferAgencyListBean.DataBean.ReferAgencyListBean bean = mData.get(i);
            mUser_tel = mData.get(i).getUser_tel();
            mUser_id = bean.getUser_id();
            mHead_photo = bean.getHead_photo();
            ImageLoader.loadImage(bean.getHead_photo(), ivHeadImg, 20, 0);
            tvName.setText(bean.getReal_name());
            tvPhone.setText(mUser_tel);
        }
    }
}
