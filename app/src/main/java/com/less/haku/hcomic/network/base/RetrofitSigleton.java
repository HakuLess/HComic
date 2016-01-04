package com.less.haku.hcomic.network.base;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by HaKu on 15/12/31.
 * Retrofit单例
 */

public class RetrofitSigleton {
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private RetrofitSigleton (){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

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

        retrofit.client().interceptors().add(new LoggingInterceptor());
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
        bilibiliRetrofit.client().interceptors().add(new LoggingInterceptor());
        return bilibiliRetrofit;
    }

    private volatile static Retrofit marvelRetrofit;
    //返回B站API
    public static Retrofit getMarvel() {
        if (marvelRetrofit == null) {
            synchronized (Retrofit.class) {
                if (marvelRetrofit == null) {
                    marvelRetrofit = new Retrofit.Builder()
                            .baseUrl("http://gateway.marvel.com/v1/public/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
//        marvelRetrofit.client().interceptors().add(new LoggingInterceptor());
        return marvelRetrofit;
    }


    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();

            //可以添加公共参数 增加校验签名等
            request = request.newBuilder().addHeader("123", "123").build();
            Log.d("retrofit request", request.url().toString());
//            Logger.d(String.format("Sending request %s on %s%n%s",
//                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
//            Log.d("retrofit response",
//                    "request time " + (t2 - t1) + "\n" +
//                            "request url "+ response.request().url().toString() + "\n" +
//                            "response body " + response.body().string());
            String bodyString = response.body().string();
            Log.d("retrofit response",
                    "request time " + (t2 - t1) / 1e6d + "ms\n" +
                            "request url " + response.request().url().toString() + "\n"
                            + "response body " + bodyString
            );

//            Logger.d(String.format("Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            // Logger.d(""+new String(response.body().bytes()));

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
        }
    }
}
