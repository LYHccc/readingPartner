package com.example.dell.readingpartner.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.dueeeke.videoplayer.player.VideoViewManager;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    public void navigateTo(Class cls) {
        Intent in = new Intent(mContext, cls);
        startActivity(in);
    }

    protected void insertValue(String key, String val) {
        SharedPreferences sp = getSharedPreferences("sp_ttit", MODE_PRIVATE);
        SharedPreferences.Editor  editor = sp.edit();
        editor.putString(key, val);
        editor.apply();
    }

    protected String findByKey(String key) {
        SharedPreferences sp = getSharedPreferences("sp_ttit", MODE_PRIVATE);
        return sp.getString(key, "");
    }

    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
    public void navigateToWithFlag(Class cls, int flags) {
        Intent in = new Intent(mContext, cls);
        in.setFlags(flags);
        startActivity(in);
    }
}
