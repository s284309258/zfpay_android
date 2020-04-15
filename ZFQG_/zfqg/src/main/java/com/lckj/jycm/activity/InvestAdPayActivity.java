package com.lckj.jycm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lckj.base.MainApplication;
import com.lckj.framework.data.DataManager;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.center.activity.map.GaodeMap_SelectAddressActivity;
import com.lckj.jycm.center.activity.map.utils.AnimationHelper;
import com.lckj.jycm.home.HomeActivity;
import com.lckj.jycm.network.CreateAdvInfoBean;
import com.lckj.jycm.network.CreateAdvInfoRequest;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.widget.CustomDialog;
import com.lckj.lckjlib.permission.PermissionManager;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvestAdPayActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_sum_money)
    TextView tvSumMoney;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_money)
    ClearEditText etMoney;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.et_share_count)
    ClearEditText etShareCount;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv_invest_area)
    TextView tvInvestArea;
    @BindView(R.id.rl_selecotr_area)
    RelativeLayout rlSelecotrArea;
    @BindView(R.id.btn_invest)
    Button btnInvest;
    private LocalMedia localMedia;
    private String link;
    private String mTitle;
    private String mUrl;
    private String mIntro;
    private String mId;
    @Inject
    InfoService infoService;
    @Inject
    DataManager dataManager;
    private String gProvince;
    private String gCity;
    private String gArea;
    private String gAddress;
    private String gLng;
    private String gLat;
    private double mGold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_ad_pay);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
        initEvents();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.toufangguanggao));
        leftAction.setText("");
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mUrl = intent.getStringExtra("url");
        mIntro = intent.getStringExtra("intro");
        link = intent.getStringExtra("link");
        mId = intent.getStringExtra("id");
        localMedia = (LocalMedia) intent.getParcelableExtra("image");
        mGold = Double.parseDouble(dataManager.getGold());
        tvGold.setText(getString(R.string.invest_ad_pay_remaining, mGold + ""));
    }

    @Override
    protected void initEvents() {
        etShareCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etShareCount.getText().toString().trim()) && !TextUtils.isEmpty(etMoney.getText().toString().trim())) {
                    double money = Double.parseDouble(etMoney.getText().toString().trim());
                    double shareCount = Double.parseDouble(etShareCount.getText().toString().trim());
                    tvSumMoney.setText(money * shareCount + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(etShareCount.getText().toString().trim()) && !TextUtils.isEmpty(etMoney.getText().toString().trim())) {
                    double money = Double.parseDouble(etMoney.getText().toString().trim());
                    double shareCount = Double.parseDouble(etShareCount.getText().toString().trim());
                    tvSumMoney.setText(money * shareCount + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.rl_selecotr_area, R.id.btn_invest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.rl_selecotr_area:
                selectorArea();
                break;
            case R.id.btn_invest:
                if (checkEdit()) {
                    showDialog();
                }
                break;
        }
    }

    private void selectorArea() {
        /*SelectTools.selectOne(getString(R.string.selector_invest_area), this, getAreaList(), new PickerArticleSelector.OnWheelChangeListenerAdapter() {
            @Override
            public void OnWheelChange(int position1, int position2, int position3, boolean isCancel) {
                if (isCancel) return;
                tvInvestArea.setText(getAreaList().get(position1));
            }
        });*/
        this.getPermissionManager().checkPermisson(this, PermissionManager.RequestPermisson.PERMISSION_LOCATION, new PermissionManager.OnPermissionListener() {
            @Override
            public void onPermissionCheckResult(boolean isAllow, int requestCode) {
                if (isAllow) {
                    //高德地图
                    Intent intentGD = new Intent();
                    intentGD.setClass(InvestAdPayActivity.this, GaodeMap_SelectAddressActivity.class);
                    //                startActivityForResult(intentGD,001);
                    AnimationHelper.startActivityForResult(InvestAdPayActivity.this, intentGD, 001, null, rlSelecotrArea, R.color.colorPrimaryDark, 500);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 001:
                    //高德地图
                    gProvince = data.getStringExtra("sheng");
                    gCity = data.getStringExtra("shi");
                    gArea = data.getStringExtra("qu");
                    gAddress = data.getStringExtra("address");
                    gLng = data.getStringExtra("lng");
                    gLat = data.getStringExtra("lat");
//                    tvLocation.setText("\n\n高德地图\n\n" + "省：" + gProvince + "\n市：" + gCity + "\n区：" + gArea + "\n地址：" + gAddress + "\n经度：" + gLng + "\n纬度：" + gLat);
                    tvInvestArea.setText(gProvince + gCity + gArea + gAddress);
                    break;
            }
        }
    }

    private List<String> getAreaList() {
        return Arrays.asList(getResources().getStringArray(R.array.article_types));
    }

    private boolean checkEdit() {
        if (TextUtils.isEmpty(etMoney.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.et_money));
            return false;
        } else if (TextUtils.isEmpty(etShareCount.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.et_share_count));
            return false;
        } else if (TextUtils.isEmpty(tvInvestArea.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.selector_invest_area));
            return false;
        } else if (Double.parseDouble(tvSumMoney.getText().toString().trim()) > mGold) {
            Utils.showMsg(this, getString(R.string.toast_gold_insufficient));
            return false;
        }
        return true;
    }

    private void showDialog() {
        String money = tvSumMoney.getText().toString().trim();
        etShareCount.getText().toString().trim();
        final CustomDialog customDialog = new CustomDialog(R.layout.dialog_invest_ad, this, R.style.BottomDialog2);
        customDialog.show();
        Button btnInvest = (Button) customDialog.findViewById(R.id.btn_invest);
        TextView tvMoney = (TextView) customDialog.findViewById(R.id.tv_money);
        tvMoney.setText(money + getString(R.string.金币));
        btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImages();
                customDialog.dismiss();
            }
        });
    }

    private void uploadImages() {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        List<String> pictureTypes = new ArrayList<>();
        if (!TextUtils.isEmpty(localMedia.getPath()) && !localMedia.getPath().startsWith("http")) {
            fileList.add(new File(localMedia.getPath()));
            pictureTypes.add(localMedia.getPictureType());
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
                invest();

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(InvestAdPayActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void invest() {
        String money = tvSumMoney.getText().toString().trim();
        String count = etShareCount.getText().toString().trim();
        String single = new DecimalFormat("#.##").format(Double.parseDouble(money) / Double.parseDouble(count));
        infoService.createAdvInfoToApp(new CreateAdvInfoRequest(mId, link, mIntro, mTitle, mUrl, gLat, gLng, money, single, dataManager.getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<CreateAdvInfoBean>(this) {
                    @Override
                    public void onSuccessCall(CreateAdvInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            dataManager.setGold(response.getData().getGold() + "");
                            Intent intent = new Intent(InvestAdPayActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("whence", 1);
                            startActivity(intent);
                        }
                        Utils.showMsg(InvestAdPayActivity.this, response.getMsg());
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }
}
