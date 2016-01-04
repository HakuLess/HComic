package com.less.haku.hcomic.network;

import com.less.haku.hcomic.data.Bangumi;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by HaKu on 15/12/31.
 * 新番请求
 */
public interface BangumiService {
    @GET("/index/bangumi/{year}-{month}.json")
    Call<List<Bangumi>> getBangumi(@Path("year") String year, @Path("month") String month);
}
