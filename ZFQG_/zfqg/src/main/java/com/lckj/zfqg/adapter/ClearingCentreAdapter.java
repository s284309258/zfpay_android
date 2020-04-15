package com.lckj.zfqg.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.R;
import com.lckj.jycm.zfqg_network.GetUserCardListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.UpdateUserCardRequest;
import com.lckj.zfqg.activity.ClearingCentreActivity;
import com.lckj.zfqg.widget.ConfirmDialog;
import com.lckj.zfqg.widget.PayDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClearingCentreAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<GetUserCardListBean.DataBean.UserCardListBean> mData;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private PayDialog mPayDialog;
    private ConfirmDialog mConfirmDialog;

    public ClearingCentreAdapter(Context context, List<GetUserCardListBean.DataBean.UserCardListBean> data) {
        mContext = context;
        mData = data;
        MainApplication.getInstance().getInjectGraph().inject(this);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bank_card, viewGroup, false);
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
        @BindView(R.id.iv_head_img)
        ImageView ivHeadImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_bank_number)
        TextView tvBankNumber;
        @BindView(R.id.iv_selector)
        ImageView ivSelector;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.rl)
        RelativeLayout rl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        private void setData(int i) {
            final GetUserCardListBean.DataBean.UserCardListBean bean = mData.get(i);
            tvName.setText(bean.getBank_name());
            if (bean.getIs_default().equals("0")) {
                ivSelector.setVisibility(View.GONE);
            } else {
                ivSelector.setVisibility(View.VISIBLE);
            }
            tvBankNumber.setText(bean.getAccount());
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showConfirmDialog(bean.getCard_id());
                }
            });
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPayDialog(bean.getCard_id());
                }
            });
            switch (bean.getStatus()) {
                case "00":
                    tvStatus.setText(mContext.getString(R.string.待审核));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.white));
                    break;
                case "08":
                    tvStatus.setText(mContext.getString(R.string.审核驳回));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                case "09":
                    tvStatus.setText(mContext.getString(R.string.已添加));
                    tvStatus.setTextColor(mContext.getResources().getColor(R.color.white));
                    break;
            }
        }
    }

    private void showConfirmDialog(String card_id) {
        if (mConfirmDialog == null)
            mConfirmDialog = new ConfirmDialog(mContext, R.style.BottomDialog2);
        mConfirmDialog.show(mContext.getString(R.string.确定设置该卡为默认结算卡吗), card_id);
    }

    private void showPayDialog(final String card_id) {
        if (mPayDialog == null) {
            mPayDialog = new PayDialog((Activity) mContext);
            mPayDialog.setOnResult(new PayDialog.OnResult() {
                @Override
                public void success(String password) {
                    delete(card_id, password);
                }
            });
        }
        mPayDialog.show();
    }

    private void delete(String card_id, String password) {
        ProgressDlgHelper.openDialog(mContext);
        mMyService.updateUserCard(new UpdateUserCardRequest(dataManager.getToken(), "user_card_del", "", password, card_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(mContext) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        ClearingCentreActivity activity = (ClearingCentreActivity) mContext;
                        activity.initData();
                    }
                }, new ThrowableConsumer<Throwable>(mContext));
    }
}
