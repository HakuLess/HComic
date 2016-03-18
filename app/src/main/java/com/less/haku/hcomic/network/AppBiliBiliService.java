package com.less.haku.hcomic.network;

import com.less.haku.hcomic.data.RecoItem;
import com.less.haku.hcomic.data.Result;
import com.less.haku.hcomic.data.TidRecommend;

import java.util.List;

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
//
    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<Result<List<RecoItem>>> getRecoListRx();
}
