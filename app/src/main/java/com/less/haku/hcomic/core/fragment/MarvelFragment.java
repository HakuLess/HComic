package com.less.haku.hcomic.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseFragment;
import com.less.haku.hcomic.data.MarvelResponse;
import com.less.haku.hcomic.network.MarvelService;
import com.less.haku.hcomic.network.base.RetrofitSigleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by HaKu on 16/1/4.
 */
public class MarvelFragment extends BaseFragment {

    @Bind(R.id.frag_hito_text)
    TextView hitoText;
    @Bind(R.id.frag_hito_source)
    TextView sourceText;
    private MarvelService marvelService;

    public static MarvelFragment newInstance() {
        MarvelFragment fragment = new MarvelFragment();
        return fragment;
    }

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
            //请求Marvel API
            requestMarvel();
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
        marvelService = RetrofitSigleton.getMarvel().create(MarvelService.class);
    }

    //请求Marvel
    public void requestMarvel() {

        Call<MarvelResponse> call = marvelService.getMarvel();
        call.enqueue(new Callback<MarvelResponse>() {
            @Override
            public void onResponse(Response<MarvelResponse> response) {
                if (response.body() != null) {
//                    hitoText.setText(response.body().code + "  ----  ");
//                sourceText.setText("----" + response.body().status);
                }
            }

            @Override
            public void onFailure(Throwable t) {
//                Log.d("failure", t.getMessage());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
