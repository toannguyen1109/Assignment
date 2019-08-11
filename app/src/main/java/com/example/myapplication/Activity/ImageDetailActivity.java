package com.example.myapplication.Activity;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Database.DAO;
import com.example.myapplication.Model.ModelFavorites;
import com.example.myapplication.ModelEmbed.Post;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;
import com.example.myapplication.event.IDownloadListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDetailActivity extends AppCompatActivity {

    private ImageView mImgBack;
    private FloatingActionMenu mFabMenu;
    private ImageView mImgDetail;
    List<Post> posts = new ArrayList<>();
    List<ModelFavorites> modelFavoritesList;

    private FloatingActionButton mIconHeart;
    private FloatingActionButton mIconShare;
    private FloatingActionButton mImgSave;
    private FloatingActionButton mImgSetBg;

    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        initView();
        dao = new DAO(this);
        dao.open();
        Intent intent = getIntent();
        final String link = intent.getStringExtra("link");
        Picasso.with(ImageDetailActivity.this).load(link).into(mImgDetail);

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        PolyRetrofit.getInstance().getSourceUrl().enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                String source = response.body().get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
//                posts.addAll(response.body());
//                Log.e("source", "onResponse: " + source);
//
//                Picasso.with(ImageDetailActivity.this).load(source).into(mImgDetail);
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//            }
//        });


        mImgSetBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWall();

            }
        });

        mIconHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelFavoritesList = new ArrayList<>();
                ModelFavorites md = new ModelFavorites(link);
                Log.e("data", "onClick: " + link );
                dao.Them(md,ImageDetailActivity.this);
                Log.d("sizeHeart", "" + modelFavoritesList.size());
//                }


            }
        });

        mIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String link = posts.get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
//                Log.e("share", "onClick: " + link);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                share.putExtra(Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(share, "Share link!"));
            }
        });


        mImgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PolyRetrofit.getInstance().getSourceUrl().enqueue(new Callback<List<Post>>() {
//                    @Override
//                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                        String source = response.body().get(position).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
//                        Log.e("source", "onResponse: " + source);
//                        imageDownload(ImageDetailActivity.this, source, new IDownloadListener() {
//                            @Override
//                            public void success() {
//                                Toast.makeText(ImageDetailActivity.this, "download success", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void failed() {
//                                Toast.makeText(ImageDetailActivity.this, "download failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Post>> call, Throwable t) {
//
//                    }
//                });
                imageDownload(ImageDetailActivity.this, link, new IDownloadListener() {
                    @Override
                    public void success() {
                        Toast.makeText(ImageDetailActivity.this, "download success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed() {
                        Toast.makeText(ImageDetailActivity.this, "download failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void initView() {
        mImgBack = findViewById(R.id.imgBack);
        mFabMenu = findViewById(R.id.fab_menu);
        mImgDetail = findViewById(R.id.imgDetail);
        mIconHeart = findViewById(R.id.iconHeart);
        mIconShare = findViewById(R.id.iconShare);
        mImgSave = findViewById(R.id.imgSave);
        mImgSetBg = findViewById(R.id.imgSetBg);
    }

    //save image
    public static void imageDownload(Context ctx, String url, final IDownloadListener downloadListener) {
        Picasso.with(ctx)
                .load(url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        saveImage(bitmap);
                        downloadListener.success();
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        downloadListener.failed();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }


    private static void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap viewToBitmap(View view, int width, int height) {
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        view.draw(canvas);
        return bm;
    }

    private void startWall() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(viewToBitmap(mImgDetail, mImgDetail.getWidth(), mImgDetail.getHeight()));
            Toast.makeText(this, "Succsess", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
