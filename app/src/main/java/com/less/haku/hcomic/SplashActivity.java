package com.less.haku.hcomic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.less.haku.hcomic.common.BaseActivity;
import com.less.haku.hcomic.core.activity.HomeActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by HaKu on 16/1/6.
 * App启动页
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_time)
    TextView splashTime;
    @Bind(R.id.splash_icon)
    ImageView splashIcon;

    private int showTime;   //闪页显示时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        compositeSubscription = new CompositeSubscription();

        showTime = 2;
        enterApp();
    }

    public void enterApp() {

        Subscription subscription = Observable.interval(showTime, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        //aLong代表interval调用次数
                        enterMain(aLong);
                        Log.d("test", aLong + "");
                    }
                });

        //将 订阅事件 加入 subscription集合(Set)，用于与Activity生命周期绑定，onDestroy时解除事件注册
        compositeSubscription.add(subscription);
    }

    public void enterMain(Long along) {
        if (along != showTime) {
            splashTime.setText("剩余" + (showTime - along) + "s");
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
