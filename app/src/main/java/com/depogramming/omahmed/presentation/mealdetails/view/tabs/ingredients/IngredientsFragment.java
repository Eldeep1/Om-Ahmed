package com.depogramming.omahmed.presentation.mealdetails.view.tabs.ingredients;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Ingredients;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.models.MealMapper;

import java.util.List;


public class IngredientsFragment extends Fragment {

    private RecyclerView ingredientsRecyclerView;
    private IngredientsRecyclerAdapter ingredientsRecyclerAdapter;
    private static final String ARG_MEAL = "meal";

    public static IngredientsFragment newInstance(Meal meal) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MEAL, meal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        loadIngredients();
    }

    private void setupRecyclerView() {
        ingredientsRecyclerAdapter = new IngredientsRecyclerAdapter();
        ingredientsRecyclerView.setAdapter(ingredientsRecyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        ingredientsRecyclerView.setLayoutManager(layoutManager);
    }

    //TODO: create a presenter for that...
    private void loadIngredients() {
        if (getArguments() != null) {
            Meal meal = getArguments().getParcelable(ARG_MEAL);
            if (meal != null) {
                List<Ingredients> ingredientsList = MealMapper.mapMealToIngredients(meal);
                ingredientsRecyclerAdapter.setIngredients(ingredientsList);
            }
        }
    }
}