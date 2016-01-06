package com.less.haku.hcomic.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by HaKu on 15/12/31.
 * 自定义ImageView，使用setUrl方法通过不同库加载网络图片
 */

public class HImageView extends SimpleDraweeView {

    public HImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init();
    }

    public HImageView(Context context) {
        super(context);
        init();
    }

    public HImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public HImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        //图片加载进度条
//        this.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
    }

    public void setUrl(String url) {
        Log.d("image", "image Url " + url);
        this.setImageURI(Uri.parse(url));
    }
}

//public class HImageView extends ImageView {
//    private Context context;
//    public HImageView(Context context) {
//        super(context);
//        this.context = context;
//    }
//
//    public HImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//    }
//
//    public HImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//    }
//
//    /**
//     * 通过不同图片加载库加载网络图片
//     * */
//    public void setUrl(String url) {
//        Glide.with(HApplication.instance()).load(url).into(this);
////        Picasso.with(HApplication.instance()).load(url).into(this);
//    }
//}
