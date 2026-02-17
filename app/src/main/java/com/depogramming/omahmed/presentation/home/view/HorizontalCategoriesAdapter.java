package com.depogramming.omahmed.presentation.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.categories.Category;

import java.util.List;

public class HorizontalCategoriesAdapter extends RecyclerView.Adapter<HorizontalCategoriesAdapter.ViewHolder>{
    private List<Category> categories;
    CategoriesView categoriesView;
    public HorizontalCategoriesAdapter(CategoriesView categoriesView) {
        this.categoriesView=categoriesView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_horizontal_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories==null?0:categories.size();
    }
    public void setCategories(List<Category> categories){
        this.categories=categories;
        notifyItemRangeInserted(0, categories.size());
//        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.horizontal_categories_text);
            imageView=itemView.findViewById(R.id.horizontal_categories_image);
        }
        public void bind(Category category){
            textView.setText(category.getStrCategory());

            Glide.with(itemView)
                    .load(category.getStrCategoryThumb())
                    .into(imageView);
            itemView.setOnClickListener(view -> categoriesView.onCategoryClick(category.getStrCategory()));
        }
    }
}
