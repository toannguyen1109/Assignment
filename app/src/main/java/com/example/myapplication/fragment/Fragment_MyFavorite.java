package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.AdapterFavorites;
import com.example.myapplication.Model.ModelFavorites;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_MyFavorite extends Fragment {
    private RecyclerView mRvFavorites;
    private List<ModelFavorites> modelFavoritesList;
    private AdapterFavorites adapterFavorites;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_favorite, container, false);
        initview(view);
        modelFavoritesList = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            modelFavoritesList.add(new ModelFavorites("", "", "", i + "", i + ""));
        }


        adapterFavorites = new AdapterFavorites(modelFavoritesList,getActivity());
        mRvFavorites.setAdapter(adapterFavorites);
        mRvFavorites.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    private void initview(View view) {
        mRvFavorites = view.findViewById(R.id.rvFavorites);
    }


}
