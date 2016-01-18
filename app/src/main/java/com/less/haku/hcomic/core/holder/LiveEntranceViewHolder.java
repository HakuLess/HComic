package com.less.haku.hcomic.core.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.widget.HImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 16/1/18.
 */
public class LiveEntranceViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.live_entrance_title)
    public TextView title;
    @Bind(R.id.live_entrance_image)
    public HImageView image;

    public LiveEntranceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}