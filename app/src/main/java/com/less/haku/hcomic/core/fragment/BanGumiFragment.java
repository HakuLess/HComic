package com.less.haku.hcomic.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseFragment;
import com.less.haku.hcomic.core.adapter.BangumiAdapter;
import com.less.haku.hcomic.data.Bangumi;
import com.less.haku.hcomic.network.BangumiService;
import com.less.haku.hcomic.network.base.RetrofitSigleton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HaKu on 15/12/31.
 * 新番Fragment
 */
public class BanGumiFragment extends BaseFragment {

    @Bind(R.id.frag_ban_list)
    RecyclerView banReyclerView;
    @Bind(R.id.frag_ban_refresh)
    SwipeRefreshLayout banRefresh;
    @Bind(R.id.frag_ban_year)
    Spinner fragBanYear;
    @Bind(R.id.frag_ban_month)
    Spinner fragBanMonth;
    @Bind(R.id.frag_ban_search)
    Button fragBanSearch;

    private BangumiService bangumiAppService;
    private BangumiService bangumiService;
    private BangumiAdapter bangumiAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View rootView;

    private String month = "1";
    private String year = "2016";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_bangumi, container, false);
            ButterKnife.bind(this, rootView);

            bangumiAdapter = new BangumiAdapter(this.getContext());
            banReyclerView.setAdapter(bangumiAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            banReyclerView.setLayoutManager(linearLayoutManager);
            //请求新番API
            requestBangumiByRetrofit(year, month);

            banRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    requestBangumiByRetrofit(year, month);
                }
            });
        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        ButterKnife.bind(this, rootView);
        //TODO 测试请求新番首页
//        requestIndex();
        return rootView;
    }

    @Override
    public void initServices() {
        bangumiService = RetrofitSigleton.getBiliBili().create(BangumiService.class);
        bangumiAppService = RetrofitSigleton.getBangumi().create(BangumiService.class);
    }

    @OnClick(R.id.frag_ban_search)
    public void search(View view) {
        month = fragBanMonth.getSelectedItem().toString();
        year = fragBanYear.getSelectedItem().toString();
        requestBangumiByRetrofit(year, month);
    }

//    public void requestIndex() {
//        bangumiAppService.getIndexRx()
//                .subscribe(new Subscriber<Result<BangumiIndex>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e("aaaaaaaa", "bbbbbbbbbbb");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("aaaaaaaa", e.getCause().toString());
//                    }
//
//                    @Override
//                    public void onNext(Result<BangumiIndex> bangumiIndexResult) {
//                        Log.e("aaaaaaaa", bangumiIndexResult.code + "" +
//                                bangumiIndexResult.result.recommendCategory.get(0).cover);
//                    }
//                });
//
//    }

    //请求新番
    public void requestBangumiByRetrofit(String year, String month) {
        bangumiService.getBangumiRx(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Bangumi>>() {
                    @Override
                    public void onCompleted() {
                        banRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        banRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<Bangumi> bangumis) {
                        bangumiAdapter.setBangumiList(bangumis);
                        bangumiAdapter.notifyDataSetChanged();
                        banReyclerView.scrollToPosition(0);
                    }
                });

//        Call<List<Bangumi>> call = bangumiService.getBangumi(year, month);
//        call.enqueue(new Callback<List<Bangumi>>() {
//            @Override
//            public void onResponse(Response<List<Bangumi>> response) {
//                bangumiAdapter.setBangumiList(response.body());
//                bangumiAdapter.notifyDataSetChanged();
//                banReyclerView.scrollToPosition(0);
//                banRefresh.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                banRefresh.setRefreshing(false);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}