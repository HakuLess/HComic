package com.less.haku.hcomic.network;


import com.less.haku.hcomic.data.Hitokoto;

import retrofit.Call;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by HaKu on 15/12/30.
 * 一言随机请求
 */
public interface HitokotoService {
    @GET("/rand")
    Call<Hitokoto> getHitokoto();

    @GET("/rand")
    Observable<Hitokoto> getHitoKotoRx();

    @GET("/rand")
    Hitokoto getHitokotoSyn();
}
