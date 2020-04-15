package com.lckj.framework.dagger.modules;

import com.lckj.jycm.network.FrontUserService;
import com.lckj.jycm.network.InfoService;
import com.lckj.jycm.network.SysVersionService;
import com.lckj.jycm.zfqg_network.MyService;
import com.lcwl.qiniu.QiNiuService;
import com.lython.network.di.HttpAlias;
import com.lython.network.tools.converter.CustomGsonConverter;

import chat.network.DomainUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class APIModules extends BaseModule {

    @Provides
    QiNiuService provideQiNiuService(@HttpAlias("uploadOkHttpClient") OkHttpClient client, CustomGsonConverter converter) {
        QiNiuService service = BaseModule.getReferenceInstance(QiNiuService.class);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(DomainUtil.getDomain("common")+"api/")
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build().create(QiNiuService.class);
            BaseModule.weakReferenceInstance(service, QiNiuService.class);
        }
        return service;
    }

    @Provides
    SysVersionService provideSysVersionService(@HttpAlias("uploadOkHttpClient") OkHttpClient client, CustomGsonConverter converter) {
        SysVersionService service = BaseModule.getReferenceInstance(SysVersionService.class);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(DomainUtil.getDomain("common") + "front/sysVersion/")
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build().create(SysVersionService.class);
            BaseModule.weakReferenceInstance(service, SysVersionService.class);
        }
        return service;
    }

    @Provides
    FrontUserService provideFrontUserService(@HttpAlias("uploadOkHttpClient") OkHttpClient client, CustomGsonConverter converter) {
        FrontUserService service = BaseModule.getReferenceInstance(FrontUserService.class);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(DomainUtil.getDomain("common") + "front/frontUser/")
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build().create(FrontUserService.class);
            BaseModule.weakReferenceInstance(service, FrontUserService.class);
        }
        return service;
    }

    @Provides
    InfoService provideInfoService(@HttpAlias("uploadOkHttpClient") OkHttpClient client, CustomGsonConverter converter) {
        InfoService service = BaseModule.getReferenceInstance(InfoService.class);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(DomainUtil.getDomain("common") + "adv/Info/")
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build().create(InfoService.class);
            BaseModule.weakReferenceInstance(service, InfoService.class);
        }
        return service;
    }


    @Provides
    MyService provideMyService(@HttpAlias("uploadOkHttpClient") OkHttpClient client, CustomGsonConverter converter) {
        MyService service = BaseModule.getReferenceInstance(MyService.class);
        if (service == null) {
            service = new Retrofit.Builder()
                    .baseUrl(DomainUtil.getDomain("common") + "api/")
                    .addConverterFactory(converter)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build().create(MyService.class);
            BaseModule.weakReferenceInstance(service, MyService.class);
        }
        return service;
    }

}
