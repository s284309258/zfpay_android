package com.lckj.lckjlib.picker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.lckjlib.R;
import com.lckj.lckjlib.picker.wheelview.OnWheelChangedListener;
import com.lckj.lckjlib.picker.wheelview.WheelPicker;
import com.lckj.lckjlib.picker.wheelview.WheelView;


public class PickerArticleSelector extends DialogFragment implements View.OnClickListener {


    private boolean seletDate;

    public void setSeletDate(boolean seletDate) {
        this.seletDate = seletDate;
    }

    public boolean isSeletDate() {
        return seletDate;
    }

    public interface OnWheelChangeListener {
        void OnWheelChange(int position1, int position2, int position3, boolean isCancel);

        void OnChangeFirst(int position);

        void OnChangeSecond(int position);

        void OnChangeThird(int position);
    }

    OnWheelChangeListener onWheelChangeListener;
    private TextView mBtnSubmit, mBtnCancel,mWheelTitle;
    private WheelPicker.DefaultWheelAdapter mWheelAdapter1, mWheelAdapter2, mWheelAdapter3;
    private WheelView mWheelViewItem1, mWheelViewItem2, mWheelViewItem3;
    private RelativeLayout mRlLineLayout;

    private String mTitleText;

    private String[] mTitle;
    private int mFirstItem,mFirstItem2,mFirstItem3;
    private int mVisibleCount;      //可见的数量
    private boolean canClose = false;
    private String leftName = "";
    private boolean isShowLine = false;


    public static PickerArticleSelector createDialog(WheelPicker.DefaultWheelAdapter adapter1, WheelPicker.DefaultWheelAdapter
            adapter2, WheelPicker.DefaultWheelAdapter adapter3, int pos, int pos2, int pos3, String[] title, int numbers, String wheelTitle) {
        PickerArticleSelector wheelPicker = new PickerArticleSelector();
        wheelPicker.mWheelAdapter1 = adapter1;
        wheelPicker.mWheelAdapter2 = adapter2;
        wheelPicker.mWheelAdapter3 = adapter3;
        wheelPicker.mFirstItem = pos;
        wheelPicker.mFirstItem2 = pos2;
        wheelPicker.mFirstItem3 = pos3;
        wheelPicker.mTitle = title;
        wheelPicker.mVisibleCount = numbers;
        wheelPicker.mTitleText = wheelTitle;
        return wheelPicker;
    }

    public static PickerArticleSelector createDialog(WheelPicker.DefaultWheelAdapter adapter1, WheelPicker.DefaultWheelAdapter
            adapter2, WheelPicker.DefaultWheelAdapter adapter3, int pos, int pos2, int pos3, String[] title, int mVisibleCount, String wheelTitle,
                                                     boolean canClose, String leftName , boolean isShowLine) {
        PickerArticleSelector wheelPicker = new PickerArticleSelector();
        wheelPicker.mWheelAdapter1 = adapter1;
        wheelPicker.mWheelAdapter2 = adapter2;
        wheelPicker.mWheelAdapter3 = adapter3;
        wheelPicker.mFirstItem = pos;
        wheelPicker.mFirstItem2 = pos2;
        wheelPicker.mFirstItem3 = pos3;
        wheelPicker.mTitle = title;
        wheelPicker.mVisibleCount = mVisibleCount;
        wheelPicker.mTitleText = wheelTitle;
        wheelPicker.canClose = canClose;
        wheelPicker.leftName = leftName;
        wheelPicker.isShowLine = isShowLine;
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
        if (canClose){
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picker_article_selector, container, false);
        mWheelViewItem1 = (WheelView) view.findViewById(R.id.wv_item1);
        mWheelViewItem2 = (WheelView) view.findViewById(R.id.wv_item2);
        mWheelViewItem3 = (WheelView) view.findViewById(R.id.wv_item3);
        mBtnSubmit = (TextView) view.findViewById(R.id.tv_wheel_submit);
        mBtnCancel = (TextView) view.findViewById(R.id.tv_wheel_cancel);
        mRlLineLayout = (RelativeLayout) view.findViewById(R.id.rl_line_wheel);
        if (!TextUtils.isEmpty(leftName)){
            mBtnCancel.setText(leftName);
        }
        if (isShowLine){
            mRlLineLayout.setVisibility(View.VISIBLE);
            mRlLineLayout.bringToFront();
        } else {
            mRlLineLayout.setVisibility(View.GONE);
        }

        mWheelTitle = (TextView) view.findViewById(R.id.tv_wheel_title);
        mWheelTitle.setText(mTitleText);


        View viewContainer = view.findViewById(R.id.ll_wheel_picker);
        TextView txtTitle1 = (TextView) view.findViewById(R.id.tv_item1_title);
        TextView txtTitle2 = (TextView) view.findViewById(R.id.tv_item2_title);
        TextView txtTitle3 = (TextView) view.findViewById(R.id.tv_item3_title);

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int winWidth = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(    //设置布局使Dialog宽度占用全屏
                winWidth,
                LinearLayout.LayoutParams.MATCH_PARENT);
        viewContainer.setLayoutParams(contentParams);

        //配置顶部按钮和标题
        mBtnSubmit.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        if (mTitle == null){
            txtTitle1.setVisibility(View.GONE);
        }else {
            txtTitle1.setVisibility(View.VISIBLE);
            txtTitle1.setText(mTitle[0]);
        }
        mWheelAdapter1.setTextType("");
        mWheelViewItem1.setVisibleItems(mVisibleCount);
        mWheelAdapter1.setCurItem(mFirstItem);
        mWheelViewItem1.setViewAdapter(mWheelAdapter1);   //需要设置Adapter之后才能设置当前currentItem 2015-11-26 by wp.nine
        mWheelViewItem1.setCurrentItem(mFirstItem);
        OnWheelChangedListener listener1 = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mWheelAdapter1.setCurItem(newValue);
                mWheelAdapter1.notifyDataChangedEvent();
                if (onWheelChangeListener != null) {
                    onWheelChangeListener.OnChangeFirst(newValue);
                }
            }
        };
        mWheelViewItem1.addChangingListener(listener1);
        if (mWheelAdapter2!=null){
            if (mTitle == null){
                txtTitle2.setVisibility(View.GONE);
            }else {
                txtTitle2.setVisibility(View.VISIBLE);
                txtTitle2.setText(mTitle[1]);
            }
            mWheelAdapter2.setTextType("");
            mWheelViewItem2.setVisibleItems(mVisibleCount);
            mWheelAdapter2.setCurItem(mFirstItem2);
            mWheelViewItem2.setViewAdapter(mWheelAdapter2);   //需要设置Adapter之后才能设置当前currentItem 2015-11-26 by wp.nine
            mWheelViewItem2.setCurrentItem(mFirstItem2);
            OnWheelChangedListener listener2 = new OnWheelChangedListener() {
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    mWheelAdapter2.setCurItem(newValue);
                    mWheelAdapter2.notifyDataChangedEvent();
                    if (onWheelChangeListener != null) {
                        onWheelChangeListener.OnChangeSecond(newValue);
                    }
                    if (mWheelAdapter3!=null&&seletDate&&mWheelAdapter3.getItemsCount()>27){
                        mWheelAdapter3.setCustomCount(getDataCount(newValue));
                        mWheelAdapter3.setCurItem(0);
                        mWheelAdapter3.notifyDataChangedEvent();
                        mWheelViewItem3.invalidate();
                    }
                }
            };
            mWheelViewItem2.addChangingListener(listener2);
        }else{
            txtTitle2.setVisibility(View.GONE);
            mWheelViewItem2.setVisibility(View.GONE);
        }

        if(mWheelAdapter3 != null){
            if (mTitle == null){
                txtTitle3.setVisibility(View.GONE);
            }else {
                txtTitle3.setVisibility(View.VISIBLE);
                txtTitle3.setText(mTitle[2]);
            }
            mWheelAdapter3.setTextType("");
            mWheelViewItem3.setVisibleItems(mVisibleCount);
            mWheelAdapter3.setCurItem(mFirstItem3);
            mWheelViewItem3.setViewAdapter(mWheelAdapter3);   //需要设置Adapter之后才能设置当前currentItem 2015-11-26 by wp.nine
            mWheelViewItem3.setCurrentItem(mFirstItem3);
            OnWheelChangedListener listener3 = new OnWheelChangedListener() {
                public void onChanged(WheelView wheel, int oldValue, int newValue) {
                    mWheelAdapter3.setCurItem(newValue);
                    mWheelAdapter3.notifyDataChangedEvent();
                    if (onWheelChangeListener != null) {
                        onWheelChangeListener.OnChangeThird(newValue);
                    }
                }
            };
            mWheelViewItem3.addChangingListener(listener3);
        }else{
            txtTitle3.setVisibility(View.GONE);
            mWheelViewItem3.setVisibility(View.GONE);
        }
        return view;
    }

    private int getDataCount(int newValue) {
        switch (newValue){
            case 0: return 31;
            case 1: return 28;
            case 2: return 31;
            case 3: return 30;
            case 4: return 31;
            case 5: return 30;
            case 6: return 31;
            case 7: return 31;
            case 8: return 30;
            case 9: return 31;
            case 10: return 30;
            case 11: return 31;
        }
        return 0;
    }

    private void executeCallback(int position1, int position2, int position3, boolean isCancel) {
        if (onWheelChangeListener != null) {
            onWheelChangeListener.OnWheelChange(position1, position2, position3, isCancel);
        }
    }

    public PickerArticleSelector setOnWheelChangeListener(OnWheelChangeListener listener) {
        onWheelChangeListener = listener;
        return this;
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_wheel_submit) {
            int position3 = 0;
            if (mWheelAdapter3 != null) {
                position3 = mWheelAdapter3.getCurSelectedItemPosition();
            }

            executeCallback(mWheelViewItem1.getCurrentItem(), mWheelViewItem2.getCurrentItem(), position3, false);
            this.dismiss();

        } else if (i == R.id.tv_wheel_cancel) {
            if (canClose) {
                executeCallback(-1, -1, -1, true); // 重置选择的条件
            } else {
                executeCallback(mFirstItem, mFirstItem2, mFirstItem3, true);    //还原为一开始进来的选项
            }
            this.dismiss();

        }
    }

    public WheelView getmWheelViewItem1(){
        return mWheelViewItem1;
    }

    public WheelView getmWheelViewItem2(){
        return mWheelViewItem2;
    }

    public WheelView getmWheelViewItem3(){
        return mWheelViewItem3;
    }

    public static class OnWheelChangeListenerAdapter implements OnWheelChangeListener {
        @Override
        public void OnWheelChange(int position1, int position2, int position3, boolean isCancel) {

        }

        @Override
        public void OnChangeFirst(int position) {

        }

        @Override
        public void OnChangeSecond(int position) {
        }

        @Override
        public void OnChangeThird(int position) {

        }
    }
}
