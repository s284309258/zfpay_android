package com.lckj.lckjlib.picker.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lckj.lckjlib.R;
import com.lckj.lckjlib.picker.wheelview.WheelPicker;

import java.util.List;

/**
 * Created by ghg on 2016/03/12.
 */
public class CarWheelAdapter extends WheelPicker.DefaultWheelAdapter {
    List<String> mListPickData;
    private int mItemHeight;

    public CarWheelAdapter(Context context, List<String> listPickData, int itemHeight) {
        super(context, R.layout.picker_item_wheel_text, R.id.tv_wheel_item);
        this.mListPickData = listPickData;
        this.mItemHeight = itemHeight;
    }

    public CarWheelAdapter(Context context, int mItemHeight) {
        super(context,R.layout.picker_item_wheel_text, R.id.tv_wheel_item);
        this.mItemHeight = mItemHeight;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView == null){
            convertView = super.getItem(index, convertView, parent);
            textView = (TextView) convertView.findViewById(R.id.tv_wheel_item);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,mItemHeight));
        }else{
            textView = (TextView) convertView.findViewById(R.id.tv_wheel_item);
        }

        textView.setText(getItemText(index));
        if (getCurSelectedItemPosition() == index) {
            textView.setTextColor(getContext().getResources().getColor(R.color.text_color_title_black)); //  修改wheelview  选中字体的颜色  by wgl 2016.9.13
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.text_size_16));
        } else {
            textView.setTextColor(getContext().getResources().getColor(R.color.gray));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimensionPixelSize(R.dimen.sp_14));
        }
        return convertView;
    }

    @Override
    public CharSequence getItemText(int index) {
        return mListPickData.get(index);
    }

    @Override
    public int getItemsCount() {
        if (mListPickData == null){
            return 0;
        }
        if (customCount!=0)return customCount;
        return mListPickData.size();
    }

}
