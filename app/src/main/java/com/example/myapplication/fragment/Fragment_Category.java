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

import com.example.myapplication.event.ItemClickRv;
import com.example.myapplication.Adapter.AdapterCategory;
import com.example.myapplication.ModelCategory.Category;
import com.example.myapplication.ModelPostOfCate.PostOfCate;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.PolyRetrofit;
import com.example.myapplication.utills.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Category extends Fragment {
    private RecyclerView mRvCate;


    private List<Category> categories;
    private AdapterCategory adapterCategory;
    private LinearLayoutManager linearLayoutManager;

    private int page = 1;
    private int per_page = 15;

    private List<PostOfCate> postOfCates;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);


        categories = new ArrayList<>();
        postOfCates = new ArrayList<>();

        adapterCategory = new AdapterCategory(categories, getActivity(), new ItemClickRv() {
            @Override
            public void onItemClick(int position, int id) {
                ViewHelper.switchFragment(getActivity(), FragmentPostOfCate.newInstance(id, position));
            }
        });

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvCate.setAdapter(adapterCategory);
        mRvCate.setLayoutManager(linearLayoutManager);
        getData(page, per_page);


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


            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        mRvCate = view.findViewById(R.id.rvCate);

    }


}
