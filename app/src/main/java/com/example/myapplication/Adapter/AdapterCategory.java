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
import com.example.myapplication.ModelCategory.Category;
import com.example.myapplication.R;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {


    private List<Category> categories;
    private Context context;
    private ItemClickRv mitemClickRv;


    public AdapterCategory(List<Category> categories, Context context, ItemClickRv itemClickRv) {
        this.categories = categories;
        this.context = context;
        mitemClickRv = itemClickRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_recycle_view_cate, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Category category = categories.get(i);
        viewHolder.tvTitle.setText(category.getName());
        viewHolder.tvCount.setText("(" + category.getCount() + ")");

        final int id = category.getId();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mitemClickRv.onItemClick(i,id);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView banner;
        private TextView tvTitle;
        private TextView tvCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = (ImageView) itemView.findViewById(R.id.imgBanner);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
        }


    }
}
