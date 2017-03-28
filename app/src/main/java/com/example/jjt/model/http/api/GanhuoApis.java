package com.example.jjt.model.http.api;

import com.example.jjt.model.bean.WelcomeBean;
import com.example.jjt.model.http.response.GanhuoHttpResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 干货集中营api
 */
public interface GanhuoApis {

    String HOST = "http://gank.io/api/";

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<GanhuoHttpResponse<List<WelcomeBean>>> getRandomGirl(@Path("num") int num);

}
