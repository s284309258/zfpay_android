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
import com.lckj.jycm.zfqg_network.GetMposOnlineActivityListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.ActivityDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PosActivityListAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetMposOnlineActivityListBean.DataBean.MposOnlineActivityListBean> mData;
    private final int mSubsetType;

    public PosActivityListAdapter(Context context, List<GetMposOnlineActivityListBean.DataBean.MposOnlineActivityListBean> data, int subsetType) {
        mContext = context;
        mData = data;
        mSubsetType = subsetType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pos_activity_list, viewGroup, false);
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
        @BindView(R.id.tv_join)
        TextView tvJoin;
        private GetMposOnlineActivityListBean.DataBean.MposOnlineActivityListBean mBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, ActivityDetailsActivity.class).putExtra("id", mBean.getActivity_id()).putExtra("subsetType",mSubsetType));
                }
            });
        }

        private void setData(int i) {
            mBean = mData.get(i);
            ImageLoader.loadImage(mBean.getCover_url(), ivHeadImg, 10, 0);
            tvTime.setText(mBean.getStart_date() + "-" + mBean.getEnd_date());
            tvTitle.setText(mBean.getActivity_name());
        }
    }
}
