package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.Adapter.AdapterFavorites;
import com.example.myapplication.Adapter.AdapterLatest;
import com.example.myapplication.Model.ModelFavorites;
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyFavoryitesActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private RecyclerView mRvFavorites;
    private List<ModelFavorites> modelFavoritesList;
    private AdapterFavorites adapterFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favoryites);
        initView();

        modelFavoritesList = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            modelFavoritesList.add(new ModelFavorites("", "", "", i + "", i + ""));
        }


        adapterFavorites = new AdapterFavorites(modelFavoritesList);
        mRvFavorites.setAdapter(adapterFavorites);
        mRvFavorites.setLayoutManager(new GridLayoutManager(this, 2));

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mRvFavorites = findViewById(R.id.rvFavorites);
    }
}
