package com.example.myapplication.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.Model.ModelFavorites;
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.R;

import java.util.List;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {
    private List<ModelFavorites> modelFavoritesList;

    public AdapterFavorites(List<ModelFavorites> modelFavoritesList) {
        this.modelFavoritesList = modelFavoritesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_grid_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelFavorites md = modelFavoritesList.get(i);
        viewHolder.tvCOuntHeart.setText(md.getTvCountHeart());
        viewHolder.tvCountEye.setText(md.getTvCountEye());
    }

    @Override
    public int getItemCount() {
        return modelFavoritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;
        ImageView imgIconEye;
        ImageView imgIconHeart;
        TextView tvCountEye;
        TextView tvCOuntHeart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.imgContent);
            imgIconEye = itemView.findViewById(R.id.imgIconEye);
            imgIconHeart = itemView.findViewById(R.id.imgIconHeart);
            tvCountEye = itemView.findViewById(R.id.tvCountEye);
            tvCOuntHeart = itemView.findViewById(R.id.tvCountHeart);
        }
    }
}
