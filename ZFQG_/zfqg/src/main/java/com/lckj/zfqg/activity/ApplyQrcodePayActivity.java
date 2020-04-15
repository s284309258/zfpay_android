package com.lckj.zfqg.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
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
import com.lckj.jycm.zfqg_network.AddApplyScanRecordRequest;
import com.lckj.jycm.zfqg_network.GetScanTraditionalPosListBean;
import com.lckj.jycm.zfqg_network.MyService;
import com.lckj.lckjlib.widgets.ClearEditText;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.luck.picture.lib2.PictureSelector;
import com.luck.picture.lib2.config.PictureConfig;
import com.luck.picture.lib2.config.PictureMimeType;
import com.luck.picture.lib2.entity.LocalMedia;
import com.lython.network.model.Const;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApplyQrcodePayActivity extends BaseActvity {
    private static final int LICENSE_IMG = 501;
    private static final int STORE_LAYOUT_IMG = 502;
    private static final int GATE_IMG = 503;
    private static final int CHECKSTAND_IMG = 504;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tv_mpos)
    TextView tvMpos;
    @BindView(R.id.tv_sn)
    TextView tvSn;
    @BindView(R.id.rl_sn)
    RelativeLayout rlSn;
    @BindView(R.id.tv_add_license)
    TextView tvAddLicense;
    @BindView(R.id.iv_license)
    ImageView ivLicense;
    @BindView(R.id.tv_add_store_layout)
    TextView tvAddStoreLayout;
    @BindView(R.id.iv_store_layout)
    ImageView ivStoreLayout;
    @BindView(R.id.tv_add_gate)
    TextView tvAddGate;
    @BindView(R.id.iv_gate)
    ImageView ivGate;
    @BindView(R.id.tv_checkstand)
    TextView tvCheckstand;
    @BindView(R.id.iv_checkstand)
    ImageView ivCheckstand;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private HomePosAdapter mHomePosAdapter;
    private String link;
    private ArrayList<LocalMedia> selectList = new ArrayList<>();
    @Inject
    DataManager dataManager;
    @Inject
    MyService mMyService;
    private boolean isOpen = true;
    private String last_id;
    private String refstatus = "down";
    private String key_word;
    List<GetScanTraditionalPosListBean.DataBean.ScanTraditionalPosListBean> mData = new ArrayList<>();
    private String mSn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_qrcode_pay);
        ButterKnife.bind(this);
        MainApplication.getInstance().getInjectGraph().inject(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        customTitle.setText(getString(R.string.申请扫码支付));
        rightAction.setText(getString(R.string.申请记录));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectList.add(0, null);
        selectList.add(1, null);
        selectList.add(2, null);
        selectList.add(3, null);
        mHomePosAdapter = new HomePosAdapter(this, mData);
        recyclerView.setAdapter(mHomePosAdapter);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    key_word = etSearch.getText().toString().trim();
                    last_id = "";
                    Utils.hideKeyboard(etSearch);
                    initData();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initEvents() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refstatus = "down";
                last_id = "";
                initData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refstatus = "up";
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        if (isOpen) {
            ProgressDlgHelper.openDialog(this);
            isOpen = false;
        }
        mMyService.getScanTraditionalPosList(new TokenRequest(dataManager.getToken(), key_word, last_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetScanTraditionalPosListBean>(this) {
                    @Override
                    public void onSuccessCall(GetScanTraditionalPosListBean response) {
                        ProgressDlgHelper.closeDialog();
                        finishLoad();
                        List<GetScanTraditionalPosListBean.DataBean.ScanTraditionalPosListBean> userFeedBackList = response.getData().getScanTraditionalPosList();
                        if (TextUtils.isEmpty(last_id)) mData.clear();
                        mData.addAll(userFeedBackList);
                        if (mData.size() > 0) last_id = mData.get(mData.size() - 1).getTrapos_id();
                        mHomePosAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailedCall(GetScanTraditionalPosListBean response) {
                        super.onFailedCall(response);
                        finishLoad();
                    }
                }, new ThrowableConsumer<Throwable>(this) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        finishLoad();

                    }
                });

    }

    public void finishLoad() {
        if ("up".equals(refstatus)) refreshLayout.finishLoadMore();
        if ("down".equals(refstatus)) refreshLayout.finishRefresh();
    }


    @OnClick({R.id.left_action, R.id.right_action, R.id.rl_sn, R.id.iv_license, R.id.iv_store_layout, R.id.iv_gate, R.id.iv_checkstand, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.right_action:
                startActivity(new Intent(this, ApplyQrcodePayRecordActivity.class));
                break;
            case R.id.rl_sn:
                drawerLayout.openDrawer(Gravity.END);
                break;
            case R.id.iv_license:
                selecotrImg(LICENSE_IMG);
                break;
            case R.id.iv_store_layout:
                selecotrImg(STORE_LAYOUT_IMG);
                break;
            case R.id.iv_gate:
                selecotrImg(GATE_IMG);
                break;
            case R.id.iv_checkstand:
                selecotrImg(CHECKSTAND_IMG);
                break;
            case R.id.btn_submit:
                if (checkEidt()) {
                    uploadImages();
                }
                break;
        }
    }

    private boolean checkEidt() {
        if (TextUtils.isEmpty(tvSn.getText().toString())) {
            Utils.showMsg(this, tvSn.getHint().toString());
            return false;
        } else if (selectList.get(0) == null) {
            Utils.showMsg(this, getString(R.string.营业执照));
            return false;
        } else if (selectList.get(1) == null) {
            Utils.showMsg(this, getString(R.string.店铺内景));
            return false;
        } else if (selectList.get(2) == null) {
            Utils.showMsg(this, getString(R.string.店铺门头));
            return false;
        } else if (selectList.get(3) == null) {
            Utils.showMsg(this, getString(R.string.收银台));
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LICENSE_IMG:
                    selectList.remove(0);
                    selectList.add(0, PictureSelector.obtainMultipleResult(data).get(0));
                    ivLicense.setImageBitmap(BitmapFactory.decodeFile(selectList.get(0).getCompressPath()));
                    break;
                case STORE_LAYOUT_IMG:
                    selectList.remove(1);
                    selectList.add(1, PictureSelector.obtainMultipleResult(data).get(0));
                    ivStoreLayout.setImageBitmap(BitmapFactory.decodeFile(selectList.get(1).getCompressPath()));
                    break;
                case GATE_IMG:
                    selectList.remove(2);
                    selectList.add(2, PictureSelector.obtainMultipleResult(data).get(0));
                    ivGate.setImageBitmap(BitmapFactory.decodeFile(selectList.get(2).getCompressPath()));
                    break;
                case CHECKSTAND_IMG:
                    selectList.remove(3);
                    selectList.add(3, PictureSelector.obtainMultipleResult(data).get(0));
                    ivCheckstand.setImageBitmap(BitmapFactory.decodeFile(selectList.get(3).getCompressPath()));
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

    private void uploadImages() {
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
                /*StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < imageUrlList.size(); i++) {
                    String s = imageUrlList.get(i);
                    if (i == 0) {
                        stringBuilder.append(s);
                    } else {
                        stringBuilder.append(",").append(s);
                    }
                }
                link = stringBuilder.toString();*/
                submit(imageUrlList);

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(ApplyQrcodePayActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit(List<String> imageUrlList) {
        ProgressDlgHelper.openDialog(this);
        mMyService.addApplyScanRecord(new AddApplyScanRecordRequest(dataManager.getToken(), imageUrlList.get(0), imageUrlList.get(1), imageUrlList.get(2), imageUrlList.get(3), mSn))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        Utils.showMsg(ApplyQrcodePayActivity.this, getString(R.string.上传成功));
                        finish();
                        startActivity(new Intent(ApplyQrcodePayActivity.this, ApplyQrcodePayRecordActivity.class));
                    }
                }, new ThrowableConsumer<Throwable>(this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.END)) {
                drawerLayout.closeDrawer(Gravity.END);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setSn(String sn) {
        mSn = sn;
        tvSn.setText(sn);
        drawerLayout.closeDrawer(Gravity.END);
    }
}
