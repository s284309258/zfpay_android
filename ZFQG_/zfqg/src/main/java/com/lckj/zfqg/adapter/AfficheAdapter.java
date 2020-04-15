package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetNewNoticeBean;
import com.lckj.zfqg.activity.AfficheDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AfficheAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<GetNewNoticeBean.DataBean.NoticeInfoListBean> mNotices;

    public AfficheAdapter(Context context, List<GetNewNoticeBean.DataBean.NoticeInfoListBean> notices) {
        mContext = context;
        mNotices = notices;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_affiche, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return mNotices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_news)
        RelativeLayout rlNews;
        private String mNotice_id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, AfficheDetailsActivity.class).putExtra("id", mNotice_id));
                }
            });
        }

        private void setData(int i) {
            tvTitle.setText(mNotices.get(i).getNotice_title());
            tvTime.setText(mNotices.get(i).getCre_date());
            mNotice_id = mNotices.get(i).getNotice_id();
            if (mNotices.get(i).getIs_read() == 0) {
                tvStatus.setVisibility(View.VISIBLE);
            } else {
                tvStatus.setVisibility(View.GONE);
            }
        }
    }
}
