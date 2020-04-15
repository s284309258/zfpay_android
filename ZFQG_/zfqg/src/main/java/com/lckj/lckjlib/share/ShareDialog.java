package com.lckj.lckjlib.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lckj.base.MainApplication;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.R;
import com.lckj.jycm.home.HomeActivity;
import com.lckj.jycm.network.CreateAdvInfoBean;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.network.ShareAdvInfoRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.widget.CustomDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ShareDialog extends Dialog {
    private String url;
    private String title;
    private String img;
    private int mArid;
    private int mAdId;
    private GridView gridView;
    private Activity mActivity;
    private List<Selection> selections = new ArrayList<>();
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private String mType;

    private ShareDialog(String imgUrl, Activity activity, int theme, String type) {
        super(activity, theme);
        MainApplication.getInstance().getInjectGraph().inject(this);
        if (!TextUtils.isEmpty(imgUrl) && !imgUrl.startsWith("http")) {
            img = dataManager.getQiniuDomain() + "/" + imgUrl;
        } else {
            img = imgUrl;
        }
        mType = type;
    }


    public static ShareDialog createDialog(Activity activity, String url, String title, String img, int arid, int adId) {
        ShareDialog shareDialog = new ShareDialog(activity, R.style.DefaultDialog, url, title, img, arid, adId);
        return shareDialog;
    }

    public static ShareDialog createDialog(Activity activity, String imgUrl, String type) {
        ShareDialog shareDialog = new ShareDialog(imgUrl, activity, R.style.DefaultDialog, type);
        return shareDialog;
    }

    private ShareDialog(Activity activity, int theme, String url, String title, String img, int arid, int adId) {
        super(activity, theme);
        MainApplication.getInstance().getInjectGraph().inject(this);
        mActivity = activity;
        this.url = url;
        mArid = arid;
        mAdId = adId;
        this.title = title;
        if (!TextUtils.isEmpty(img) && !img.startsWith("http")) {
            img = ProviderModule.getDataManager(activity).getQiniuDomain() + "/" + img;
        }
        this.img = img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.share_dialog_share);
        gridView = (GridView) findViewById(R.id.gvShareSelection);
        TextView tvCancel = (TextView) findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        selections.add(new Selection("微信好友", R.mipmap.icon_wechat));
        selections.add(new Selection("微信朋友圈", R.mipmap.icon_wechat_moments));
        ShareAdapter shareAdapter = new ShareAdapter(getContext(), selections);
        gridView.setAdapter(shareAdapter);
        gridView.setOnItemClickListener(mItemClickListener);

        setCanceledOnTouchOutside(true);
        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        dialogWindow.setWindowAnimations(R.style.DefaultDialog);

        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        this.getWindow().setAttributes(lp);
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            if (position == 0) {
                cn.sharesdk.framework.Platform.ShareParams sp = new cn.sharesdk.framework.Platform.ShareParams();
                sp.setUrl(dataManager.getQrCodeUrl());
                sp.setImageUrl(img);
                sp.setTitle("中付钱柜");
                sp.setShareType(mType.equals("02")?Platform.SHARE_IMAGE:Platform.SHARE_WEBPAGE);
                Platform platform = ShareSDK.getPlatform(Wechat.NAME);
                // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        arg2.printStackTrace();
                    }

                    public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                        shareSucceed(position);
//                            Toast.makeText(getContext(), "分享成功~", Toast.LENGTH_SHORT).show();
                    }

                    public void onCancel(Platform arg0, int arg1) {
                        Toast.makeText(getContext(), "分享取消~", Toast.LENGTH_SHORT).show();
                    }
                });
                platform.share(sp);
                dismiss();
            } else {
                cn.sharesdk.framework.Platform.ShareParams sp = new cn.sharesdk.framework.Platform.ShareParams();
                sp.setUrl(dataManager.getQrCodeUrl());
                sp.setImageUrl(img);
                sp.setTitle("中付钱柜");
                sp.setShareType(mType.equals("02")?Platform.SHARE_IMAGE:Platform.SHARE_WEBPAGE);
                Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
                // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        arg2.printStackTrace();
                    }

                    public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                        shareSucceed(position);
//                            Toast.makeText(getContext(), "分享成功~", Toast.LENGTH_SHORT).show();
                    }

                    public void onCancel(Platform arg0, int arg1) {
                        Toast.makeText(getContext(), "分享取消~", Toast.LENGTH_SHORT).show();
                    }
                });
                platform.share(sp);
                dismiss();
            }
        }
    };

    private void shareSucceed(int position) {
        ProgressDlgHelper.openDialog(mActivity);
        int[] intArray = mActivity.getResources().getIntArray(com.lckj.jycm.R.array.share);
        int shareChannel = 1;
        if (position < intArray.length) {
            shareChannel = intArray[position];
        }
        infoService.shareAdvInfo(new ShareAdvInfoRequest(mAdId, mArid, shareChannel, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<CreateAdvInfoBean>(mActivity) {
                    @Override
                    public void onSuccessCall(CreateAdvInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            dataManager.setGold(response.getData().getGold() + "");
                            showSuccessDialog(response.getData().getShareReward());
                        }
                        Utils.showMsg(mActivity, response.getMsg());
                    }
                }, new ThrowableConsumer<Throwable>(mActivity));
    }

    private void showSuccessDialog(String shareReward) {
        if (!shareReward.equals("0")) {
            final CustomDialog customDialog = new CustomDialog(com.lckj.jycm.R.layout.dialog_invest_ad, mActivity, com.lckj.jycm.R.style.BottomDialog2);
            customDialog.show();
            TextView tvHint = (TextView) customDialog.findViewById(com.lckj.jycm.R.id.tv_hint);
            TextView tvMoney = (TextView) customDialog.findViewById(com.lckj.jycm.R.id.tv_money);
            Button btnInvest = (Button) customDialog.findViewById(com.lckj.jycm.R.id.btn_invest);
            btnInvest.setText(mActivity.getResources().getString(com.lckj.jycm.R.string.zhenzhidaole));
            tvMoney.setText(shareReward + mActivity.getResources().getString(com.lckj.jycm.R.string.金币));
            tvHint.setText(mActivity.getResources().getString(com.lckj.jycm.R.string.share_earn_hint));
            btnInvest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            new Handler() {
                public void handleMessage(Message msg) {
                    customDialog.dismiss();
                    Intent intent = new Intent(mActivity, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("whence", 1);
                    mActivity.startActivity(intent);
                }
            }.sendEmptyMessageDelayed(0, 1500);
        } else {
            Intent intent = new Intent(mActivity, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("whence", 1);
            mActivity.startActivity(intent);
        }
    }

    private class Selection {
        String shareName;
        int shareIcon;

        public Selection(String shareName, int shareIcon) {
            this.shareName = shareName;
            this.shareIcon = shareIcon;
        }
    }

    private class ShareAdapter extends ArrayAdapter<Selection> {
        private List<Selection> shareSelection;

        private ShareAdapter(Context context, List<Selection> shareSelection) {
            super(context, 0, shareSelection);
            this.shareSelection = shareSelection;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RecycleHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.share_item_share_selection, null);
                viewHolder = new RecycleHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (RecycleHolder) convertView.getTag();
            }
            Selection selection = getItem(position);
            viewHolder.tvTitle.setText(selection.shareName);
            viewHolder.ivIcon.setBackgroundResource(selection.shareIcon);
            return convertView;
        }

        private class RecycleHolder {
            TextView tvTitle;
            ImageView ivIcon;

            public RecycleHolder(View view) {
                tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            }
        }

    }


}
