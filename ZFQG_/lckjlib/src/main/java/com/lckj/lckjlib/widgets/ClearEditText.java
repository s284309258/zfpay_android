package com.lckj.lckjlib.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lckj.lckjlib.R;


public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements
        View.OnFocusChangeListener, TextWatcher {
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucus;

    private String mHintText = "";//长度过长提示

    private Boolean isShowLengthHint = false;// 是否显示长度过长提示  默认为显示
    OnTextChangeListener mOnTextChangeListener;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        initLength(attrs, context);
    }


    private void init() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.icon_et_delete);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setClearIconVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFoucus) {
        this.hasFoucus = hasFoucus;
        if (hasFoucus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
        setCompoundDrawablePadding(mClearDrawable.getMinimumWidth() / 3);
    }


    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count,
                              int after) {
        if(hasFoucus){
            setClearIconVisible(s.length() > 0);
        }
        if(mOnTextChangeListener != null){
            mOnTextChangeListener.onTextChanged(s);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        if(mOnTextChangeListener != null){
            mOnTextChangeListener.onTextChangeBefore(s);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(mOnTextChangeListener != null){
            mOnTextChangeListener.onTextChangeAfter(s);
        }
    }

    private void initLength(AttributeSet a, Context context) {
        String namespace = "http://schemas.android.com/apk/res/android";
        //获取属性中设置的最大长度
        int maxLength = a.getAttributeIntValue(namespace, "maxLength", -1);
        //如果设置了最大长度，给出相应的处理
        if (maxLength > -1) {
            setFilters(new InputFilter[]{new MyLengthFilter(maxLength,context)});
        }
    }

    //设置长度过长的提示语
    public void setLengthHint(String mHintText){
        this.mHintText = mHintText;
    }

    public void setLengthHintVisible(boolean isShowLengthHint){
        this.isShowLengthHint = isShowLengthHint;
    }

    class MyLengthFilter implements InputFilter {

        private final int mMax;
        private Context context;

        public MyLengthFilter(int max, Context context) {
            mMax = max;
            this.context = context;
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                   int dstart, int dend) {
            int keep = mMax - (dest.length() - (dend - dstart));
            if (keep <= 0) {
                //这里，用来给用户提示  暂时用户密码的长度判断显示提示

                if (isShowLengthHint){
                    if (TextUtils.isEmpty(mHintText)){
                        Toast.makeText(context, "密码最多" + mMax + "位", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, mHintText + "最多" + mMax + "位", Toast.LENGTH_SHORT).show();
                    }
                }

                return "";
            } else if (keep >= end - start) {
                return null; // keep original
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
                return source.subSequence(start, keep);
            }
        }

        /**
         * @return the maximum length enforced by this input filter
         */
        public int getMax() {
            return mMax;
        }

    }

    public void setOnTextChangeListener(OnTextChangeListener  onTextChangeListener){
        mOnTextChangeListener = onTextChangeListener;
    }

    public interface OnTextChangeListener{
        void onTextChangeAfter(Editable s);
        void onTextChangeBefore(CharSequence s);
        void onTextChanged(CharSequence s);
    }

}
