package com.less.haku.hcomic.core.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.widget.banner.Banner;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 16/1/20.
 * 直播页Banner
 */
public class LiveBannerViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_live_banner)
    public Banner banner;

    public LiveBannerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}