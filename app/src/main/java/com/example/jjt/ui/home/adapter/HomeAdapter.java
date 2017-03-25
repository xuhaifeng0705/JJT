package com.example.jjt.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jjt.model.bean.HomeArticleModel;
import com.example.jjt.model.bean.HomeMenuModel;
import com.example.jjt.model.bean.HomeShopModel;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeMenuModel.CatesBean> mMenuList;
    private List<HomeArticleModel> mArticleList;
    private List<HomeShopModel.CustomersBean> mShopList;
    private LayoutInflater inflater;
    private Context mContext;

    public enum ITEM_TYPE {
        ITEM_MENU,       //分类
        ITEM_WENZHANG_TITLE,//文章title
        ITEM_WENZHANG,   //文章列表
        ITEM_WENZHANG_LOAD, //文章加载
        ITEM_SHOP  //商店列表
    }

    public HomeAdapter(Context mContext, List<HomeMenuModel.CatesBean> mMenuList,List<HomeArticleModel> mArticleList,List<HomeShopModel.CustomersBean> mShopList) {
        this.mMenuList = mMenuList;
        this.mArticleList = mArticleList;
        this.mShopList = mShopList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
