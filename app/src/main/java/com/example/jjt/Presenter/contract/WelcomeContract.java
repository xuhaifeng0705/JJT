package com.example.jjt.Presenter.contract;


import com.example.jjt.base.BasePresenter;
import com.example.jjt.base.BaseView;
import com.example.jjt.model.bean.WelcomeBean;

import java.util.List;

/**
 * Created by codeest on 16/8/15.
 */

public interface WelcomeContract {

    interface View extends BaseView {

        void showContent(List<WelcomeBean> welcomeBean);
//        void showShop(TextModel textModel);//显示商店
        void jumpToMain();

    }

    interface  Presenter extends BasePresenter<View> {

        void getWelcomeData();
//        void getshop();

    }
}
