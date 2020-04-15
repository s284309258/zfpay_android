package com.lckj.jycm.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.activity.AdDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingTrolleyAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;

    public ShoppingTrolleyAdapter(Activity activity) {
        mActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_shopping_trolley, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkbox;
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
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mActivity.startActivity(new Intent(mActivity, AdDetailsActivity.class));
                }
            });
        }
    }
}
