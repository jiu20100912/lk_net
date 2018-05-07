package cn.lkong.lkong.core;

import android.app.Application;

import cn.lkong.lkong.di.component.AppComponent;
import cn.lkong.lkong.di.component.DaggerAppComponent;
import cn.lkong.lkong.di.module.AppModule;

public class App extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
