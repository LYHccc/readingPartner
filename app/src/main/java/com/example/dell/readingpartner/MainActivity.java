package com.example.dell.readingpartner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.dell.readingpartner.activity.LoginActivity;
import com.example.dell.readingpartner.activity.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }
}
