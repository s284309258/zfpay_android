package com.lckj.jycm.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.activity.TaskDetailsActivity;
import com.lckj.jycm.network.AdvInfoListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdatper extends RecyclerView.Adapter<TaskAdatper.ViewHolder> {

    private final Context mContext;
    private boolean select;
    private TextView lastCheck;
    private final List<AdvInfoListBean.DataBean.ListBean> mDatas;
    private AdvInfoListBean.DataBean.ListBean selectItem;
    private int mI;

    public AdvInfoListBean.DataBean.ListBean getSelectItem() {
        return selectItem;
    }

    public TaskAdatper(Context context, List<AdvInfoListBean.DataBean.ListBean> data) {
        mContext = context;
        mDatas = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_task, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean getSelect() {
        return select;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.btn_share)
        Button btnShare;
        @BindView(R.id.tv_check)
        TextView tvCheck;
        private AdvInfoListBean.DataBean.ListBean mListBean;
        private int mI;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            if (select) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lastCheck != null) {
                            lastCheck.setEnabled(false);
                        }
                        tvCheck.setEnabled(true);
                        TaskAdatper.this.mI = mI;
                        lastCheck = tvCheck;
                        selectItem = mListBean;
                    }
                });
            } else {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, TaskDetailsActivity.class);
                        intent.putExtra("data", mListBean);
                        mContext.startActivity(intent);
                    }
                });
            }
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TaskDetailsActivity.class);
                    intent.putExtra("data", mListBean);
                    mContext.startActivity(intent);
                }
            });
        }

        private void setData(int i) {
            if (i == 0 && selectItem == null && lastCheck == null) {
                selectItem = mDatas.get(0);
                lastCheck = tvCheck;
            }
            mI = i;
            if (TaskAdatper.this.mI == i) {
                tvCheck.setEnabled(true);
            } else {
                tvCheck.setEnabled(false);
            }
//            tvCheck.setEnabled(tvCheck.isEnabled());
            mListBean = mDatas.get(i);
            btnShare.setVisibility(select ? View.GONE : View.VISIBLE);
            tvCheck.setVisibility(select ? View.VISIBLE : View.GONE);
            ImageLoader.loadImage(mListBean.getHeadPhoto(), ivHeadImg, 10, 0);
            tvCount.setText(mContext.getString(R.string.task_share_count, mListBean.getShareNum() + ""));
            tvTitle.setText(mListBean.getAdvTitle());
            tvMoney.setText(mContext.getString(R.string.task_share_money, mListBean.getRevShare() + ""));
        }
    }
}
