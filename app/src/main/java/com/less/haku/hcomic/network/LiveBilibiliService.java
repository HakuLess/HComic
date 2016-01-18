package com.less.haku.hcomic.network;

import com.less.haku.hcomic.data.LiveIndex;
import com.less.haku.hcomic.data.Result;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by HaKu on 16/1/18.
 * BiliBili直播相关服务
 */
public interface LiveBilibiliService {
//    http://live.bilibili.com/AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758
    @GET("AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Observable<Result<LiveIndex>> getIndexRx();

}
