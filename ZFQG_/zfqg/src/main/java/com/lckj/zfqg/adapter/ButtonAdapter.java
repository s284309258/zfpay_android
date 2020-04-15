package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.SelectPolicy3RecordBean;
import com.lckj.zfqg.widget.PrizeDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ButtonAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean.Policy3ListBean> mData;
    private final String mChoose;
    private final String[] mId;
    private final SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean mBean;

    public ButtonAdapter(Context context, SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean bean) {
        mContext = context;
        mData = bean.getPolicy3List();
        mId = bean.getPolicy_id().split(",");
        mChoose = bean.getChoose();
        mBean = bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_btn, viewGroup, false);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean.Policy3ListBean bean = mData.get(i);
            if (mChoose.equals("0") || TextUtils.isEmpty(mChoose)) {
                for (int j = 0; j < mId.length; j++) {
                    if (mId[j].equals(bean.getId())) {
                        tv.setText(bean.getPolicy_quantity() / 10000 + "万 | 领取");
                        tv.setTextColor(mContext.getResources().getColor(R.color.white));
                        tv.setBackgroundResource(R.drawable.bg_green_shape6);
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDialog(bean);
                            }
                        });
                    } else {
                        tv.setText(bean.getPolicy_quantity() / 10000 + "万 | 领取");
                        tv.setTextColor(mContext.getResources().getColor(R.color.gray2));
                        tv.setBackgroundResource(R.drawable.bg_gray_line_shape2);
                    }
                }
            } else {
                for (int j = 0; j < mId.length; j++) {
                    if (mId[j].equals(bean.getId())) {
                        tv.setText(bean.getPolicy_quantity() / 10000 + "万 | 已领");
                        tv.setTextColor(mContext.getResources().getColor(R.color.white));
                        tv.setBackgroundResource(R.drawable.bg_gray_shape5);
                    } else {
                        tv.setText(bean.getPolicy_quantity() / 10000 + "万 | 领取");
                        tv.setTextColor(mContext.getResources().getColor(R.color.gray2));
                        tv.setBackgroundResource(R.drawable.bg_gray_line_shape2);
                    }
                }
            }
        }
    }

    private void showDialog(SelectPolicy3RecordBean.DataBean.MachinesPolicy3RecordBean.Policy3ListBean bean) {
        PrizeDialog prizeDialog = new PrizeDialog(mContext, R.style.dialog);
        prizeDialog.show(bean.getId(), bean.getPolicy_name(), mBean.getMer_id(), mBean.getMer_name());
    }
}
