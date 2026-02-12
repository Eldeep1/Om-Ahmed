package com.depogramming.omahmed.presentation.mealdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.presentation.mealdetails.presentation.MealDetailsPresenter;
import com.depogramming.omahmed.presentation.mealdetails.presentation.MealDetailsPresenterImp;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Date;


public class MealDetailsFragment extends Fragment implements MealDetailsView {

    MealDetailsPresenter mealDetailsPresenter;
    ImageView selectedMealImage;
    ImageView selectedMealFavButton;
    ImageView selectedMealBacButton;
    TextView selectedMealTitle;
    TextView selectedMealCategory;
    TextView selectedMealCountry;
    ViewPager2 viewPager;
    TabLayout tabLayout;
    Button addToPlannerButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);
        selectedMealImage = view.findViewById(R.id.selectedMealImage);
        selectedMealFavButton = view.findViewById(R.id.selectedMealFavButton);
        selectedMealTitle = view.findViewById(R.id.selectedMealTitle);
        selectedMealCategory = view.findViewById(R.id.selectedMealCategory);
        selectedMealBacButton = view.findViewById(R.id.selectedMealBacButton);
        selectedMealCountry = view.findViewById(R.id.selectedMealCountry);
        addToPlannerButton = view.findViewById(R.id.addToPlannerButton);
        addToPlannerButton.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).setTheme(R.style.ThemeOverlay_App_DatePicker)
                    .build();
            datePicker.addOnPositiveButtonClickListener(selection -> {
                Date selectedDate = new Date(selection);
                mealDetailsPresenter.addToPlanner(selectedDate,getActivity());
            });

            datePicker.show(getChildFragmentManager(), "DATE_PICKER");

        });

        Meal meal = getArguments().getParcelable("meal");
        mealDetailsPresenter = new MealDetailsPresenterImp(this, meal, getContext().getApplicationContext());

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        viewPager.setAdapter(new MealDetailsAdapter(getActivity(), meal));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Ingredients");
                    break;
                case 1:
                    tab.setText("Instructions");
                    break;
            }
        }).attach();

        mealDetailsPresenter.getMealIngredients();
        selectedMealBacButton.setOnClickListener(view1 -> mealDetailsPresenter.backButton());

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
        selectedMealFavButton.setOnClickListener(v -> mealDetailsPresenter.toggleFavourite(getActivity()));
        selectedMealTitle.setText(meal.strMeal);
        selectedMealCategory.setText(meal.strArea);
        selectedMealCountry.setText(meal.strCategory);
    }

    @Override
    public void toggleFavouriteButton(boolean isFav) {
        selectedMealFavButton.setImageResource(isFav ? R.drawable.alreadyfav : R.drawable.addfav);
    }

    @Override
    public void addToPlannerSuccess() {
        Toast.makeText(requireContext(), "Added Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backButtonClicked() {
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onStop() {
        super.onStop();
        mealDetailsPresenter.clear();
    }
}