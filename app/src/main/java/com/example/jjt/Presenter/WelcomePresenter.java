package com.example.jjt.Presenter;

import com.example.jjt.Presenter.contract.WelcomeContract;
import com.example.jjt.base.RxPresenter;
import com.example.jjt.model.bean.WelcomeBean;
import com.example.jjt.model.http.RetrofitHelper;
import com.example.jjt.model.http.response.GanhuoHttpResponse;
import com.example.jjt.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter{
    private static final int num = 1;

    private static final int COUNT_DOWN_TIME = 2200;

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WelcomePresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper=mRetrofitHelper;
    }

    @Override
    public void getWelcomeData() {
        Subscription rxSubscription=mRetrofitHelper.fetchWelcomeInfo(num)
                .compose(RxUtils.<GanhuoHttpResponse<List<WelcomeBean>>>rxSchedulerHelper())
                .compose(RxUtils.<List<WelcomeBean>>handleResult())
                .subscribe(new Action1<List<WelcomeBean>>() {
                    @Override
                    public void call(List<WelcomeBean> welcomeBeen) {
                        mView.showContent(welcomeBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.jumpToMain();
                    }
                });
        addSubscrebe(rxSubscription);

    }

//    @Override
//    public void getshop() {
//        Subscription rxSubscription=mRetrofitHelper.fetchHomeShop("1","","","")
//                .compose(RxUtils.<JJTHttpResponse<TextModel>>rxSchedulerHelper())
//                .compose(RxUtils.<TextModel>handleJJTResult())
//                .subscribe(new Action1<TextModel>() {
//                    @Override
//                    public void call(TextModel homeShopModel) {
//                        mView.showShop(homeShopModel);
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        mView.showError(throwable.getMessage().toString());
//                    }
//                });
//        addSubscrebe(rxSubscription);
//    }
}
