package com.lcwl.framework.dagger;

import com.lckj.framework.dagger.modules.APIModules;
import com.lckj.framework.dagger.modules.AppModule;
import com.lckj.framework.dagger.modules.ProviderModule;
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
public interface InjectGraphNetwork {
    void inject(UploadImageToQnUtils uploadImageToQnUtils);
}



