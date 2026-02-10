package com.depogramming.omahmed.presentation.planner.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class PlannedMealsAdapter extends RecyclerView.Adapter<PlannedMealsAdapter.PlannedMealsViewHolder> {

    List<MealsPlanModel> mealsPlanModels;
    private final OnPlannedMealClick onPlannedMealClick;

    public PlannedMealsAdapter(OnPlannedMealClick onPlannedMealClick) {
        this.onPlannedMealClick = onPlannedMealClick;
    }

    @NonNull
    @Override
    public PlannedMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_plan_item, parent, false);
        return new PlannedMealsAdapter.PlannedMealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedMealsViewHolder holder, int position) {
        MealsPlanModel mealsPlanModel = mealsPlanModels.get(position);
        holder.bind(mealsPlanModel);
        holder.removeButton.setOnClickListener(view -> onPlannedMealClick.onRemoveButtonClicked(mealsPlanModel, position));
        holder.cardView.setOnClickListener(view -> onPlannedMealClick.onCardClicked(mealsPlanModel));
    }

    @Override
    public int getItemCount() {
        return mealsPlanModels == null ? 0 : mealsPlanModels.size();
    }
    public void setPlannedMeals(List<MealsPlanModel> meals){
        this.mealsPlanModels=meals;
        notifyDataSetChanged();
    }

    public static class PlannedMealsViewHolder extends RecyclerView.ViewHolder {

        private final MaterialCardView cardView;
        private final ImageView removeButton;
        private final ImageView plannerImageView;
        private final TextView plannerMealName;

        public PlannedMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.plannerFullItem);
            removeButton = itemView.findViewById(R.id.removeButton);
            plannerImageView = itemView.findViewById(R.id.plannerImageView);
            plannerMealName = itemView.findViewById(R.id.plannerMealName);
        }

        public void bind(MealsPlanModel mealsPlanModel) {
            plannerMealName.setText(mealsPlanModel.strMeal);
            Glide.with(itemView.getContext()).load(mealsPlanModel.strMealThumb).into(plannerImageView);
        }
    }
}
