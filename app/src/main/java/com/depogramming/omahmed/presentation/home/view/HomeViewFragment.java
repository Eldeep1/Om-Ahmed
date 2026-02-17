package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
import com.depogramming.omahmed.data.meals.model.categories.Category;
import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.presentation.home.presenter.HomePresenter;
import com.depogramming.omahmed.presentation.home.presenter.HomePresenterImp;
import com.depogramming.omahmed.utils.UserAlerts;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class HomeViewFragment extends Fragment implements HomeView, OnItemClick {

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
     ShimmerFrameLayout shimmerLayout;
     View homeContent;
    ConstraintLayout homeErrorLayout;
    Button retryButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenterImp( getActivity());
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

        horizontalCategoriesAdapter = new HorizontalCategoriesAdapter(this);
        recommendationsAdapter = new RecommendationsAdapter(this);

        categoriesRecyclerView.setAdapter(horizontalCategoriesAdapter);
        recommendationsRecyclerView.setAdapter(recommendationsAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recommendationsRecyclerView.setLayoutManager(gridLayoutManager);

        shimmerLayout = view.findViewById(R.id.homeShimmerLayout);
        homeContent = view.findViewById(R.id.homeContent);
        homeErrorLayout=view.findViewById(R.id.homeErrorLayout);
        retryButton=view.findViewById(R.id.retryButton);
        retryButton.setOnClickListener(view1 -> homePresenter.retryAllButton());
        return view;
    }



    @Override
    public void categoriesGotSuccessfully(List<Category> categories) {
        horizontalCategoriesAdapter.setCategories(categories);
    }


    @Override
    public void onCategoryClick(String category) {
        homePresenter.navigateToSearch(category);
    }

    @Override
    public void onCategoryClickAction(Bundle result) {
        getParentFragmentManager().setFragmentResult("category", result);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.bottomNavigationFragments);

        navController.navigate(R.id.searchFragment, result);
    }


    @Override
    public void recommendationsMealsSuccessful(List<Meal> meals) {
        recommendationsAdapter.setMeals(meals);
    }


    @Override
    public void dailyMealSuccessfully(Meal meal) {
        mealOfTheDayCategory.setText(meal.strCategory);
        mealOfTheDayCountry.setText(meal.strArea);
        mealOfTheDayTitle.setText(meal.strMeal);
        mealOfTheDayFavButton.setImageResource(meal.isFav ? R.drawable.alreadyfav : R.drawable.addfav);
        mealOfTheDayFavButton.setOnClickListener(view -> homePresenter.changeDailyMealFavState(meal, getActivity()));
        Glide.with(getActivity()).load(meal.strMealThumb).into(mealOfTheDayImage);
        mealOfTheDayDetailsButton.setOnClickListener(v ->
                homePresenter.navigateToMealDetails(meal)
        );
    }

    @Override
    public void updateDailyMealFavState(boolean isFav, String message) {
        mealOfTheDayFavButton.setImageResource(isFav ? R.drawable.alreadyfav : R.drawable.addfav);
        UserAlerts.showSnackBar(getView(),message);
    }

    @Override
    public void onHeartClicked(Meal meal, int position) {
        homePresenter.changeRecommendationsFavState(meal, position, getActivity());
    }

    @Override
    public void onCardClicked(Meal meal) {
        homePresenter.navigateToMealDetails(meal);
    }

    @Override
    public void updateListViewHeart(int position, boolean isFavourite, String message) {
        recommendationsAdapter.notifyItemChanged(position, isFavourite);
        UserAlerts.showSnackBar(getView(), message);
    }

    @Override
    public void navigateToMealDetails(Bundle bundle) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void allMealsLoading() {
        shimmerLayout.setVisibility(View.VISIBLE);
        homeContent.setVisibility(View.GONE);
        homeErrorLayout.setVisibility(View.GONE);
    }

    @Override
    public void allMealsSuccessfully() {
        shimmerLayout.setVisibility(View.GONE);
        homeContent.setVisibility(View.VISIBLE);
        homeErrorLayout.setVisibility(View.GONE);
    }

    @Override
    public void allMealsError() {
        shimmerLayout.setVisibility(View.GONE);
        homeContent.setVisibility(View.GONE);
        homeErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        homePresenter.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        homePresenter.setView(this);

    }
}