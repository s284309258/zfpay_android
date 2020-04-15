package com.lckj.zfqg.widget;

import android.content.Context;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lckj.zfqg.activity.TrendActivity;

import java.util.List;

public class XFormattedValue extends ValueFormatter {


    private final String[] mArr;

    public XFormattedValue(String[] arr) {
        mArr = arr;
    }

    @Override
    public String getFormattedValue(float value) {
        try {
            return mArr[(int) value];
        } catch (Exception e) {
            e.printStackTrace();
            return mArr[0];
        }
    }
}
