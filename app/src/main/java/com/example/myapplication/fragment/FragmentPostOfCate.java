package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.Adapter.AdapterListPost;
import com.example.myapplication.event.ItemClickRv;
import com.example.myapplication.ModelPostOfCate.PostOfCate;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;
import com.example.myapplication.utills.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPostOfCate extends Fragment implements ItemClickRv {
    public static final String KEY_POSITION = "FragmentPostOfCate.KEY_POSITION";
    public static final String KEY_ID = "FragmentPostOfCate.KEY_ID";

    private ImageView mImgBack;
    private RecyclerView mRvListPost;
    private List<PostOfCate> postOfCates;
    private AdapterListPost adapterListPost;

    private int id;
    private int position;

    public FragmentPostOfCate() {
        postOfCates = new ArrayList<>();
    }



    public static FragmentPostOfCate newInstance(int id, int position) {
        Bundle args = new Bundle();


        args.putInt(KEY_ID, id);
        args.putInt(KEY_POSITION, position);

        FragmentPostOfCate fragment = new FragmentPostOfCate();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_post_of_cate_acitivity, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        postOfCates = new ArrayList<>();

        setupAdapter();

    }

    private void setupAdapter() {
        adapterListPost = new AdapterListPost(postOfCates, getActivity(), new ItemClickRv() {
            @Override
            public void onItemClick(int position, int id) {
                ViewHelper.switchFragment(getActivity(), FragmentMediaOfPost.newInstanceMedia(id, position));
            }
        });
        mRvListPost.setAdapter(adapterListPost);
        mRvListPost.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initData() {

        if (getArguments() != null) {
            id = getArguments().getInt(KEY_ID);
            position = getArguments().getInt(KEY_POSITION);
        }
        PolyRetrofit.getInstance().getPostOfCate(id, 5, 1).enqueue(new Callback<List<PostOfCate>>() {
            @Override
            public void onResponse(Call<List<PostOfCate>> call, Response<List<PostOfCate>> response) {
                if (response.body() != null) {
                    adapterListPost.updateData(response.body());
                    Log.e("listPost", "onResponse: " + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PostOfCate>> call, Throwable t) {
            }
        });
    }


    private void initView(View view) {
        mImgBack = view.findViewById(R.id.imgBack);
        mRvListPost = view.findViewById(R.id.rvListPost);
    }


    @Override
    public void onItemClick(int position, int id) {


    }
}
