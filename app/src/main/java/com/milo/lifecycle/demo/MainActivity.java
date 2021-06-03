package com.milo.lifecycle.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.milo.lifecycleplugin.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}