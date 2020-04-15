package com.lython.network.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lython.network.BuildConfig;
import com.lython.network.HttpManager;
import com.lython.network.tools.converter.CustomGsonConverter;
import com.lython.network.tools.interceptor.CustomInterceptor;

import java.io.File;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

@Module
public class HttpModule {
    private static final int DISK_CACHE_SIZE = 8 * 1024 * 1024; // 8MB

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app) {
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(5L, TimeUnit.SECONDS)
                .writeTimeout(5L, TimeUnit.SECONDS)
                .connectTimeout(10L,TimeUnit.SECONDS)
                .cache(cache)
                .connectionPool(new ConnectionPool())
                .addInterceptor(new CustomInterceptor())
                .build();
        return client;
    }

    @Provides
    @Singleton
    @HttpAlias("downloadOkHttpClient")
    OkHttpClient provideDownloadOkHttpClient(Application app) {

        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(20L, TimeUnit.SECONDS)
                .connectTimeout(25L,TimeUnit.SECONDS)
                .cache(cache)
                .connectionPool(new ConnectionPool())
                .addInterceptor(new CustomInterceptor())
                .build();
        return client;
    }

    @Provides
    @Singleton
    @HttpAlias("uploadOkHttpClient")
    OkHttpClient provideUploadOkHttpClient(Application app) {
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(20L, TimeUnit.SECONDS)
                .connectTimeout(25L, TimeUnit.SECONDS)
                .cache(cache)
                .connectionPool(new ConnectionPool())
                .addInterceptor(new CustomInterceptor());
        if (!"debug".equals(BuildConfig.BUILD_TYPE)){
            builder.proxy(Proxy.NO_PROXY);
        }
        OkHttpClient client = builder
                .build();
        return client;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    @Provides
    @Singleton
    public CustomGsonConverter provideCustomGsonConverter(Gson gson) {
        return CustomGsonConverter.create(gson);
    }

    @Provides
    @Singleton
    @HttpAlias("reactNativeGsonConverter")
    public CustomGsonConverter provideNativeGsonConverter(Gson gson) {
        return CustomGsonConverter.create(gson);
    }

}
