package com.depogramming.omahmed.presentation.mealdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.presentation.mealdetails.presentation.MealDetailsPresenter;
import com.depogramming.omahmed.presentation.mealdetails.presentation.MealDetailsPresenterImp;
import com.depogramming.omahmed.presentation.onboarding.views.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MealDetailsFragment extends Fragment implements MealDetailsView {

    MealDetailsPresenter mealDetailsPresenter;
    ImageView selectedMealImage;
    ImageView selectedMealFavButton;
    TextView selectedMealTitle;
    TextView selectedMealCategory;
    TextView selectedMealCountry;
    ViewPager2 viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);
        selectedMealImage = view.findViewById(R.id.selectedMealImage);
        selectedMealFavButton = view.findViewById(R.id.selectedMealFavButton);
        selectedMealTitle = view.findViewById(R.id.selectedMealTitle);
        selectedMealCategory = view.findViewById(R.id.selectedMealCategory);
        selectedMealCountry = view.findViewById(R.id.selectedMealCountry);
        Meal meal = getArguments().getParcelable("meal");
        mealDetailsPresenter = new MealDetailsPresenterImp(this, meal);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        viewPager.setAdapter(new MealDetailsAdapter(getActivity(),meal));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Ingredients"); break;
                case 1: tab.setText("Instructions"); break;
            }
        }).attach();

        mealDetailsPresenter.getMealIngredients();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealDetailsPresenter.getMealIngredients();
    }

    @Override
    public void showMealIngredients(Meal meal) {
        Glide.with(getActivity().getApplicationContext()).load(meal.strMealThumb).into(selectedMealImage);

        selectedMealFavButton.setImageResource(meal.isFav ? R.drawable.alreadyfav : R.drawable.addfav);
        selectedMealFavButton.setOnClickListener(v -> mealDetailsPresenter.toggleFavourite(meal));
        selectedMealTitle.setText(meal.strMeal);
        selectedMealCategory.setText(meal.strCategory);
        selectedMealCountry.setText(meal.strCategory);
    }

    @Override
    public void showMealInstructions(String instructions) {

    }

    @Override
    public void showMealVideo(String strYoutube) {

    }

    @Override
    public void showError() {

    }
}