package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.SearchLikeBankBean;
import com.lckj.zfqg.activity.AddBankCardActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopupWindowAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<SearchLikeBankBean.DataBean.SysBankListBean> mData;

    public PopupWindowAdapter(Context context, List<SearchLikeBankBean.DataBean.SysBankListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pop, viewGroup, false);
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
        @BindView(R.id.tv_bank_name)
        TextView tvBankName;
        @BindView(R.id.tv_bank_code)
        TextView tvBankCode;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            final SearchLikeBankBean.DataBean.SysBankListBean bean = mData.get(i);
            tvBankName.setText(bean.getBank_name());
            tvBankCode.setText(bean.getBank_code());
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddBankCardActivity activity = (AddBankCardActivity) mContext;
                    activity.setData(bean);

                }
            });
        }
    }
}
