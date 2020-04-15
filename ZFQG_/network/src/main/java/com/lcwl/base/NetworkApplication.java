package com.lcwl.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.lckj.framework.dagger.modules.APIModules;
import com.lckj.framework.dagger.modules.AppModule;
import com.lckj.framework.dagger.modules.ProviderModule;
//import com.lcwl.framework.dagger.DaggerInjectGraphIM;
import com.lcwl.framework.dagger.DaggerInjectGraphNetwork;
import com.lcwl.framework.dagger.InjectGraphNetwork;
import com.lython.network.HttpManager;
import com.lython.network.di.HttpModule;


public class NetworkApplication extends MultiDexApplication {
  private static NetworkApplication sInstance;
  private InjectGraphNetwork mInjectGraph;
  private ProviderModule mProviderModule;
  private HttpModule mHttpModule;
  private AppModule mAppModule;
  private APIModules mAPIModule;


  public static Context getAppContext() {
    return sInstance;
  }


  public static InjectGraphNetwork getInjectGraphNetwork() {
    return sInstance.mInjectGraph;
  }

  private InjectGraphNetwork createGraph(NetworkApplication app) {
        /*
        mDaoModule = new DaoModule();*/
        mAPIModule = new APIModules();
    mAppModule = new AppModule(app);
    mProviderModule = new ProviderModule();
    mHttpModule = new HttpModule();
    return DaggerInjectGraphNetwork.builder()
            .appModule(mAppModule)
//                .daoModule(mDaoModule)
            .aPIModules(mAPIModule)
            .providerModule(mProviderModule)
            .httpModule(mHttpModule)
            .build();
//    return null;
  }


  public static NetworkApplication getInstance() {
    return sInstance!=null?sInstance:new NetworkApplication();
  }


  @Override
  public void onCreate() {
    super.onCreate();
    sInstance = this;
    mInjectGraph = createGraph(this);
    HttpManager.init(this);
  }

  public void coloseActivitys() {

  }
}
