package com.less.haku.hcomic.request;

import com.less.haku.hcomic.data.Hitokoto;
import com.less.haku.hcomic.request.base.BaseRequest;
import com.squareup.okhttp.Request;

/**
 * Created by HaKu on 15/12/22.
 */
public class HitokotoRequest extends BaseRequest {
    public HitokotoRequest() {
        url = "http://api.hitokoto.us/rand";
        url = addPublicParam(url);
        request = new Request.Builder()
                .url(url)
                .build();

        outCls = Hitokoto.class;
    }
}
