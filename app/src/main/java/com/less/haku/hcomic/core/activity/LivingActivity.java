package com.less.haku.hcomic.core.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.less.haku.hcomic.R;
import com.less.haku.hcomic.common.BaseActivity;
import com.less.haku.hcomic.network.LiveBilibiliService;
import com.less.haku.hcomic.network.base.RetrofitSigleton;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by HaKu on 16/3/14.
 * 直播播放Activity
 */
public class LivingActivity extends BaseActivity {
    @Bind(R.id.video_view)
    SurfaceView videoView;
    @Bind(R.id.start)
    TextView start;
    @Bind(R.id.pause)
    TextView pause;
    @Bind(R.id.stop)
    TextView stop;

    private IjkMediaPlayer ijkMediaPlayer;
    private SurfaceHolder holder;
    private OkHttpClient client;


    private LiveBilibiliService liveService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        client = new OkHttpClient();
        holder = videoView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();

        liveService = RetrofitSigleton.getLiveBilibiliRetrofit().create(LiveBilibiliService.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void execute() throws Exception {
        ijkMediaPlayer.stop();

        int cid = getIntent().getIntExtra("cid", 0);
        String url = "http://live.bilibili.com/api/playurl?player=1&quality=0&cid=" + cid;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String str = response.body().string();
            Log.d("response", str);
            String result = str.substring(str.lastIndexOf("[") + 1, str.lastIndexOf("]") - 1);
            playVideo(result);
        }
    }



    private void playVideo(String uri) {
        try {
            ijkMediaPlayer.setDataSource(this, Uri.parse(uri));
            ijkMediaPlayer.setDisplay(holder);
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    ijkMediaPlayer.setDisplay(holder);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ijkMediaPlayer.setKeepInBackground(false);
    }

    @OnClick(R.id.start)
    void start() {
        ijkMediaPlayer.start();
    }

    @OnClick(R.id.pause)
    void pause() {
        ijkMediaPlayer.pause();
    }

    @OnClick(R.id.stop)
    void stop() {
        ijkMediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaPlayer.release();
    }
}
