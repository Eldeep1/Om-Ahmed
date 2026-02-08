package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.presentation.home.presenter.HomePresenter;
import com.depogramming.omahmed.presentation.home.presenter.HomePresenterImp;

import java.util.List;

public class HomeViewFragment extends Fragment implements HomeView, OnHeartClick {

    HomePresenter homePresenter;
    RecyclerView categoriesRecyclerView;
    HorizontalCategoriesAdapter horizontalCategoriesAdapter;
    RecyclerView recommendationsRecyclerView;
    RecommendationsAdapter recommendationsAdapter;
    ImageView mealOfTheDayImage;
    ImageView mealOfTheDayFavButton;
    TextView mealOfTheDayCategory;
    TextView mealOfTheDayCountry;
    TextView mealOfTheDayTitle;
    Button mealOfTheDayDetailsButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenterImp(this, getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mealOfTheDayImage = view.findViewById(R.id.mealOfTheDayImage);
        mealOfTheDayFavButton = view.findViewById(R.id.mealOfTheDayFavButton);
        mealOfTheDayCategory = view.findViewById(R.id.mealOfTheDayCategory);
        mealOfTheDayCountry = view.findViewById(R.id.mealOfTheDayCountry);
        mealOfTheDayTitle = view.findViewById(R.id.mealOfTheDayTitle);
        mealOfTheDayDetailsButton = view.findViewById(R.id.mealOfTheDayDetailsButton);

        categoriesRecyclerView = view.findViewById(R.id.homeCategoriesHorizontalList);
        recommendationsRecyclerView = view.findViewById(R.id.dailyRecommendationsListView);

        horizontalCategoriesAdapter = new HorizontalCategoriesAdapter();
        recommendationsAdapter = new RecommendationsAdapter(this);

        categoriesRecyclerView.setAdapter(horizontalCategoriesAdapter);
        recommendationsRecyclerView.setAdapter(recommendationsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recommendationsRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homePresenter.getAllCategories();
        homePresenter.getDailyRecommendations();
        homePresenter.getDailyMeal();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void categoriesLoading() {
    }

    @Override
    public void categoriesGotSuccessfully(List<Category> categories) {
        horizontalCategoriesAdapter.setCategories(categories);
    }


    @Override
    public void categoriesFailed(String errorMessage) {

    }

    @Override
    public void recommendationMealsLoading() {

    }

    @Override
    public void recommendationsMealsSuccessful(List<Meal> meals) {
        recommendationsAdapter.setMeals(meals);
    }

    @Override
    public void recommendationsMealsFailed(String errorMessage) {

    }

    @Override
    public void dailyMealLoading() {

    }

    @Override
    public void dailyMealSuccessfully(Meal meal) {
        mealOfTheDayCategory.setText(meal.strCategory);
        mealOfTheDayCountry.setText(meal.strArea);
        mealOfTheDayTitle.setText(meal.strMeal);
        mealOfTheDayFavButton.setImageResource(meal.isFav ? R.drawable.alreadyfav : R.drawable.addfav);
        mealOfTheDayFavButton.setOnClickListener(view -> {
            homePresenter.changeDailyMealFavState(meal);});
        Glide.with(getActivity()).load(meal.strMealThumb).into(mealOfTheDayImage);
        mealOfTheDayDetailsButton.setOnClickListener(v ->
                homePresenter.navigateToMealDetails(meal)
        );
    }
    @Override
    public void updateDailyMealFavState(boolean isFav) {
        mealOfTheDayFavButton.setImageResource(isFav ? R.drawable.alreadyfav : R.drawable.addfav);
    }
    @Override
    public void dailyMealFailed(String errorMessage) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public void onHeartClicked(Meal meal, int position) {
        homePresenter.changeRecommendationsFavState(meal,position);
    }
    @Override
    public void updateListViewHeart(int position, boolean isFavourite){
        recommendationsAdapter.notifyItemChanged(position,isFavourite);
    }

    @Override
    public void navigateToMealDetails(Bundle bundle) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_mealDetailsFragment, bundle);
    }


}