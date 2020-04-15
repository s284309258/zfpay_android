package com.lckj.lckjlib.picker.wheelview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.lckjlib.R;
import com.lckj.lckjlib.picker.adapter.AbstractWheelTextAdapter;

/**
 * Created by ghg on 2016/03/12.
 */
public class WheelPicker  extends DialogFragment implements View.OnClickListener {

    public interface OnWheelChangeListener {
        /***
         * @param position 选中的ITEM的位置
         */
        void OnWheelChange(int position);
    }
    OnWheelChangeListener onWheelChangeListener;
    private TextView mBtnSubmit, mBtnCancel;
    private DefaultWheelAdapter mWheelAdapter;
    private WheelView mWheelView;

    private String mTitle;
    private int mFirstItem;
    private int mVisibleCount;      //可见的数量

    public static WheelPicker createDialog(DefaultWheelAdapter adapter, int pos, String title, int numbers){
        WheelPicker wheelPicker =  new WheelPicker();
        wheelPicker.mWheelAdapter = adapter;
        wheelPicker.mFirstItem = pos;
        wheelPicker.mTitle = title;
        wheelPicker.mVisibleCount = numbers;
        return wheelPicker;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DefaultDialog);
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.picker_view_wheel_picker,container,false);
        mWheelView = (WheelView) view.findViewById(R.id.wp_year);
        mBtnSubmit = (TextView)view.findViewById(R.id.tv_wheel_submit);
        mBtnCancel = (TextView)view.findViewById(R.id.tv_wheel_cancel);
        View viewContainer = view.findViewById(R.id.ll_wheel_picker);
        TextView txtTitle=(TextView)view.findViewById(R.id.tv_wheel_title);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int winWidth = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(    //设置布局使Dialog宽度占用全屏
                winWidth,
                LinearLayout.LayoutParams.MATCH_PARENT);
        viewContainer.setLayoutParams(contentParams);

        //配置顶部按钮和标题
        txtTitle.setText(mTitle);
        mBtnSubmit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);


        mWheelView.setCenterLineColor(Color.parseColor("#e5e5e5"));
        mWheelAdapter.setTextType("");
        mWheelView.setVisibleItems(mVisibleCount);
        mWheelAdapter.setCurItem(mFirstItem);
        mWheelView.setViewAdapter(mWheelAdapter);   //需要设置Adapter之后才能设置当前currentItem 2015-11-26 by wp.nine
        mWheelView.setCurrentItem(mFirstItem);
        OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mWheelAdapter.setCurItem(newValue);
                executeCallback(newValue);
                mWheelAdapter.notifyDataChangedEvent();
            }
        };
        mWheelView.addChangingListener(listener);
        return view;
    }

    private void executeCallback(int position)
    {
        if (onWheelChangeListener!=null){
            onWheelChangeListener.OnWheelChange(position);
        }
    }

    public WheelPicker setOnWheelChangeListener(OnWheelChangeListener listener){
        onWheelChangeListener = listener;
        return this;
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_wheel_submit) {
            executeCallback(mWheelView.getCurrentItem());
            this.dismiss();

        } else if (i == R.id.tv_wheel_cancel) {
            executeCallback(mFirstItem);    //还原为一开始进来的选项
            this.dismiss();

        }
    }



    public static abstract class DefaultWheelAdapter extends AbstractWheelTextAdapter {
        private int mCurItem;
        private Context mContext;
        public DefaultWheelAdapter(Context context) {
            super(context);
            this.mContext = context;
        }

        public DefaultWheelAdapter(Context context, int itemResource) {
            super(context, itemResource);
            this.mContext = context;
        }

        public DefaultWheelAdapter(Context context, int itemResource, int itemTextResource) {
            super(context, itemResource, itemTextResource);
            this.mContext = context;
        }

        public int getCurSelectedItemPosition(){
            return mCurItem;
        }

        public Context getContext(){
            return mContext;
        }

        public void setCurItem(int index){
            this.mCurItem = index;
        }

        @Override
        public CharSequence getItemText(int index) {
            return null;
        }

        public int customCount;

        public void setCustomCount(int customCount) {
            this.customCount = customCount;
        }
    }
}
