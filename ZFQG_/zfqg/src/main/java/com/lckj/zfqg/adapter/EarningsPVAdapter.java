package com.lckj.zfqg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.zfqg.bean.CardInfo;
import com.lckj.zfqg.fragment.EarningsFragment;
import com.lckj.zfqg.widget.CardAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarningsPVAdapter extends PagerAdapter implements CardAdapter {

    private final Context mContext;
    private final List<CardView> mViews;
    private final List<CardInfo> mDataList;
    private final EarningsFragment mFragment;
    private float mBaseElevation;

    public EarningsPVAdapter(Context context, List<CardView> views, List<CardInfo> dataList, EarningsFragment fragment) {
        mContext = context;
        mViews = views;
        mDataList = dataList;
        mFragment = fragment;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.9f;
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_card, container, false);
        container.addView(view);
        new ViewHolder(view, position, mDataList);
        CardView cardView = (CardView) view.findViewById(R.id.bank_card);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }


    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_month_count)
        TextView tvMonthCount;
        @BindView(R.id.tv_direct_count)
        TextView tvDirectCount;
        @BindView(R.id.tv_agency_count)
        TextView tvAgencyCount;
        @BindView(R.id.iv_line)
        ImageView ivLine;
        @BindView(R.id.bank_card)
        CardView bankCard;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder(View view, final int position, List<CardInfo> dataList) {
            ButterKnife.bind(this, view);
            tvTime.setText(dataList.get(position).getDate());
            tvTitle.setText(dataList.get(position).getName());
            tvTitle.setBackgroundResource(dataList.get(position).getName_bg());
            ivLine.setBackgroundResource(dataList.get(position).getCard_bg());
            tvAgencyCount.setText(dataList.get(position).getAgency_benefit());
            tvDirectCount.setText(dataList.get(position).getMerchant_benefit());
            tvMonthCount.setText(dataList.get(position).getBenefit());
            tvTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.showTimeDialog();
                }
            });
        }
    }
}
