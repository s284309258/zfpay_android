package com.lckj.zfqg.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.lckj.jycm.network.TokenRequest;
import com.lckj.jycm.utils.Utils;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.jycm.zfqg_network.SearchLikeBankBean;
import com.lckj.jycm.zfqg_network.UpdateUserCardRequest;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lckj.zfqg.adapter.PopupWindowAdapter;
import com.lckj.zfqg.widget.PayDialog;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
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

public class AddBankCardActivity extends BaseActvity {
    private static final int FRONT_BANK_CARD_IMG = 201;
    private static final int BACK_BANK_CARD_IMG = 202;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_bank_name)
    ClearEditText etBankName;
    @BindView(R.id.et_bank_code)
    ClearEditText etBankCode;
    @BindView(R.id.et_bank_number)
    ClearEditText etBankNumber;
    @BindView(R.id.tv_add_front)
    TextView tvAddFront;
    @BindView(R.id.iv_front)
    ImageView ivFront;
    @BindView(R.id.tv_add_back)
    TextView tvAddBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.rl_selector)
    RelativeLayout rlSelector;
    @BindView(R.id.xx)
    View xx;
    private boolean isSelected;
    private String link;
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private List<LocalMedia> selectList = new ArrayList<>();
    private PayDialog mPayDialog;
    private PopupWindow mPopupWindow;
    private PopupWindowAdapter mAdapter;
    List<SearchLikeBankBean.DataBean.SysBankListBean> mData = new ArrayList<>(2);
    private RecyclerView mRecyclerView;
    private boolean isOpen = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.添加结算卡));
        rightAction.setText(getString(R.string.完成));
        selectList.add(null);
        selectList.add(null);
        rightAction.setTextColor(getResources().getColor(R.color.green2));
        etName.setText(dataManager.getUserName());
        etName.setEnabled(false);
        initPop();
    }

    @SuppressLint("WrongConstant")
    private void initPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_bank_search, null, false);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(this, 195), true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PopupWindowAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvents() {
        etBankName.addTextChangedListener(mTextWatcher);
        /*etBankName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    etBankName.addTextChangedListener(mTextWatcher);
                } else {
                    etBankName.removeTextChangedListener(mTextWatcher);
                }
            }
        });*/
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isOpen) {
                if (!TextUtils.isEmpty(s.toString())) {
                    search(s.toString());
                } else {
                    mData.clear();
                    mAdapter.notifyDataSetChanged();
                    mPopupWindow.dismiss();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void search(String key) {
        mMyService.searchLikeBank(new TokenRequest(dataManager.getToken(), key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<SearchLikeBankBean>(this) {
                    @Override
                    public void onSuccessCall(SearchLikeBankBean response) {
                        mData.clear();
                        mAdapter.notifyDataSetChanged();
                        mData.addAll(response.getData().getSysBankList());
                        if (mData.size() != 0) {
                            mAdapter.notifyDataSetChanged();
                            mRecyclerView.scrollToPosition(0);
                            if (mPopupWindow != null && !mPopupWindow.isShowing()) {
                                mPopupWindow.showAsDropDown(xx);
                            }
                        } else {
                            mPopupWindow.dismiss();
                        }
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.left_action, R.id.right_action, R.id.iv_front, R.id.iv_back, R.id.rl_selector})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                if (checkEdit()) {
                    showPayDialog();
                }
                break;
            case R.id.iv_front:
                selecotrImg(FRONT_BANK_CARD_IMG);
                break;
            case R.id.iv_back:
                selecotrImg(BACK_BANK_CARD_IMG);
                break;
            case R.id.rl_selector:
                isSelected = !isSelected;
                ivCheck.setSelected(isSelected);
                break;
        }
    }

    private boolean checkEdit() {
        if(TextUtils.isEmpty(etName.getText().toString())){
            Utils.showMsg(this,getString(R.string.请输入持卡人姓名));
            return false;
        }else if(TextUtils.isEmpty(etBankName.getText().toString())){
            Utils.showMsg(this,etBankName.getHint().toString());
            return false;
        }else if(TextUtils.isEmpty(etBankCode.getText().toString())){
            Utils.showMsg(this,etBankCode.getHint().toString());
            return false;
        }else if(TextUtils.isEmpty(etBankNumber.getText().toString())){
            Utils.showMsg(this,etBankNumber.getHint().toString());
            return false;
        }else if (selectList.get(0) == null) {
            Utils.showMsg(this, getString(R.string.银行卡正面照));
            return false;
        } else if (selectList.get(1) == null) {
            Utils.showMsg(this, getString(R.string.银行卡反面照));
            return false;
        }
        return true;
    }

    private void showPayDialog() {
        if (mPayDialog == null) {
            mPayDialog = new PayDialog(this);
            mPayDialog.setOnResult(new PayDialog.OnResult() {
                @Override
                public void success(String password) {
                    uploadImages(password);
                }
            });
        }
        mPayDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FRONT_BANK_CARD_IMG:
                    selectList.remove(0);
                    selectList.add(0, PictureSelector.obtainMultipleResult(data).get(0));
                    ivFront.setImageBitmap(BitmapFactory.decodeFile(selectList.get(0).getCompressPath()));
                    break;
                case BACK_BANK_CARD_IMG:
                    selectList.remove(1);
                    selectList.add(1, PictureSelector.obtainMultipleResult(data).get(0));
                    ivBack.setImageBitmap(BitmapFactory.decodeFile(selectList.get(1).getCompressPath()));
                    break;
            }
        }
    }

    private void selecotrImg(int code) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_QQ_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                            .selectionMedia(selectList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .compress(true)
                .forResult(code);//结果回调onActivityResult code
    }

    private void uploadImages(final String password) {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        List<String> pictureTypes = new ArrayList<>();
        for (LocalMedia localMedia : selectList) {
            if (!TextUtils.isEmpty(localMedia.getCompressPath()) && !localMedia.getCompressPath().startsWith("http")) {
                fileList.add(new File(localMedia.getCompressPath()));
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
                submit(password);

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(AddBankCardActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit(String password) {
        String bankCode = etBankCode.getText().toString();
        String bankName = etBankName.getText().toString();
        String bankNumber = etBankNumber.getText().toString();
        String name = etName.getText().toString();
        String isDefault = isSelected ? "1" : "0";
        ProgressDlgHelper.openDialog(this);
        mMyService.updateUserCard(new UpdateUserCardRequest(dataManager.getToken(), "user_card_add", bankCode, bankName, link, isDefault, bankNumber, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        setResult(RESULT_OK);
                        finish();
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    public void setData(SearchLikeBankBean.DataBean.SysBankListBean bean) {
        isOpen = false;
        etBankName.setText(bean.getBank_name());
        etBankCode.setText(bean.getBank_code());
        etBankNumber.setFocusable(true);
        etBankNumber.requestFocus();
        etBankNumber.setFocusableInTouchMode(true);
        mPopupWindow.dismiss();
        isOpen = true;

    }
}
