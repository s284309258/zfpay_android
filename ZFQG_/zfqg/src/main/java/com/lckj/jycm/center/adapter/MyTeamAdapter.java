package com.lckj.jycm.center.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.center.bean.MyTeamData;
import com.lckj.jycm.network.TeamListBean;
import com.lckj.lckjlib.imageloader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.ViewHolder> {

    private final ArrayList<TeamListBean.DataBean.ListBean> mData;

    public MyTeamAdapter(ArrayList<TeamListBean.DataBean.ListBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_team, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder holder = viewHolder;
        holder.setData(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(TeamListBean.DataBean.ListBean listBean) {
            ImageLoader.loadImage(listBean.getHeadPhoto(), ivHeadImg, 10, 0);
            tvName.setText(listBean.getUserName());
        }
    }
}
