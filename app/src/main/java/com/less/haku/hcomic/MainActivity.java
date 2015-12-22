package com.less.haku.hcomic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.less.haku.hcomic.common.BaseActivity;
import com.less.haku.hcomic.data.Hitokoto;
import com.less.haku.hcomic.request.HitokotoRequest;
import com.less.haku.hcomic.request.base.HOkHttpClient;

public class MainActivity extends BaseActivity {

    private TextView hitoText;
    private TextView sourceText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestHitokoto();
            }
        });
    }

    public void initViews() {
        hitoText = (TextView) this.findViewById(R.id.main_hito_text);
        sourceText = (TextView) this.findViewById(R.id.main_hito_source);
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

    public void requestHitokoto() {

        final HitokotoRequest hitokotoRequest = new HitokotoRequest();

//        showProgressDialog("加载中");
        sendJsonRequest(hitokotoRequest, new HOkHttpClient.IRequestListener<Hitokoto>() {
            @Override
            public void onSucceed(Hitokoto response) {
//                dismissDialog();
                if (response == null) {
                    return;
                }
                hitoText.setText(response.hitokoto);
                sourceText.setText(response.source);
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
//                dismissDialog();
            }
        });
    }
}
