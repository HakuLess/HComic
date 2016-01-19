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
 * 直播分类ViewHolder
 */
public class LivePartitionViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_live_partition_icon)
    public HImageView itemIcon;
    @Bind(R.id.item_live_partition_title)
    public TextView itemTitle;
    @Bind(R.id.item_live_partition_count)
    public TextView itemCount;

    public LivePartitionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}