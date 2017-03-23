package com.example.jjt.Presenter;

import com.example.jjt.Presenter.contract.MainContract;
import com.example.jjt.base.RxPresenter;
import com.example.jjt.model.http.RetrofitHelper;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper mRetrofitHelper){
        this.mRetrofitHelper=mRetrofitHelper;
    }




    @Override
    public void checkVersion(String currentVersion) {

    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {

    }
}
