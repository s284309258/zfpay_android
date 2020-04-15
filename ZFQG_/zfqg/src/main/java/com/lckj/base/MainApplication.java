package com.lckj.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.lckj.framework.dagger.DaggerInjectGraph;
import com.lckj.framework.dagger.InjectGraph;
import com.lckj.framework.dagger.modules.APIModules;
import com.lckj.framework.dagger.modules.AppModule;
import com.lckj.framework.dagger.modules.ProviderModule;
import com.lckj.jycm.Base.BaseActvity;
import com.lckj.jycm.R;
import com.lckj.jycm.activity.PwLoginActivity;
import com.lckj.jycm.zfqg_network.GetUnReadNewsBean;
import com.lckj.jycm.zfqg_network.GetUserNewInfoBean;
import com.lcwl.base.NetworkApplication;
import com.lython.network.HttpManager;
import com.lython.network.di.HttpModule;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.mob.MobSDK;
import com.networkbench.agent.impl.NBSAppAgent;
import com.qtopay.agentlibrary.GlobalApp;
import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;


public class MainApplication extends NetworkApplication {
    private static MainApplication sInstance;
    private InjectGraph mInjectGraph;
    private ProviderModule mProviderModule;
    private HttpModule mHttpModule;
    private AppModule mAppModule;
    private APIModules mAPIModule;
    private ArrayList<WeakReference<ImageView>> avatars;
    private ArrayList<BaseActvity> activitys = new ArrayList<>();
    private GetUserNewInfoBean.DataBean.UserNewInfoBean mUserNewInfo;
    private GetUnReadNewsBean.DataBean mUnReadNewsBean;


    public static Context getAppContext() {
        return sInstance;
    }


    public static InjectGraph getInjectGraph() {
        return sInstance.mInjectGraph;
    }

    private InjectGraph createGraph2(MainApplication app) {
        /*
        mDaoModule = new DaoModule();*/
        mAPIModule = new APIModules();
        mAppModule = new AppModule(app);
        mProviderModule = new ProviderModule();
        mHttpModule = new HttpModule();
        return DaggerInjectGraph.builder()
                .appModule(mAppModule)
//                .daoModule(mDaoModule)
                .aPIModules(mAPIModule)
                .providerModule(mProviderModule)
                .httpModule(mHttpModule)
                .build();
//    return null;
    }


    public static MainApplication getInstance() {
        return sInstance != null ? sInstance : new MainApplication();
    }


    @Override
    public void onCreate() {
        // 我们可以从这里获得Tinker加载过程的信息
        ApplicationLike tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(1);

        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
        super.onCreate();
        sInstance = this;
        mInjectGraph = createGraph2(this);
        HttpManager.init(this);
//    CrashHandler.getInstance().init(this);
        avatars = new ArrayList<>();
//        LeakCanary.install(this);
        // 加载系统默认设置，字体不随用户设置变化
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        MobSDK.init(this);
        if (isUseTingyun()) {
            NBSAppAgent.setLicenseKey("7ce76b4b6b8e42f5a9c80864b5931fda").withLocationServiceEnabled(true).start(this.getApplicationContext());//Appkey 请从官网获取
        }
        GlobalApp.Companion.getInstance().initQtoPaySDK(this);
    }

    public boolean isUseTingyun() {
        return getResources().getBoolean(R.bool.useTingyun);
    }


    //初始化视频缓存库
    private HttpProxyCacheServer proxy;

    public HttpProxyCacheServer getProxy(Context context) {
        return proxy == null ? (proxy = newProxy()) : proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }

    public void addHeader(ImageView avatarIv) {
        if (avatars != null) {
            avatars.add(new WeakReference<>(avatarIv));
        }
    }


    public void addActivity(BaseActvity baseActvity) {
        activitys.add(baseActvity);
    }

    public void removeActivity(BaseActvity baseActvity) {
        if (activitys.contains(baseActvity)) activitys.remove(baseActvity);
    }

    public void coloseActivitys() {
        for (int i = 0; i < activitys.size(); i++) {
            activitys.get(i).finish();
        }
    }

    public ArrayList<BaseActvity> getActivitys() {
        return activitys;
    }

    public Context getActivityContext() {
        if (activitys.size() > 0) {
            return activitys.get(activitys.size() - 1);
        } else {
            return getBaseContext();
        }
    }

    public BaseActvity getLastActivity() {
        if (activitys.size() > 0) {
            return activitys.get(activitys.size() - 1);
        } else {
            return null;
        }
    }

    public void clearActivitys() {
        for (int i = 0; i < activitys.size(); i++) {
            activitys.get(i).finish();
        }
    }

    public void setUserNewInfo(GetUserNewInfoBean.DataBean.UserNewInfoBean userNewInfo) {
        mUserNewInfo = userNewInfo;
    }

    private GetUserNewInfoBean.DataBean.UserNewInfoBean getUserNewInfo() {
        return mUserNewInfo;
    }

    public void setUnReadNews(GetUnReadNewsBean.DataBean unReadNewsBean) {
        mUnReadNewsBean = unReadNewsBean;
    }

    public GetUnReadNewsBean.DataBean getUnReadNews() {
        return mUnReadNewsBean;
    }
}
