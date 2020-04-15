package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosSysParamRateListBean;
import com.lckj.zfqg.activity.MPOSAllocationActivity;
import com.lckj.zfqg.activity.MPOSAllocationUpdateActivity;
import com.lckj.zfqg.activity.POSAllocationActivity;
import com.lckj.zfqg.activity.POSAllocationUpdateActivity;
import com.lckj.zfqg.activity.UpdateRateActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfigAdapter extends RecyclerView.Adapter {

    private GetTraditionalPosSysParamRateListBean.DataBean.TraditionalPosSysParamRateBean mConfigData = new GetTraditionalPosSysParamRateListBean.DataBean.TraditionalPosSysParamRateBean();
    private final Context mContext;
    private int mType;
    private List<String> mData;

    public ConfigAdapter(Context context) {
        mContext = context;
    }

    public ConfigAdapter(Context context, List<String> data, int type) {
        mContext = context;
        mData = data;
        mType = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_config, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        if (mType == 0)
            return mConfigData.getCard_settle_price_list().size();
        else if (mType == 1)
            return mConfigData.getCloud_settle_price_list().size();
        else if (mType == 2)
            return mConfigData.getSingle_profit_rate_list().size();
        else if (mType == 3)
            return mConfigData.getCash_back_rate_list().size();
        else if (mType == 4)
            return mConfigData.getWeixin_settle_price_list().size();
        else if (mType == 5)
            return mConfigData.getZhifubao_settle_price_list().size();
        else if (mType == 6)
            return mData.size();
        else if (mType == 7)
            return mConfigData.getMer_cap_fee_list().size();
        else if (mType == 8)
            return mConfigData.getCard_settle_price_vip_list().size();
        return 0;
    }

    public void setType(int type) {
        mType = type;
    }

    public void setData(GetTraditionalPosSysParamRateListBean.DataBean.TraditionalPosSysParamRateBean configData) {
        mConfigData = configData;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof MPOSAllocationActivity) {
                        MPOSAllocationActivity activity = (MPOSAllocationActivity) mContext;
                        activity.setData(tvName.getText().toString(), mType);
                    } else if (mContext instanceof POSAllocationActivity) {
                        POSAllocationActivity activity = (POSAllocationActivity) mContext;
                        activity.setData(tvName.getText().toString(), mType);
                    } else if (mContext instanceof POSAllocationUpdateActivity) {
                        POSAllocationUpdateActivity activity = (POSAllocationUpdateActivity) mContext;
                        activity.setData(tvName.getText().toString(), mType);
                    } else if (mContext instanceof MPOSAllocationUpdateActivity) {
                        MPOSAllocationUpdateActivity activity = (MPOSAllocationUpdateActivity) mContext;
                        activity.setData(tvName.getText().toString(), mType);
                    } else if (mContext instanceof UpdateRateActivity) {
                        UpdateRateActivity activity = (UpdateRateActivity) mContext;
                        activity.setData(tvName.getText().toString());
                    }
                }
            });
        }

        private void setData(int i) {
            switch (mType) {
                case 0:
                    tvName.setText(mConfigData.getCard_settle_price_list().get(i) + "%");
                    break;
                case 1:
                    tvName.setText(mConfigData.getCloud_settle_price_list().get(i) + "%");
                    break;
                case 2:
                    tvName.setText(mConfigData.getSingle_profit_rate_list().get(i) + "%");
                    break;
                case 3:
                    tvName.setText(mConfigData.getCash_back_rate_list().get(i) + "%");
                    break;
                case 4:
                    tvName.setText(mConfigData.getWeixin_settle_price_list().get(i) + "%");
                    break;
                case 5:
                    tvName.setText(mConfigData.getZhifubao_settle_price_list().get(i) + "%");
                    break;
                case 6:
                    tvName.setText(mData.get(i) + "%");
                    break;
                case 7:
                    tvName.setText(mConfigData.getMer_cap_fee_list().get(i));
                    break;
                case 8:
                    tvName.setText(mConfigData.getCard_settle_price_vip_list().get(i) + "%");
                    break;
            }
        }
    }
}
