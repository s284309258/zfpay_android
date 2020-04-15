package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetMessageRecordListBean;
import com.lckj.zfqg.activity.InformDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetMessageRecordListBean.DataBean.MessageRecordListBean> mData;

    public InformAdapter(Context context, List<GetMessageRecordListBean.DataBean.MessageRecordListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inform, viewGroup, false);
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
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        private String mMessage_id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, InformDetailsActivity.class).putExtra("id", mMessage_id));
                }
            });
        }

        private void setData(int i) {
            GetMessageRecordListBean.DataBean.MessageRecordListBean bean = mData.get(i);
            mMessage_id = bean.getMessage_id();
            tvTime.setText(bean.getCre_datetime());
            tvMoney.setText( "+" + bean.getMoney() + mContext.getString(R.string.元));
        }
    }
}
