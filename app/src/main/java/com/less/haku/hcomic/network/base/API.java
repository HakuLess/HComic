package com.less.haku.hcomic.network.base;

import android.util.Log;
import android.widget.Toast;

import com.less.haku.hcomic.application.HApplication;
import com.less.haku.hcomic.data.LiveIndex;
import com.less.haku.hcomic.data.RecoItem;
import com.less.haku.hcomic.data.Result;
import com.less.haku.hcomic.network.AppBiliBiliService;
import com.less.haku.hcomic.network.LiveBilibiliService;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by HaKu on 16/1/18.
 * 统一封装接口
 */
public class API {

    public static Observable<LiveIndex> getLiveIndex(LiveBilibiliService bilibiliService) {

        return bilibiliService.getIndexRx()
                .flatMap(new Func1<Result<LiveIndex>, Observable<LiveIndex>>() {
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

    public static Observable<List<RecoItem>> getRecoIndex(AppBiliBiliService bilibiliService) {

        return bilibiliService.getRecoListRx()
                .flatMap(new Func1<Result<List<RecoItem>>, Observable<List<RecoItem>>>() {
                    @Override
                    public Observable<List<RecoItem>> call(Result<List<RecoItem>> result) {
                        if (result.code != 0) {
                            Log.e("retrofit error", result.message);
                            throw new RuntimeException();
                        }
                        return Observable.just(result.result);
                    }
                });
    }
}

//                    @Override
//                    public Observable<List<RecoItem>> call(Result<List<RecoItem>> liveIndexResult) {
//                        if (liveIndexResult.code != 0) {
//                            Log.e("retrofit error", liveIndexResult.message);
//                            throw new RuntimeException();
//                        }
//                        return Observable.just(liveIndexResult.data);
//                    }

class HttpResultFunc<T> implements Func1<Result<T>, T>{

    @Override
    public T call(Result<T> httpResult) {
        if (httpResult.code != 0) {
            Toast.makeText(HApplication.instance(), httpResult.message, Toast.LENGTH_SHORT).show();
        }
        return httpResult.getData();
    }
}