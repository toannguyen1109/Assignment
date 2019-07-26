package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Adapter.AdapterCategory;
import com.example.myapplication.Model.ModelCatagory;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Category extends Fragment {
    private RecyclerView mRvCate;
    private List<ModelCatagory> modelCatagoryList;
    private AdapterCategory adapterCategory;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);

        modelCatagoryList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            modelCatagoryList.add(new ModelCatagory("", "Animals " + "(" + i + ")"));
        }

        adapterCategory = new AdapterCategory(modelCatagoryList);
        mRvCate.setAdapter(adapterCategory);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRvCate.setLayoutManager(manager);
        return view;
    }

    private void initView(View view) {
        mRvCate = view.findViewById(R.id.rvCate);
    }
}
