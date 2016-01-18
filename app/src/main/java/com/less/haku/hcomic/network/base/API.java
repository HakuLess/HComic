package com.less.haku.hcomic.network.base;

import android.util.Log;

import com.less.haku.hcomic.data.LiveIndex;
import com.less.haku.hcomic.data.Result;
import com.less.haku.hcomic.network.LiveBilibiliService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by HaKu on 16/1/18.
 * 统一封装接口
 */
public class API {

    public static Observable<LiveIndex> getLiveIndex() {

        return RetrofitSigleton.getLiveBilibiliRetrofit().create(LiveBilibiliService.class)
                .getIndexRx().flatMap(new Func1<Result<LiveIndex>, Observable<LiveIndex>>() {
                    @Override
                    public Observable<LiveIndex> call(Result<LiveIndex> liveIndexResult) {
                        if (liveIndexResult.code != 0) {
                            Log.e("retrofit error", liveIndexResult.message);
                            throw new RuntimeException();
                        }
                        return Observable.just(liveIndexResult.data);
                    }
                });
    }
}
