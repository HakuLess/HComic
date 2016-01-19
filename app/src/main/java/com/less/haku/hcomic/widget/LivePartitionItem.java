package com.less.haku.hcomic.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.data.Live;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 16/1/19.
 * BiliBili直播子Item
 */
public class LivePartitionItem extends LinearLayout {

    @Bind(R.id.item_live_cover)
    HImageView itemLiveCover;
    @Bind(R.id.item_live_user)
    TextView itemLiveUser;
    @Bind(R.id.item_live_title)
    TextView itemLiveTitle;

    private Live live;

    public LivePartitionItem(Context context) {
        this(context, null);
    }

    public LivePartitionItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LivePartitionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_live_partition, this, true);
        ButterKnife.bind((Activity) context);
    }

    public void setLive(Live data) {
        this.live = data;
        updateView();
    }

    private void updateView() {
        itemLiveCover.setUrl(live.cover.src);
        itemLiveUser.setText(live.owner.name);
        itemLiveTitle.setText(live.title);
    }
}