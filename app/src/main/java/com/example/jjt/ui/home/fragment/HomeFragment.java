package com.example.jjt.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jjt.Presenter.HomePresenter;
import com.example.jjt.Presenter.contract.HomeContract;
import com.example.jjt.R;
import com.example.jjt.base.BaseFragment;
import com.example.jjt.component.ImageLoader;
import com.example.jjt.model.bean.HomeCarouselModel;
import com.example.jjt.model.http.api.JJTApis;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.iv_scrollpic)
    ImageView ivScrollpic;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getCarousel();

    }

    @Override
    public void showCarousel(HomeCarouselModel homeCarouselModel) {
        ImageLoader.load(mContext, JJTApis.PicHost+homeCarouselModel.getBanners().get(0).getPic(),ivScrollpic);

    }

    @Override
    public void showMenu() {

    }

    @Override
    public void showArticle() {

    }

    @Override
    public void showMoreArticle() {

    }

    @Override
    public void showShop() {

    }

    @Override
    public void showMoreShop() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
