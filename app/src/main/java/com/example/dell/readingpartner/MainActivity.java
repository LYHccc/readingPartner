package com.example.dell.readingpartner;

import android.view.View;
import android.widget.Button;
import com.example.dell.readingpartner.activity.BaseActivity;
import com.example.dell.readingpartner.activity.HomeActivity;
import com.example.dell.readingpartner.activity.LoginActivity;
import com.example.dell.readingpartner.activity.RegisterActivity;
import com.example.dell.readingpartner.util.StringUtil;

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
        if (!StringUtil.isEmpty(findByKey("token"))) {
            navigateTo(HomeActivity.class);
            finish();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(LoginActivity.class);
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
