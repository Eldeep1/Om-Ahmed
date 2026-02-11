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
import com.depogramming.omahmed.data.home.models.Meal;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class SearchedMealsAdapter extends RecyclerView.Adapter<SearchedMealsAdapter.SearchedMealsViewHolder> {

    List<Meal> meals;
    OnSearchItemClick onItemClick;

    public SearchedMealsAdapter(OnSearchItemClick onItemClick) {
        meals = new ArrayList<>();
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public SearchedMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_recommendations_vertical_list_item, parent, false);
        return new SearchedMealsAdapter.SearchedMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedMealsViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
        holder.recommendationFavouriteIcon.setOnClickListener(v -> {
            onItemClick.onHeartClicked(meal, position);
        });
    }

    @Override
    public int getItemCount() {
        return meals == null ? 0 : meals.size();
    }

    public void addToList(List<Meal> meals) {
        this.meals.addAll(meals);
        notifyDataSetChanged();
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    public class SearchedMealsViewHolder extends RecyclerView.ViewHolder {
        ImageView recommendationImageView;
        ImageView recommendationFavouriteIcon;
        TextView recommendationName;
        MaterialCardView recommendationFullItem;

        public SearchedMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            recommendationImageView = itemView.findViewById(R.id.recommendationImageView);
            recommendationFavouriteIcon = itemView.findViewById(R.id.recommendationFavouriteIcon);
            recommendationName = itemView.findViewById(R.id.recommendationName);
            recommendationFullItem = itemView.findViewById(R.id.recommendationFullItem);
        }

        public void bind(Meal meal) {
            recommendationName.setText(meal.strMeal);
            Glide.with(itemView).load(meal.strMealThumb).into(recommendationImageView);
            recommendationFavouriteIcon.setImageResource(meal.isFav ? R.drawable.alreadyfav : R.drawable.addfav);
            recommendationFullItem.setOnClickListener(view -> onItemClick.onCardClicked(meal));

        }
    }
}
