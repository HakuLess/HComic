package com.less.haku.hcomic.core.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.less.haku.hcomic.core.fragment.BanGumiFragment;
import com.less.haku.hcomic.core.fragment.HitoKotoFragment;

/**
 * Created by HaKu on 15/12/30.
 */
public class HitoFragmentAdapter extends FragmentPagerAdapter {
    public final int COUNT = 3;
    private String[] titles = new String[]{"HitoKoto", "新番", "useless"};
    private Context context;
    public HitoFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("sdggsdg", position + " pos");
        switch (position) {
            case 0 :
                return HitoKotoFragment.newInstance();
            case 1 :
                return BanGumiFragment.newInstance();
            case 2 :
                return HitoKotoFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}