package com.less.haku.hcomic.util;

/**
 * Created by HaKu on 15/11/3.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class JsonUtil {
    public JsonUtil() {
    }

    public static <T> T json2Java(String json, Class<T> clazz) {
        try {
            Gson e = new Gson();
            return e.fromJson(json, clazz);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> T json2Java(JSONObject json, Class<T> clazz) {
        try {
            Gson e = new Gson();
            return e.fromJson(json.toString(), clazz);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String Java2Json(Object data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    /** @deprecated */
    @Deprecated
    public static <T> List<T> json2List(String json, Class<T> clazz) {
        try {
            Gson e = new Gson();
            List test = (List)e.fromJson(json, (new TypeToken() {
            }).getType());
            return test;
        } catch (Exception var4) {
            return null;
        }
    }

    public static <T> List<T> str2List(String s, Class<T[]> clazz) {
        T[] arr = (T[])(new Gson()).fromJson(s, clazz);
        return Arrays.asList(arr);
    }
}
