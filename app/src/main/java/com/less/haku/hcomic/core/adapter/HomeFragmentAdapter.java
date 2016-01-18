package com.less.haku.hcomic.core.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.less.haku.hcomic.core.fragment.BanGumiSearchFragment;
import com.less.haku.hcomic.core.fragment.BangumiFragment;
import com.less.haku.hcomic.core.fragment.HitoKotoFragment;
import com.less.haku.hcomic.core.fragment.LiveFragment;

/**
 * Created by HaKu on 15/12/30.
 * 首页PagerAdapter
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"HitoKoto", "新番", "useless", "直播"};
    private Context context;
    public HomeFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new HitoKotoFragment();
            case 1 :
                return new BanGumiSearchFragment();
            case 2 :
                return new BangumiFragment();
            case 3 :
                return new LiveFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}