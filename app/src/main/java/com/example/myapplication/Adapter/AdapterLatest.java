package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.ImageDetailActivity;
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.R;
import com.example.myapplication.fragment.Fragment_Latest;

import java.util.List;

public class AdapterLatest extends RecyclerView.Adapter<AdapterLatest.ViewHolder> {
    private List<ModelLatest> modelLatestList;
    Context context;



    public AdapterLatest(List<ModelLatest> modelLatestList, Context context) {
        this.modelLatestList = modelLatestList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_grid_view, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelLatest md = modelLatestList.get(i);
        viewHolder.tvCOuntHeart.setText(md.getTvCountHeart());
        viewHolder.tvCountEye.setText(md.getTvCountEye());

        viewHolder.imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ImageDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelLatestList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgContent;
        ImageView imgIconEye;
        ImageView imgIconHeart;
        TextView tvCountEye;
        TextView tvCOuntHeart;


        ViewHolder(View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.imgContent);
            imgIconEye = itemView.findViewById(R.id.imgIconEye);
            imgIconHeart = itemView.findViewById(R.id.imgIconHeart);
            tvCountEye = itemView.findViewById(R.id.tvCountEye);
            tvCOuntHeart = itemView.findViewById(R.id.tvCountHeart);

        }
    }
}
