package com.lckj.jycm.wallet;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.network.AccountRecordListBean;
import com.lckj.jycm.wallet.bean.WithdrawalRecordData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GainRecordAdapter extends RecyclerView.Adapter {
    private final List<AccountRecordListBean.DataBean.ListBean> mData;

    public GainRecordAdapter(List<AccountRecordListBean.DataBean.ListBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gain_record, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_count)
        TextView tvCount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(AccountRecordListBean.DataBean.ListBean listBean) {
            tvName.setText(listBean.getDealType());
            tvCount.setText(listBean.getChangeTxt());
            tvTime.setText(listBean.getCreateDate());
        }
    }
}
