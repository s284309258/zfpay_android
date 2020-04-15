package com.lckj.jycm.network;

import com.lckj.framework.network.CommonBean;
import com.lckj.framework.network.HttpResponse;
import com.lcwl.qiniu.GetTokenBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FrontUserService {

    @GET("front/sendPictureCode")
    Observable<SendPictureCodeBean> sendPictureCode();

    @POST("front/sendValidateCode")
    Observable<HttpResponse> selllvalidateCode(@Body SelllValidateCodeRequest request);

    @POST("register")
    Observable<RegisterBean> register(@Body RegisterRequest request);

    @POST("login")
    Observable<LoginBean> login(@Body LoginRequest request);

    @POST("loginout")
    Observable<CommonBean> loginout(@Body TokenRequest request);

    @POST("updatePassword")
    Observable<HttpResponse> updatePassword(@Body UpdatePasswordRequest request);

    @POST("updatePersonal")
    Observable<HttpResponse> updatePersonal(@Body UpdatePersonalRequest request);

    @POST("frontWithdrawals")
    Observable<HttpResponse> frontWithdrawals(@Body FrontWithdrawalsRequest request);

    @POST("userBalanceDetails")
    Observable<UserBalanceDetailsBean> userBalanceDetails(@Body UserBalanceDetailsRequest request);

    @POST("getToken")
    Observable<GetTokenBean> getToken(@Body TokenRequest request);

    @POST("underNum")
    Observable<UnderNumBean> underNum(@Body TokenRequest request);

    @POST("addUserExpand")
    Observable<HttpResponse> addUserExpand(@Body AddUserExpandRequest request);

    @POST("addMerchantInfo")
    Observable<CommonBean> addMerchantInfo(@Body AddMerchantInfoRequest request);

    @POST("showAccountRecordList")
    Observable<AccountRecordListBean> showAccountRecordList(@Body AccountRecordListRequest request);

    @POST("getPersonInfo")
    Observable<PersonInfoBean> getPersonInfo(@Body TokenRequest request);

    @POST("showSignInfo")
    Observable<SignInfoBean> showSignInfo(@Body TokenRequest request);

    @POST("verifyNoteCode")
    Observable<HttpResponse> verifyNoteCode(@Body VerifyNoteCodeRequest request);

    @POST("getUserExpandInfo")
    Observable<UserExpandInfoBean> getUserExpandInfo(@Body TokenRequest request);

    @POST("getVersionInfo")
    Observable<VersionInfoBean> getVersionInfo(@Body VersionInfoRequest request);

    @POST("getTeamList")
    Observable<TeamListBean> getTeamList(@Body TeamListRequest request);
}
