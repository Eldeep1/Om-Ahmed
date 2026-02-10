package com.depogramming.omahmed.presentation.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Category;

import java.util.List;

public class CategoriesDropDownAdapter extends RecyclerView.Adapter<CategoriesDropDownAdapter.ViewHolder>{

    List<Category> categories;
    private OnDropDownItemClickListener listener;

    public CategoriesDropDownAdapter(OnDropDownItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drop_down_list_item, parent, false);
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
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView categoryImage;
        private final TextView categoryString;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.dropDownImage);
            categoryString=itemView.findViewById(R.id.dropDownText);
            itemView.setOnClickListener(v -> {
                    int position = getAdapterPosition();

                        listener.onDropdownItemClickListener(categories.get(position));


            });
        }
        public void bind(Category category) {
            categoryString.setText(category.getStrCategory());
            Glide.with(itemView).load(category.getStrCategoryThumb()).into(categoryImage);
        }
    }
}
