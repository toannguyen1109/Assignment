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
import com.example.myapplication.Model.ModelLatest;
import com.example.myapplication.ModelRetrofit.Post;
import com.example.myapplication.ModelRetrofit.WpFeaturedmedium_;
import com.example.myapplication.R;
import com.example.myapplication.fragment.Fragment_Latest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterLatest extends RecyclerView.Adapter<AdapterLatest.ViewHolder> {
    private List<ModelLatest> modelLatestList;
    private List<Post> load;

    public AdapterLatest(List<ModelLatest> modelLatestList, Context context) {
        //chưa gọi tới fun này nên null
        this.modelLatestList = modelLatestList;
    }

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
//        ModelLatest md = modelLatestList.get(i);
//        Post mdd = load.get(i);
//        viewHolder.tvCOuntHeart.setText(md.getTvCountHeart());
//        viewHolder.tvCountEye.setText(md.getTvCountEye());
        if (load !=null) {
            Post post = load.get(i);
            Picasso.with(context).load(post.getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl()).into(viewHolder.imgContent);
        }

                    //                      .getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getMediumLarge().getSourceUrl()
//        Log.e("img", ""+ load.get(i).getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getLarge().getSourceUrl());

//        load.get(i).getEmbedded().getWpFeaturedmedia().get(0).getMediaDetails().getSizes().getLarge();

        viewHolder.imgContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ImageDetailActivity.class);
                intent.putExtra("position",i);
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
