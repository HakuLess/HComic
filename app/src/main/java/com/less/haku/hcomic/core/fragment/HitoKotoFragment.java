package com.less.haku.hcomic.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.data.Hitokoto;
import com.less.haku.hcomic.network.HitokotoService;
import com.less.haku.hcomic.network.base.RetrofitSigleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by HaKu on 15/12/30.
 * 一言fragment
 */
public class HitoKotoFragment extends Fragment {

    @Bind(R.id.frag_hito_text)
    TextView hitoText;
    @Bind(R.id.frag_hito_source)
    TextView sourceText;
    private HitokotoService hitokotoService;

    public static HitoKotoFragment newInstance() {
        HitoKotoFragment fragment = new HitoKotoFragment();
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
            //请求一言API
            requestHitokotoByRetrofit();
        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    //请求一言
    public void requestHitokotoByRetrofit() {
        hitokotoService = RetrofitSigleton.getSingleton().create(HitokotoService.class);

        Call<Hitokoto> call = hitokotoService.getHitokoto();
        call.enqueue(new Callback<Hitokoto>() {
            @Override
            public void onResponse(Response<Hitokoto> response) {
                hitoText.setText(response.body().hitokoto);
                sourceText.setText("----" + response.body().source);
            }

            @Override
            public void onFailure(Throwable t) {
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
