package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.Activity.ImageDetailActivity;

import com.example.myapplication.Modell.MediaOfPost;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMediaOfPost extends RecyclerView.Adapter<AdapterMediaOfPost.ViewHolder> {

    List<MediaOfPost> mediaOfPosts;

    public AdapterMediaOfPost(List<MediaOfPost> mediaOfPosts) {
        this.mediaOfPosts = mediaOfPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_grid_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Context context = viewHolder.itemView.getContext();

        if (mediaOfPosts!=null){
            MediaOfPost mediaOfPost = mediaOfPosts.get(i);
            Picasso.with(context).load(mediaOfPost.getSourceUrl()).into(viewHolder.imageView);
        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ImageDetailActivity.class);
                MediaOfPost mediaOfPost = mediaOfPosts.get(i);
                String a = mediaOfPost.getSourceUrl();
                intent.putExtra("link",a);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mediaOfPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgContent);
        }
    }

    public void setLoads(List<MediaOfPost> data){
        if (data == null) {
            data = new ArrayList<>();
        }
        mediaOfPosts.addAll(data);
        notifyDataSetChanged();
    }
}
