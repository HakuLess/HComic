package com.less.haku.hcomic.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.less.haku.hcomic.R;
import com.less.haku.hcomic.core.holder.BangumiViewHolder;
import com.less.haku.hcomic.data.Bangumi;

import java.util.List;

/**
 * Created by HaKu on 15/12/31.
 */
public class BangumiAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Bangumi> bangumiList;

    //构造函数中初始化list
    public BangumiAdapter(Context context) {
        this.context = context;
    }

    public void setBangumiList(List<Bangumi> bangumiList) {
        this.bangumiList = bangumiList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bangumi_list, null);
        return new BangumiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BangumiViewHolder) {
            ((BangumiViewHolder) holder).bangumiTitle.setText(bangumiList.get(position).title);
            Glide.with(context).load(bangumiList.get(position).cover)
                    .into(((BangumiViewHolder) holder).bangumiCover);
//            ((BangumiViewHolder) holder).bangumiCover.setUrl(bangumiList.get(position).cover);
        }
    }

    @Override
    public int getItemCount() {
        if (bangumiList != null) {
            return bangumiList.size();
        } else {
            return 0;
        }
    }
}
