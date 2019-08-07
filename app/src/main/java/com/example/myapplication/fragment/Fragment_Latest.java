package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.AdapterLatest;
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.ModelRetrofit.Post;
import com.example.myapplication.ModelRetrofit.WpFeaturedmedium;
import com.example.myapplication.ModelRetrofit.WpFeaturedmedium_;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Latest extends Fragment {
    private RecyclerView mGridViewLatest;
    private List<ModelLatest> modelLatestList;
    private AdapterLatest adapterLatest;

    private List<Post> load = new ArrayList<>();;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        initView(view);
        mGridViewLatest.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterLatest = new AdapterLatest(load);
        mGridViewLatest.setAdapter(adapterLatest);

        PolyRetrofit.getInstance().getSourceUrl().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.e("code", "" + response.code());
                if (response.code() == 200) {
                    if (response.body() == null) return;
                    adapterLatest.setLoads(response.body());
                    Log.e("abcde", "onResponse: " + response.body().get(0).getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getMediumLarge().getSourceUrl());
                }
            }
            // ok
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });




        modelLatestList = new ArrayList<>();

//
//        for (int i = 0; i < 40; i++) {
//            modelLatestList.add(new ModelLatest( "", "", i + "", i + ""));
//        }
//
//        Log.e("size", load.size() + "");


        return view;
    }

    private void initView(View view) {
        mGridViewLatest = view.findViewById(R.id.gridViewLatest);
    }
}
