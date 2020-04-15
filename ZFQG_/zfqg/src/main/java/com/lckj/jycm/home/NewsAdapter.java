package com.lckj.jycm.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.article.activity.ArticleShareActivity;
import com.lckj.jycm.network.AdvArticleListBean;
import com.lckj.jycm.network.HomePageBean;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final ArrayList<HomePageBean.DataBean.ListBean> datas;


    public NewsAdapter(ArrayList<HomePageBean.DataBean.ListBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final HomePageBean.DataBean.ListBean data = datas.get(i);
        holder.tvTitle.setText(data.getArtTitle());
        holder.tvForwardCount.setText(""+data.getShareNum());
        holder.tvSource.setVisibility(TextUtils.isEmpty(data.getSource())?View.GONE:View.VISIBLE);
        holder.tvSource.setText(data.getSource());
        ImageLoader.loadImage(data.getArtCover(),holder.ivImg,3,0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), ArticleShareActivity.class)
                        .putExtra("url", data.getArtUrl())
                        .putExtra("title", data.getArtTitle())
                        .putExtra("arId", data.getId())
                        .putExtra("status", data.getAuditStatus())
                        .putExtra("img", data.getArtCover()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.tv_forward_count)
        TextView tvForwardCount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
