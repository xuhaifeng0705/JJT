package com.example.jjt.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jjt.R;
import com.example.jjt.component.ImageLoader;
import com.example.jjt.model.bean.HomeArticleModel;
import com.example.jjt.model.bean.HomeMenuModel;
import com.example.jjt.model.bean.HomeShopModel;
import com.example.jjt.model.http.api.JJTApis;
import com.example.jjt.widget.MyGridView;
import com.example.jjt.widget.RatingBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeMenuModel.CatesBean> mMenuList;
    private List<HomeArticleModel> mArticleList;
    private List<HomeShopModel.CustomersBean> mShopList;
    private LayoutInflater inflater;
    private Context mContext;

    private HomeMenuAdapter menuAdapter;

    public enum ITEM_TYPE {
        ITEM_MENU,       //分类+文章title
        ITEM_WENZHANG,   //文章列表
//        ITEM_WENZHANG_LOAD, //文章加载
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
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//        unreachable statement 永远都执行不到
        if(position==0){
            return ITEM_TYPE.ITEM_MENU.ordinal();
        }else if(position<=mArticleList.size()){
            return ITEM_TYPE.ITEM_WENZHANG.ordinal();
        }
//        else if(position==mArticleList.size()+1){
//            return ITEM_TYPE.ITEM_WENZHANG_LOAD.ordinal();
//        }
        else{
            return ITEM_TYPE.ITEM_SHOP.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE.ITEM_MENU.ordinal()){
            menuAdapter=new HomeMenuAdapter(mContext,mMenuList);
            return new MenuViewHolder(inflater.inflate(R.layout.item_home_top_one,parent,false));
        }else if(viewType==ITEM_TYPE.ITEM_WENZHANG.ordinal()){
            return new ArticleViewHolder(inflater.inflate(R.layout.item_home_top_two,parent,false));
        }else {
            return new ShopViewHolder(inflater.inflate(R.layout.item_home_top_three,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MenuViewHolder){
            ((MenuViewHolder) holder).my_gridview.setAdapter(menuAdapter);
        }else if(holder instanceof ArticleViewHolder){
            ImageLoader.load(mContext, JJTApis.PicHost+mArticleList.get(position-1).getImage(),((ArticleViewHolder) holder).siv_zx_pic);
            ((ArticleViewHolder) holder).tv_zx_title.setText(mArticleList.get(position-1).getTitle());
            ((ArticleViewHolder) holder).tv_zx_content.setText(mArticleList.get(position-1).getShort_content());
            ((ArticleViewHolder) holder).tv_zx_time.setText(mArticleList.get(position-1).getCreate_time());
            ((ArticleViewHolder) holder).tv_zx_see.setText(mArticleList.get(position-1).getView()+"");

//            if (position == mZxlist.size() - 1) {//显示加载
//                holderView3.tv_zx_add_more.setVisibility(View.VISIBLE);
//                if (zixun) {
//                    holderView3.tv_zx_add_more.setText("暂无更多资讯，敬请期待");
//                    holderView3.tv_zx_add_more.setEnabled(false);
//                } else {
//                    final HolderView3 finalHolderView = holderView3;
//                    holderView3.tv_zx_add_more.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mZxlist2 = mZxlist;
//                            wen_load = true;
//                            wen_page++;
//                            loadzxlist();
//                            finalHolderView.tv_zx_add_more.setVisibility(View.GONE);
//                        }
//                    });
//                }
//            } else {
//                holderView3.tv_zx_add_more.setVisibility(View.GONE);
//            }
        }else if(holder instanceof ShopViewHolder){
            ImageLoader.load(mContext,JJTApis.PicHost+mShopList.get(position-1-mArticleList.size()).getHeadpic(),((ShopViewHolder)holder).siv_goods_pic);
            ((ShopViewHolder)holder).tv_ct_name.setText(mShopList.get(position-1-mArticleList.size()).getShopname());
            ((ShopViewHolder)holder).rtb_star.setStar(Float.parseFloat(mShopList.get(position-1-mArticleList.size()).getCmt_star()));
            ((ShopViewHolder)holder).tv_ct_level.setText(mShopList.get(position-1-mArticleList.size()).getCmt_star()+"分");
            ((ShopViewHolder)holder).tv_bili.setText(mShopList.get(position-1-mArticleList.size()).getDistance());

            String tag = "";
            for (int i = 0; i < mShopList.get(position-1-mArticleList.size()).getCates().size(); i++) {
                tag += mShopList.get(position-1-mArticleList.size()).getCates().get(i) + " ";
            }
            ((ShopViewHolder)holder).tv_ct_tag.setText(tag);
            ((ShopViewHolder)holder).tv_ct_length.setText("积分返还比例:"+Float.parseFloat(mShopList.get(position-1-mArticleList.size()).getCent_rate())*100+"%");
        }

    }

    @Override
    public int getItemCount() {
        return 1+mArticleList.size()+mShopList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.my_gridview)
        MyGridView my_gridview;
        @BindView(R.id.rl_newest_zx)
        RelativeLayout rl_newest_zx;

        public MenuViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_zx_add_more)
        TextView tv_zx_add_more;
        @BindView(R.id.tv_zx_content)
        TextView tv_zx_content;
        @BindView(R.id.tv_zx_title)
        TextView tv_zx_title;
        @BindView(R.id.tv_zx_time)
        TextView tv_zx_time;
        @BindView(R.id.tv_zx_see)
        TextView tv_zx_see;
        @BindView(R.id.siv_zx_pic)
        ImageView siv_zx_pic;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_ct_name)
        TextView tv_ct_name;
        @BindView(R.id.siv_goods_pic)
        ImageView siv_goods_pic;
        @BindView(R.id.rtb_star)
        RatingBar rtb_star;
        @BindView(R.id.tv_ct_level)
        TextView tv_ct_level;
        @BindView(R.id.tv_ct_length)
        TextView tv_ct_length;
        @BindView(R.id.tv_ct_tag)
        TextView tv_ct_tag;
        @BindView(R.id.tv_bili)
        TextView tv_bili;
        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
