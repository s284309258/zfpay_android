package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.SelectPolicy3RecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandardMerchantListAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean> mData;

    public StandardMerchantListAdapter(Context context, List<SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_standard_merchant_list, viewGroup, false);
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
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.tv_store_number)
        TextView tvStoreNumber;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;
        private boolean isSet = true;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean bean = mData.get(i);
            tvStoreName.setText(bean.getMer_name());
            tvStoreNumber.setText(bean.getMer_id());
            tvMoney.setText(bean.getTrade_amount() + "");
            tvDay.setText(bean.getExpire_day() + "天");
            if (isSet) {
                isSet = false;
                recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
                recyclerView.addItemDecoration(new SpaceItemDecoration(Utils.dp2px(mContext, 10), 2));
            }
            recyclerView.setAdapter(new ButtonAdapter(mContext, bean));
        }
    }
}
