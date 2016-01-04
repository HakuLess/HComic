package com.less.haku.hcomic.network;

import com.less.haku.hcomic.data.MarvelResponse;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by HaKu on 16/1/4.
 */

public interface MarvelService {
    @GET("comics")
    Call<MarvelResponse> getMarvel();
}
