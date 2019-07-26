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
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Latest extends Fragment {
    private RecyclerView mGridViewLatest;
    private List<ModelLatest> modelLatestList;
    private AdapterLatest adapterLatest;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        initView(view);


        modelLatestList = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            modelLatestList.add(new ModelLatest("", "", "", i + "", i + ""));
        }

        Log.e("size", modelLatestList.size() + "");
        adapterLatest = new AdapterLatest(modelLatestList);
        mGridViewLatest.setAdapter(adapterLatest);
        mGridViewLatest.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    private void initView(View view) {
        mGridViewLatest = view.findViewById(R.id.gridViewLatest);
    }
}
