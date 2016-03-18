package com.less.haku.hcomic.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HaKu on 16/3/18.
 * 推荐视频bodyItem
 */
public class BodyItem {
    public String title;
    public String style;
    public String cover;
    public String param;
    @SerializedName("goto")
    public String go;
    public String width;
    public String height;
    public String play;
    public String danmaku;
}