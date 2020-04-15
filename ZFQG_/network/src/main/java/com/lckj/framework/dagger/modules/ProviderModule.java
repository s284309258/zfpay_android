package com.lckj.framework.dagger.modules;

import android.content.Context;

import com.lckj.framework.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module(
)
public class ProviderModule extends BaseModule{

    public static DataManager sDataManager;
    public static DataManager getDataManager(Context context) {
        if (sDataManager == null) {
            sDataManager = new DataManager(context.getApplicationContext());
        }
        return sDataManager;
    }



    @Provides
    @Singleton
    DataManager provideDataManager(Context context) {
        return getDataManager(context);
    }

    /*@Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();

        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        client.setCache(cache);
        client.setConnectionPool(new ConnectionPool(0, 0));
        client.setReadTimeout(5L, TimeUnit.SECONDS);
        client.setWriteTimeout(5L, TimeUnit.SECONDS);
        client.setConnectTimeout(10L,TimeUnit.SECONDS);
        client.interceptors().add(new CustomInterceptor());
        return client;
    }*/


}
