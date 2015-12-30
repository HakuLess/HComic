package com.less.haku.hcomic.network;


import com.less.haku.hcomic.data.Hitokoto;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by HaKu on 15/12/30.
 */
public interface HitokotoService {
    @GET("/rand")
    Call<Hitokoto> getHitokoto();
}
