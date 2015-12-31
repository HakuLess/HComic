package com.less.haku.hcomic.core.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.less.haku.hcomic.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 15/12/31.
 * 新番Item的ViewHodler
 */
public class BangumiViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_bangumi_title)
    public TextView bangumiTitle;
    @Bind(R.id.item_bangumi_cover)
    public ImageView bangumiCover;

    public BangumiViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
