package com.depogramming.omahmed.presentation.favourites.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.FavouriteMeals;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {

    List<FavouriteMeals> favouriteMeals;
    OnHeartClicked onHeartClicked;

    public FavouritesAdapter(OnHeartClicked onHeartClicked) {
        this.onHeartClicked = onHeartClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_recommendations_vertical_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavouriteMeals favouriteMeals1=favouriteMeals.get(position);
        holder.recommendationFavouriteIcon.setOnClickListener(view -> onHeartClicked.removeFavouriteLogic(favouriteMeals1,position));
        holder.bind(favouriteMeals1);
    }
    public void setFavouriteMeals(List<FavouriteMeals> favouriteMeals){
        this.favouriteMeals=favouriteMeals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favouriteMeals==null?0:favouriteMeals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView recommendationImageView;
        private final ImageView recommendationFavouriteIcon;
        private final TextView recommendationName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recommendationImageView=itemView.findViewById(R.id.recommendationImageView);
            recommendationFavouriteIcon=itemView.findViewById(R.id.recommendationFavouriteIcon);
            recommendationName=itemView.findViewById(R.id.recommendationName);
        }

        public void bind(FavouriteMeals favouriteMeals) {
            recommendationName.setText(favouriteMeals.strMeal);
            Glide.with(itemView).load(favouriteMeals.strMealThumb).into(recommendationImageView);
            recommendationFavouriteIcon.setImageResource(favouriteMeals.favouriteFlag?R.drawable.alreadyfav:R.drawable.addfav);

        }
    }
}
