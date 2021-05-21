package com.example.dell.readingpartner.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.example.dell.readingpartner.R;
import com.example.dell.readingpartner.activity.LoginActivity;
import com.example.dell.readingpartner.activity.MyCollectActivity;


public class MyFragment extends BaseFragment {

    @BindView(R.id.img_header)
    ImageView imgHeader;

    Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    public MyFragment() {
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @OnClick({R.id.img_header, R.id.rl_collect, R.id.rl_skin, R.id.rl_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_header:
                break;
            case R.id.rl_collect:
                navigateTo(MyCollectActivity.class);
                break;
            case R.id.rl_skin:
                break;
            case R.id.rl_logout:
                removeByKey("token");
                navigateToWithFlag(LoginActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
