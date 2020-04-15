package com.lckj.jycm.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lckj.jycm.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridViewAdapter extends BaseAdapter {

    private final Activity mActivity;
    private final List<String> mTypeList;
    private int mPosition;

    public GridViewAdapter(Activity activity, List<String> typeList) {
        mActivity = activity;
        mTypeList = typeList;
    }

    @Override
    public int getCount() {
        return mTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_grid, parent, false);
        new ViewHolder(view, position);
        return view;
    }

    class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view, final int position) {
            ButterKnife.bind(this, view);
            tv.setText(mTypeList.get(position));
            if (mPosition == position) {
                tv.setEnabled(false);
            } else {
                tv.setEnabled(true);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition = position;
                    notifyDataSetChanged();
                }
            });
        }
    }
}
