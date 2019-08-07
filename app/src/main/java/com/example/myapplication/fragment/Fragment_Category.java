package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.AdapterCategory;
import com.example.myapplication.EndlessRecyclerViewScrollListener;
import com.example.myapplication.Model.ModelCatagory;
import com.example.myapplication.ModelCategory.Category;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Category extends Fragment {
    private RecyclerView mRvCate;


    private List<Category> categories;
    private AdapterCategory adapterCategory;
    private SwipeRefreshLayout mF5;
    private LinearLayoutManager linearLayoutManager;

    private int page = 1;
    private int per_page = 5;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);


        categories = new ArrayList<>();

//        for (int i = 0; i < 10; i++) {
//            modelCatagoryList.add(new ModelCatagory("", "Animals " + "(" + i + ")"));
//        }

        adapterCategory = new AdapterCategory(categories);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvCate.setAdapter(adapterCategory);
        mRvCate.setLayoutManager(linearLayoutManager);
//        getData(page, per_page);
        mF5.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //lay du lieu
                page = 1;
                getData(page, per_page);
            }
        });


        mRvCate.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                per_page = per_page + 1;
                getData(page, per_page);
            }
        });

        return view;
    }

    public void getData(int page, int per_page) {
        PolyRetrofit.getInstance().getCategories(page, per_page).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.e("category", "" + response.toString());
                categories.addAll(response.body());
                Log.e("size", "onResponse: " + response.body().size());
                adapterCategory.notifyDataSetChanged();
                mF5.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        mRvCate = view.findViewById(R.id.rvCate);
        mF5 = view.findViewById(R.id.f5);
    }
}
