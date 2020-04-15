package com.lckj.framework.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule extends BaseModule {

    private Application app;

    public AppModule() {
    }

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }


    @Provides
    @Singleton
    Context provideContext(){
        return app;
    }
}
