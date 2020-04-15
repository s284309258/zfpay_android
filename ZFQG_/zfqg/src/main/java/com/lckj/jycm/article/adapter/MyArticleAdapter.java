package com.lckj.jycm.article.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.activity.InvestAdActivity;
import com.lckj.jycm.activity.SelectorArticleActivity;
import com.lckj.jycm.article.activity.ArticleShareActivity;
import com.lckj.jycm.network.AdvArticleListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyArticleAdapter extends RecyclerView.Adapter<MyArticleAdapter.MyViewHolder> {
    private final ArrayList<AdvArticleListBean.DataBean.ListBean> datas;
    private final int mType;
    private final FragmentActivity mActivity;
    private AdvArticleListBean.DataBean.ListBean selectItem;
    private TextView lastCheck;
    private int mI;

    public MyArticleAdapter(ArrayList<AdvArticleListBean.DataBean.ListBean> mDatas, int type, FragmentActivity activity) {
        this.datas = mDatas;
        mType = type;
        mActivity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    public AdvArticleListBean.DataBean.ListBean getSelectItem() {
        return selectItem;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        final AdvArticleListBean.DataBean.ListBean data = datas.get(i);
        if (mType == 1) {
            holder.tvCheck.setVisibility(View.GONE);
        } else {
            holder.tvCheck.setVisibility(View.VISIBLE);
        }
        holder.tvTitle.setText(data.getArtTitle());
        ImageLoader.loadImage(data.getArtCover(), holder.ivImgRight, 3, 0);
        holder.tvForwardCount.setText("" + data.getShareNum());
        if (TextUtils.isEmpty(data.getSource())) {
            holder.tvSource.setVisibility(View.GONE);
        } else {
            holder.tvSource.setVisibility(View.VISIBLE);
            holder.tvSource.setText(data.getSource());
        }
        if (mI == i) {
            holder.tvCheck.setEnabled(true);
        } else {
            holder.tvCheck.setEnabled(false);
        }
        if (i == 0 && selectItem == null) {
            selectItem = data;
            lastCheck = holder.tvCheck;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mType == 1) {
                    mActivity.startActivity(new Intent(v.getContext(), ArticleShareActivity.class)
                            .putExtra("url", data.getArtUrl())
                            .putExtra("title", data.getArtTitle())
                            .putExtra("arId", data.getId())
                            .putExtra("status", data.getAuditStatus())
                            .putExtra("img", data.getArtCover()));
                } else {
                    mI = i;
                    if (lastCheck != null) {
                        lastCheck.setEnabled(false);
                    }
                    holder.tvCheck.setEnabled(true);
                    lastCheck = holder.tvCheck;
                    selectItem = data;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static
    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_img_right)
        ImageView ivImgRight;
        @BindView(R.id.v_divider)
        View vDivider;
        @BindView(R.id.tv_source)
        TextView tvSource;
        @BindView(R.id.tv_forward_count)
        TextView tvForwardCount;
        @BindView(R.id.tv_check)
        TextView tvCheck;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
