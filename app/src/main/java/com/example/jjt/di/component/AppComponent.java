package com.example.jjt.di.component;


import com.example.jjt.app.App;
import com.example.jjt.di.module.AppModule;
import com.example.jjt.model.db.RealmHelper;
import com.example.jjt.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App getContext();  // 提供App的Context

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

}
