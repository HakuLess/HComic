package com.less.haku.hcomic.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.core.holder.LiveEntranceViewHolder;
import com.less.haku.hcomic.data.LiveIndex;

/**
 * Created by HaKu on 16/1/18.
 * 直播首页Adapter
 */
public class LiveFragmentAdapter extends RecyclerView.Adapter {
    private Context context;
    private LiveIndex liveIndex;


    public LiveFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setLiveIndex(LiveIndex data) {
        this.liveIndex = data;
    }

    public int getSpanSize(int pos) {
        int viewType = getItemViewType(pos);
        switch (viewType) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 4;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_entrance, null);
        return new LiveEntranceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LiveEntranceViewHolder) {
            ((LiveEntranceViewHolder) holder).title.setText(liveIndex.entranceIcons.get(position).name);

//            Glide.with(context).load(bangumiList.get(position).cover)
//                    .into(((BangumiViewHolder) holder).bangumiCover);
            ((LiveEntranceViewHolder) holder).image.setUrl(liveIndex.entranceIcons.get(position).entrance_icon.src);
        }
    }

    @Override
    public int getItemCount() {
        if (liveIndex != null) {
            return liveIndex.entranceIcons.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
