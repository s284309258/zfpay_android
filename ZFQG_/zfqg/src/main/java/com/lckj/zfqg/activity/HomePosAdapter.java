package com.lckj.zfqg.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetMposListBean;
import com.lckj.jycm.zfqg_network.GetRefererAgencyBean;
import com.lckj.jycm.zfqg_network.GetScanTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePosAdapter<E> extends RecyclerView.Adapter {

    private final Context mContext;
    private ImageView mLastIvCheck;
    private List<E> mData = new ArrayList<>();

    public HomePosAdapter(Context context) {
        mContext = context;
    }

    public HomePosAdapter(Context context, List<E> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_pos, viewGroup, false);
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

    public void setData(List<E> data) {
        mData = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.tv_tag)
        TextView tvTag;
        private String mUser_id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            if (mData.get(i) instanceof GetScanTraditionalPosListBean.DataBean.ScanTraditionalPosListBean) {
                GetScanTraditionalPosListBean.DataBean.ScanTraditionalPosListBean bean = (GetScanTraditionalPosListBean.DataBean.ScanTraditionalPosListBean) mData.get(i);
                tvSn.setText(bean.getSn());
                tvTag.setText(bean.getTrapos_id());
                tvTag.setVisibility(View.GONE);
            } else if (mData.get(i) instanceof GetMposListBean.DataBean.MposListBean) {
                GetMposListBean.DataBean.MposListBean bean = (GetMposListBean.DataBean.MposListBean) mData.get(i);
                tvSn.setText(bean.getSn());
                if (!TextUtils.isEmpty(bean.getName()) && !TextUtils.isEmpty(bean.getTel()))
                    tvTag.setText(bean.getName() + "(" + bean.getTel() + ")");
                tvTag.setVisibility(View.VISIBLE);
            } else if (mData.get(i) instanceof GetRefererAgencyBean.DataBean.RefererAgencyListBean) {
                GetRefererAgencyBean.DataBean.RefererAgencyListBean bean = (GetRefererAgencyBean.DataBean.RefererAgencyListBean) mData.get(i);
                tvSn.setText(bean.getReal_name());
                tvTag.setText(bean.getUser_tel());
                tvTag.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                mUser_id = bean.getUser_id();
            }else if (mData.get(i) instanceof GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) {
                GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean bean = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                tvSn.setText(bean.getSn());
                tvTag.setVisibility(View.GONE);
            } else {

            }
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivCheck.setVisibility(View.VISIBLE);
                    if (mContext instanceof HomeMPOSActivity) {
                        HomeMPOSActivity activity = (HomeMPOSActivity) mContext;
                        activity.setSn(tvSn.getText().toString());
                    } else if (mContext instanceof ApplyQrcodePayActivity) {
                        ApplyQrcodePayActivity activity = (ApplyQrcodePayActivity) mContext;
                        activity.setSn(tvSn.getText().toString());
                    }else if (mContext instanceof MPOSAllocationActivity) {
                        MPOSAllocationActivity activity = (MPOSAllocationActivity) mContext;
                        activity.setName(tvSn.getText().toString(), mUser_id);
                    }else if (mContext instanceof POSAllocationActivity) {
                        POSAllocationActivity activity = (POSAllocationActivity) mContext;
                        activity.setName(tvSn.getText().toString(), mUser_id);
                    }else if (mContext instanceof CardAllocationActivity) {
                        CardAllocationActivity activity = (CardAllocationActivity) mContext;
                        activity.setName(tvSn.getText().toString(), mUser_id);
                    }else if (mContext instanceof HomeMPOSActivity) {
                        HomeMPOSActivity activity = (HomeMPOSActivity) mContext;
                        activity.setSn(tvSn.getText().toString());
                    }
                    if (mLastIvCheck != null) {
                        mLastIvCheck.setVisibility(View.GONE);
                    }
                    mLastIvCheck = ivCheck;
                }
            });
        }
    }
}
