package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.github.clans.fab.FloatingActionMenu;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private FloatingActionMenu mFabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        initView();
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mFabMenu = findViewById(R.id.fab_menu);
    }
}
