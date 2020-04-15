package com.lckj.jycm.network;

import com.lckj.framework.network.CommonBean;
import com.lckj.framework.network.HttpResponse;
import com.lcwl.qiniu.GetTokenRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InfoService {
    @POST("getArticleByURL")
    Observable<ArticleByURLResponse> getArticleByURL(@Body ArticleByURLRequest request);
    @POST("createAdvArticle")
    Observable<CommonBean> createAdvArticle(@Body CreateAdvArticleRequest request);
    @POST("showAdvArticleList")
    Observable<AdvArticleListBean> showAdvArticleList(@Body AdvArticleListRequest request);
    @POST("createAdvInfoToApp")
    Observable<CreateAdvInfoBean> createAdvInfoToApp(@Body CreateAdvInfoRequest request);
    @POST("showAppAdvInfoList")
    Observable<AdvInfoListBean> showAppAdvInfoList(@Body AdvInfoListRequest request);
    @POST("getMerchantInfo")
    Observable<MerchantInfoBean> getMerchantInfo(@Body SimpleRequest request);
    /*21、查询商户列表-广告地图*/
    @POST("queryMerchantList")
    Observable<MerchantListBean> queryMerchantList(@Body MerchantListRequest request);
    @POST("shareAdvInfo")
    Observable<CreateAdvInfoBean> shareAdvInfo(@Body ShareAdvInfoRequest request);
    @POST("showHomePage")
    Observable<HomePageBean> showHomePage(@Body TokenRequest request);
}
