package com.less.haku.hcomic.core.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseActivity;
import com.less.haku.hcomic.core.adapter.HomeFragmentAdapter;

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

        //初始化tabLayout，填充fragment
        initTabLayout();
    }

    public void initTabLayout() {
        //tabLayout与viewPager绑定
        //使用该方法绑定，无法通过viewPager让tabLayout显示icon，只能是文本
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //填充Adapter
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos) {
                    case 0 :
                        tab.setIcon(R.drawable.rank_id2);
                        break;
                    case 1 :
                        tab.setIcon(R.drawable.rank_id2);
                        break;
                    case 2 :
                        tab.setIcon(R.drawable.rank_id2);
                        break;
                }
                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos) {
                    case 0 :
                        tab.setIcon(R.drawable.lost);
                        break;
                    case 1 :
                        tab.setIcon(R.drawable.lost);
                        break;
                    case 2 :
                        tab.setIcon(R.drawable.lost);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
