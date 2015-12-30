package com.less.haku.hcomic.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.less.haku.hcomic.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HaKu on 15/12/30.
 */
public class HitoKotoFragment extends Fragment {

    @Bind(R.id.frag_hito_text)
    TextView hitoText;
    @Bind(R.id.frag_hito_source)
    TextView sourceText;

    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    public static HitoKotoFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        HitoKotoFragment fragment = new HitoKotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hitokoto,container,false);
        ButterKnife.bind(this, view);

        hitoText.setText("第" + mPage + "页");
        Log.d("fragment", "第" + mPage + "页");
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
