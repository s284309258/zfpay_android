package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;
import com.lckj.jycm.network.TokenRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyService {
    //1、图形验证码接口（MD5验签方式）
    @POST("common/imgCode/createImgCode")
    Observable<CreateImgCodeBean> createImgCode(@Body CreateImgCodeRequest request);

    //2、发送短信验证码接口（一：RSA加密方式：注册和忘记密码）
    @POST("common/smsCode/sendSmsCodeOnly")
    Observable<HttpResponse> sendSmsCodeOnly(@Body SendSmsCodeOnlyRequest request);

    //3、发送短信验证码接口（二：RSA加密方式+token验证）
    @POST("common/smsCode/sendSmsCodeToken")
    Observable<HttpResponse> sendSmsCodeToken(@Body SendSmsCodeTokenRequest request);

    //4、用户注册接口（RSA加密方式）====》注册
    @POST("user/login/userRegister")
    Observable<UserRegisterBean> userRegister(@Body UserRegisterRequest request);

    //5、用户登录接口（RSA加密方式）======》登录
    @POST("user/login/userLogin")
    Observable<UserLoginBean> userLogin(@Body UserLoginRequest request);

    //6、用户退出登录接口（MD5验签方式）=====》登录
    @POST("user/login/userLogOut")
    Observable<HttpResponse> userLogOut(@Body TokenRequest request);

    //7、用户找回密码接口（RSA加密方式）=====》忘记密码-1
    @POST("user/login/userForgetPass")
    Observable<HttpResponse> userForgetPass(@Body UserForgetPassRequest request);

    //8、用户修改交易密码接口（RSA加密方式）=====》修改交易密码
    @POST("user/info/modifyPayPass")
    Observable<HttpResponse> modifyPayPass(@Body ModifyPayPassRequest request);

    //9、用户修改登录密码接口（RSA加密方式）======》修改登录密码
    @POST("user/info/modifyLoginPass")
    Observable<HttpResponse> modifyLoginPass(@Body ModifyLoginPassRequest request);

    //10、用户修改头像接口(MD5验签方式）=======>设置
    @POST("user/info/modifyUserInfo")
    Observable<HttpResponse> modifyUserInfo(@Body ModifyUserInfoRequest request);

    //12、查询系统最新版本接口(MD5验签方式）
    @POST("sys/version/getNewVersion")
    Observable<GetNewVersionBean> getNewVersion(@Body GetNewVersionRequest request);

    //13、添加意见反馈接口 (MD5验签方式）=======>保留
    @POST("user/feedBack/addUserFeedBack")
    Observable<HttpResponse> addUserFeedBack(@Body AddUserFeedBackRequest request);

    //14、查询意见反馈列表 (MD5验签方式）=======>保留
    @POST("user/feedBack/getUserFeedBackList")
    Observable<GetUserFeedBackListBean> getUserFeedBackList(@Body LastIdRequest request);

    //15、查询用户实时最新信息接口 (MD5验签方式）
    @POST("user/info/getUserNewInfo")
    Observable<GetUserNewInfoBean> getUserNewInfo(@Body TokenRequest request);

    //16、查询APP图片列表 (MD5验签方式）
    @POST("sys/appImg/getAppImgList")
    Observable<GetAppImgListBean> getAppImgList(@Body GetAppImgListRequest request);

    //17、用户修改手机号接第一步口（RSA加密方式）=======>修改手机
    @POST("user/info/modifyTelFirst")
    Observable<ModifyTelFirstBean> modifyTelFirst(@Body ModifyTelFirstRequest request);

    //18、用户修改手机号接第二步口（RSA加密方式）=======>修改手机
    @POST("user/info/modifyTelSecond")
    Observable<HttpResponse> modifyTelSecond(@Body ModifyTelSecondRequest request);

    //19、查询用户实名认证信息状态 (MD5验签方式）=======>实名认证
    @POST("user/info/getUserAuthStatus")
    Observable<GetUserAuthStatusBean> getUserAuthStatus(@Body TokenRequest request);

    //20、用户提交实名认证资料信息 (MD5验签方式）=======>实名认证
    @POST("user/info/submitUserAuthInfo")
    Observable<HttpResponse> submitUserAuthInfo(@Body SubmitUserAuthInfoRequest request);

    //21、查询用户结算卡列表 (MD5验签方式）=======>结算卡中心
    @POST("user/cardInfo/getUserCardList")
    Observable<GetUserCardListBean> getUserCardList(@Body TokenRequest request);

    //22、模糊匹配银行信息列表 (MD5验签方式）=======>添加结算卡
    @POST("sys/bankInfo/searchLikeBank")
    Observable<SearchLikeBankBean> searchLikeBank(@Body TokenRequest request);

    //23、设置用户结算卡（RSA加密方式）=======>添加结算卡
    @POST("user/cardInfo/updateUserCard")
    Observable<UpdateUserCardBean> updateUserCard(@Body UpdateUserCardRequest request);

    //24、查询提现基本信息 (MD5验签方式）=======>提现
    @POST("user/cashRecord/getCashInfo")
    Observable<GetCashInfoBean> getCashInfo(@Body TokenRequest request);

    //25、用户申请提现（RSA加密方式）=======>提现
    @POST("user/cashRecord/applyCash")
    Observable<HttpResponse> applyCash(@Body ApplyCashRequest request);

    //26、查询提现记录列表 (MD5验签方式）=======>提现记录
    @POST("user/cashRecord/getCashRecordList")
    Observable<GetCashRecordListBean> getCashRecordList(@Body LastIdRequest request);

    //27、首页 - 查询最新公告 (MD5验签方式）
    @POST("sys/notice/getNewNotice")
    Observable<GetNewNoticeBean> getNewNotice(@Body TokenRequest request);

    //28、首页 - 查询最新资讯 (MD5验签方式）
    @POST("sys/news/getNewNews")
    Observable<GetNewNewsBean> getNewNews(@Body TokenRequest request);

    //29、首页 - 查询资讯详情 (MD5验签方式）
    @POST("sys/news/getNewsDetail")
    Observable<GetNewsDetailBean> getNewsDetail(@Body GetNewsDetailRequest request);

    //30、传统POS - 申请扫码支付 - 查询传统POS列表 (MD5验签方式）
    @POST("sys/traditionalpos/getScanTraditionalPosList")
    Observable<GetScanTraditionalPosListBean> getScanTraditionalPosList(@Body TokenRequest request);

    //31、传统POS - 申请扫码支付 - 提交申请记录 (MD5验签方式）
    @POST("sys/traditionalpos/addApplyScanRecord")
    Observable<HttpResponse> addApplyScanRecord(@Body AddApplyScanRecordRequest request);

    //32、传统POS - 申请扫码支付 - 查询记录列表 (MD5验签方式）
    @POST("sys/traditionalpos/getApplyScanRecordList")
    Observable<GetApplyScanRecordListBean> getApplyScanRecordList(@Body TokenRequest request);

    //33、MPOS - 查询MPOS列表 (MD5验签方式）
    @POST("sys/mpos/getMposList")
    Observable<GetMposListBean> getMposList(@Body TokenRequest request);

    //34、MPOS - 修改商户信息 (MD5验签方式）
    @POST("sys/mpos/editMposMerInfo")
    Observable<HttpResponse> editMposMerInfo(@Body EditMposMerInfoRequest request);

    //35、线上活动 - 查询传统POS活动列表 (MD5验签方式）
    @POST("sys/onlineactivity/getTraditionalPosOnlineActivityList")
    Observable<GetMposOnlineActivityListBean> getTraditionalPosOnlineActivityList(@Body TokenRequest request);

    //36、线上活动 - 查询MPOS活动列表 (MD5验签方式）
    @POST("sys/onlineactivity/getMposOnlineActivityList")
    Observable<GetMposOnlineActivityListBean> getMposOnlineActivityList(@Body TokenRequest request);

    //37、线上活动 - 查询传统POS活动奖励列表 (MD5验签方式）
    @POST("sys/onlineactivity/getTraditionalPosRewardRecordList")
    Observable<GetMposRewardRecordListBean> getTraditionalPosRewardRecordList(@Body LastIdRequest request);

    //38、线上活动 - 查询MPOS活动奖励列表 (MD5验签方式）
    @POST("sys/onlineactivity/getMposRewardRecordList")
    Observable<GetMposRewardRecordListBean> getMposRewardRecordList(@Body LastIdRequest request);

    //39、线上活动 - 查询用户活动申请列表（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/getTraditionalPosActivityApplyList")
    Observable<GetTraditionalPosActivityApplyListBean> getTraditionalPosActivityApplyList(@Body LastIdRequest request);

    //40、线上活动 - 查询用户活动申请列表（MPOS） (MD5验签方式）
    @POST("sys/onlineactivity/getMposActivityApplyList")
    Observable<GetTraditionalPosActivityApplyListBean> getMposActivityApplyList(@Body LastIdRequest request);

    //41、线上活动 - 查询用户活动申请详情（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/getTraditionalPosActivityApplyDetail")
    Observable<GetPosActivityApplyListDetailBean> getTraditionalPosActivityApplyDetail(@Body GetPosActivityApplyListDetailRequest request);

    //42、线上活动 - 查询用户活动申请详情（传统MPOS） (MD5验签方式）
    @POST("sys/onlineactivity/getMposActivityApplyDetail")
    Observable<GetPosActivityApplyListDetailBean> getMposActivityApplyDetail(@Body GetPosActivityApplyListDetailRequest request);

    //43、线上活动 - 查询活动详情（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/getTraditionalPosOnlineActivityDetail")
    Observable<GetTraditionalPosOnlineActivityDetailBean> getTraditionalPosOnlineActivityDetail(@Body GetTraditionalPosOnlineActivityDetailRequest request);

    //44、线上活动 - 查询活动详情（MPOS） (MD5验签方式）
    @POST("sys/onlineactivity/getMposOnlineActivityDetail")
    Observable<GetTraditionalPosOnlineActivityDetailBean> getMposOnlineActivityDetail(@Body GetTraditionalPosOnlineActivityDetailRequest request);

    //45、线上活动 - 查询参与活动选择信息（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/getTraditionalPosPartActivityInfo")
    Observable<GetTraditionalPosPartActivityInfoBean> getTraditionalPosPartActivityInfo(@Body GetTraditionalPosOnlineActivityDetailRequest request);

    //46、线上活动 - 查询参与活动选择信息（MPOS） (MD5验签方式）
    @POST("sys/onlineactivity/getMposPartActivityInfo")
    Observable<GetTraditionalPosPartActivityInfoBean> getMposPartActivityInfo(@Body GetTraditionalPosOnlineActivityDetailRequest request);

    //47、线上活动 - 提交活动申请（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/submitTraditionalPosActivityApply")
    Observable<HttpResponse> submitTraditionalPosActivityApply(@Body SubmitTraditionalPosActivityApplyRequest request);

    //48、线上活动 - 提交活动申请（MPOS） (MD5验签方式）
    @POST("sys/onlineactivity/submitMposActivityApply")
    Observable<HttpResponse> submitMposActivityApply(@Body SubmitTraditionalPosActivityApplyRequest request);

    //49、线上活动 - 取消活动申请（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/cancelTraditionalPosActivityApply")
    Observable<HttpResponse> cancelTraditionalPosActivityApply(@Body GetPosActivityApplyListDetailRequest request);

    //50、线上活动 - 取消活动申请（传统POS） (MD5验签方式）
    @POST("sys/onlineactivity/cancelMposActivityApply")
    Observable<HttpResponse> cancelMposActivityApply(@Body GetPosActivityApplyListDetailRequest request);

    //51、机具管理 - 获取待分配列表（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getTraditionalPosAllocationList")
    Observable<GetTraditionalPosAllocationListBean> getTraditionalPosAllocationList(@Body GetTraditionalPosAllocationListRequest request);

    //52、机具管理 - 获取待分配列表（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getMposAllocationList")
    Observable<GetMposAllocationListBean> getMposAllocationList(@Body GetTraditionalPosAllocationListRequest request);

    //53、机具管理 - 获取待分配列表（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/getTrafficCardAllocationList")
    Observable<GetTrafficCardAllocationListBean> getTrafficCardAllocationList(@Body GetTraditionalPosAllocationListRequest request);

    //54、机具管理 - 获取待召回列表（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getTraditionalPosRecallList")
    Observable<GetTraditionalPosRecallListBean> getTraditionalPosRecallList(@Body GetTraditionalPosAllocationListRequest request);

    //55、机具管理 - 获取待召回列表（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getMposRecallList")
    Observable<GetMposRecallListBean> getMposRecallList(@Body GetTraditionalPosAllocationListRequest request);

    //56、机具管理 - 获取待召回列表（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/getTrafficCardRecallList")
    Observable<GetTrafficCardRecallListBean> getTrafficCardRecallList(@Body GetTraditionalPosAllocationListRequest request);

    //57、机具管理 - 查询直推代理 (MD5验签方式）
    @POST("sys/machinesmanage/getRefererAgency")
    Observable<GetRefererAgencyBean> getRefererAgency(@Body TokenRequest request);

    //58、机具管理 - 查询系统配置参数（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getTraditionalPosSysParamRateList")
    Observable<GetTraditionalPosSysParamRateListBean> getTraditionalPosSysParamRateList(@Body SnRequest request);

    //59、机具管理 - 查询系统配置参数（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getMposSysParamRateList")
    Observable<GetTraditionalPosSysParamRateListBean> getMposSysParamRateList(@Body SnRequest request);

    //60、机具管理 - 分配（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/allocationTraditionalPos")
    Observable<HttpResponse> allocationTraditionalPos(@Body AllocationTraditionalPosRequest request);

    //61、机具管理 - 召回（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/recallTraditionalPos")
    Observable<HttpResponse> recallTraditionalPos(@Body SnListRequest request);

    //62、机具管理 - 解绑（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/unbindTraditionalPos")
    Observable<HttpResponse> unbindTraditionalPos(@Body SnRequest request);

    //63、机具管理 - 分配（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/allocationMpos")
    Observable<HttpResponse> allocationMpos(@Body AllocationTraditionalPosRequest request);

    //64、机具管理 - 召回（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/recallMpos")
    Observable<HttpResponse> recallMpos(@Body SnListRequest request);

    //65、机具管理 - 解绑（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/unbindMpos")
    Observable<HttpResponse> unbindMpos(@Body SnRequest request);

    //66、机具管理 - 分配（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/allocationTrafficCard")
    Observable<HttpResponse> allocationTrafficCard(@Body AllocationTrafficCardRequest request);

    //67、机具管理 - 召回（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/recallTrafficCard")
    Observable<HttpResponse> recallTrafficCard(@Body RecallTrafficCardRequest request);

    //68、机具管理 - 查询解绑记录（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getTraditionalPosUnbindRecordList")
    Observable<GetTraditionalPosUnbindRecordListBean> getTraditionalPosUnbindRecordList(@Body TokenRequest request);

    //69、机具管理 - 查询解绑记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getMposUnbindRecordList")
    Observable<GetTraditionalPosUnbindRecordListBean> getMposUnbindRecordList(@Body TokenRequest request);

    //70、机具管理 - 查询分配记录（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getAllocationTraditionalPosList")
    Observable<GetAllocationTraditionalPosListBean> getAllocationTraditionalPosList(@Body TokenRequest request);

    //71、机具管理 - 查询分配详情（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getAllocationTraditionalPosDetail")
    Observable<GetAllocationTraditionalPosDetailBean> getAllocationTraditionalPosDetail(@Body GetAllocationTraditionalPosDetailRequest request);

    //72、机具管理 - 修改分配记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/editAllocationTraditionalPos")
    Observable<HttpResponse> editAllocationTraditionalPos(@Body EditAllocationTraditionalPosRequest request);

    //73、机具管理 - 查询分配记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getAllocationMposList")
    Observable<GetAllocationTraditionalPosListBean> getAllocationMposList(@Body TokenRequest request);

    //74、机具管理 - 查询分配详情（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getAllocationMposDetail")
    Observable<GetAllocationTraditionalPosDetailBean> getAllocationMposDetail(@Body GetAllocationTraditionalPosDetailRequest request);

    //75、机具管理 - 修改分配记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/editAllocationMpos")
    Observable<HttpResponse> editAllocationMpos(@Body EditAllocationTraditionalPosRequest request);

    //76、机具管理 - 查询分配记录（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/getAllocationTrafficCardList")
    Observable<GetAllocationTraditionalPosListBean> getAllocationTrafficCardList(@Body TokenRequest request);

    //77、机具管理 - 查询召回记录（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/getRecallTraditionalPosList")
    Observable<GetRecallTraditionalPosListBean> getRecallTraditionalPosList(@Body StatusRequest request);

    //78、机具管理 - 处理召回记录（传统POS） (MD5验签方式）
    @POST("sys/machinesmanage/dealRecallTraditionalPos")
    Observable<HttpResponse> dealRecallTraditionalPos(@Body StatusRequest request);

    //79、机具管理 - 查询召回记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getRecallMposList")
    Observable<GetRecallTraditionalPosListBean> getRecallMposList(@Body StatusRequest request);

    //80、机具管理 - 处理召回记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/dealRecallMpos")
    Observable<HttpResponse> dealRecallMpos(@Body StatusRequest request);

    //81、机具管理 - 查询召回记录（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/getRecallTrafficCardList")
    Observable<GetRecallTraditionalPosListBean> getRecallTrafficCardList(@Body StatusRequest request);

    //82、机具管理 - 处理召回记录（流量卡） (MD5验签方式）
    @POST("sys/machinesmanage/dealRecallTrafficCard")
    Observable<HttpResponse> dealRecallTrafficCard(@Body StatusRequest request);

    //83、费率申请 - 查询POS机列表（传统POS） (MD5验签方式）
    @POST("sys/creditcardratesapply/getApplyRateTraditionalPosList")
    Observable<GetApplyRateTraditionalPosListBean> getApplyRateTraditionalPosList(@Body GetTraditionalPosAllocationListRequest request);

    //84、费率申请 - 查询POS机列表（MPOS） (MD5验签方式）
    @POST("sys/creditcardratesapply/getApplyRateMposList")
    Observable<GetApplyRateTraditionalPosListBean> getApplyRateMposList(@Body GetTraditionalPosAllocationListRequest request);

    //85、费率申请 - 查询刷卡费率列表 (MD5验签方式）
    @POST("sys/creditcardratesapply/getCreditCardRateList")
    Observable<GetCreditCardRateListBean> getCreditCardRateList(@Body TokenRequest request);

    //86、费率申请 - 申请刷卡费率（传统POS） (MD5验签方式）
    @POST("sys/creditcardratesapply/addApplyRateTraditionalPos")
    Observable<HttpResponse> addApplyRateTraditionalPos(@Body GetApplyRateTraditionalPosRequest request);

    //87、费率申请 - 申请刷卡费率（MPOS） (MD5验签方式）
    @POST("sys/creditcardratesapply/addApplyRateMpos")
    Observable<HttpResponse> addApplyRateMpos(@Body GetApplyRateTraditionalPosRequest request);

    //88、费率申请 - 查询费率申请列表（传统POS） (MD5验签方式）
    @POST("sys/creditcardratesapply/getApplyRateTraditionalPosRecordList")
    Observable<GetApplyRateTraditionalPosRecordListBean> getApplyRateTraditionalPosRecordList(@Body TokenRequest request);

    //89、费率申请 - 查询费率申请列表（MPOS） (MD5验签方式）
    @POST("sys/creditcardratesapply/getApplyRateMposRecordList")
    Observable<GetApplyRateTraditionalPosRecordListBean> getApplyRateMposRecordList(@Body TokenRequest request);

    //90、商户查询 - 直营商户汇总（传统POS） (MD5验签方式）
    @POST("sys/merchant/getSummaryTraditionalPosList")
    Observable<GetSummaryTraditionalPosListBean> getSummaryTraditionalPosList(@Body TokenRequest request);

    //91、商户查询 - 直营商户汇总（MPOS） (MD5验签方式）
    @POST("sys/merchant/getSummaryMposList")
    Observable<GetSummaryTraditionalPosListBean> getSummaryMposList(@Body TokenRequest request);

    //92、商户查询 - 全部商户列表（传统POS） (MD5验签方式）
    @POST("sys/merchant/getAllMerchantTraditionalPosList")
    Observable<GetAllMerchantTraditionalPosListBean> getAllMerchantTraditionalPosList(@Body TokenRequest request);

    //93、商户查询 - 优质商户列表（传统POS） (MD5验签方式）
    @POST("sys/merchant/getExcellentMerchantTraditionalPosList")
    Observable<GetAllMerchantTraditionalPosListBean> getExcellentMerchantTraditionalPosList(@Body TokenRequest request);

    //94、商户查询 - 活跃商户列表（传统POS） (MD5验签方式）
    @POST("sys/merchant/getActiveMerchantTraditionalPosList")
    Observable<GetAllMerchantTraditionalPosListBean> getActiveMerchantTraditionalPosList(@Body TokenRequest request);

    //95、商户查询 - 休眠商户列表（传统POS） (MD5验签方式）
    @POST("sys/merchant/getDormantMerchantTraditionalPosList")
    Observable<GetAllMerchantTraditionalPosListBean> getDormantMerchantTraditionalPosList(@Body TokenRequest request);

    //96、商户查询 - 全部商户列表（MPOS） (MD5验签方式）
    @POST("sys/merchant/getAllMerchantMposList")
    Observable<GetAllMerchantTraditionalPosListBean> getAllMerchantMposList(@Body TokenRequest request);

    //97、商户查询 - 优质商户列表（MPOS） (MD5验签方式）
    @POST("sys/merchant/getExcellentMerchantMposList")
    Observable<GetAllMerchantTraditionalPosListBean> getExcellentMerchantMposList(@Body TokenRequest request);

    //98、商户查询 - 活跃商户列表（MPOS） (MD5验签方式）
    @POST("sys/merchant/getActiveMerchantMposList")
    Observable<GetAllMerchantTraditionalPosListBean> getActiveMerchantMposList(@Body TokenRequest request);

    //99、商户查询 - 休眠商户列表（MPOS） (MD5验签方式）
    @POST("sys/merchant/getDormantMerchantMposList")
    Observable<GetAllMerchantTraditionalPosListBean> getDormantMerchantMposList(@Body TokenRequest request);

    //100、商户查询 - 查询商户详情（传统POS） (MD5验签方式）
    @POST("sys/merchant/getTraditionalPosDetail")
    Observable<GetTraditionalPosDetailBean> getTraditionalPosDetail(@Body SnRequest request);

    //101、商户查询 - 查询商户详情（MPOS） (MD5验签方式）
    @POST("sys/merchant/getMposDetail")
    Observable<GetTraditionalPosDetailBean> getMposDetail(@Body SnRequest request);

    //102、商户查询 - 查询代理列表 (MD5验签方式）
    @POST("sys/merchant/getReferAgencyList")
    Observable<GetReferAgencyListBean> getReferAgencyList(@Body TokenRequest request);

    //103、商户查询 - 查询代理人数 (MD5验签方式）
    @POST("sys/merchant/getReferAgencyNum")
    Observable<GetReferAgencyNumBean> getReferAgencyNum(@Body TokenRequest request);

    //104、商户查询 - 查询代理详情头部（传统POS） (MD5验签方式）
    @POST("sys/merchant/getReferAgencyHeadTraditionalPosInfo")
    Observable<GetReferAgencyHeadTraditionalPosInfoBean> getReferAgencyHeadTraditionalPosInfo(@Body UserRequest request);

    //105、商户查询 - 查询代理商户列表（传统POS） (MD5验签方式）
    @POST("sys/merchant/getReferAgencyTraditionalPosList")
    Observable<GetReferAgencyTraditionalPosListBean> getReferAgencyTraditionalPosList(@Body UserRequest request);

    //106、商户查询 - 查询代理详情头部（MPOS） (MD5验签方式）
    @POST("sys/merchant/getReferAgencyHeadMposInfo")
    Observable<GetReferAgencyHeadTraditionalPosInfoBean> getReferAgencyHeadMposInfo(@Body UserRequest request);

    //107、商户查询 - 查询代理商户列表（MPOS） (MD5验签方式）
    @POST("sys/merchant/getReferAgencyMposList")
    Observable<GetReferAgencyTraditionalPosListBean> getReferAgencyMposList(@Body UserRequest request);

    //108、钱柜学院 - 查询钱柜列表 (MD5验签方式）
    @POST("sys/moneylockercollege/getMoneyLockerCollegeList")
    Observable<GetMoneyLockerCollegeListBean> getMoneyLockerCollegeList(@Body LastIdRequest request);

    //109、钱柜学院 - 查询钱柜详情 (MD5验签方式）
    @POST("sys/moneylockercollege/getMoneyLockerCollegeDetail")
    Observable<GetMoneyLockerCollegeDetailBean> getMoneyLockerCollegeDetail(@Body GetMoneyLockerCollegeDetailRequest request);

    //110、收益中心 - 查询头部信息 (MD5验签方式）
    @POST("sys/benefitcentre/getHeaderInformation")
    Observable<GetHeaderInformationBean> getHeaderInformation(@Body TokenRequest request);

    //111、收益中心 - 查询每月汇总信息（传统POS） (MD5验签方式）
    @POST("sys/benefitcentre/getBenefitCentreTraditionalPosDetail")
    Observable<GetBenefitCentreMposDetailBean> getBenefitCentreTraditionalPosDetail(@Body DateRequest request);

    //112、收益中心 - 查询每月汇总信息（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getBenefitCentreMposDetail")
    Observable<GetBenefitCentreMposDetailBean> getBenefitCentreMposDetail(@Body DateRequest request);

    //113、收益中心 - 分润记录列表（传统POS） (MD5验签方式）
    @POST("sys/benefitcentre/getShareBenefitTraditionalPosList")
    Observable<GetShareBenefitTraditionalPosListBean> getShareBenefitTraditionalPosList(@Body DateRequest request);

    //114、收益中心 - 机器返现列表（传统POS） (MD5验签方式）
    @POST("sys/benefitcentre/getMachineBackTraditionalPosList")
    Observable<GetMachineBackTraditionalPosListBean> getMachineBackTraditionalPosList(@Body DateRequest request);

    //115、收益中心 - 活动奖励列表（传统POS） (MD5验签方式）
    @POST("sys/benefitcentre/getActivityRewardTraditionalPosList")
    Observable<GetActivityRewardTraditionalPosListBean> getActivityRewardTraditionalPosList(@Body DateRequest request);

    //116、收益中心 - 未达标列表（传统POS） (MD5验签方式）
    @POST("sys/benefitcentre/getDeductTraditionalPosList")
    Observable<GetDeductTraditionalPosListBean> getDeductTraditionalPosList(@Body DateRequest request);

    //117、收益中心 - 分润记录列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getShareBenefitMposList")
    Observable<GetShareBenefitTraditionalPosListBean> getShareBenefitMposList(@Body DateRequest request);

    //118、收益中心 - 机器返现列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getMachineBackMposList")
    Observable<GetMachineBackTraditionalPosListBean> getMachineBackMposList(@Body DateRequest request);

    //119、收益中心 - 活动奖励列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getActivityRewardMposList")
    Observable<GetActivityRewardTraditionalPosListBean> getActivityRewardMposList(@Body DateRequest request);

    //120、收益中心 - 未达标列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getDeductMposList")
    Observable<GetDeductTraditionalPosListBean> getDeductMposList(@Body DateRequest request);

    //121、报表中心 - 报表首页 (MD5验签方式）
    @POST("sys/reportforms/getHomePageInfo")
    Observable<GetHomePageInfoBean> getHomePageInfo(@Body TokenRequest request);

    //122、报表中心 - 代理每天详情（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getDayAgencyTraditionalPosDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getDayAgencyTraditionalPosDetail(@Body DateRequest request);

    //123、报表中心 - 代理每月详情（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getMonthAgencyTraditionalPosDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getMonthAgencyTraditionalPosDetail(@Body DateRequest request);

    //124、报表中心 - 商户每天详情（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getDayMerchantTraditionalPosDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getDayMerchantTraditionalPosDetail(@Body DateRequest request);

    //125、报表中心 - 商户每月详情（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getMonthMerchantTraditionalPosDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getMonthMerchantTraditionalPosDetail(@Body DateRequest request);

    //126、报表中心 - 代理每天走势（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getDayAgencyTraditionalPosList")
    Observable<GetDayAgencyTraditionalPosListBean> getDayAgencyTraditionalPosList(@Body DateRequest request);

    //127、报表中心 - 代理每月走势（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getMonthAgencyTraditionalPosList")
    Observable<GetDayAgencyTraditionalPosListBean> getMonthAgencyTraditionalPosList(@Body DateRequest request);

    //128、报表中心 - 商户每天走势（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getDayMerchantTraditionalPosList")
    Observable<GetDayAgencyTraditionalPosListBean> getDayMerchantTraditionalPosList(@Body DateRequest request);

    //129、报表中心 - 商户每月走势（传统POS） (MD5验签方式）
    @POST("sys/reportforms/getMonthMerchantTraditionalPosList")
    Observable<GetDayAgencyTraditionalPosListBean> getMonthMerchantTraditionalPosList(@Body DateRequest request);


    //130、报表中心 - 代理每天详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getDayAgencyMposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getDayAgencyMposDetail(@Body DateRequest request);

    //131、报表中心 - 代理每月详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getMonthAgencyMposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getMonthAgencyMposDetail(@Body DateRequest request);

    //132、报表中心 - 商户每天详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getDayMerchantMposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getDayMerchantMposDetail(@Body DateRequest request);

    //133、报表中心 - 商户每月详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getMonthMerchantMposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getMonthMerchantMposDetail(@Body DateRequest request);

    //134、报表中心 - 代理每天走势（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getDayAgencyMposList")
    Observable<GetDayAgencyTraditionalPosListBean> getDayAgencyMposList(@Body DateRequest request);

    //135、报表中心 - 代理每月走势（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getMonthAgencyMposList")
    Observable<GetDayAgencyTraditionalPosListBean> getMonthAgencyMposList(@Body DateRequest request);

    //136、报表中心 - 商户每天走势（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getDayMerchantMposList")
    Observable<GetDayAgencyTraditionalPosListBean> getDayMerchantMposList(@Body DateRequest request);

    //137、报表中心 - 商户每月走势（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getMonthMerchantMposList")
    Observable<GetDayAgencyTraditionalPosListBean> getMonthMerchantMposList(@Body DateRequest request);

    //138、个人中心 - 通知列表 (MD5验签方式）
    @POST("sys/message/getMessageRecordList")
    Observable<GetMessageRecordListBean> getMessageRecordList(@Body LastIdRequest request);

    //139、个人中心 - 通知详情 (MD5验签方式）
    @POST("sys/message/getMessageRecordDetail")
    Observable<GetMessageRecordDetailBean> getMessageRecordDetail(@Body GetMessageRecordDetailRequest request);

    //140、查询用户有效结算卡列表 (MD5验签方式）=======>用户提现
    @POST("user/cardInfo/getUserValidCardList")
    Observable<GetUserCardListBean> getUserValidCardList(@Body TokenRequest request);

    //141、个人中心 - 公告列表 (MD5验签方式）
    @POST("sys/notice/getNoticeList")
    Observable<GetNewNoticeBean> getNoticeList(@Body PageRequest request);

    //142、个人中心 - 公告详情(MD5验签方式）
    @POST("sys/notice/getNoticeDetail")
    Observable<GetNoticeDetailBean> getNoticeDetail(@Body GetNoticeDetailRequest request);

    //143、传统POS-进件商户查询列表 (MD5验签方式）
    @POST("interface/zfback/getTraditionalPosInstallList")
    Observable<GetTraditionalPosInstallListBean> getTraditionalPosInstallList(@Body GetTraditionalPosInstallListRequest request);

    //144、传统POS-进件商户查询详情(MD5验签方式）
    @POST("interface/zfback/getTraditionalPosInstallDetail")
    Observable<GetTraditionalPosInstallDetailBean> getTraditionalPosInstallDetail(@Body GetTraditionalPosInstallDetailRequest request);

    //145、查询MPOS,传统POS,流量卡的分配批次 (MD5验签方式）
    @POST("sys/machinesmanage/selectPosBatchAllocate")
    Observable<SelectPosBatchAllocateBean> selectPosBatchAllocate(@Body TokenRequest request);

    //146、批次修改MPOS分配记录功能接口 (MD5验签方式）
    @POST("sys/machinesmanage/editAllocationMPosBatch")
    Observable<HttpResponse> editAllocationMPosBatch(@Body EditAllocationMPosBatchRequest request);

    //147、批次修改传统POS分配记录功能接口 (MD5验签方式）
    @POST("sys/machinesmanage/editAllocationTraditionalPosBatch")
    Observable<SelectPosBatchAllocateBean> editAllocationTraditionalPosBatch(@Body EditAllocationMPosBatchRequest request);

    //148、根据SN号和UserID查询代理的结算价格等信息 (MD5验签方式）
    @POST("sys/machinesmanage/selectPosSettlePriceBySN")
    Observable<SelectPosSettlePriceBySNBean> selectPosSettlePriceBySN(@Body TokenRequest2 request);

    //149、查询可以解绑的传统POS
    @POST("sys/machinesmanage/selectUnbindTraditionalPos")
    Observable<GetTraditionalPosRecallListBean> selectUnbindTraditionalPos(@Body GetTraditionalPosAllocationListRequest request);

    //150、查询可以解绑的MPOS
    @POST("sys/machinesmanage/selectUnbindMpos")
    Observable<GetMposRecallListBean> selectUnbindMpos(@Body GetTraditionalPosAllocationListRequest request);

    //151、查询达标商户
    @POST("sys/machinesmanage/selectPolicy3Record")
    Observable<SelectPolicy3RecordBean> selectPolicy3Record(@Body TokenRequest2 request);

    //153、查询达标商户
    @POST("sys/machinesmanage/chooseAward")
    Observable<HttpResponse> chooseAward(@Body ChooseAwardRequest request);

    //154、查看是否有小红点
    @POST("sys/notice/getUnReadNews")
    Observable<GetUnReadNewsBean> getUnReadNews(@Body TokenRequest request);

    //155、更新小红点(消灭掉)
    @POST("sys/notice/updateNewsReadFlag")
    Observable<HttpResponse> updateNewsReadFlag(@Body UpdateNewsReadFlagRequest request);

    //156、传统POS我的代理商列表头部月交易额月台均
    @POST("sys/merchant/getReferAgencyTraditionalPosTradeAmountAvg")
    Observable<ReferAgencyPosAmountAvgBean> getReferAgencyTraditionalPosTradeAmountAvg(@Body MonthRequest request);

    //157、MPOS我的代理商列表头部月交易额月台均
    @POST("sys/merchant/getReferAgencyMPosTradeAmountAvg")
    Observable<ReferAgencyPosAmountAvgBean> getReferAgencyMPosTradeAmountAvg(@Body MonthRequest request);

    //158、更新商户姓名和商户电话
    @POST("sys/merchant/updateMerchantNameAndTel")
    Observable<HttpResponse> updateMerchantNameAndTel(@Body UpdateMerchantNameAndTelRequest request);




    //159、报表中心 - 代理每天详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getDayAgencyEposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getDayAgencyEposDetail(@Body DateRequest request);

    //160、报表中心 - 代理每月详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getMonthAgencyEposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getMonthAgencyEposDetail(@Body DateRequest request);

    //161、报表中心 - 商户每天详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getDayMerchantEposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getDayMerchantEposDetail(@Body DateRequest request);

    //162、报表中心 - 商户每月详情（MPOS） (MD5验签方式）
    @POST("sys/reportforms/getMonthMerchantEposDetail")
    Observable<GetDayAgencyTraditionalPosDetailBean> getMonthMerchantEposDetail(@Body DateRequest request);



    //163、收益中心 - 分润记录列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getShareBenefitEposList")
    Observable<GetShareBenefitTraditionalPosListBean> getShareBenefitEposList(@Body DateRequest request);

    //164、收益中心 - 机器返现列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getMachineBackEposList")
    Observable<GetMachineBackTraditionalPosListBean> getMachineBackEposList(@Body DateRequest request);

    //165、收益中心 - 活动奖励列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getActivityRewardEposList")
    Observable<GetActivityRewardTraditionalPosListBean> getActivityRewardEposList(@Body DateRequest request);

    //166、收益中心 - 未达标列表（MPOS） (MD5验签方式）
    @POST("sys/benefitcentre/getDeductEposList")
    Observable<GetDeductTraditionalPosListBean> getDeductEposList(@Body DateRequest request);

    //167、机具管理 - 查询召回记录（MPOS） (MD5验签方式）
    @POST("sys/machinesmanage/getRecallEposList")
    Observable<GetRecallTraditionalPosListBean> getRecallEposList(@Body StatusRequest request);

    //167、机具管理 - 查询召回记录（MPOS） (MD5验签方式）
    @POST("sys/merchant/getTraditionalPosTradeDetail")
    Observable<GetTraditionalPosTradeDetailBean> getTraditionalPosTradeDetail(@Body SnRequest request);

}
