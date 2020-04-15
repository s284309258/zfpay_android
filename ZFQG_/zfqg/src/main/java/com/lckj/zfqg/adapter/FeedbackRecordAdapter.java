package com.lckj.zfqg.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetUserFeedBackListBean;
import com.lckj.zfqg.activity.FeedbackDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackRecordAdapter extends RecyclerView.Adapter {
    private ArrayList<GetUserFeedBackListBean.DataBean.UserFeedBackListBean> datas;

    public FeedbackRecordAdapter(ArrayList<GetUserFeedBackListBean.DataBean.UserFeedBackListBean> mDatas) {
        datas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feedback_record, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        GetUserFeedBackListBean.DataBean.UserFeedBackListBean data = datas.get(i);
        if (viewHolder instanceof ViewHolder){
            ((ViewHolder) viewHolder).setData(data);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_view_answer)
        TextView tvViewAnswer;
        private GetUserFeedBackListBean.DataBean.UserFeedBackListBean data;

        ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), FeedbackDetailActivity.class).putExtra("data",data));
                }
            });
        }

        public void setData(GetUserFeedBackListBean.DataBean.UserFeedBackListBean data) {
            this.data = data;
            tvQuestion.setText(data.getFeedback_title());
            tvTime.setText(data.getCre_time());
        }

    }
}
