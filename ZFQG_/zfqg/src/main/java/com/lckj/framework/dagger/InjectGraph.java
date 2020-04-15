package com.lckj.framework.dagger;

import com.lckj.framework.dagger.modules.APIModules;
import com.lckj.framework.dagger.modules.AppModule;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.jycm.MainActivity;
import com.lckj.zfqg.activity.ActivityDetailsActivity;
import com.lckj.zfqg.activity.ActivityOrderDetailsActivity;
import com.lckj.zfqg.activity.ActivityRecordActivity;
import com.lckj.zfqg.activity.AddBankCardActivity;
import com.lckj.zfqg.activity.AfficheDetailsActivity;
import com.lckj.zfqg.activity.AgencyActivity;
import com.lckj.zfqg.activity.AgencyInfoActivity;
import com.lckj.zfqg.activity.ApparatusManagerActivity;
import com.lckj.zfqg.activity.ApplyQrcodePayActivity;
import com.lckj.zfqg.activity.ApplyQrcodePayRecordActivity;
import com.lckj.zfqg.activity.CardAllocationActivity;
import com.lckj.zfqg.activity.ClearingCentreActivity;
import com.lckj.zfqg.activity.CoffersSchoolActivity;
import com.lckj.zfqg.activity.CoffersSchoolDetailsActivity;
import com.lckj.zfqg.activity.DetailRecordActivity;
import com.lckj.zfqg.activity.FeedbackActivity;
import com.lckj.zfqg.activity.FeedbackDetailActivity;
import com.lckj.zfqg.activity.FeedbackRecordActivity;
import com.lckj.zfqg.activity.HomeMPOSActivity;
import com.lckj.zfqg.activity.HomePOSActivity;
import com.lckj.zfqg.activity.InformDetailsActivity;
import com.lckj.zfqg.activity.JoinEventActivity;
import com.lckj.zfqg.activity.MPOSAllocationActivity;
import com.lckj.zfqg.activity.MPOSAllocationUpdateActivity;
import com.lckj.zfqg.activity.MerchantDealInfoActivity;
import com.lckj.zfqg.activity.MerchantInDetailsActivity;
import com.lckj.zfqg.activity.MerchantInfoActivity;
import com.lckj.zfqg.activity.MerchantListActivity;
import com.lckj.zfqg.activity.POSAllocationActivity;
import com.lckj.zfqg.activity.POSAllocationUpdateActivity;
import com.lckj.zfqg.activity.StandardMerchantListActivity;
import com.lckj.zfqg.activity.TrendActivity;
import com.lckj.zfqg.activity.WithdrawalActivity;
import com.lckj.zfqg.activity.WithdrawalRecordActivity;
import com.lckj.zfqg.adapter.ClearingCentreAdapter;
import com.lckj.zfqg.fragment.AgencyInfoFragment;
import com.lckj.zfqg.activity.UpdatePayPwActivity;
import com.lckj.zfqg.activity.UpdateRemarkActivity;
import com.lckj.jycm.activity.ExemptPwLoginActivity;
import com.lckj.jycm.activity.ForgetPwActivity;
import com.lckj.jycm.activity.GainRecordFragment;
import com.lckj.jycm.activity.InvestAdActivity;
import com.lckj.jycm.activity.InvestAdPayActivity;
import com.lckj.jycm.activity.PwLoginActivity;
import com.lckj.jycm.activity.RealNameActivity;
import com.lckj.jycm.activity.StartActivity;
import com.lckj.zfqg.activity.UpdateLoginPwActivity;
import com.lckj.zfqg.activity.UpdatePhoneActivity;
import com.lckj.jycm.activity.WithdrawalRecordFragment;
import com.lckj.jycm.article.activity.ArticleShareActivity;
import com.lckj.jycm.article.activity.CreateArticleActivity;
import com.lckj.jycm.article.activity.CreateArticleCommitActivity;
import com.lckj.jycm.article.activity.SelectTaskActivity;
import com.lckj.jycm.center.activity.ApplyMerchantFunctionActivity;
import com.lckj.jycm.center.activity.MyTeamActivity;
import com.lckj.jycm.fragment.MyAdFragment;
import com.lckj.jycm.fragment.MyArticleFragment;
import com.lckj.jycm.fragment.PersonCenterFragment;
import com.lckj.jycm.fragment.TaskFragment;
import com.lckj.jycm.home.AdvertisingMapFragment;
import com.lckj.jycm.home.HomeActivity;
import com.lckj.jycm.home.NewsFragment;
import com.lckj.jycm.setting.PersonInfoActivity;
import com.lckj.zfqg.activity.SettingActivity;
import com.lckj.lckjlib.share.ShareDialog;
import com.lckj.zfqg.fragment.ActivityOrderFragment;
import com.lckj.zfqg.activity.LoginActivity;
import com.lckj.zfqg.activity.RegisterActivity;
import com.lckj.zfqg.activity.UpdateRateActivity;
import com.lckj.zfqg.fragment.ActivityListFragment;
import com.lckj.zfqg.fragment.AfficheFragment;
import com.lckj.zfqg.fragment.AgreeRefuseFragment;
import com.lckj.zfqg.fragment.AllocationRecordFragment;
import com.lckj.zfqg.fragment.DirectFragment;
import com.lckj.zfqg.fragment.EarningsFragment;
import com.lckj.zfqg.fragment.HomeFragment;
import com.lckj.zfqg.fragment.InformFragment;
import com.lckj.zfqg.fragment.MaterialFragment;
import com.lckj.zfqg.fragment.MerchantInFragment;
import com.lckj.zfqg.fragment.MyFragment;
import com.lckj.zfqg.fragment.PendingFragment;
import com.lckj.zfqg.fragment.PerformanceFragment;
import com.lckj.zfqg.fragment.PosActivityListFragment;
import com.lckj.zfqg.fragment.RateApplyFragment;
import com.lckj.zfqg.fragment.RateApplyRecordFragment;
import com.lckj.zfqg.fragment.ReportFragment;
import com.lckj.zfqg.fragment.UnbindFragment;
import com.lckj.zfqg.fragment.UnbindRecordFragment;
import com.lckj.zfqg.widget.ConfirmDialog;
import com.lckj.zfqg.widget.PrizeDialog;
import com.lckj.zfqg.widget.SnDialog;
import com.lcwl.qiniu.UploadImageToQnUtils;
import com.lython.network.di.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                /*

                DaoModule.class,*/
                APIModules.class,
                AppModule.class,
                ProviderModule.class,
                HttpModule.class,
        }
)
public interface InjectGraph {

    void inject(UploadImageToQnUtils uploadImageToQnUtils);

    void inject(MainActivity mainActivity);

    void inject(HomeActivity homeActivity);

    void inject(ExemptPwLoginActivity exemptPwLoginActivity);

    void inject(PwLoginActivity pwLoginActivity);

    void inject(StartActivity startActivity);

    void inject(PersonCenterFragment personCenterFragment);

    void inject(ForgetPwActivity forgetPwActivity);

    void inject(CreateArticleActivity createArticleActivity);

    void inject(CreateArticleCommitActivity createArticleCommitActivity);

    void inject(MyArticleFragment myArticleFragment);

    void inject(UpdateLoginPwActivity updateLoginPwActivity);

    void inject(SettingActivity settingActivity);


    void inject(ApplyMerchantFunctionActivity applyMerchantFunctionActivity);
    void inject(AdvertisingMapFragment advertisingMapFragment);

    void inject(InvestAdActivity investAdActivity);

    void inject(TaskFragment taskFragment);

    void inject(PersonInfoActivity personInfoActivity);

    void inject(UpdateRemarkActivity updateRemarkActivity);

    void inject(UpdatePhoneActivity updatePhoneActivity);

    void inject(MyAdFragment myAdFragment);

    void inject(InvestAdPayActivity investAdPayActivity);

    void inject(RealNameActivity realNameActivity);

    void inject(SelectTaskActivity selectTaskActivity);

    void inject(NewsFragment newsFragment);

    void inject(ShareDialog shareDialog);

    void inject(ArticleShareActivity articleShareActivity);

    void inject(GainRecordFragment gainRecordFragment);

    void inject(WithdrawalRecordFragment withdrawalRecordFragment);

    void inject(MyTeamActivity myTeamActivity);

    void inject(com.lckj.zfqg.activity.HomeActivity homeActivity);

    void inject(com.lckj.zfqg.activity.ForgetPwActivity forgetPwActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(HomeFragment homeFragment);

    void inject(EarningsFragment earningsFragment);

    void inject(ReportFragment reportFragment);

    void inject(RateApplyFragment rateApplyFragment);

    void inject(UpdateRateActivity updateRateActivity);

    void inject(ActivityOrderFragment activityOrderFragment);

    void inject(ActivityListFragment activityListFragment);

    void inject(PosActivityListFragment posActivityListFragment);

    void inject(UpdatePayPwActivity updatePayPwActivity);

    void inject(AddBankCardActivity addBankCardActivity);

    void inject(com.lckj.zfqg.activity.RealNameActivity realNameActivity);

    void inject(InformFragment informFragment);

    void inject(AfficheFragment afficheFragment);

    void inject(AgreeRefuseFragment agreeRefuseFragment);

    void inject(PendingFragment pendingFragment);

    void inject(UnbindFragment unbindFragment);

    void inject(UnbindRecordFragment unbindRecordFragment);

    void inject(RateApplyRecordFragment rateApplyRecordFragment);

    void inject(AllocationRecordFragment allocationRecordFragment);

    void inject(DirectFragment directFragment);

    void inject(AgencyInfoFragment agencyInfoFragment);

    void inject(PerformanceFragment performanceFragment);

    void inject(ApplyQrcodePayActivity applyQrcodePayActivity);

    void inject(MyFragment myFragment);

    void inject(ClearingCentreActivity clearingCentreActivity);

    void inject(ClearingCentreAdapter clearingCentreAdapter);

    void inject(ConfirmDialog confirmDialog);

    void inject(WithdrawalActivity withdrawalActivity);

    void inject(WithdrawalRecordActivity withdrawalRecordActivity);

    void inject(FeedbackActivity feedbackActivity);

    void inject(FeedbackRecordActivity feedbackRecordActivity);

    void inject(ApplyQrcodePayRecordActivity applyQrcodePayRecordActivity);

    void inject(HomeMPOSActivity homeMPOSActivity);

    void inject(ActivityRecordActivity activityRecordActivity);

    void inject(ActivityDetailsActivity activityDetailsActivity);

    void inject(ActivityOrderDetailsActivity activityOrderDetailsActivity);

    void inject(SnDialog snDialog);

    void inject(JoinEventActivity joinEventActivity);

    void inject(ApparatusManagerActivity apparatusManagerActivity);

    void inject(MPOSAllocationActivity mposAllocationActivity);

    void inject(POSAllocationActivity posAllocationActivity);

    void inject(CardAllocationActivity cardAllocationActivity);

    void inject(POSAllocationUpdateActivity posAllocationUpdateActivity);

    void inject(MPOSAllocationUpdateActivity mposAllocationUpdateActivity);

    void inject(MerchantListActivity merchantListActivity);

    void inject(MerchantInfoActivity merchantInfoActivity);

    void inject(AgencyActivity agencyActivity);

    void inject(AgencyInfoActivity agencyInfoActivity);

    void inject(CoffersSchoolActivity coffersSchoolActivity);

    void inject(CoffersSchoolDetailsActivity coffersSchoolDetailsActivity);

    void inject(DetailRecordActivity detailRecordActivity);

    void inject(TrendActivity trendActivity);

    void inject(InformDetailsActivity informDetailsActivity);

    void inject(AfficheDetailsActivity afficheDetailsActivity);

    void inject(FeedbackDetailActivity feedbackDetailActivity);

    void inject(MerchantInFragment merchantInFragment);

    void inject(MerchantInDetailsActivity merchantInDetailsActivity);

    void inject(HomePOSActivity homePOSActivity);

    void inject(StandardMerchantListActivity standardMerchantListActivity);

    void inject(PrizeDialog prizeDialog);

    void inject(MaterialFragment materialFragment);

    void inject(MerchantDealInfoActivity merchantDealInfoActivity);
}



