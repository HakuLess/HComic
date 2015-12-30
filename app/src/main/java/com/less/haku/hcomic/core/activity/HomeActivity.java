package com.less.haku.hcomic.core.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseActivity;
import com.less.haku.hcomic.core.adapter.HitoFragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 15/12/30.
 */
public class HomeActivity extends BaseActivity {
    @Bind(R.id.home_tabLayout)
    TabLayout tabLayout;    //首页tab布局
    @Bind(R.id.home_viewPager)
    ViewPager viewPager;    //首页内容页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initTabLayout();
    }

    public void initTabLayout() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        HitoFragmentAdapter adapter = new HitoFragmentAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
