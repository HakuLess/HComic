package com.less.haku.hcomic.request.base;

/**
 * Created by HaKu on 15/11/5.
 */

import android.net.Uri;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseRequest {
    public Request request;
    public String url;
    public Class<?> outCls;
    public Call call;

    /**
     * 给 URL 拼接公共参数
     * @param url
     * @return 添加公共参数的 URL
     */
    protected String addPublicParam(String url) {
        HashMap<String, String> publicParam = getPublicParam();
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Iterator<Map.Entry<String, String>> iter = publicParam.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)iter.next();
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }

        return builder.build().toString();
    }

    /**
     * 网络请求公共参数获取*/
    protected HashMap<String, String> getPublicParam() {
        /**
         * 公共参数初始化
         * @param uid 用户id
         * @param cid 城市id
         * @param u 设备唯一标示
         * @param w 屏幕宽
         * @param h 屏幕高
         * @param an app名称
         * @param av app版本
         * @param sid storeId*/
        HashMap<String, String> params = new HashMap<>();
        params.put("an", "HKApp");
        return params;
    }
}
