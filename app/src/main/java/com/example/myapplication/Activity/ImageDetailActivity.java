package com.example.myapplication.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.ModelRetrofit.Post;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private FloatingActionMenu mFabMenu;
    private ImageView mImgDetail;
    List<Post> posts;
    Bitmap bm;

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

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", 0);
        PolyRetrofit.getInstance().getSourceUrl().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                String source = response.body().get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
                Log.e("source", "onResponse: " + source);

                Picasso.with(ImageDetailActivity.this).load(source).into(mImgDetail);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        Log.e("int", "onCreate: "+ position);
//        String source = posts.get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
//        Log.e("source", " "+source );
//
//
//
//        mImgDetail.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        // check to see if the file exists
//        File file = new File(source);
//        if (file.exists()){
//
//            bm = BitmapFactory.decodeFile(source);
//        }
//
//
//        // set the image and text
//        mImgDetail.setImageBitmap(bm);
    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mFabMenu = findViewById(R.id.fab_menu);
        mImgDetail = findViewById(R.id.imgDetail);
    }
}
