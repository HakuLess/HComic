package com.less.haku.hcomic.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HaKu on 16/3/18.
 * 推荐Item
 */
public class RecoItem {
    public String type;
    public Head head;
    public List<BodyItem> body;
}

class Head {
    public String param;
    @SerializedName("goto")
    public String go;
    public String style;
    public String title;
}
