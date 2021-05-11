package com.example.dell.readingpartner;

import android.view.View;
import android.widget.Button;
import com.example.dell.readingpartner.activity.BaseActivity;
import com.example.dell.readingpartner.activity.HomeActivity;
import com.example.dell.readingpartner.activity.RegisterActivity;

public class MainActivity extends BaseActivity {

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                navigateTo(LoginActivity.class);
                navigateTo(HomeActivity.class);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(RegisterActivity.class);
            }
        });
    }
}
