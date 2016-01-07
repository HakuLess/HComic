package com.less.haku.hcomic.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by HaKu on 16/1/4.
 *
 */
public class BaseFragment extends Fragment {

    protected CompositeSubscription compositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeSubscription = new CompositeSubscription();
        initServices();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeSubscription.unsubscribe();
    }

    public void initServices() {

    }
}
