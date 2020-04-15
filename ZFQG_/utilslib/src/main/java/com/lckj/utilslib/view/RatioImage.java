package com.lckj.utilslib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lckj.utilslib.R;


/*  */
public class RatioImage extends ImageView {
    private static final int sEnumHeight = 1;
    private static final int sEnumWidth = 0;
    private float ratioHeight;
    private float ratioWidth;
    private int standard;

    public RatioImage(Context context) {
        this(context, null);
    }

    public RatioImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RatioImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioImage, i, 0);
        if (obtainStyledAttributes != null) {
            this.ratioWidth = obtainStyledAttributes.getFloat(R.styleable.RatioImage_ri_ratio_width, 1.0f);
            this.ratioHeight = obtainStyledAttributes.getFloat(R.styleable.RatioImage_ri_ratio_height, 1.0f);
            this.standard = obtainStyledAttributes.getInt(R.styleable.RatioImage_ri_standard, 0);
            obtainStyledAttributes.recycle();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        switch (this.standard) {
            case 0:
                measuredHeight = (int) ((((float) measuredWidth) / this.ratioWidth) * this.ratioHeight);
                break;
            case 1:
                measuredWidth = (int) ((((float) measuredHeight) / this.ratioHeight) * this.ratioWidth);
                break;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}