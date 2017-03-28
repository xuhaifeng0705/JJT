package com.example.jjt.ui.home.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.example.jjt.utils.LogUtil;
import com.example.jjt.utils.SnackbarUtil;
import com.example.jjt.utils.SystemUtil;

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
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private List<HomeMenuModel.CatesBean> mMenuList;
    private List<HomeArticleModel> mArticleList;
    private List<HomeShopModel.CustomersBean> mShopList;
    private HomeAdapter adapter;
    private int page=1;
    private String pageSize="3";
    private int pageshop=1;
    private String show_type="",lng="",lat="";
    private boolean isLoadingMoreShop=false;
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
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) rvContent.getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = rvContent.getLayoutManager().getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {  //还剩2个Item时加载更多
                    if(!isLoadingMoreShop){
                        isLoadingMoreShop = true;
                        pageshop++;
                        mPresenter.getShop(pageshop+"",show_type,lng,lat);
                        tabLayout.setVisibility(View.VISIBLE);
                        tabLayout.getTabAt(1).select();
                    }
                }
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                pageshop=1;
                mPresenter.getCarousel();
//                mPresenter.getShop(pageshop+"",show_type,lng,lat);
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeRefresh.setEnabled(true);
                } else {
                    swipeRefresh.setEnabled(false);
                    float rate = (float)(SystemUtil.dp2px(mContext, 256) + verticalOffset * 2) / SystemUtil.dp2px(mContext, 256);
                    if (rate >= 0)
                        ivScrollpic.setAlpha(rate);
                }
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("全部餐厅"));
        tabLayout.addTab(tabLayout.newTab().setText("推荐餐厅"));
        tabLayout.addTab(tabLayout.newTab().setText("附近餐厅"));
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
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
        if(isLoadingMoreShop){
            mShopList.addAll(homeShopModel.getCustomers());
            isLoadingMoreShop=false;
            adapter.notifyDataSetChanged();
        }else{
            mShopList=homeShopModel.getCustomers();
            adapter=new HomeAdapter(mContext,mMenuList,mArticleList,mShopList);
            rvContent.setLayoutManager(new LinearLayoutManager(mContext));
            rvContent.setAdapter(adapter);
        }
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
        LogUtil.d(msg);
    }
}
