package com.less.haku.hcomic.network;

import com.less.haku.hcomic.data.TidRecommend;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by HaKu on 16/1/5.
 * app 哔哩哔哩 服务请求
 */
public interface AppBiliBiliService {
    @GET("/bangumi/tid_recommend")
    Call<TidRecommend> getRecommed(@Query("page") int page,
                                   @Query("pagesize") int pageSize,
                                   @Query("tid") int tid);


    @GET("/bangumi/tid_recommend")
    Observable<TidRecommend> getRecommedRx(@Query("page") int page,
                                           @Query("pagesize") int pageSize,
                                           @Query("tid") int tid);
}
