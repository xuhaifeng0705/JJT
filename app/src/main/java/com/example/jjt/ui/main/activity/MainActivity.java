package com.example.jjt.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.example.jjt.Presenter.MainPresenter;
import com.example.jjt.Presenter.contract.MainContract;
import com.example.jjt.R;
import com.example.jjt.app.Constants;
import com.example.jjt.base.BaseActivity;
import com.example.jjt.ui.home.fragment.HomeFragment;
import com.example.jjt.utils.SharedPreferenceUtil;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;


    private HomeFragment mHomeFragment;
    private int hideFragment = Constants.TYPE_HOME;
    private int showFragment = Constants.TYPE_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            SharedPreferenceUtil.setNightModeState(false);
        }else{
            showFragment=SharedPreferenceUtil.getCurrentItem();
            hideFragment = Constants.TYPE_HOME;
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            mNavigationView.getMenu().findItem(R.id.drawer_zhihu).setChecked(false);
//            mToolbar.setTitle(mNavigationView.getMenu().findItem(getCurrentItem(showFragment)).getTitle().toString());
            hideFragment = showFragment;
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mHomeFragment=new HomeFragment();
        loadMultipleRootFragment(R.id.fl_main_content,0,mHomeFragment);
        SharedPreferenceUtil.setCurrentItem(showFragment);
        mDrawerLayout.closeDrawers();
        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        hideFragment = showFragment;
    }

    @Override
    public void showUpdateDialog(String versionContent) {

    }

    @Override
    public void startDownloadService() {

    }

    @Override
    public void showError(String msg) {

    }
    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_HOME:
                return mHomeFragment;
//            case Constants.TYPE_GANK:
//                return mGankFragment;
//            case Constants.TYPE_WECHAT:
//                return mWechatFragment;
//            case Constants.TYPE_GOLD:
//                return mGoldFragment;
//            case Constants.TYPE_VTEX:
//                return mVtexFragment;
//            case Constants.TYPE_LIKE:
//                return mLikeFragment;
//            case Constants.TYPE_SETTING:
//                return mSettingFragment;
//            case Constants.TYPE_ABOUT:
//                return mAboutFragment;
        }
        return mHomeFragment;
    }

    private int getCurrentItem(int item) {
        switch (item) {
            case Constants.TYPE_HOME:
                return R.id.drawer_zhihu;
//            case Constants.TYPE_GANK:
//                return R.id.drawer_gank;
//            case Constants.TYPE_WECHAT:
//                return R.id.drawer_wechat;
//            case Constants.TYPE_GOLD:
//                return R.id.drawer_gold;
//            case Constants.TYPE_VTEX:
//                return R.id.drawer_vtex;
//            case Constants.TYPE_LIKE:
//                return R.id.drawer_like;
//            case Constants.TYPE_SETTING:
//                return R.id.drawer_setting;
//            case Constants.TYPE_ABOUT:
//                return R.id.drawer_about;
        }
        return R.id.drawer_zhihu;
    }
}
