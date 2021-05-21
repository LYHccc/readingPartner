package com.example.dell.readingpartner.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.dueeeke.videoplayer.player.VideoViewManager;

import static android.content.Context.MODE_PRIVATE;

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
            initView();
        }
        //将fragment和butterKnife进行绑定
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /**
     * 释放绑定
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    public void navigateTo(Class cls) {
        Intent in = new Intent(getActivity(), cls);
        startActivity(in);
    }

    protected void saveStringToSp(String key, String val) {
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit", MODE_PRIVATE);
        SharedPreferences.Editor  editor = sp.edit();
        editor.putString(key, val);
        editor.commit();
    }

    protected String getStringFromSp(String key) {
        SharedPreferences sp = getActivity().getSharedPreferences("sp_ttit", MODE_PRIVATE);
        return sp.getString(key, "");
    }

    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
}
