package com.less.haku.hcomic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.less.haku.hcomic.common.BaseActivity;
import com.less.haku.hcomic.network.HitokotoService;
import com.less.haku.hcomic.widget.ComicPage;
import com.less.haku.hcomic.widget.CustomView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_hito_text)
    TextView hitoText;
    @Bind(R.id.main_hito_source)
    TextView sourceText;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_comic_page)
    ComicPage mainComicPage;
    @Bind(R.id.main_custom_view)
    CustomView customView;

    private HitokotoService hitokotoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.main_comic_page)
    public void refresh(View view) {
        mainComicPage.refresh();
    }

    @OnClick({R.id.main_hito_text, R.id.fab})
    public void requestHitokotoByRetrofit() {
//        hitokotoService = RetrofitSigleton.getSingleton().create(HitokotoService.class);
//
//        Call<Hitokoto> call = hitokotoService.getHitokoto();
//        call.enqueue(new Callback<Hitokoto>() {
//            @Override
//            public void onResponse(Response<Hitokoto> response) {
//                hitoText.setText(response.body().hitokoto);
//                sourceText.setText("----" + response.body().source);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    public void requestHitokoto() {
//
//        final HitokotoRequest hitokotoRequest = new HitokotoRequest();
//
////        showProgressDialog("加载中");
//        sendJsonRequest(hitokotoRequest, new HOkHttpClient.IRequestListener<Hitokoto>() {
//            @Override
//            public void onSucceed(Hitokoto response) {
////                dismissDialog();
//                if (response == null) {
//                    return;
//                }
//                hitoText.setText(response.hitokoto);
//                sourceText.setText("----" + response.source);
//            }
//
//            @Override
//            public void onFailed(int errCode, String errMsg) {
////                dismissDialog();
//            }
//        });
//    }
}
