package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        serverURL = BuildConfig.isRelease;
//        Toast.makeText(this, "serverURL:" + serverURL, Toast.LENGTH_SHORT).show();
//
        if (BuildConfig.isRelease) {
//            Log.d(TAG, "onCreate: 当前是：集成化 线上环境，以app壳为主导运行的方式");
//            Toast.makeText(this, "当前是：集成化 线上环境，以app壳为主导运行的方式", Toast.LENGTH_SHORT).show();
        } else {
//            Log.d(TAG, "onCreate: 当前是：组件化 测试环境，所有的子模块都可以独立运行");
//            Toast.makeText(this, "当前是：组件化 测试环境，所有的子模块都可以独立运行", Toast.LENGTH_SHORT).show();
        }

    }
}