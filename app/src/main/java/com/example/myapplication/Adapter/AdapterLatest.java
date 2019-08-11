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
import com.example.myapplication.ModelEmbed.Post;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterLatest extends RecyclerView.Adapter<AdapterLatest.ViewHolder> {
    private List<Post> load;

    public AdapterLatest(List<Post> load) {
        this.load = load;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_grid_view, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Context context = viewHolder.itemView.getContext();

        if (load !=null) {
            Post post = load.get(i);
            Picasso.with(context).load(post.getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl()).into(viewHolder.imgContent);
        }
    load.get(i).getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getLarge();

        viewHolder.imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ImageDetailActivity.class);
                Post post = load.get(i);
                String a = post.getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl();
                intent.putExtra("link",a);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return load.size();
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

    public void setLoads(List<Post> load){
        if (load == null) {
            load = new ArrayList<>();
        }
        this.load.addAll(load);
        notifyDataSetChanged();
    }
}
