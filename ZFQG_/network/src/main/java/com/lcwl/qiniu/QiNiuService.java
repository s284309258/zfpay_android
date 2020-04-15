package com.lcwl.qiniu;



import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xlt on 2016/03/18.
 */
public interface QiNiuService {
    /*
    * 获取七牛token,不传xid，不回调
    * */
    /*@GET("getImgUploadTokenNoXid")
    Observable<BaseResponse<String>> getImgUploadTokenNoXid(@QueryMap Map<String, Object> params);*/

    /*
    * 获取七牛token和七牛永久域名
    * */
    /*@GET("getToken")
    Observable<BaseResponse<QiNiuResponse>> getToken(@QueryMap Map<String, Object> params);*/

    @POST("common/qiniu/getQiNiuToken")
    Observable<GetTokenBean> getToken(@Body GetTokenRequest request);


    /*
    * 获取七牛token和七牛永久域名
    * */
    /*@POST("getImgUploadToken")
    Observable<BaseResponse<String>> getImgUploadToken(@QueryMap Map<String, Object> params);*/
}
