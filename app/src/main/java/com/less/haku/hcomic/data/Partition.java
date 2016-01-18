package com.less.haku.hcomic.data;

import java.util.List;

/**
 * Created by HaKu on 16/1/18.
 * 直播分割？
 */
public class Partition {
    public PartitionSub partition;
    public List<Live> lives;
}

class PartitionSub {
    public int id;
    public String name;
    public String area;
    public IconHW sub_icon;
    public int count;
}

class Live {
    public Owner owner;
    public IconHW cover;
    public String title;
    public int room_id;
    public int online;
}

class Owner {
    public String face;
    public int mid;
    public String name;
}
