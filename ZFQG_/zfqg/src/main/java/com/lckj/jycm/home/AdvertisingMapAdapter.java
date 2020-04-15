package com.lckj.jycm.home;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.activity.AdDetailsActivity;
import com.lckj.jycm.network.MerchantListBean;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvertisingMapAdapter extends RecyclerView.Adapter<AdvertisingMapAdapter.ViewHolder> {

    private final Activity mActivity;
    private final ArrayList<MerchantListBean.DataBean.ListBean> datas;

    public AdvertisingMapAdapter(Activity activity, ArrayList<MerchantListBean.DataBean.ListBean> listDatas) {
        mActivity = activity;
       this.datas = listDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_advertising_map, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final MerchantListBean.DataBean.ListBean data = datas.get(i);
        ImageLoader.loadImage(data.getFirstImg(),holder.ivHeadImg, 10, 0);
        holder.tvName.setText(data.getMerName());
        holder.tvLocation.setText(Utils.formatDistance(data.getDistance())+"|"+data.getLocaltion());
        holder.tvVocation.setText(mActivity.getResources().getStringArray(R.array.industrys)[data.getIndustryIndex()]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startActivity(new Intent(mActivity, AdDetailsActivity.class).putExtra("data",data));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_vocation)
        TextView tvVocation;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.iv_add)
        ImageView ivAdd;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
