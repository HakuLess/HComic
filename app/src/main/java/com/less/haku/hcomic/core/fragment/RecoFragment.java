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
import android.widget.Toast;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseFragment;
import com.less.haku.hcomic.core.adapter.RecoFragmentAdapter;
import com.less.haku.hcomic.data.RecoItem;
import com.less.haku.hcomic.network.AppBiliBiliService;
import com.less.haku.hcomic.network.base.API;
import com.less.haku.hcomic.network.base.RetrofitSingleton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HaKu on 16/3/18.
 * BiliBili推荐页面
 */
public class RecoFragment extends BaseFragment {

    @Bind(R.id.frag_live_recycler)
    RecyclerView recoRecyclerView;
    @Bind(R.id.frag_live_refresh)
    SwipeRefreshLayout recoRefresh;

    private AppBiliBiliService appBiliBiliService;
    private RecoFragmentAdapter recoFragmentAdapter;
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

            recoFragmentAdapter = new RecoFragmentAdapter(this.getContext());
            recoRecyclerView.setAdapter(recoFragmentAdapter);

//            LinearLayoutManager layout = new LinearLayoutManager(this.getContext());
//            layout.setOrientation(LinearLayoutManager.VERTICAL);
            GridLayoutManager layout = new GridLayoutManager(this.getContext(), 12);
            layout.setOrientation(LinearLayoutManager.VERTICAL);
            layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return recoFragmentAdapter.getSpanSize(position);
                }
            });

            recoRecyclerView.setLayoutManager(layout);
            recoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    recoRequest();
                }
            });
            //开始请求
            recoRequest();
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
        appBiliBiliService = RetrofitSingleton.getAppBiliBili().create(AppBiliBiliService.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 请求列表
     * */
    private void recoRequest() {
        API.getRecoIndex(appBiliBiliService)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RecoItem>>() {
                    @Override
                    public void onCompleted() {
                        recoRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Retrofit error", e.getCause().toString());
                        recoRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<RecoItem> result) {
                        recoFragmentAdapter.setRecoIndex(result);
                        recoFragmentAdapter.notifyDataSetChanged();
//                        liveFragmentAdapter.setLiveIndex(liveIndex);
//                        liveFragmentAdapter.notifyDataSetChanged();
//                        liveRecyclerView.scrollToPosition(0);
                        List<RecoItem> recoItemList = result;
                        Toast.makeText(getContext(),"success", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
