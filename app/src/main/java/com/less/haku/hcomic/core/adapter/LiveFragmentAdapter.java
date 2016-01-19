package com.less.haku.hcomic.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.core.holder.LiveEntranceViewHolder;
import com.less.haku.hcomic.core.holder.LiveItemViewHolder;
import com.less.haku.hcomic.core.holder.LivePartitionViewHolder;
import com.less.haku.hcomic.data.Live;
import com.less.haku.hcomic.data.LiveIndex;
import com.less.haku.hcomic.data.PartitionSub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaKu on 16/1/18.
 * 直播首页Adapter
 */
public class LiveFragmentAdapter extends RecyclerView.Adapter {
    private Context context;
    private LiveIndex liveIndex;

    private int entranceSize;
    private int partitionSize;

    private static final int TYPE_ENTRANCE = 0;     //快速入口
    private static final int TYPE_LIVE_ITEM = 1;    //直播Item
    private static final int TYPE_PARTITION = 2;    //分类Title

    public LiveFragmentAdapter(Context context) {
        this.context = context;
    }

//    private int[] liveSize = new int[];
    private List<Integer> liveSizes = new ArrayList<>();
    private int tempSize;
    public void setLiveIndex(LiveIndex data) {
        this.liveIndex = data;
        entranceSize = data.entranceIcons.size();
        partitionSize = data.partitions.size();

        liveSizes.clear();
        tempSize = 0;
        for (int i = 0; i < partitionSize; i++) {
            liveSizes.add(tempSize);
            tempSize += data.partitions.get(i).lives.size();
//            liveSize[i] = tempSize;
        }
    }

    public int getSpanSize(int pos) {
        int viewType = getItemViewType(pos);
        switch (viewType) {
            case TYPE_ENTRANCE:
                return 3;
            case TYPE_LIVE_ITEM:
                return 6;
            case TYPE_PARTITION:
                return 12;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ENTRANCE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_entrance, null);
                return new LiveEntranceViewHolder(view);
            case TYPE_LIVE_ITEM:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_partition, null);
                return new LiveItemViewHolder(view);
            case TYPE_PARTITION:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_live_partition_title, null);
                return new LivePartitionViewHolder(view);
        }
        return null;
    }

    private PartitionSub partition;
    private Live item;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LiveEntranceViewHolder) {
            ((LiveEntranceViewHolder) holder).title.setText(liveIndex.entranceIcons.get(position).name);
            ((LiveEntranceViewHolder) holder).image.setUrl(liveIndex.entranceIcons.get(position).entrance_icon.src);
        } else if (holder instanceof LiveItemViewHolder) {
            item = liveIndex.partitions.get(partitionCol(position)).lives.get(position - 1 - entranceSize - partitionCol(position) * 5);
            ((LiveItemViewHolder) holder).itemLiveCover.setUrl(item.cover.src);
            ((LiveItemViewHolder) holder).itemLiveTitle.setText(item.title);
            ((LiveItemViewHolder) holder).itemLiveUser.setText(item.owner.name);
        } else if (holder instanceof LivePartitionViewHolder) {
            partition = liveIndex.partitions.get(partitionCol(position)).partition;
            ((LivePartitionViewHolder) holder).itemIcon.setUrl(partition.sub_icon.src);
            ((LivePartitionViewHolder) holder).itemTitle.setText(partition.name);
            ((LivePartitionViewHolder) holder).itemCount.setText("当前" + partition.count + "个直播");
        }
    }

    @Override
    public int getItemCount() {
        if (liveIndex != null) {
            return liveIndex.entranceIcons.size()
                    + liveIndex.partitions.size() * 5;
//                    + tempSize;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < entranceSize) {
            return TYPE_ENTRANCE;
        } else if (ifPartitionTitle(position)) {
            return TYPE_PARTITION;
        } else {
            return TYPE_LIVE_ITEM;
        }
    }

    /**
     * 获取当前Item在第几组中
     * */
    private int partitionCol(int pos) {
        pos -= entranceSize;
        return pos / 5;
    }

    private boolean ifPartitionTitle(int pos) {
        pos -= entranceSize;
        return (pos % 5 == 0);
    }
}
