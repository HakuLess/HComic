package com.less.haku.hcomic.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseFragment;
import com.less.haku.hcomic.core.adapter.LiveFragmentAdapter;
import com.less.haku.hcomic.data.LiveIndex;
import com.less.haku.hcomic.network.LiveBilibiliService;
import com.less.haku.hcomic.network.base.API;
import com.less.haku.hcomic.network.base.RetrofitSigleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HaKu on 16/1/18.
 * BiliBili直播页面
 */
public classLiveFragment extends BaseFragment {

    @Bind(R.id.frag_live_recycler)
    RecyclerView liveRecyclerView;
    @Bind(R.id.frag_live_refresh)
    SwipeRefreshLayout liveRefresh;

    private LiveBilibiliService liveService;
    private LiveFragmentAdapter liveFragmentAdapter;

    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_live, container, false);
            ButterKnife.bind(this, rootView);

            liveFragmentAdapter = new LiveFragmentAdapter(this.getContext());
            liveRecyclerView.setAdapter(liveFragmentAdapter);

//            LinearLayoutManager layout = new LinearLayoutManager(this.getContext());
//            layout.setOrientation(LinearLayoutManager.VERTICAL);
            GridLayoutManager layout = new GridLayoutManager(this.getContext(), 12);
            layout.setOrientation(LinearLayoutManager.VERTICAL);
            layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return liveFragmentAdapter.getSpanSize(position);
                }
            });

            liveRecyclerView.setLayoutManager(layout);
            liveRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //开始请求
                    liveRequest();
                }
            });
            //开始请求
            liveRequest();
        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void initServices() {
        liveService = RetrofitSigleton.getLiveBilibiliRetrofit().create(LiveBilibiliService.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void liveRequest() {
        API.getLiveIndex(liveService)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveIndex>() {
                    @Override
                    public void onCompleted() {
                        liveRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit error", e.getCause().toString());
                        liveRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onNext(LiveIndex liveIndex) {
                        liveFragmentAdapter.setLiveIndex(liveIndex);
                        liveFragmentAdapter.notifyDataSetChanged();
                        liveRecyclerView.scrollToPosition(0);
                    }
                });
    }
}
