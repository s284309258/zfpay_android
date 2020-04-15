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
import com.lckj.jycm.zfqg_network.GetNewNewsBean;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.activity.CoffersSchoolDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetNewNewsBean.DataBean.NewsInfoListBean> mNews;

    public HomeAdapter(Context context, List<GetNewNewsBean.DataBean.NewsInfoListBean> news) {
        mContext = context;
        mNews = news;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_count)
        TextView tvCount;
        private GetNewNewsBean.DataBean.NewsInfoListBean mBean;
        private String mNews_id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, CoffersSchoolDetailsActivity.class).putExtra("id", mNews_id).putExtra("who", true));
                }
            });
        }

        private void setData(int i) {
            mBean = mNews.get(i);
            mNews_id = mBean.getNews_id();
            ImageLoader.loadImage(mBean.getNews_cover(), ivHeadImg, 10, 0);
            tvTitle.setText(mBean.getNews_title());
            tvTime.setText(mBean.getCre_date());
            tvCount.setText(mBean.getBrowse_num());
        }
    }
}
