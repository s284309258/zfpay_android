package com.lckj.jycm.center.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.CommonBean;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.article.picker.SelectTools;
import com.lckj.jycm.center.activity.map.GaodeMap_SelectAddressActivity;
import com.lckj.jycm.center.activity.map.utils.AnimationHelper;
import com.lckj.jycm.center.adapter.ApplyMerchantFunctionAdapter;
import com.lckj.jycm.network.AddMerchantInfoRequest;
import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.network.MerchantInfoBean;
import com.lckj.jycm.network.SimpleRequest;
import com.lckj.jycm.utils.ToastHelper;
import com.lckj.jycm.utils.Utils;
import com.lckj.lckjlib.permission.PermissionManager;
import com.lckj.lckjlib.picker.PickerArticleSelector;
import com.lckj.utilslib.TextWatcherHelper;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApplyMerchantFunctionActivity extends BaseActvity {
    public List<LocalMedia> operatingList;
    public ApplyMerchantFunctionAdapter operatingAdapter;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.recycler_view1)
    RecyclerView recyclerView1;
    @BindView(R.id.recycler_view2)
    RecyclerView recyclerView2;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.et_mer_name)
    EditText etMerName;
    @BindView(R.id.et_contact_name)
    EditText etContactName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.tv_shenhe_left)
    TextView tvShenheLeft;
    @BindView(R.id.tv_shenhe)
    TextView tvShenhe;
    @BindView(R.id.tv_complete_left)
    TextView tvCompleteLeft;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.ll_industry)
    LinearLayout llIndustry;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    private ApplyMerchantFunctionAdapter adapter1;
    private ApplyMerchantFunctionAdapter adapter2;
    private ArrayList<LocalMedia> mDatas1 = new ArrayList<>();
    private ArrayList<LocalMedia> mDatas2 = new ArrayList<>();
    public static ApplyMerchantFunctionActivity instance;
    private String gProvince;
    private String gCity;
    private String gArea;
    private String gAddress;
    private String gLng;
    private String gLat;

    @Inject
    FrontUserService frontUserService;
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private int intIndustry = 1;
    private String showImg;
    private String buslic;
    private TextView data1Watcher;
    private TextView data2Watcher;
    private boolean showImgReady;
    private boolean buslicReady;
    private int status;
    private String proCity;
    private String localtion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_merchant_function);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initDatas();
        getDatas();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    private void initDatas() {
    }

    @Override
    protected void initView() {
        customTitle.setText(R.string.申请商家功能);
        data1Watcher = new TextView(this);
        data2Watcher = new TextView(this);
        adapter1 = new ApplyMerchantFunctionAdapter(this, mDatas1, 3, data1Watcher);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.addItemDecoration(new ApplyMerchantFunctionAdapter.SpaceItemDecoration(Utils.dp2px(this, 6), Utils.dp2px(this, 4)));
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 3));
        adapter2 = new ApplyMerchantFunctionAdapter(this, mDatas2, 3, data2Watcher);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.addItemDecoration(new ApplyMerchantFunctionAdapter.SpaceItemDecoration(Utils.dp2px(this, 6), Utils.dp2px(this, 4)));
        // TODO: 2019/4/13 图片问题
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 3));
        TextWatcherHelper.bindView(false, tvCommit, etMerName, etContactName, etPhone, tvIndustry, tvLocation, data1Watcher, data2Watcher);
    }

    private void disableEdit(boolean disable) {
        tvCommit.setVisibility(View.GONE);
        etMerName.setEnabled(false);
        etContactName.setEnabled(false);
        etPhone.setEnabled(false);
        llIndustry.setEnabled(false);
        llLocation.setEnabled(false);
        adapter1.setEnabled(false);
        adapter2.setEnabled(false);
    }

    private void getDatas() {
        ProgressDlgHelper.openDialog(this);
        infoService.getMerchantInfo(new SimpleRequest(dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<MerchantInfoBean>(this) {
                    @Override
                    public void onSuccessCall(MerchantInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        MerchantInfoBean.DataBean data = response.getData();
                        if (data != null) {
                            disableEdit(true);
                            status = data.getStatus();
                            updateStatus();
                            etMerName.setText(data.getMerName());
                            etContactName.setText(data.getUserName());
                            etPhone.setText(data.getUserTel());
                            intIndustry = Integer.parseInt(data.getIndustry());
                            tvIndustry.setText(getIndustry().get(intIndustry));
                            tvLocation.setText(data.getLocaltion());
                            gLat = data.getLat();
                            gLng = data.getLng();
                            String showImg = data.getShowImg();
                            String[] split = showImg.split(",");
                            for (int i = 0; i < split.length; i++) {
                                String s = split[i];
                                if (!s.startsWith("http")) {
                                    s = ProviderModule.getDataManager(getContext()).getQiniuDomain() + "/" + s;
                                }
                                mDatas1.add(new LocalMedia().setPath(s));
                            }
                            adapter1.notifyDataSetChanged();
                            String busLic = data.getBusLic();
                            String[] split1 = busLic.split(",");
                            for (int i = 0; i < split1.length; i++) {
                                String s = split1[i];
                                if (!s.startsWith("http")) {
                                    s = ProviderModule.getDataManager(getContext()).getQiniuDomain() + "/" + s;
                                }
                                mDatas2.add(new LocalMedia().setPath(s));
                            }
                            adapter2.notifyDataSetChanged();
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));

    }


    private void updateStatus() {
        switch (status) {
            case 1:
                tvCommit.setVisibility(View.GONE);
                tvShenhe.setEnabled(true);
                tvShenheLeft.setEnabled(true);
                tvComplete.setEnabled(false);
                tvCompleteLeft.setEnabled(false);
                break;
            case 2:
                tvCommit.setVisibility(View.GONE);
                tvShenhe.setEnabled(true);
                tvComplete.setEnabled(true);
                tvCompleteLeft.setEnabled(true);
                tvShenheLeft.setEnabled(true);
                break;
            case 3:
                tvCommit.setVisibility(View.GONE);
                tvShenhe.setEnabled(true);
                tvComplete.setEnabled(true);
                tvCompleteLeft.setEnabled(true);
                tvShenheLeft.setEnabled(true);
                tvCommit.setVisibility(View.VISIBLE);
                break;
            default:
                tvCommit.setVisibility(View.VISIBLE);
                tvShenhe.setEnabled(false);
                tvShenheLeft.setEnabled(false);
                tvComplete.setEnabled(false);
                tvCompleteLeft.setEnabled(false);
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (operatingAdapter != null && operatingList != null) {
                        if (localMedia!=null&&localMedia.size()>0){
                            for (LocalMedia media : localMedia) {
                                media.setPath(media.getCutPath());
                            }
                        }
                        operatingList.addAll(localMedia);
                        operatingAdapter.notifyDataSetChanged();
                    }
                    break;
                case 001:
                    //高德地图
                    gProvince = data.getStringExtra("sheng");
                    gCity = data.getStringExtra("shi");
                    gArea = data.getStringExtra("qu");
                    gAddress = data.getStringExtra("address");
                    gLng = data.getStringExtra("lng");
                    gLat = data.getStringExtra("lat");
                    proCity = gProvince + gCity;
                    localtion = gArea + gAddress;
//                    tvLocation.setText("\n\n高德地图\n\n" + "省：" + gProvince + "\n市：" + gCity + "\n区：" + gArea + "\n地址：" + gAddress + "\n经度：" + gLng + "\n纬度：" + gLat);
                    tvLocation.setText(/*gProvince + gCity + */gArea + gAddress);
                    break;
            }
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.tv_commit, R.id.ll_location, R.id.ll_industry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.tv_commit:
                uploadImgs();
                break;
            case R.id.ll_location:
                this.getPermissionManager().checkPermisson(this, PermissionManager.RequestPermisson.PERMISSION_LOCATION, new PermissionManager.OnPermissionListener() {
                    @Override
                    public void onPermissionCheckResult(boolean isAllow, int requestCode) {
                        if (isAllow) {
                            //高德地图
                            Intent intentGD = new Intent();
                            intentGD.setClass(ApplyMerchantFunctionActivity.this, GaodeMap_SelectAddressActivity.class);
                            //                startActivityForResult(intentGD,001);
                            AnimationHelper.startActivityForResult(ApplyMerchantFunctionActivity.this, intentGD, 001, null, ivLocation, R.color.colorPrimaryDark, 500);
                        }
                    }
                });
                break;
            case R.id.ll_industry:
                SelectTools.selectOne(getString(R.string.selector_hangye), this, getIndustry(), new PickerArticleSelector.OnWheelChangeListenerAdapter() {
                    @Override
                    public void OnWheelChange(int position1, int position2, int position3, boolean isCancel) {
                        if (isCancel) return;
                        tvIndustry.setText(getIndustry().get(position1));
                        intIndustry = position1 + 1;
                    }
                });
                break;
        }
    }

    private List<String> getIndustry() {
        return Arrays.asList(getResources().getStringArray(R.array.industrys));
    }

    private void uploadImgs() {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < mDatas1.size(); i++) {
            LocalMedia localMedia = mDatas1.get(i);
            String path = localMedia.getPath();
            if (path != null && !path.startsWith("http")) {
                fileList.add(new File(path));
            }
        }
        showImgReady = false;
        uploadImageToQnUtils.uploadImage(null, Const.UPLOAD_TOKEN_BIZ_COMMON, fileList, ProviderModule.getDataManager(this).getToken(), "0");
        uploadImageToQnUtils.setPicCallback(new UploadImageToQnUtils.QiniuTokenListener() {
            @Override
            public void onSuccessedcallBack(final List<String> imageUrlList, List<String> fileList) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < imageUrlList.size(); i++) {
                    if (i == 0) {
                        stringBuilder.append(imageUrlList.get(i));
                    } else {
                        stringBuilder.append(",").append(imageUrlList.get(i));
                    }
                }
                showImg = stringBuilder.toString();
                showImgReady = !TextUtils.isEmpty(showImg);
                if (showImgReady && buslicReady) {
                    commit();
                }
            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                ToastHelper.showToast(getString(R.string.failed_to_upload_pictures));
            }
        });
        UploadImageToQnUtils uploadImageToQnUtils2 = new UploadImageToQnUtils(this);
        List<File> fileList2 = new ArrayList<>();
        for (int i = 0; i < mDatas2.size(); i++) {
            LocalMedia localMedia = mDatas2.get(i);
            String path = localMedia.getPath();
            if (path != null && !path.startsWith("http")) {
                fileList2.add(new File(path));
            }
        }
        buslicReady = false;
        uploadImageToQnUtils2.uploadImage(null, Const.UPLOAD_TOKEN_BIZ_COMMON, fileList2, ProviderModule.getDataManager(this).getToken(), "0");
        uploadImageToQnUtils2.setPicCallback(new UploadImageToQnUtils.QiniuTokenListener() {
            @Override
            public void onSuccessedcallBack(final List<String> imageUrlList, List<String> fileList) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < imageUrlList.size(); i++) {
                    if (i == 0) {
                        stringBuilder.append(imageUrlList.get(i));
                    } else {
                        stringBuilder.append(",").append(imageUrlList.get(i));
                    }
                }
                buslic = stringBuilder.toString();
                buslicReady = !TextUtils.isEmpty(buslic);
                if (showImgReady && buslicReady) {
                    commit();
                }
            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                ToastHelper.showToast(getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void commit() {
        ProgressDlgHelper.openDialog(this);
        frontUserService.addMerchantInfo(new AddMerchantInfoRequest(dataManager.getToken(), etMerName.getText().toString(), etContactName.getText().toString()
                , etPhone.getText().toString(), String.valueOf(intIndustry), proCity,localtion, gLat, gLng, showImg, buslic))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<CommonBean>(this) {
                    @Override
                    public void onSuccessCall(CommonBean response) {
                        ProgressDlgHelper.closeDialog();
                        status = 1;
                        updateStatus();
                        disableEdit(true);
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

}
