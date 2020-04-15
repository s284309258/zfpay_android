package com.lcwl.qiniu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.framework.rxjava.BaseConsumer;
import com.lckj.framework.rxjava.ThrowableConsumer;
import com.lckj.framework.thread.ThreadPoolManager;
import com.lcwl.base.NetworkApplication;
import com.lython.network.model.Const;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;


/**
 * Created by xlt on 2016/03/18.
 */
public class UploadImageToQnUtils {
    private Context mContext;
    private List<String> imageUrlList = new ArrayList<>();
    private List<String > listFile = new ArrayList<String>();
    private Map<Integer, String> imageUrlMap = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 > o2 ? 1 : -1;
        }
    });
    private String mDomain = "http://pjkh5ako6.bkt.clouddn.com";
    @Inject
    QiNiuService mQiNiuService;
    private boolean mIsFailedcallBack = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public UploadImageToQnUtils(Context mContext) {
        NetworkApplication.getInjectGraphNetwork().inject(this);
        this.mContext = mContext;

    }

    /**
     * 上传图片默认方法 2016.4.27 added by wp.nine
     */
    public void uploadImage(String biz, List<File> files) {
        /*String xid = User.getIns(mContext).getXid();
        uploadImage(biz, files, xid, null);*/
    }

    /**
     * 上传图片默认方法 2016.4.27 added by wp.nine
     */
    public void uploadImage(String biz, File file, String json) {
        /*String xid = User.getIns(mContext).getXid();
        ArrayList<File> files = new ArrayList<>();
        files.add(file);
        uploadImage(biz, files, xid, json, null);*/
    }


    public void uploadImage( List<String> pictureTypes,String biz, List<File> fileList, String xid, String isCallBack) {
        uploadImage(pictureTypes,biz, fileList, xid, null, isCallBack);
    }

    /**
     * */
    public void uploadImage(List<String> pictureTypes,String biz, List<File> fileList, String xid, String json, String isCallBack) {
        if (TextUtils.isEmpty(xid)) {
//            getUploadImageUrlNoXid(biz, fileList, json, isCallBack);
        } else if (biz.equals(Const.UPLOAD_TOKEN_BIZ_VIDEO)) {
//            getUploadImageUrlWithVideo(biz, fileList, xid, json, isCallBack);
            getImgUploadTokenAndDomain(pictureTypes,biz, fileList, xid, json, isCallBack);
        } else {
            getImgUploadTokenAndDomain(pictureTypes,biz, fileList, xid, json, isCallBack);
        }

    }


    /***
     * 由于可视对讲需要传递json值，当前的okhttp械架不支持get请求里面放 json数据格式（当前框架存在bug），
     * 所以用post请求发送，但后台不支持传json格式，所以可视对讲图片上传这块特殊处理
     * todo:升级okhttp框架解决该问题 2016.5.5 by wp.nine
     */
    /*private void getUploadImageUrlWithVideo(String biz, final List<File> fileList, String xid, String json, String isCallBack) {
        final String url = ConfigManager.getHostAppCommon() + "/qiniu/getImgUploadToken";
        final List<SecurityUtils.KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new SecurityUtils.KeyValue("biz", biz));
        keyValues.add(new SecurityUtils.KeyValue("json", json));
        keyValues.add(new SecurityUtils.KeyValue("xid", ProviderModule.getDataManager(mContext).getXid()));
        ThreadPoolManager.getInstance().start(new Runnable(){
            @Override
            public void run() {
                try {
                    String result = com.oecommunity.oepay.utils.Utils.httpPost(App.getAppContext(), url, keyValues);
                    Gson gson = new Gson();
                    BaseResponse<String> response = gson.fromJson(result, new TypeToken<BaseResponse<String>>() {
                    }.getType());
                    if (response != null && response.getCode().equals(Const.API_CODE_SUCCESS)) {
                        uploadImageToQN(response.getData(), fileList);
                    } else {
                        if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                            mIsFailedcallBack = true;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mQiniuTokenListener.onFailedcallBack();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                        mIsFailedcallBack = true;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mQiniuTokenListener.onFailedcallBack();
                            }
                        });
                    }
                }
            }
        });
    }*/


    private void getUploadImageUrlNoXid(String biz, final List<File> fileList, String json, String isCallBack) {                   //服务器获取上传图片到七牛的token(不需要xid)
        /*UploadRequest re = new UploadRequest();
        re.biz = biz;
        re.json = json;
        if (!TextUtils.isEmpty(isCallBack)) {
            re.setIsCallBack(isCallBack);
        }
        mQiNiuService.getImgUploadTokenNoXid(Utils.convertObjectToMap(re))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseAction<BaseResponse<String>>(mContext) {
                    @Override
                    public void onSuccessedCall(BaseResponse<String> response) {
                        uploadImageToQN(response.getData(), fileList);
                    }

                    @Override
                    public void onFailedCall(BaseResponse<String> response) {
                        if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                            mIsFailedcallBack = true;
                            mQiniuTokenListener.onFailedcallBack();
                        }
                    }

                }, new ThrowableAction(mContext) {
                    @Override
                    public void onError(Throwable throwable) {
                        if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                            mIsFailedcallBack = true;
                            mQiniuTokenListener.onFailedcallBack();
                        }
                    }
                });*/
    }


    private void getImgUploadTokenAndDomain(final List<String> pictureTypes, String biz, final List<File> fileList, String xid, String json, String isCallBack) {                   //获取七牛token 和七牛永久域名，需要传xid
        UploadRequest re = new UploadRequest();
        re.biz = biz;
        re.json = json;
        re.setXid(ProviderModule.getDataManager(mContext).getToken());
        if (!TextUtils.isEmpty(isCallBack)) {
            re.setIsCallBack(isCallBack);
        }
        mQiNiuService.getToken(new GetTokenRequest(ProviderModule.getDataManager(mContext).getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseConsumer<GetTokenBean>(mContext) {
                    @Override
                    public void onSuccessCall(GetTokenBean response) {
                        uploadImageToQN(pictureTypes,response.getData().getQiniu_token(), fileList);
                        mDomain = response.getData().getQiniu_domain();
                        if (mDomain!=null&&mDomain.endsWith("/"))mDomain = mDomain.substring(0,mDomain.length()-1);
                    }

                    @Override
                    public void onFailedCall(GetTokenBean response) {
                        if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                            mIsFailedcallBack = true;
                            mQiniuTokenListener.onFailedcallBack();
                        }
                    }

                }, new ThrowableConsumer(mContext) {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                            mIsFailedcallBack = true;
                            mQiniuTokenListener.onFailedcallBack();
                        }
                    }
                });
    }

    private void uploadImageToQN(final List<String> pictureTypes, final String token, final List<File> fileList) {         //上传图片到七牛，获取hash
        ThreadPoolManager.getInstance().start(new Runnable(){     //压缩图片放在图片异步线程当中 2016.11.17 by wp.nine
            @Override
            public void run() {
                final List<File> reallyAllFile = new ArrayList<>();
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    boolean donotCompress = false;
                    if (pictureTypes!=null&&i<pictureTypes.size()){
                        if (pictureTypes.get(i).contains("gif")||pictureTypes.get(i).contains("png")){
                            donotCompress = true;
                        }
                    }
                    if (donotCompress||isBitmapEmpty(file.toString())) {
                        reallyAllFile.add(file);
                    } else {
                        try {
                            List<File> files = Luban.with(NetworkApplication.getInstance().getApplicationContext()).load(file).get();
                            if (files!=null&&files.size()>0){
                                reallyAllFile.addAll(files);
                            }else{
                                if(mQiniuTokenListener != null){//说明图片有问题，直接提示
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mQiniuTokenListener.onFailedcallBack();
                                        }
                                    });
                                    return;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                imageUrlMap.clear();
                listFile.clear();
                for (int i = 0; i < reallyAllFile.size(); i++) {
                    uploadImgSigle(reallyAllFile.get(i), token, i, reallyAllFile.size());
                }
            }
        });
    }

    private boolean isBitmapEmpty(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        if (h/w>3)return true;
        boolean isEmpty = true;
        if (w == 0 ||w == -1 ||h == -14
                || h == 0) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return isEmpty ;
    }

    private void uploadImgSigle(final File file, String token, final int pos, final int size) {
        new UploadManager().put(file, null, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        String url = "";
                        try {
                            JSONTokener jsonParser = new JSONTokener(res.toString());
                            JSONObject person = (JSONObject) jsonParser.nextValue();
                            url = person.getString("hash");
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                                mIsFailedcallBack = true;
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mQiniuTokenListener.onFailedcallBack();
                                    }
                                });

                            }
                        }
                        if (!TextUtils.isEmpty(url)) {
                            imageUrlMap.put(pos, url);
                            listFile.add(file.getAbsolutePath());
                        } else {
                            if (mQiniuTokenListener != null && !mIsFailedcallBack) {
                                mIsFailedcallBack = true;
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mQiniuTokenListener.onFailedcallBack();
                                    }
                                });
                            }
                            return;//结束掉后面的操作 2016.5.6 by wp.nine
                        }
                        if (imageUrlMap.size() == size && mQiniuTokenListener != null) {
                            for (String value : imageUrlMap.values()) {
                                imageUrlList.add(value);
                            }

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mQiniuTokenListener.onSuccessedcallBack(imageUrlList, listFile);
                                }
                            });
                        }
                    }
                }, null);
    }

    QiniuTokenListener mQiniuTokenListener = null;

    public UploadImageToQnUtils setPicCallback(QiniuTokenListener qiniuTokenListener) {
        this.mQiniuTokenListener = qiniuTokenListener;
        return this;
    }

    public static interface QiniuTokenListener {
        public void onSuccessedcallBack(List<String> imageUrlList, List<String> fileList);

        public void onFailedcallBack();
    }

    private Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//        float hh = 800f;//这里设置高度为800f
//        float ww = 480f;//这里设置宽度为480f
//        float ww = Utils.getWidth(mContext);//这里设置宽度为480f
//        float hh = Utils.getsHeight(mContext);//这里设置高度为800f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        /*if(ww > 720){
            ww = 720;
        }*/
        /*if(hh > 1920){
            hh = 1920;
        }*/

        /*if(w > h){
            float temp = hh;
            hh = ww;
            ww = temp;
        }*/

        /*int be = Math.max((int) (newOpts.outWidth / ww), (int) (newOpts.outHeight / hh));
        if (be <= 0)
            be = 1;*/


        int be= w/360;
        newOpts.inSampleSize = be;//设置缩放比例

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    private File compressImage(Bitmap image) {           //压缩图片小于200k
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream output = null;
        BufferedOutputStream bufferedOutput = null;
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length*1.0 / 1024 > 200) {
            options -= 10;
            if (options <= 0) {
                options = 1;
            }
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        String savePath = Const.getCacheImageDir();
        String filepath = "";
        File file = null;
        try {
            File path = new File(savePath);
            filepath = savePath + System.currentTimeMillis() + ".png";
            file = new File(filepath);
            if (!path.exists()) {
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            output = new FileOutputStream(file);
            bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(baos.toByteArray());
            bufferedOutput.close();
            output.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutput != null) {
                    bufferedOutput.close();
                }
                if (output != null) {
                    output.close();
                }
                if (baos != null) {
                    baos.close();
                }
                image.recycle();
                bufferedOutput = null;
                output = null;
                baos = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
