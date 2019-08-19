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

import com.example.myapplication.Adapter.AdapterMediaOfPost;
import com.example.myapplication.Modell.MediaOfPost;
import com.example.myapplication.event.ItemClickRv;

import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMediaOfPost extends Fragment implements ItemClickRv {

    private RecyclerView mRvMedia;

    public static final String KEY_POSITION = "FragmentPostOfCate.KEY_POSITION";
    public static final String KEY_ID = "FragmentPostOfCate.KEY_ID";


    private List<MediaOfPost> mediaOfPosts;
    private AdapterMediaOfPost adapterMediaOfPost;

    private int id;
    private int position;

    public FragmentMediaOfPost() {
        mediaOfPosts = new ArrayList<>();
    }



    public static FragmentMediaOfPost newInstanceMedia(int id, int position) {
        Bundle args = new Bundle();


        args.putInt(KEY_ID, id);
        args.putInt(KEY_POSITION, position);


        FragmentMediaOfPost fragment = new FragmentMediaOfPost();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int position, int id) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media_of_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        setupAdapter();
    }

    private void setupAdapter() {
        adapterMediaOfPost = new AdapterMediaOfPost(mediaOfPosts);
        mRvMedia.setAdapter(adapterMediaOfPost);
        mRvMedia.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private void initData() {
        if(getArguments() != null){

            id = getArguments().getInt(KEY_ID);
            position = getArguments().getInt(KEY_POSITION);
        }
        PolyRetrofit.getInstance().getMediaOfPost(id).enqueue(new Callback<List<MediaOfPost>>() {
            @Override
            public void onResponse(Call<List<MediaOfPost>> call, Response<List<MediaOfPost>> response) {
                adapterMediaOfPost.setLoads(response.body());
                Log.e("AAA", "onResponse: "+ response.toString());
                Log.e("mediaSize", "onResponse: " + mediaOfPosts.size() );
                String a = mediaOfPosts.get(0).getSourceUrl();
                Log.e("url", "onResponse:" + a);
            }

            @Override
            public void onFailure(Call<List<MediaOfPost>> call, Throwable t) {

            }
        });
    }


    private void initView(View view) {

        mRvMedia = view.findViewById(R.id.rvMedia);
    }
}
