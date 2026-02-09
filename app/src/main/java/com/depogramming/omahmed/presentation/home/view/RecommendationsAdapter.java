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
import com.depogramming.omahmed.data.home.models.Meal;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RecommendationsAdapter extends RecyclerView.Adapter<RecommendationsAdapter.ViewHolder> {
    List<Meal> meals;
    OnItemClick onItemClick;

    public RecommendationsAdapter(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_recommendations_vertical_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
        holder.recommendationFavouriteIcon.setOnClickListener(v -> {
                onItemClick.onHeartClicked(meal, position);
        });
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (!payloads.isEmpty()) {
            boolean isFav = (boolean) payloads.get(0);
            holder.recommendationFavouriteIcon.setImageResource(isFav ? R.drawable.alreadyfav : R.drawable.addfav);
        } else {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return meals==null?0:meals.size();
    }
    public void setMeals(List<Meal> meals){
        this.meals=meals;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final MaterialCardView materialCardView;
        private final ImageView recommendationImageView;
        private final ImageView recommendationFavouriteIcon;
        private final TextView recommendationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recommendationImageView=itemView.findViewById(R.id.recommendationImageView);
            recommendationFavouriteIcon=itemView.findViewById(R.id.recommendationFavouriteIcon);
            recommendationName=itemView.findViewById(R.id.recommendationName);
            materialCardView=itemView.findViewById(R.id.recommendationFullItem);
        }
        public void bind(Meal meal){
            recommendationName.setText(meal.strMeal);
            Glide.with(itemView).load(meal.strMealThumb).into(recommendationImageView);
            recommendationFavouriteIcon.setImageResource(meal.isFav?R.drawable.alreadyfav:R.drawable.addfav);
            materialCardView.setOnClickListener(view ->onItemClick.onCardClicked(meal));
        }

    }
}
