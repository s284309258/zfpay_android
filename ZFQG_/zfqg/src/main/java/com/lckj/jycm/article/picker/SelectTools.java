package com.lckj.jycm.article.picker;

import android.support.v4.app.FragmentActivity;


import com.lckj.jycm.article.activity.CreateArticleCommitActivity;
import com.lckj.lckjlib.R;
import com.lckj.lckjlib.picker.adapter.CarWheelAdapter;
import com.lckj.lckjlib.picker.PickerArticleSelector;

import java.util.List;

public class SelectTools {
    public static void selectOne(String title, final FragmentActivity fragmentActivity, List<String> ones, PickerArticleSelector.OnWheelChangeListener onWheelChangeListener) {
        CarWheelAdapter provinceAdapter = new CarWheelAdapter(fragmentActivity, ones, fragmentActivity.getResources().getDimensionPixelOffset(R.dimen.dp_size_40));
        final PickerArticleSelector wheelPicker = PickerArticleSelector.createDialog(provinceAdapter, null
                , null, safeIndex(0),0, 0, null, 5, title
        ,true,"",true);
        wheelPicker.show(fragmentActivity.getSupportFragmentManager(), CreateArticleCommitActivity.class.getName());
        wheelPicker.setOnWheelChangeListener(onWheelChangeListener);
    }

    public static PickerArticleSelector selectDate(FragmentActivity activity, List<String> ones, List<String> twos, List<String> threes, int pos1, int pos2, int pos3, PickerArticleSelector.OnWheelChangeListener onWheelChangeListener) {
        CarWheelAdapter adapter1 = new CarWheelAdapter(activity, ones, activity.getResources().getDimensionPixelOffset(R.dimen.dp_size_60));
        final CarWheelAdapter adapter2 = new CarWheelAdapter(activity, twos, activity.getResources().getDimensionPixelOffset(R.dimen.dp_size_60));
        final CarWheelAdapter adapter3 = new CarWheelAdapter(activity, threes, activity.getResources().getDimensionPixelOffset(R.dimen.dp_size_60));
        if (pos1==0)pos1= ones.size()/2;
        final PickerArticleSelector wheelPicker = PickerArticleSelector.createDialog(adapter1, adapter2, threes==null?null:adapter3, safeIndex(pos1), safeIndex(pos2), safeIndex(pos3), null, 3, "");
        wheelPicker.setSeletDate(true);
        wheelPicker.show(activity.getSupportFragmentManager(), CreateArticleCommitActivity.class.getName());
        wheelPicker.setOnWheelChangeListener(onWheelChangeListener);
        return wheelPicker;
    }
    public static int safeIndex(int i) {
        return i < 0 ? 0 : i;
    }

}
