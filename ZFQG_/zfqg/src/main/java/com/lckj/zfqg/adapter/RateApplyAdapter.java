package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetApplyRateTraditionalPosListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RateApplyAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<GetApplyRateTraditionalPosListBean.DataBean.ApplyRateTraditionalPosListBean> mData;
    private final int mType;
    public Map<Integer, Boolean> mMap = new HashMap<>();

    public RateApplyAdapter(Context context, List<GetApplyRateTraditionalPosListBean.DataBean.ApplyRateTraditionalPosListBean> data, int type) {
        mContext = context;
        mData = data;
        mType = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rate_apply, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        for (int i = 0; i < mData.size(); i++) {
            if (!mMap.containsKey(i)) {
                mMap.put(i, false);
            }
        }
        return mData.size();
    }

    public void setAll(boolean isAll) {
        for (Map.Entry<Integer, Boolean> entry : mMap.entrySet()) {
            entry.setValue(isAll);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(final int i) {
            GetApplyRateTraditionalPosListBean.DataBean.ApplyRateTraditionalPosListBean bean = mData.get(i);
            tvSn.setText(bean.getSn());
            tvRate.setText(mContext.getString(R.string.当前费率2) + bean.getCredit_card_rate() + "%");
            ivCheck.setSelected(mMap.get(i));
            if(mType == 0){
                ivHeadImg.setImageResource(R.mipmap.icon_item_pos);
            }else if(mType == 1){
                ivHeadImg.setImageResource(R.mipmap.icon_item_mpos);
            }else if(mType == 2){
                ivHeadImg.setImageResource(R.mipmap.icon_item_epos);
            }
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMap.put(i, !mMap.get(i));
                    ivCheck.setSelected(mMap.get(i));
                }
            });
        }
    }
}
