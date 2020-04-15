package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetActivityRewardTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetDeductTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetMachineBackTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.GetShareBenefitTraditionalPosListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailRecordAdapter<E> extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<E> mData;
    private final int mType;

    public DetailRecordAdapter(Context context, List<E> baseData, int type) {
        mContext = context;
        mData = baseData;
        mType = type;
    }
    //新增EPOS
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        if (mType == 0 || mType == 4 || mType == 8) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_record, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else if (mType == 1 || mType == 5 || mType == 9) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_record2, viewGroup, false);
            viewHolder = new ViewHolder2(view);
        } else if (mType == 2 || mType == 6 || mType == 10) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_record3, viewGroup, false);
            viewHolder = new ViewHolder3(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_record4, viewGroup, false);
            viewHolder = new ViewHolder4(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (mType == 0 || mType == 4 || mType == 8) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.setData(i);
        } else if (mType == 1 || mType == 5 || mType == 9) {
            ViewHolder2 holder = (ViewHolder2) viewHolder;
            holder.setData(i);
        } else if (mType == 2 || mType == 6 || mType == 10) {
            ViewHolder3 holder = (ViewHolder3) viewHolder;
            holder.setData(i);
        } else {
            ViewHolder4 holder = (ViewHolder4) viewHolder;
            holder.setData(i);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_deal_type)
        TextView tvDealType;
        @BindView(R.id.tv_card_type)
        TextView tvCardType;
        @BindView(R.id.tv_fenlun_type)
        TextView tvFenlunType;
        @BindView(R.id.tv_single_money)
        TextView tvSingleMoney;
        @BindView(R.id.tv_deal_count)
        TextView tvDealCount;
        @BindView(R.id.tv_deal_time)
        TextView tvDealTime;
        @BindView(R.id.tv_earnings_time)
        TextView tvEarningsTime;
        @BindView(R.id.tv_chanpin)
        TextView tvChanpin;
        @BindView(R.id.ll_chanpin)
        LinearLayout llChanpin;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            E e = mData.get(i);
            if (e instanceof GetShareBenefitTraditionalPosListBean.DataBean.ShareBenefitTraditionalPosListBean) {
                GetShareBenefitTraditionalPosListBean.DataBean.ShareBenefitTraditionalPosListBean bean = (GetShareBenefitTraditionalPosListBean.DataBean.ShareBenefitTraditionalPosListBean) e;
                tvDealTime.setText(bean.getTrans_datetime());
                tvEarningsTime.setText(bean.getCre_datetime());
                tvMoney.setText("￥" + bean.getBenefit_money());
                tvOrderNumber.setText(bean.getOrder_id());
                tvDealCount.setText(bean.getTrans_amount());
                tvSn.setText(bean.getSn());
                tvSingleMoney.setText(bean.getSingle_amount());
                switch (bean.getCard_type()) {
                    case "1":
                        tvCardType.setText(mContext.getString(R.string.借记卡));
                        break;
                    case "2":
                        tvCardType.setText(mContext.getString(R.string.贷记卡));
                        if (TextUtils.isEmpty(bean.getTrans_product())) {
                            llChanpin.setVisibility(View.GONE);
                        } else {
                            llChanpin.setVisibility(View.VISIBLE);
                            switch (bean.getTrans_product()) {
                                case "1":
                                    tvChanpin.setText(mContext.getString(R.string.付款秒到));
                                    break;
                                case "2":
                                    tvChanpin.setText(mContext.getString(R.string.VIP秒到));
                                    break;
                                case "3":
                                    tvChanpin.setText(mContext.getString(R.string.星券秒到));
                                    break;
                            }
                        }
                        break;
                    case "3":
                        tvCardType.setText(mContext.getString(R.string.境外卡));
                        break;
                    case "4":
                        tvCardType.setText(mContext.getString(R.string.云闪付));
                        break;
                }
                switch (bean.getTrans_type()) {
                    case "1":
                        tvDealType.setText(mContext.getString(R.string.刷卡));
                        break;
                    case "2":
                        tvDealType.setText(mContext.getString(R.string.快捷支付));
                        break;
                    case "3":
                        tvDealType.setText(mContext.getString(R.string.微信));
                        break;
                    case "4":
                        tvDealType.setText(mContext.getString(R.string.支付宝));
                        break;
                    case "5":
                        tvDealType.setText(mContext.getString(R.string.银联二维码));
                        break;
                }
                switch (bean.getBenefit_type()) {
                    case "01":
                        tvFenlunType.setText(mContext.getString(R.string.结算价));
                        break;
                    case "02":
                        tvFenlunType.setText(mContext.getString(R.string.单笔));
                        break;
                }
            }
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_deal_type)
        TextView tvDealType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_earnings_time)
        TextView tvEarningsTime;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            E e = mData.get(i);
            if (e instanceof GetMachineBackTraditionalPosListBean.DataBean.MachineBackTraditionalPosListBean) {
                GetMachineBackTraditionalPosListBean.DataBean.MachineBackTraditionalPosListBean bean = (GetMachineBackTraditionalPosListBean.DataBean.MachineBackTraditionalPosListBean) e;
                tvTime.setText(bean.getFrozen_time());
                tvEarningsTime.setText(bean.getCre_datetime());
                tvMoney.setText("￥" + bean.getMoney());
                tvOrderNumber.setText(bean.getOrder_id());
                tvSn.setText(bean.getSn());
                tvDealType.setText(mContext.getString(R.string.激活返现));
            }
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_order_title)
        TextView tvOrderTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_earnings_time)
        TextView tvEarningsTime;


        ViewHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            E e = mData.get(i);
            if (e instanceof GetActivityRewardTraditionalPosListBean.DataBean.ActivityRewardTraditionalPosListBean) {
                GetActivityRewardTraditionalPosListBean.DataBean.ActivityRewardTraditionalPosListBean bean = (GetActivityRewardTraditionalPosListBean.DataBean.ActivityRewardTraditionalPosListBean) e;
                tvTime.setText(bean.getStart_date() + "-" + bean.getEnd_date());
                tvEarningsTime.setText(bean.getCre_datetime());
                tvMoney.setText("￥" + bean.getMoney());
                tvOrderNumber.setText(bean.getOrder_id());
                tvSn.setText(bean.getSn());
                tvOrderTitle.setText(bean.getActivity_name());
            }
        }
    }

    class ViewHolder4 extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_sn)
        TextView tvSn;
        @BindView(R.id.tv_order_title)
        TextView tvOrderTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_earnings_time)
        TextView tvEarningsTime;

        ViewHolder4(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            E e = mData.get(i);
            if (e instanceof GetDeductTraditionalPosListBean.DataBean.DeductTraditionalPosListBean) {
                GetDeductTraditionalPosListBean.DataBean.DeductTraditionalPosListBean bean = (GetDeductTraditionalPosListBean.DataBean.DeductTraditionalPosListBean) e;
                tvTime.setText(bean.getStart_date() + "-" + bean.getEnd_date());
                tvEarningsTime.setText(bean.getCre_datetime());
                tvMoney.setText("￥" + bean.getMoney());
                tvOrderNumber.setText(bean.getOrder_id());
                tvSn.setText(bean.getSn());
                tvOrderTitle.setText(bean.getAssess_name());
            }
        }
    }
}
