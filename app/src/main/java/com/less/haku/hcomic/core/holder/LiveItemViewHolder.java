package com.less.haku.hcomic.core.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.widget.HImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 16/1/19.
 * 直播间Item
 */
public class LiveItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_live_cover)
    public HImageView itemLiveCover;
    @Bind(R.id.item_live_user)
    public TextView itemLiveUser;
    @Bind(R.id.item_live_title)
    public TextView itemLiveTitle;

    @Bind(R.id.item_live_user_cover)
    public HImageView itemLiveUserCover;
    @Bind(R.id.item_live_count)
    public TextView itemLiveCount;

    public LiveItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}