package com.example.myapplication.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.Adapter.AdapterCategory;
import com.example.myapplication.Model.ModelCatagory;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {


    private ImageView mImgBack;
    private RecyclerView mRvCate;
    private List<ModelCatagory> modelCatagoryList;
    private AdapterCategory adapterCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initView();


        modelCatagoryList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            modelCatagoryList.add(new ModelCatagory("", "Animals " + "(" + i + ")"));
        }

        adapterCategory = new AdapterCategory(modelCatagoryList);
        mRvCate.setAdapter(adapterCategory);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRvCate.setLayoutManager(manager);

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mRvCate = findViewById(R.id.rvCate);
    }
}
