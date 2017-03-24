package com.example.jjt.di.component;

import android.app.Activity;

import com.example.jjt.di.FragmentScope;
import com.example.jjt.di.module.FragmentModule;
import com.example.jjt.ui.home.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(HomeFragment homeFragment);

//    void inject(DailyFragment dailyFragment);
//
//    void inject(ThemeFragment themeFragment);
//
//    void inject(SectionFragment sectionFragment);
//
//    void inject(HotFragment hotFragment);
//
//    void inject(CommentFragment longCommentFragment);
//
//    void inject(TechFragment techFragment);
//
//    void inject(GirlFragment girlFragment);
//
//    void inject(LikeFragment likeFragment);
//
//    void inject(WechatMainFragment wechatMainFragment);
//
//    void inject(SettingFragment settingFragment);
//
//    void inject(GoldMainFragment goldMainFragment);
//
//    void inject(GoldPagerFragment goldPagerFragment);
//
//    void inject(VtexPagerFragment vtexPagerFragment);
}
