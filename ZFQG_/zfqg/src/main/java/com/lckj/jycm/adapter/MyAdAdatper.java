package com.lckj.jycm.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.activity.AdDetailsActivity;
import com.lckj.jycm.activity.TaskDetailsActivity;
import com.lckj.jycm.network.AdvArticleListBean;
import com.lckj.jycm.network.AdvInfoListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdAdatper extends RecyclerView.Adapter {

    private final Activity mActivity;
    private final int mType;
    private final ArrayList<AdvInfoListBean.DataBean.ListBean> mData;

    public MyAdAdatper(ArrayList<AdvInfoListBean.DataBean.ListBean> data, int type, Activity activity) {
        mActivity = activity;
        mType = type;
        mData = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_my_ad, viewGroup, false);
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
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_time)
        TextView tvTime;
        private AdvInfoListBean.DataBean.ListBean mListBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, TaskDetailsActivity.class);
                    intent.putExtra("data", mListBean);
                    mActivity.startActivity(intent);
                }
            });
        }

        @SuppressLint("StringFormatInvalid")
        private void setData(int i) {
            mListBean = mData.get(i);
            ImageLoader.loadImage(mListBean.getAdvImg(), ivImg, 10, 0);
            tvContent.setText(mListBean.getAdvIntro());
            tvMoney.setText(mActivity.getString(R.string.my_ad_item_sum_money, mListBean.getPutBudget() + ""));
            tvTitle.setText(mListBean.getAdvTitle());
            tvTime.setText(mListBean.getCreateDate());
        }
    }
}
