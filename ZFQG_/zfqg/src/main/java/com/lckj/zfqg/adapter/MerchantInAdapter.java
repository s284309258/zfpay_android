package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosInstallListBean;
import com.lckj.zfqg.activity.MerchantInDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantInAdapter extends RecyclerView.Adapter {

    private final List<GetTraditionalPosInstallListBean.DataBean.TraditionalPosInstallListBean> mData;
    private final Context mContext;
    private final String mType;

    public MerchantInAdapter(Context context, List<GetTraditionalPosInstallListBean.DataBean.TraditionalPosInstallListBean> data, String type) {
        mData = data;
        mType = type;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_merchant_in, viewGroup, false);
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
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.btn_details)
        Button btnDetails;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            GetTraditionalPosInstallListBean.DataBean.TraditionalPosInstallListBean bean = mData.get(i);
            tvTitle.setText(bean.getMerchant_name());
            if (mType.equals("00"))
                tvName.setText(mContext.getString(R.string.商户号2) + bean.getMer_code());
            else
                tvName.setText(mContext.getString(R.string.退回原因2) + bean.getBiz_msg().replace("审核不通过：", ""));
            tvNumber.setText(mContext.getString(R.string.审核时间2) + bean.getCre_datetime());
            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MerchantInDetailsActivity.class).putExtra("id", bean.getInstall_id()).putExtra("type", mType));
                }
            });
        }
    }
}
