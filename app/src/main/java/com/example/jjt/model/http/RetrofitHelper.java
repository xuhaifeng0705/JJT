package com.example.jjt.model.http;


import com.example.jjt.BuildConfig;
import com.example.jjt.app.Constants;
import com.example.jjt.model.bean.HomeArticleModel;
import com.example.jjt.model.bean.HomeCarouselModel;
import com.example.jjt.model.bean.HomeMenuModel;
import com.example.jjt.model.bean.HomeShopModel;
import com.example.jjt.model.bean.WelcomeBean;
import com.example.jjt.model.http.api.GanhuoApis;
import com.example.jjt.model.http.api.JJTApis;
import com.example.jjt.model.http.response.GanhuoHttpResponse;
import com.example.jjt.model.http.response.JJTHttpResponse;
import com.example.jjt.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;
    private static GanhuoApis ganhuoApiService=null;
    private static JJTApis jjtApiService=null;

    private void init() {
        initOkHttp();
        ganhuoApiService=getApiService(GanhuoApis.HOST,GanhuoApis.class);
        jjtApiService=getApiService(JJTApis.HOST,JJTApis.class);
    }

    public RetrofitHelper() {
        init();
    }

    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        }
//        设置统一的请求头部参数
//        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private <T> T getApiService(String baseUrl, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    //欢迎页面的图片获取
    public Observable<GanhuoHttpResponse<List<WelcomeBean>>> fetchWelcomeInfo(int num) {
        return ganhuoApiService.getRandomGirl(num);
    }
    //首页启动页
    public Observable<JJTHttpResponse<HomeCarouselModel>> fetchHomeCarousel() {
        return jjtApiService.getHomeCarousel();
    }
    //首页分类
    public Observable<JJTHttpResponse<HomeMenuModel>> fetchHomeMenu() {
        return jjtApiService.getHomeMenu("jijitong123");
    }
    //首页文章
    public Observable<JJTHttpResponse<List<HomeArticleModel>>> fetchArticle(String page, String pageSize) {
        return jjtApiService.getHomeArticle(page,pageSize);
    }
    //首页商品
    public Observable<JJTHttpResponse<HomeShopModel>>fetchHomeShop(String page, String show_type, String lng, String lat){
        return jjtApiService.getHomeShop(page,show_type,lng,lat);
    }




//    public Observable<DailyListBean> fetchDailyListInfo() {
//        return zhihuApiService.getDailyList();
//    }
//
//    public Observable<DailyBeforeListBean> fetchDailyBeforeListInfo(String date) {
//        return zhihuApiService.getDailyBeforeList(date);
//    }
//
//    public Observable<ThemeListBean> fetchDailyThemeListInfo() {
//        return zhihuApiService.getThemeList();
//    }
//
//    public Observable<ThemeChildListBean> fetchThemeChildListInfo(int id) {
//        return zhihuApiService.getThemeChildList(id);
//    }
//
//    public Observable<SectionListBean> fetchSectionListInfo() {
//        return zhihuApiService.getSectionList();
//    }
//
//    public Observable<SectionChildListBean> fetchSectionChildListInfo(int id) {
//        return zhihuApiService.getSectionChildList(id);
//    }
//
//    public Observable<ZhihuDetailBean> fetchDetailInfo(int id) {
//        return zhihuApiService.getDetailInfo(id);
//    }
//
//    public Observable<DetailExtraBean> fetchDetailExtraInfo(int id) {
//        return zhihuApiService.getDetailExtraInfo(id);
//    }
//
//    public Observable<WelcomeBean> fetchWelcomeInfo(String res) {
//        return zhihuApiService.getWelcomeInfo(res);
//    }
//
//    public Observable<CommentBean> fetchLongCommentInfo(int id) {
//        return zhihuApiService.getLongCommentInfo(id);
//    }
//
//    public Observable<CommentBean> fetchShortCommentInfo(int id) {
//        return zhihuApiService.getShortCommentInfo(id);
//    }
//
//    public Observable<HotListBean> fetchHotListInfo() {
//        return zhihuApiService.getHotList();
//    }
//
//    public Observable<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page) {
//        return gankApiService.getTechList(tech, num, page);
//    }
//
//    public Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
//        return gankApiService.getGirlList(num, page);
//    }
//
//    public Observable<GankHttpResponse<List<GankItemBean>>> fetchRandomGirl(int num) {
//        return gankApiService.getRandomGirl(num);
//    }
//
//    public Observable<GankHttpResponse<List<GankSearchItemBean>>> fetchGankSearchList(String query,String type,int num,int page) {
//        return gankApiService.getSearchList(query,type,num,page);
//    }
//
//    public Observable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page) {
//        return wechatApiService.getWXHot(Constants.KEY_API, num, page);
//    }
//
//    public Observable<WXHttpResponse<List<WXItemBean>>> fetchWechatSearchListInfo(int num, int page, String word) {
//        return wechatApiService.getWXHotSearch(Constants.KEY_API, num, page, word);
//    }
//
//    public Observable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
//        return myApiService.getVersionInfo();
//    }
//
//    public Observable<GoldHttpResponse<List<GoldListBean>>> fetchGoldList(String type, int num, int page) {
//        return goldApiService.getGoldList(Constants.LEANCLOUD_ID, Constants.LEANCLOUD_SIGN,
//                "{\"category\":\"" + type + "\"}", "-createdAt", "user,user.installation", num, page * num);
//    }
//
//    public Observable<GoldHttpResponse<List<GoldListBean>>> fetchGoldHotList(String type, String dataTime, int limit) {
//        return goldApiService.getGoldHot(Constants.LEANCLOUD_ID, Constants.LEANCLOUD_SIGN,
//                "{\"category\":\"" + type + "\",\"createdAt\":{\"$gt\":{\"__type\":\"Date\",\"iso\":\"" + dataTime + "T00:00:00.000Z\"}},\"objectId\":{\"$nin\":[\"58362f160ce463005890753e\",\"583659fcc59e0d005775cc8c\",\"5836b7358ac2470065d3df62\"]}}",
//                "-hotIndex", "user,user.installation", limit);
//    }
//
//    public Observable<NodeBean> fetchNodeInfo(String name) {
//        return vtexApiService.getNodeInfo(name);
//    }
//
//    public Observable<List<NodeListBean>> fetchTopicList(String name) {
//        return vtexApiService.getTopicList(name);
//    }
//
//    public Observable<List<NodeListBean>> fetchTopicInfo(String id) {
//        return vtexApiService.getTopicInfo(id);
//    }
//
//    public Observable<List<RepliesListBean>> fetchRepliesList(String id){
//        return vtexApiService.getRepliesList(id);
//    }
}
