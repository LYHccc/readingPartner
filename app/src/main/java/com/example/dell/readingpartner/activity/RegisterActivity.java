package com.example.dell.readingpartner.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.dell.readingpartner.R;
import com.example.dell.readingpartner.api.Api;
import com.example.dell.readingpartner.api.ApiConfig;
import com.example.dell.readingpartner.api.TtitCallback;
import com.example.dell.readingpartner.entity.LoginResponse;
import com.example.dell.readingpartner.util.StringUtil;
import com.google.gson.Gson;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {
    private EditText etAccount;
    private EditText etPwd;
    private Button btnRegister;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                register(account, pwd);
            }
        });
    }

    private void register(String account, String pwd) {
        if (StringUtil.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        if (StringUtil.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", account);
        params.put("password", pwd);
        Api.config(ApiConfig.REGISTER, params).postRequest(new TtitCallback(){

            @Override
            public void onSuccess(final String res) {
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(res, LoginResponse.class);
                if (loginResponse.getCode() == 0) {
                    showToastSync("注册成功");
                } else {
                    showToastSync("注册失败:" + loginResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("onFailure", e.toString());
            }
        });
    }
}
