package cn.lkong.lkong.di.component;

import android.content.Context;

import javax.inject.Singleton;

import cn.lkong.lkong.api.ApiService;
import cn.lkong.lkong.core.SPUtils;
import cn.lkong.lkong.di.module.AppModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();

    ApiService getApiService();

    SPUtils getSPUtils();
}
