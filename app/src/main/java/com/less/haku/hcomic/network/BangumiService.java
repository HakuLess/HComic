package com.less.haku.hcomic.network;

import com.less.haku.hcomic.data.Bangumi;
import com.less.haku.hcomic.data.BangumiIndex;
import com.less.haku.hcomic.data.Result;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by HaKu on 15/12/31.
 * 新番请求
 */
public interface BangumiService {
    @GET("/index/bangumi/{year}-{month}.json")
    Call<List<Bangumi>> getBangumi(@Path("year") String year, @Path("month") String month);

//    @GET("app_index_page")
//    Call<List<RecommendCategory>> getIndex();

    @GET("app_index_page")
    Call<Result<BangumiIndex>> getIndex();

    @GET("app_index_page")
    Observable<Result<BangumiIndex>> getIndexRx();

}
