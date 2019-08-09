package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.Activity.ImageDetailActivity;
import com.example.myapplication.Database.DAO;
import com.example.myapplication.Model.ModelFavorites;
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.ModelEmbed.Post;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {
    private List<ModelFavorites> modelFavoritesList;
    Context context;
    private DAO dao;

    public AdapterFavorites(List<ModelFavorites> modelFavoritesList, Context context) {
        this.modelFavoritesList = modelFavoritesList;
        this.context = context;
        dao = new DAO(context);
        dao.open();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_grid_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelFavorites md = modelFavoritesList.get(i);
        Log.e("Strimg img", "onBindViewHolder: " + modelFavoritesList.get(i).getImgContent());
        Picasso.with(context).load(md.getImgContent()).into(viewHolder.imgContent);
    }


    @Override
    public int getItemCount() {
        return modelFavoritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.imgContent);

        }
    }
}
