package com.less.haku.hcomic.request.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.less.haku.hcomic.util.JsonUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by HaKu on 15/11/6.
 */
public class HOkHttpClient {

    private static int SUCCESS_CODE = 200;
    private JSONObject object;
    private OkHttpClient client = new OkHttpClient();
    private static HOkHttpClient _instance;
    private Handler handler;

    public HOkHttpClient() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static HOkHttpClient instance(Application application) {
        if (_instance == null) {
            _instance = new HOkHttpClient();
        }
        return _instance;
    }

    public void sendRequest(final BaseRequest baseRequest, final IRequestListener listener) {
        Log.d("send request", baseRequest.request.urlString());
        baseRequest.call = client.newCall(baseRequest.request);
        baseRequest.call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    object = new JSONObject(response.body().string());
                } catch (JSONException e) {

                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (object != null) {
                            listener.onSucceed(JsonUtil.json2Java(object, baseRequest.outCls));
                        }
                    }
                });
            }

            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("request failed", request.urlString());
            }
        });
    }

    public interface IRequestListener<T> {
        void onSucceed(T response);
        void onFailed(int errCode, String errMsg);
    }
}
