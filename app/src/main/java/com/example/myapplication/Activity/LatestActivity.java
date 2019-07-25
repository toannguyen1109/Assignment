package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.myapplication.Adapter.AdapterLatest;
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class LatestActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private RecyclerView mGridViewLatest;
    private List<ModelLatest> modelLatestList;
    private AdapterLatest adapterLatest;

// không nên sử dụng nhiều act như này
    // nên sử dụng fragment
    // tài liệu tham khaorL: https://guides.codepath.com/android/using-the-recyclerview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest);
        initView();

        modelLatestList = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            modelLatestList.add(new ModelLatest("", "", "", i + "", i + ""));
        }

        Log.e("size",modelLatestList.size()+"");
        adapterLatest = new AdapterLatest(modelLatestList);
        mGridViewLatest.setAdapter(adapterLatest);
        mGridViewLatest.setLayoutManager(new GridLayoutManager(this, 2));

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mGridViewLatest = findViewById(R.id.gridViewLatest);
    }
}
