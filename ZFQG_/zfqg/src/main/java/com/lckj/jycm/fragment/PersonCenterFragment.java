package com.lckj.jycm.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lckj.base.MainApplication;
import com.lckj.custom.CustomPictureExternalPreviewActivity;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseFragment;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.MyAdActivity;
import com.lckj.jycm.activity.MyWalletActivity;
import com.lckj.jycm.activity.RealNameActivity;
import com.lckj.jycm.article.activity.MyArticleActivity;
import com.lckj.jycm.center.activity.ApplyMerchantFunctionActivity;
import com.lckj.jycm.center.activity.MyTeamActivity;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.PersonInfoBean;
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.network.UpdatePersonalRequest;
import com.lckj.zfqg.activity.SettingActivity;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.imageloader.ImageLoader;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.entity.LocalMedia;
import com.luck.picture.lib2.tools.DoubleUtils;
import com.lython.network.model.Const;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonCenterFragment extends BaseFragment {
    Unbinder unbinder;
    @Inject
    DataManager dataManager;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.fl_head_img)
    FrameLayout flHeadImg;
    @BindView(R.id.tv_invate_friend)
    TextView tvInvateFriend;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.ll_my_team)
    LinearLayout llMyTeam;
    @BindView(R.id.tv_my_wallet)
    TextView tvMyWallet;
    @BindView(R.id.ll_apply_merchant_function)
    LinearLayout llApplyMerchantFunction;
    @BindView(R.id.ll_my_article)
    LinearLayout llMyArticle;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;
    @BindView(R.id.tv_coin)
    TextView tvCoin;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.ll_my_ad)
    LinearLayout llMyAd;
    @BindView(R.id.ll_real_name)
    LinearLayout llRealName;
    @BindView(R.id.tv_invitation_code)
    TextView tvInvitationCode;
    private List<LocalMedia> localMedia = new ArrayList<>();
    private String link;
    @Inject
    FrontUserService mFrontUserService;

    @Nullable
    @Override
    public void onCreateView(Bundle savedInstanceState) {
        View view = setContentView(R.layout.frg_person_center);
        unbinder = ButterKnife.bind(this, view);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataManager.getmPref().registerOnSharedPreferenceChangeListener(mListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(dataManager.USER_NAME)) {
                tvNickName.setText(dataManager.getUserName());
            } else if (key.equals(dataManager.USER_HEAD_PHOTO)) {
                initData();
                ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 10, 1);
            } else if (key.equals(dataManager.USER_GOLD)) {
                tvCoin.setText(dataManager.getGold());
            }
        }
    };

    private void initData() {
        ProgressDlgHelper.openDialog(getActivity());
        mFrontUserService.getPersonInfo(new TokenRequest(dataManager.getToken(), "getPersonInfo"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<PersonInfoBean>(this) {
                    @Override
                    public void onSuccessCall(PersonInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            tvCoin.setText(response.getData().getFrontUserAccountDO().getGold() + "");
                            dataManager.setGold(response.getData().getFrontUserAccountDO().getGold() + "");
                            tvMoney.setText(response.getData().getFrontUserAccountDO().getAmount() + "");
                            tvTeam.setText(response.getData().getFrontUserAccountDO().getTeamMember() + "");
                        } else {
                            Utils.showMsg(getActivity(), response.getMsg());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    private List<LocalMedia> getHeadPhotoList() {
        String headPhoto = dataManager.getHeadPhoto();
        localMedia.clear();
        if (headPhoto.startsWith("http")) {
            localMedia.add(new LocalMedia().setPath(headPhoto));
        } else {
            localMedia.add(new LocalMedia().setPath(ProviderModule.getDataManager(getActivity()).getQiniuDomain() + "/" + headPhoto));
        }
        return localMedia;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        dataManager.getmPref().unregisterOnSharedPreferenceChangeListener(mListener);
    }

    @Override
    protected void initView() {
        ImageLoader.loadImage(dataManager.getHeadPhoto(), ivHeadImg, 10, 1);
        tvNickName.setText(dataManager.getUserName());
        tvInvitationCode.setText(getString(R.string.邀请码) + ":" + dataManager.getInvitationCode());
        getHeadPhotoList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> localMedia1 = PictureSelector.obtainMultipleResult(data);
                if (localMedia1 != null && localMedia1.size() > 0) {
                    localMedia.clear();
                    localMedia.add(new LocalMedia().setPath(localMedia1.get(0).getCutPath()));
                }
                uploadImages();
            }
        }

    }

    private void uploadImages() {
        ProgressDlgHelper.openDialog(getActivity());
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(getActivity());
        List<File> fileList = new ArrayList<>();
        List<String> pictureTypes = new ArrayList<>();
        for (LocalMedia localMedia : localMedia) {
            if (!TextUtils.isEmpty(localMedia.getPath()) && !localMedia.getPath().startsWith("http")) {
                fileList.add(new File(localMedia.getPath()));
                pictureTypes.add(localMedia.getPictureType());
            }
        }
        String biz = Const.UPLOAD_TOKEN_BIZ_COMMON;
        uploadImageToQnUtils.uploadImage(pictureTypes, biz, fileList, dataManager.getToken(), "0");
        uploadImageToQnUtils.setPicCallback(new UploadImageToQnUtils.QiniuTokenListener() {
            @Override
            public void onSuccessedcallBack(List<String> imageUrlList, List<String> fileList) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < imageUrlList.size(); i++) {
                    String s = imageUrlList.get(i);
                    if (i == 0) {
                        stringBuilder.append(s);
                    } else {
                        stringBuilder.append(",").append(s);
                    }
                }
                link = stringBuilder.toString();
                submit();

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(getActivity(), getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit() {
        mFrontUserService.updatePersonal(new UpdatePersonalRequest("", "", "", "", link, dataManager.getToken(), "", ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            Utils.showMsg(getActivity(), getString(R.string.toast_update_success));
                            dataManager.setHeadPhoto(link);
                            ImageLoader.loadImage(link, ivHeadImg, 10, 1);
                        } else {
                            Utils.showMsg(getActivity(), response.getMsg());
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.fl_head_img, R.id.tv_my_wallet, R.id.ll_question, R.id.ll_my_article, R.id.ll_my_team, R.id.iv_setting, R.id.ll_apply_merchant_function, R.id.tv_invate_friend, R.id.tv_invate_friend_2,
            R.id.ll_real_name, R.id.ll_my_ad})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_ad:
                startActivity(new Intent(getActivity(), MyAdActivity.class));
                break;
            case R.id.fl_head_img:
                if (!DoubleUtils.isFastDoubleClick()) {
                    Intent intent = new Intent(getActivity(), CustomPictureExternalPreviewActivity.class);
                    intent.putExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST, (Serializable) getHeadPhotoList());
                    intent.putExtra(PictureConfig.EXTRA_POSITION, 0);
                    intent.putExtra("tv", getString(R.string.update_head_photo));
                    intent.putExtra("cropRadio", 1f);
                    startActivityForResult(intent, PictureConfig.CHOOSE_REQUEST);
                }
                break;
            case R.id.tv_my_wallet:
                startActivity(new Intent(getContext(), MyWalletActivity.class));
                break;
            case R.id.ll_real_name:
                startActivity(new Intent(getContext(), RealNameActivity.class));
                break;
            case R.id.ll_question:
                break;
            case R.id.ll_my_article:
                startActivity(new Intent(getContext(), MyArticleActivity.class));
                break;
            case R.id.ll_my_team:
                startActivity(new Intent(getContext(), MyTeamActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.ll_apply_merchant_function:
                startActivity(new Intent(getContext(), ApplyMerchantFunctionActivity.class));
                break;
            case R.id.tv_invate_friend:
            case R.id.tv_invate_friend_2:
//                startActivity(new Intent(getContext(), InvateFriendActivity.class));

                Platform platform = ShareSDK.getPlatform(Wechat.NAME);
                Platform.ShareParams shareParams = new  Platform.ShareParams();
                shareParams.setText("帮我点一下吧\uD83D\uDE4F\uD83C\uDFFB你也有钱拿\uD83D\uDCB0\uD83D\uDCB0\uD83D\uDCB0 http://www.rxhwl.com/share/indexs.html?invtCode="+dataManager.getInvitationCode());
                shareParams.setTitle("");
                shareParams.setShareType(Platform.SHARE_TEXT);
                platform.setPlatformActionListener(new PlatformActionListener() {
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        arg2.printStackTrace();
                    }

                    public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                            Toast.makeText(getContext(), "分享成功~", Toast.LENGTH_SHORT).show();
                    }

                    public void onCancel(Platform arg0, int arg1) {
                        Toast.makeText(getContext(), "分享取消~", Toast.LENGTH_SHORT).show();
                    }
                });
                platform.share(shareParams);
                break;
        }
    }

}
