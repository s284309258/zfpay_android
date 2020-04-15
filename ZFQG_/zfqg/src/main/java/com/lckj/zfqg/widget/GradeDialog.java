package com.lckj.zfqg.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.lckj.zfqg.activity.JoinEventActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GradeDialog extends Dialog {
    private final Context mContext;
    private final List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosActivityRewardListBean> mRewardList;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private GradeDialogAdapter mGradeDialogAdapter;
    private String mGrade;
    private String mReward_id;

    public GradeDialog(Context context, int themeResId, List<GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosActivityRewardListBean> rewardList) {
        super(context, themeResId);
        mContext = context;
        mRewardList = rewardList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_grade, null);
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
        mGradeDialogAdapter = new GradeDialogAdapter(getContext());
        recyclerView.setAdapter(mGradeDialogAdapter);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        dismiss();
        JoinEventActivity activity = (JoinEventActivity) mContext;
        activity.setGrade(mGrade, mReward_id);
    }

    public class GradeDialogAdapter extends RecyclerView.Adapter {

        private final Context mContext;
        private ImageView mLastIvCheck;

        public GradeDialogAdapter(Context context) {
            mContext = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_grade_dialog, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            GradeDialogAdapter.ViewHolder holder = (ViewHolder) viewHolder;
            holder.setData(i);
        }

        @Override
        public int getItemCount() {
            return mRewardList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_sn)
            TextView tvSn;
            @BindView(R.id.tv_rate)
            TextView tvRate;
            @BindView(R.id.iv_check)
            ImageView ivCheck;
            @BindView(R.id.rl)
            RelativeLayout rl;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }

            private void setData(int i) {
                final GetTraditionalPosPartActivityInfoBean.DataBean.TraditionalPosActivityRewardListBean bean = mRewardList.get(i);
                tvSn.setText(mContext.getString(R.string.库存数量台, bean.getPos_num()));
                tvRate.setText(mContext.getString(R.string.交易额达到万返现元, bean.getExpenditure(), bean.getReward_money()));
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivCheck.setVisibility(View.VISIBLE);
                        mReward_id = bean.getActivity_reward_id();
                        mGrade = tvSn.getText().toString();
                        if (mLastIvCheck != null) {
                            mLastIvCheck.setVisibility(View.GONE);
                        }
                        mLastIvCheck = ivCheck;
                    }
                });
            }
        }
    }

}
