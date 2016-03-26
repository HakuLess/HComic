package com.less.haku.hcomic.data;

/**
 * Created by HaKu on 16/1/12.
 * 定义返回请求结构
 * code message result形式
 */
public class Result<T> {
    public int code;
    public String message;
    public T result;
    public T data;

    public T getResult() {
        return result;
    }

    public T getData() {
        return result;
    }
}
