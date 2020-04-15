package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetTraditionalPosPartActivityInfoBean;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.activity.JoinEventActivity;
import com.lckj.zfqg.adapter.RateApplyAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnDialog extends Dialog {
    private final Context mContext;
    private final List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosListBean> mSnList;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private String mSn = "";
    private RateApplyAdapter mRateApplyAdapter;
    private boolean isAll;
    private Map<Integer, Boolean> mMap = new HashMap<>();

    public SnDialog(Context context, int themeResId, List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosListBean> snList) {
        super(context, themeResId);
        mContext = context;
        mSnList = snList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sn, null);
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_dialog_anim);
        setContentView(contentView);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRateApplyAdapter = new RateApplyAdapter(getContext(), mSnList);
        recyclerView.setAdapter(mRateApplyAdapter);
    }

    @OnClick({R.id.tv_all, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                isAll = !isAll;
                tvAll.setSelected(isAll);
                mRateApplyAdapter.setAll(isAll);
                mRateApplyAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_confirm:
                mSn = "";
                for (int i = 0; i < mSnList.size(); i++) {
                    if (mMap.get(i)) {
                        if (TextUtils.isEmpty(mSn)) {
                            mSn += mSnList.get(i).getSn();
                        } else {
                            mSn += "," + mSnList.get(i).getSn();
                        }
                    }
                }
                dismiss();
                JoinEventActivity activity = (JoinEventActivity) mContext;
                activity.setSn(mSn);
                break;
        }
    }

    public class RateApplyAdapter extends RecyclerView.Adapter {
        private final Context mContext;

        public RateApplyAdapter(Context context, List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosListBean> snList) {
            mContext = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_sn_dialog, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.setData(i);
        }

        @Override
        public int getItemCount() {
            for (int i = 0; i < mSnList.size(); i++) {
                if (!mMap.containsKey(i)) {
                    mMap.put(i, false);
                }
            }
            return mSnList.size();
        }

        public void setAll(boolean isAll) {
            for (Map.Entry<Integer, Boolean> entry : mMap.entrySet()) {
                entry.setValue(isAll);
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv)
            TextView tv;
            @BindView(R.id.tv_sn)
            TextView tvSn;
            @BindView(R.id.iv_check)
            ImageView ivCheck;
            @BindView(R.id.rl)
            RelativeLayout rl;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }

            private void setData(final int i) {
                GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosListBean bean = mSnList.get(i);
                tvSn.setText(bean.getSn());
                ivCheck.setSelected(mMap.get(i));
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMap.put(i, !mMap.get(i));
                        ivCheck.setSelected(mMap.get(i));
                    }
                });
            }
        }
    }
}
