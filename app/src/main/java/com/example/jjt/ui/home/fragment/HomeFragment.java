package com.example.jjt.ui.home.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.jjt.Presenter.HomePresenter;
import com.example.jjt.Presenter.contract.HomeContract;
import com.example.jjt.R;
import com.example.jjt.base.BaseFragment;
import com.example.jjt.component.ImageLoader;
import com.example.jjt.model.bean.HomeArticleModel;
import com.example.jjt.model.bean.HomeCarouselModel;
import com.example.jjt.model.bean.HomeMenuModel;
import com.example.jjt.model.bean.HomeShopModel;
import com.example.jjt.model.http.api.JJTApis;
import com.example.jjt.ui.home.adapter.HomeAdapter;
import com.example.jjt.utils.SnackbarUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.iv_scrollpic)
    ImageView ivScrollpic;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<HomeMenuModel.CatesBean> mMenuList;
    private List<HomeArticleModel> mArticleList;
    private List<HomeShopModel.CustomersBean> mShopList;
    private HomeAdapter adapter;
    private int page=1;
    private String pageSize="3";
    private int pageshop=1;
    private String show_type="",lng="",lat="";
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
        mPresenter.getCarousel();//获取banner

    }

    @Override
    public void showCarousel(HomeCarouselModel homeCarouselModel) {
        ImageLoader.load(mContext, JJTApis.PicHost + homeCarouselModel.getBanners().get(0).getPic(), ivScrollpic);
        mPresenter.getMenu();//获取分类

    }

    @Override
    public void showMenu(HomeMenuModel homeMenuModel) {
        mMenuList=homeMenuModel.getCates();
        mPresenter.getArticle(page+"",pageSize);//获取文章
    }

    @Override
    public void showArticle(List<HomeArticleModel> homeArticleModels) {
        mArticleList=homeArticleModels;
        mPresenter.getShop(pageshop+"",show_type,lng,lat);

    }

    @Override
    public void showMoreArticle() {

    }

    @Override
    public void showShop(HomeShopModel homeShopModel) {
        mShopList=homeShopModel.getCustomers();
        adapter=new HomeAdapter(mContext,mMenuList,mArticleList,mShopList);
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(adapter);
    }

    @Override
    public void showMoreShop() {

    }

    @Override
    public void showError(String msg) {
        super.showError(msg);

        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        } else {
//            ivProgress.stop();
        }
        SnackbarUtil.showShort(rvContent,msg);
    }
}
