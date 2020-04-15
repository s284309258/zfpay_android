package com.lckj.jycm.widget;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lckj.jycm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListPopupWindow {

    private final Activity activity;
    private final ItemOnClickListener itemOnClickListener;
    private List<String> mData;
    public PopupWindow mPopupWindow;
    private RecyclerViewAdapter RecyclerViewAdapter;
    private final View mView;
    private int mCode;
    private int mPosition;

    public static ListPopupWindow getInstance(Activity activity, ItemOnClickListener itemOnClickListener) {
        return new ListPopupWindow(activity, itemOnClickListener);
    }

    public ListPopupWindow(Activity activity, ItemOnClickListener itemOnClickListener) {
        this.activity = activity;
        this.itemOnClickListener = itemOnClickListener;
        mView = LayoutInflater.from(activity).inflate(R.layout.pop_list, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

    }

    public void show(View view, int position, ArrayList<String> datas, int code) {
        mData = datas;
        mPosition = position;
        mCode = code;
        mPopupWindow.showAsDropDown(view, 0, 0);
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        RecyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(RecyclerViewAdapter);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_list, viewGroup, false);
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

            private void setData(final int position) {
                tv.setText(mData.get(position));
                if (mPosition == position) {
                    tv.setSelected(true);
                } else {
                    tv.setSelected(false);
                }
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemOnClickListener.onClick(position, tv.getText().toString().trim(), mCode);
                        mPopupWindow.dismiss();
                    }
                });
            }
        }
    }

    //定义接口
    public interface ItemOnClickListener {
        void onClick(int position, String typeName, int code);
    }

}
