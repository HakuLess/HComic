package com.less.haku.hcomic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.less.haku.hcomic.application.HApplication;

/**
 * Created by HaKu on 15/12/31.
 * 自定义ImageView，使用setUrl方法通过不同库加载网络图片
 */

public class HImageView extends ImageView {
    private Context context;
    public HImageView(Context context) {
        super(context);
        this.context = context;
    }

    public HImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public HImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * 通过不同图片加载库加载网络图片
     * */
    public void setUrl(String url) {
        Glide.with(HApplication.instance()).load(url).into(this);
//        Picasso.with(HApplication.instance()).load(url).into(this);
    }
}
