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
import java.security.MessageDigest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by HaKu on 16/3/18.
 * 视频点播
 */
public class VideoActivity extends BaseActivity {
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

        String cid = getIntent().getStringExtra("cid");

        String appkey = "f3bb208b3d081dc8";
        String secretkey = "ea85624dfcf12d7cc7b2b3a94fac1f2c";
        String sign_this = string2MD5("appkey=" + appkey + "&cid=" + cid + secretkey);
        String url = "http://interface.bilibili.com/playurl?appkey=" + appkey + "&cid=" + cid + "&sign=" + sign_this;
        Log.d("video request", url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String str = response.body().string();
            Log.d("response", str);
            String result = str.substring(str.lastIndexOf("[") + 1, str.lastIndexOf("]") - 1);
            Log.d("video result", result);
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

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
