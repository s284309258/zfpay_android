package com.lckj.zfqg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.center.adapter.ApplyMerchantFunctionAdapter;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.AddUserFeedBackRequest;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FeedbackActivity extends BaseActvity {
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_title)
    ClearEditText etTitle;
    @BindView(R.id.tv_title_count)
    TextView tvTitleCount;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.et_content)
    ClearEditText etContent;
    @BindView(R.id.tv_content_count)
    TextView tvContentCount;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_img_count)
    TextView tvImgCount;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private TextView data1Watcher;
    public List<LocalMedia> operatingList = new ArrayList<>();
    public ApplyMerchantFunctionAdapter operatingAdapter;
    @Inject
    MyService myService;
    @Inject
    DataManager dataManager;
    private String link = "";
    private List<LocalMedia> selectList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.意见反馈));
        rightAction.setText(getString(R.string.反馈记录));
        data1Watcher = new TextView(this);
        operatingAdapter = new ApplyMerchantFunctionAdapter(this, operatingList, 4, data1Watcher);
        recyclerView.setAdapter(operatingAdapter);
        recyclerView.addItemDecoration(new ApplyMerchantFunctionAdapter.SpaceItemDecoration(Utils.dp2px(this, 6), Utils.dp2px(this, 4)));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        rightAction.setText(R.string.反馈记录);
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
                        operatingList.clear();
                        operatingList.addAll(localMedia);
                        operatingAdapter.notifyDataSetChanged();
                        tvImgCount.setText(operatingList.size() + "/3");
                    }
                    break;
            }
        }
    }

    @Override
    protected void initEvents() {
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTitleCount.setText(etTitle.getText().toString().length() + "/50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvContentCount.setText(etContent.getText().toString().length() + "/505");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.btn_submit, R.id.right_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.btn_submit:
                if (check()) {
                    if (operatingList.size() != 0) {
                        uploadImages();
                    } else {
                        submit();
                    }
                }
                break;
            case R.id.right_action:
                startActivity(new Intent(this, FeedbackRecordActivity.class));
                break;
        }
    }

    private boolean check() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            Utils.showMsg(this, getString(R.string.请输入您的主题概要));
            return false;
        } else if (TextUtils.isEmpty(etContent.getText().toString())) {
            Utils.showMsg(this, etContent.getHint().toString());
            return false;
        } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Utils.showMsg(this, etPhone.getHint().toString());
            return false;
        }
        return true;
    }

    private void uploadImages() {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < operatingList.size(); i++) {
            LocalMedia localMedia = operatingList.get(i);
            String path = localMedia.getPath();
            if (path != null && !path.startsWith("http")) {
                fileList.add(new File(path));
            }
        }
        uploadImageToQnUtils.uploadImage(null, Const.UPLOAD_TOKEN_BIZ_COMMON, fileList, dataManager.getToken(), "0");
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
                Utils.showMsg(FeedbackActivity.this, getString(R.string.上传图片失败));
            }
        });

    }

    private void submit() {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        String phone = etPhone.getText().toString();
        ProgressDlgHelper.openDialog(this);
        myService.addUserFeedBack(new AddUserFeedBackRequest(dataManager.getToken(), title, content, link, phone))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(FeedbackActivity.this, getString(R.string.上传成功));
                        startActivity(new Intent(FeedbackActivity.this, FeedbackRecordActivity.class));
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void setCount(int size) {
        tvImgCount.setText(operatingList.size() + "/5");
    }
}
