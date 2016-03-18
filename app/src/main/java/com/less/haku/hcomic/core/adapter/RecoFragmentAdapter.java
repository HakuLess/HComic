package com.less.haku.hcomic.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.core.activity.LivingActivity;
import com.less.haku.hcomic.core.activity.VideoActivity;
import com.less.haku.hcomic.core.holder.LiveEntranceViewHolder;
import com.less.haku.hcomic.core.holder.LiveItemViewHolder;
import com.less.haku.hcomic.core.holder.LivePartitionViewHolder;
import com.less.haku.hcomic.data.BodyItem;
import com.less.haku.hcomic.data.RecoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaKu on 16/1/18.
 * 直播首页Adapter
 */
public class RecoFragmentAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BodyItem> bodyItemList;
    private List<RecoItem> recoItemList;

    private static final int TYPE_RECO_HEAD = 0;     //标题栏
    private static final int TYPE_RECO_ITEM = 1;     //视频Item

    public RecoFragmentAdapter(Context context) {
        this.context = context;
        bodyItemList = new ArrayList<BodyItem>();
    }

    public void setRecoIndex(List<RecoItem> data) {
        this.recoItemList = data;
    }

    public int getSpanSize(int pos) {
        int viewType = getItemViewType(pos);
        switch (viewType) {
            case TYPE_RECO_HEAD:
                return 12;
            case TYPE_RECO_ITEM:
                return 6;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_RECO_HEAD:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_partition_title, null);
                return new LivePartitionViewHolder(view);
            case TYPE_RECO_ITEM:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_partition, null);
                return new LiveItemViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BodyItem item;
        if (holder instanceof LiveEntranceViewHolder) {

        } else if (holder instanceof LiveItemViewHolder) {
            item = bodyItemList.get(position);
            ((LiveItemViewHolder) holder).itemLiveCover.setUrl(item.cover);
            ((LiveItemViewHolder) holder).itemLiveTitle.setText(item.title);
            ((LiveItemViewHolder) holder).itemLiveLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (item.go) {
                        case "live" :
                            intent = new Intent(context, LivingActivity.class);
                            intent.putExtra("cid", Integer.parseInt(item.param));
                            context.startActivity(intent);
                            break;
                        case "av" :
                            Toast.makeText(context, item.param, Toast.LENGTH_SHORT).show();
                            intent = new Intent(context, VideoActivity.class);
                            intent.putExtra("cid", item.param);
                            context.startActivity(intent);
                            break;
                        case "bangumi_list":
                            Toast.makeText(context, "番剧列表", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (recoItemList != null) {
            return calSize(recoItemList);
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_RECO_ITEM;
    }


    private int calSize(List<RecoItem> recoItems) {
        int result = 0;
        bodyItemList.clear();
        for (RecoItem reco : recoItems) {
            result += reco.body.size();
            bodyItemList.addAll(reco.body);
        }
        return result;
    }
}
