package com.example.jjt.Presenter.contract;

import com.example.jjt.base.BasePresenter;
import com.example.jjt.base.BaseView;
import com.tbruyelle.rxpermissions.RxPermissions;

/**
 * Created by codeest on 16/8/9.
 */

public interface MainContract {

    interface View extends BaseView {

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    interface  Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);
    }
}
