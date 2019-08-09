package com.example.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.event.ItemClickRv;
import com.example.myapplication.ModelPostOfCate.PostOfCate;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListPost extends RecyclerView.Adapter<AdapterListPost.ViewHolder> {

    private ItemClickRv mitemClickRv;
    private List<PostOfCate> postOfCates;
    private Context context;

    public AdapterListPost(List<PostOfCate> postOfCates, Context context, ItemClickRv itemClickRv) {
        this.postOfCates = postOfCates;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public AdapterListPost.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_recycle_view_cate, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        PostOfCate postOfCate = postOfCates.get(i);
        viewHolder.tvTitle.setText(postOfCate.getTitle().getRendered());


        final int id = postOfCate.getId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickRv.onItemClick(i, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postOfCates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView banner;
        private TextView tvTitle;
        private TextView tvCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.imgBanner);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCount = itemView.findViewById(R.id.tvCount);
        }
    }

    public void updateData(List<PostOfCate> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        postOfCates.addAll(data);
        notifyDataSetChanged();
    }
}
