package com.lckj.zfqg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lckj.jycm.R;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lckj.zfqg.widget.PushCenterDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<String> mData;
    private final String mType;
    private PushCenterDialog mPushCenterDialog;

    public MaterialAdapter(Context context, List<String> data, String type) {
        mContext = context;
        mData = data;
        mType = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_material, viewGroup, false);
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
        @BindView(R.id.iv)
        ImageView iv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            String url = mData.get(i);
            ImageLoader.loadImage(url, iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(url);
                }
            });
        }
    }

    private void showDialog(String url) {
        if (mPushCenterDialog == null) {
            mPushCenterDialog = new PushCenterDialog(mContext, R.style.BottomDialog2);
        }
        ArrayList<String> imgs = new ArrayList<>();
        imgs.add(url);
        mPushCenterDialog.show(imgs, mType);
    }
}
