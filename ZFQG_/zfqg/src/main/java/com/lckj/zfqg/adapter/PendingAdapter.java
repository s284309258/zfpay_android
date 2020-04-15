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
import com.lckj.jycm.zfqg_network.GetRecallTraditionalPosListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetRecallTraditionalPosListBean.DataBean.RecallTraditionalPosListBean> mData;
    public Map<Integer, Boolean> mMap = new HashMap<>();
    private int mWho;

    public PendingAdapter(Context context, List<GetRecallTraditionalPosListBean.DataBean.RecallTraditionalPosListBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pending, viewGroup, false);
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

    public void setWho(int who) {
        mWho = who;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(final int i) {
            switch (mWho) {
                case 0:
                    tvSn.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
                    break;
                case 1:
                    tvSn.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
                    break;
                case 2:
                    tvSn.setText(mContext.getString(R.string.流量卡号2) + mData.get(i).getCard_no());
                    break;
                case 3:
                    tvSn.setText(mContext.getString(R.string.SN码2) + mData.get(i).getSn());
                    break;
            }
            tvTime.setText(mData.get(i).getCre_datetime());
            ivCheck.setSelected(mMap.get(i));
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
