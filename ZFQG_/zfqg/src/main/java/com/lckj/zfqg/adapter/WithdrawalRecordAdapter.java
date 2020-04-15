package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.framework.data.DataManager;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetCashRecordListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WithdrawalRecordAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetCashRecordListBean.DataBean.CashRecordListBean> mData;
    private final DataManager mDataManager;

    public WithdrawalRecordAdapter(Context context, List<GetCashRecordListBean.DataBean.CashRecordListBean> data, DataManager dataManager) {
        mContext = context;
        mData = data;
        mDataManager = dataManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_withdrawal_record_, viewGroup, false);
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
        @BindView(R.id.tv_bank_number)
        TextView tvBankNumber;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_real_count)
        TextView tvRealCount;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;
        @BindView(R.id.tv_sum_count)
        TextView tvSumCount;
        @BindView(R.id.tv_service_count)
        TextView tvServiceCount;
        @BindView(R.id.tv_tax_count)
        TextView tvTaxCount;
        @BindView(R.id.tv_deduct_count)
        TextView tvDeductCount;
        @BindView(R.id.ll_deduct)
        LinearLayout llDeduct;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            if (mDataManager.getAlgebra().equals("1")) {
                llDeduct.setVisibility(View.VISIBLE);
            } else {
                llDeduct.setVisibility(View.GONE);
            }
            GetCashRecordListBean.DataBean.CashRecordListBean bean = mData.get(i);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(new ProgressAdapter(mContext, bean.getCashRecordDetailList()));
            tvBankNumber.setText(bean.getAccount());
            tvServiceCount.setText("-" + bean.getSingle_feet_money());
            tvTaxCount.setText("-" + bean.getRate_feet_money());
            tvDeductCount.setText("-" + bean.getDeduct_money());
            tvSumCount.setText(bean.getCash_money());
            tvRealCount.setText("￥" + bean.getCash_actual_money());
            tvTime.setText(bean.getCre_date());
        }
    }
}
