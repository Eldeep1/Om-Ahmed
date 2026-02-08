package com.depogramming.omahmed.presentation.mealdetails.view.tabs.ingredients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.Ingredients;

import java.util.List;

public class IngredientsRecyclerAdapter extends RecyclerView.Adapter<IngredientsRecyclerAdapter.ViewHolder>{

    List<Ingredients> ingredients;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingradients_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredients ingredient = ingredients.get(position);
        holder.bind(ingredient);
    }

    public void setIngredients(List<Ingredients> ingredients){
        this.ingredients=ingredients;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return ingredients==null?0:ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientTextView;
        public TextView measurementTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView=itemView.findViewById(R.id.ingredientTextView);
            measurementTextView=itemView.findViewById(R.id.measurementTextView);
        }

        public void bind(Ingredients ingredient) {
            ingredientTextView.setText(ingredient.getIngredient());
            measurementTextView.setText(ingredient.getMeasurement());
            System.out.println("the ingredient to be binded:"+ingredient.getIngredient());
        }
    }
}
