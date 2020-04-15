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
import com.lckj.jycm.zfqg_network.GetMoneyLockerCollegeListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.CoffersSchoolActivity;
import com.lckj.zfqg.activity.CoffersSchoolDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoffersSchoolAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetMoneyLockerCollegeListBean.DataBean.MoneyLockerCollegeListBean> mData;

    public CoffersSchoolAdapter(Context context, List<GetMoneyLockerCollegeListBean.DataBean.MoneyLockerCollegeListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_coffers_school, viewGroup, false);
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
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        private String mid;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, CoffersSchoolDetailsActivity.class).putExtra("id", mid));
                }
            });
        }

        private void setData(int i) {
            mid = mData.get(i).getMoney_locker_id();
            ImageLoader.loadImage(mData.get(i).getMoney_locker_cover(), ivHeadImg, 10, 0);
            tvTime.setText(mData.get(i).getCre_datetime());
            tvTitle.setText(mData.get(i).getMoney_locker_title());
        }
    }
}
