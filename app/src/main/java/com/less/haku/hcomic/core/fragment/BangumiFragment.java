package com.less.haku.hcomic.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseFragment;
import com.less.haku.hcomic.data.TidRecommend;
import com.less.haku.hcomic.network.AppBiliBiliService;
import com.less.haku.hcomic.network.base.RetrofitSingleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by HaKu on 16/1/5.
 */
public class BangumiFragment extends BaseFragment {

    @Bind(R.id.frag_hito_text)
    TextView fragHitoText;
    @Bind(R.id.frag_hito_source)
    TextView fragHitoSource;
    private AppBiliBiliService appBiliBiliService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_hitokoto, container, false);
            ButterKnife.bind(this, rootView);
            //请求APP BiliBili API

        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        ButterKnife.bind(this, rootView);
        requestRecommend();
        return rootView;
    }

    @Override
    public void initServices() {
        appBiliBiliService = RetrofitSingleton.getAppBiliBili().create(AppBiliBiliService.class);
    }

    public void requestRecommend() {

        Call<TidRecommend> call = appBiliBiliService.getRecommed(1, 10, 1);
        call.enqueue(new Callback<TidRecommend>() {
            @Override
            public void onResponse(Response<TidRecommend> response) {
                fragHitoText.setText(response.body().list.get(0).description);
                fragHitoSource.setText("----" + response.body().list.size());
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
