package com.lckj.jycm.activity;

import com.lckj.jycm.Base.BaseActvity;

public class RealNameActivity extends BaseActvity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
   /* private static final int SELECTOR_FRONT_PHOTO = 1;
    private static final int SELECTOR_REVERSE_PHOTO = 2;
    @BindView(R.id.left_action)
    TextView leftAction;
    @BindView(R.id.custom_title)
    TextView customTitle;
    @BindView(R.id.right_action)
    TextView rightAction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_real_name)
    ClearEditText etRealName;
    @BindView(R.id.et_id_card)
    ClearEditText etIdCard;
    @BindView(R.id.rl_front_img)
    RelativeLayout rlFrontImg;
    @BindView(R.id.iv_front_photo)
    ImageView ivFrontPhoto;
    @BindView(R.id.rl_reverse_img)
    RelativeLayout rlReverseImg;
    @BindView(R.id.iv_reverse_photo)
    ImageView ivReversePhoto;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.btn_anew)
    Button btnAnew;
    private int count;
    @Inject
    FrontUserService mFrontUserService;
    @Inject
    DataManager dataManager;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String mCardFrontImg;
    private String mCardBackImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        MainApplication.getInstance().getInjectGraph().inject(this);
        ButterKnife.bind(this);
        initView();
        initEvents();
        initData();
    }

    @Override
    protected void initView() {
        toolBar.setBackgroundResource(R.color.yellow);
        customTitle.setText(getString(R.string.real_name));
        leftAction.setText("");
        selectList.add(new LocalMedia());
        selectList.add(new LocalMedia());
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        ProgressDlgHelper.openDialog(this);
        mFrontUserService.getUserExpandInfo(new TokenRequest(dataManager.getToken(), "getUserExpandInfo"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<UserExpandInfoBean>(this) {
                    @Override
                    public void onSuccessCall(UserExpandInfoBean response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.getData() != null) {
                            switch (response.getData().getStatus()) {
                                case 1:
                                    llStatus.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.icon_realname_audit);
                                    tvIdCard.setText(response.getData().getIdCard());
                                    tvRealName.setText(response.getData().getUserName());
                                    break;
                                case 2:
                                    llStatus.setVisibility(View.VISIBLE);
                                    tvIdCard.setText(response.getData().getIdCard());
                                    ivStatus.setImageResource(R.mipmap.icon_realname_tong);
                                    tvRealName.setText(response.getData().getUserName());
                                    break;
                                case 3:
                                    llStatus.setVisibility(View.VISIBLE);
                                    tvIdCard.setText(response.getData().getIdCard());
                                    btnAnew.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.icon_realname_error);
                                    tvRealName.setText(response.getData().getUserName());
                                    break;
                                default:
                                    llEdit.setVisibility(View.VISIBLE);
                                    break;
                            }
                        } else {
                            llEdit.setVisibility(View.VISIBLE);
                        }
                    }

                }, new ThrowableConsumer<Throwable>(this));
    }

    @OnClick({R.id.left_action, R.id.iv_front_photo, R.id.iv_reverse_photo, R.id.btn_submit, R.id.btn_anew})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_action:
                finish();
                break;
            case R.id.iv_front_photo:
                selectorImg(SELECTOR_FRONT_PHOTO);
                break;
            case R.id.iv_reverse_photo:
                selectorImg(SELECTOR_REVERSE_PHOTO);
                break;
            case R.id.btn_submit:
                if (checkEidt()) {
                    uploadImages();
                }
                break;
            case R.id.btn_anew:
                llEdit.setVisibility(View.VISIBLE);
                llStatus.setVisibility(View.GONE);
                break;
        }
    }

    private void uploadImages() {
        ProgressDlgHelper.openDialog(this);
        UploadImageToQnUtils uploadImageToQnUtils = new UploadImageToQnUtils(this);
        List<File> fileList = new ArrayList<>();
        List<String> pictureTypes = new ArrayList<>();
        for (LocalMedia localMedia : selectList) {
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
                if (count == 0) {
                    count++;
                    mCardFrontImg = imageUrlList.get(0);
                } else {
                    mCardBackImg = imageUrlList.get(0);
                }
                submit();

            }

            @Override
            public void onFailedcallBack() {
                ProgressDlgHelper.closeDialog();
                Utils.showMsg(RealNameActivity.this, getString(R.string.failed_to_upload_pictures));
            }
        });
    }

    private void submit() {
        String idCard = etIdCard.getText().toString().trim();
        String realName = etRealName.getText().toString().trim();
        mFrontUserService.addUserExpand(new AddUserExpandRequest(mCardBackImg, mCardFrontImg, idCard, dataManager.getToken(), realName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<HttpResponse>(this) {
                    @Override
                    public void onSuccessCall(HttpResponse response) {
                        ProgressDlgHelper.closeDialog();
                        if (response.isSuccess()) {
                            finish();
                        }
                        Utils.showMsg(RealNameActivity.this, response.getMsg());
                    }

                }, new ThrowableConsumer<Throwable>(this));
    }

    private boolean checkEidt() {
        if (TextUtils.isEmpty(etRealName.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_real_name));
            return false;
        } else if (TextUtils.isEmpty(etIdCard.getText().toString().trim())) {
            Utils.showMsg(this, getString(R.string.toast_id_card));
            return false;
        } else if (ivFrontPhoto.getDrawable() == null) {
            Utils.showMsg(this, getString(R.string.toast_front_photo));
            return false;
        } else if (ivReversePhoto.getDrawable() == null) {
            Utils.showMsg(this, getString(R.string.toast_reverse_photo));
            return false;
        }
        return true;
    }

    private void selectorImg(int requestCode) {
        PictureSelector.create(RealNameActivity.this)
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
                .enableCrop(true)
                .withAspectRatio(3, 2)
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                            .selectionMedia(selectList)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECTOR_FRONT_PHOTO:
                    List<LocalMedia> localMedia1 = PictureSelector.obtainMultipleResult(data);
                    if (localMedia1 != null && localMedia1.size() > 0) {
                        LocalMedia localMedia2 = localMedia1.get(0);
                        localMedia2.setPath(localMedia2.getCutPath());
                        selectList.add(0, localMedia2);
                        File file = new File(selectList.get(0).getPath());
                        if (file.exists()) {
                            Uri uri = Uri.fromFile(file);
                            ivFrontPhoto.setImageURI(uri);
                            rlFrontImg.setVisibility(View.GONE);
                        }
                    }
                    break;
                case SELECTOR_REVERSE_PHOTO:
                    List<LocalMedia> localMedia3 = PictureSelector.obtainMultipleResult(data);
                    if (localMedia3 != null && localMedia3.size() > 0) {
                        LocalMedia localMedia2 = localMedia3.get(0);
                        localMedia2.setPath(localMedia2.getCutPath());
                        selectList.add(1, localMedia2);
                        File file = new File(selectList.get(1).getPath());
                        if (file.exists()) {
                            Uri uri = Uri.fromFile(file);
                            ivReversePhoto.setImageURI(uri);
                            rlReverseImg.setVisibility(View.GONE);
                        }
                    }
                    break;
            }
        }
    }*/
}
