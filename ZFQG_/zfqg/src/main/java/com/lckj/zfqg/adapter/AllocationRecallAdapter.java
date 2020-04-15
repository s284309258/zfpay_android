package com.lckj.zfqg.adapter;

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
import com.lckj.jycm.zfqg_network.GetMposAllocationListBean;
import com.lckj.jycm.zfqg_network.GetMposRecallListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTraditionalPosRecallListBean;
import com.lckj.jycm.zfqg_network.GetTrafficCardAllocationListBean;
import com.lckj.jycm.zfqg_network.GetTrafficCardRecallListBean;
import com.lckj.zfqg.fragment.AllocationRecallFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllocationRecallAdapter<E> extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<E> mData;
    private final int mType;
    private final AllocationRecallFragment<E> mFragment;
    private int mSubtype;
    public Map<Integer, Boolean> mMap = new HashMap<>();

    public AllocationRecallAdapter(Context context, List<E> data, int type, AllocationRecallFragment<E> fragment) {
        mContext = context;
        mData = data;
        mType = type;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_allocation_recall, viewGroup, false);
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

    public void setSubtype(int subtype) {
        mSubtype = subtype;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_offline)
        TextView tvOffline;
        @BindView(R.id.tv_online)
        TextView tvOnline;
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.iv_head_img2)
        ImageView ivHeadImg2;
        @BindView(R.id.tv_sn2)
        TextView tvSn2;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.tv_policy)
        TextView tvPolicy;
        @BindView(R.id.tv_vip)
        TextView tvVip;
        @BindView(R.id.iv_check2)
        ImageView ivCheck2;
        @BindView(R.id.rl2)
        RelativeLayout rl2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(final int i) {
            if (mType == 0) {
                if (mSubtype == 0) {
                    rl.setVisibility(View.VISIBLE);
                    rl2.setVisibility(View.GONE);
                    tvVip.setVisibility(View.GONE);
                    ivHeadImg.setImageResource(R.mipmap.icon_item_mpos);
                    GetMposAllocationListBean.DataBean.MposAllocationListBean bean = (GetMposAllocationListBean.DataBean.MposAllocationListBean) mData.get(i);
                    tvSn.setText(mContext.getString(R.string.SN码2) + bean.getSn());
                    tvOnline.setText(mContext.getString(R.string.刷卡结算价2) + bean.getCard_settle_price() + "%");
                    tvOffline.setText(mContext.getString(R.string.云闪付结算价2) + bean.getCloud_settle_price() + "%");
                    if (TextUtils.isEmpty(bean.getExpire_day())) {
                        tvDay.setVisibility(View.GONE);
                    } else {
                        tvDay.setVisibility(View.VISIBLE);
                        tvDay.setText("激活剩余天数：" + bean.getExpire_day());
                    }
                    if (TextUtils.isEmpty(bean.getPolicy_name())) {
                        tvPolicy.setVisibility(View.GONE);
                    } else {
                        tvPolicy.setVisibility(View.VISIBLE);
                        tvPolicy.setText("代理政策：" + bean.getPolicy_name());
                    }
                } else if (mSubtype == 1) {
                    rl.setVisibility(View.VISIBLE);
                    rl2.setVisibility(View.GONE);
                    ivHeadImg.setImageResource(R.mipmap.icon_item_pos);
                    GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean bean = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                    tvSn.setText(mContext.getString(R.string.SN码2) + bean.getSn());
                    tvOnline.setText(mContext.getString(R.string.刷卡结算价2) + bean.getCard_settle_price() + "%");
                    tvOffline.setText(mContext.getString(R.string.云闪付结算价2) + bean.getCloud_settle_price() + "%");
                    if (TextUtils.isEmpty(bean.getExpire_day())) {
                        tvDay.setVisibility(View.GONE);
                    } else {
                        tvDay.setVisibility(View.VISIBLE);
                        tvDay.setText("激活剩余天数：" + bean.getExpire_day());
                    }
                    if (TextUtils.isEmpty(bean.getPolicy_name())) {
                        tvPolicy.setVisibility(View.GONE);
                    } else {
                        tvPolicy.setVisibility(View.VISIBLE);
                        tvPolicy.setText("代理政策：" + bean.getPolicy_name());
                    }if (TextUtils.isEmpty(bean.getCard_settle_price_vip())) {
                        tvVip.setVisibility(View.GONE);
                    } else {
                        tvVip.setVisibility(View.VISIBLE);
                        tvVip.setText("VIP刷卡结算价：" + bean.getCard_settle_price_vip());
                    }
                } else if (mSubtype == 2) {
                    rl.setVisibility(View.VISIBLE);
                    rl2.setVisibility(View.GONE);
                    ivHeadImg.setImageResource(R.mipmap.icon_item_epos);
                    GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean bean = (GetTraditionalPosAllocationListBean.DataBean.TraditionalPosAllocationListBean) mData.get(i);
                    tvSn.setText(mContext.getString(R.string.SN码2) + bean.getSn());
                    tvOnline.setText(mContext.getString(R.string.刷卡结算价2) + bean.getCard_settle_price() + "%");
                    tvOffline.setText(mContext.getString(R.string.云闪付结算价2) + bean.getCloud_settle_price() + "%");
                    if (TextUtils.isEmpty(bean.getExpire_day())) {
                        tvDay.setVisibility(View.GONE);
                    } else {
                        tvDay.setVisibility(View.VISIBLE);
                        tvDay.setText("激活剩余天数：" + bean.getExpire_day());
                     }
                    if (TextUtils.isEmpty(bean.getPolicy_name())) {
                        tvPolicy.setVisibility(View.GONE);
                    } else {
                        tvPolicy.setVisibility(View.VISIBLE);
                        tvPolicy.setText("代理政策：" + bean.getPolicy_name());
                    }if (TextUtils.isEmpty(bean.getCard_settle_price_vip())) {
                        tvVip.setVisibility(View.GONE);
                    } else {
                        tvVip.setVisibility(View.VISIBLE);
                        tvVip.setText("VIP刷卡结算价：" + bean.getCard_settle_price_vip());
                    }
                } else {
                    rl.setVisibility(View.GONE);
                    rl2.setVisibility(View.VISIBLE);
                    GetTrafficCardAllocationListBean.DataBean.TrafficCardAllocationListBean bean = (GetTrafficCardAllocationListBean.DataBean.TrafficCardAllocationListBean) mData.get(i);
                    tvSn2.setText(mContext.getString(R.string.流量卡号2) + bean.getCard_no());
                }
            } else {
                rl.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);
                if (mSubtype == 0) {
                    ivHeadImg2.setImageResource(R.mipmap.icon_item_mpos);
                    GetMposRecallListBean.DataBean.MposRecallListBean bean = (GetMposRecallListBean.DataBean.MposRecallListBean) mData.get(i);
                    tvSn2.setText(mContext.getString(R.string.SN码2) + bean.getSn());
                } else if (mSubtype == 1) {
                    ivHeadImg2.setImageResource(R.mipmap.icon_item_pos);
                    GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean bean = (GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i);
                    tvSn2.setText(mContext.getString(R.string.SN码2) + bean.getSn());
                }else if (mSubtype == 2) {
                    ivHeadImg2.setImageResource(R.mipmap.icon_item_epos);
                    GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean bean = (GetTraditionalPosRecallListBean.DataBean.TraditionalPosRecallListBean) mData.get(i);
                    tvSn2.setText(mContext.getString(R.string.SN码2) + bean.getSn());
                } else {
                    ivHeadImg2.setImageResource(R.mipmap.icon_liuliangka);
                    GetTrafficCardRecallListBean.DataBean.TrafficCardRecallListBean bean = (GetTrafficCardRecallListBean.DataBean.TrafficCardRecallListBean) mData.get(i);
                    tvSn2.setText(mContext.getString(R.string.流量卡号2) + bean.getCard_no());
                }
            }
            ivCheck.setSelected(mMap.get(i));
            ivCheck2.setSelected(mMap.get(i));
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMap.put(i, !mMap.get(i));
                    ivCheck.setSelected(mMap.get(i));
                    mFragment.count();
                }
            });
            rl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMap.put(i, !mMap.get(i));
                    ivCheck2.setSelected(mMap.get(i));
                    mFragment.count();
                }
            });
        }
    }
}
