package com.less.haku.hcomic.network.base;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by HaKu on 15/12/31.
 * Retrofit单例
 */

public class RetrofitSigleton {
    private RetrofitSigleton (){}
    private volatile static Retrofit retrofit;
    //返回一言API
    public static Retrofit getSingleton() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://api.hitokoto.us")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    private volatile static Retrofit apiBilibiliRetrofit;
    //返回B站API
    public static Retrofit getApiBiliBili() {
        if (apiBilibiliRetrofit == null) {
            synchronized (Retrofit.class) {
                if (apiBilibiliRetrofit == null) {
                    apiBilibiliRetrofit = new Retrofit.Builder()
                            .baseUrl("http://api.bilibili.cn")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return apiBilibiliRetrofit;
    }

    private volatile static Retrofit bilibiliRetrofit;
    //返回B站API
    public static Retrofit getBiliBili() {
        if (bilibiliRetrofit == null) {
            synchronized (Retrofit.class) {
                if (bilibiliRetrofit == null) {
                    bilibiliRetrofit = new Retrofit.Builder()
                            .baseUrl("http://www.bilibili.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return bilibiliRetrofit;
    }
}
