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

import java.util.ArrayList;
import java.util.List;

public class IngredientsFragment extends Fragment {

    RecyclerView ingredientsRecyclerView;
    IngredientsRecyclerAdapter ingredientsRecyclerAdapter;
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
        ingredientsRecyclerAdapter = new IngredientsRecyclerAdapter();
        ingredientsRecyclerView.setAdapter(ingredientsRecyclerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        ingredientsRecyclerView.setLayoutManager(layoutManager);
        if (getArguments() != null) {
            Meal meal = getArguments().getParcelable(ARG_MEAL);
            if (meal != null) {
                List<Ingredients> ingredientsList = extractIngredients(meal);
                ingredientsRecyclerAdapter.setIngredients(ingredientsList);
                System.out.println("printing ingredients...");
                System.out.println(ingredientsList.size());
                for (Ingredients ingredient : ingredientsList) {
                    System.out.println(ingredient.getIngredient() + " " + ingredient.getMeasurement());
                }
            }
        }

    }

    private List<Ingredients> extractIngredients(Meal meal) {
        List<Ingredients> ingredientsList = new ArrayList<>();

        // Add each ingredient-measure pair
        addIngredient(ingredientsList, meal.strIngredient1, meal.strMeasure1);
        addIngredient(ingredientsList, meal.strIngredient2, meal.strMeasure2);
        addIngredient(ingredientsList, meal.strIngredient3, meal.strMeasure3);
        addIngredient(ingredientsList, meal.strIngredient4, meal.strMeasure4);
        addIngredient(ingredientsList, meal.strIngredient5, meal.strMeasure5);
        addIngredient(ingredientsList, meal.strIngredient6, meal.strMeasure6);
        addIngredient(ingredientsList, meal.strIngredient7, meal.strMeasure7);
        addIngredient(ingredientsList, meal.strIngredient8, meal.strMeasure8);
        addIngredient(ingredientsList, meal.strIngredient9, meal.strMeasure9);
        addIngredient(ingredientsList, meal.strIngredient10, meal.strMeasure10);
        addIngredient(ingredientsList, meal.strIngredient11, meal.strMeasure11);
        addIngredient(ingredientsList, meal.strIngredient12, meal.strMeasure12);
        addIngredient(ingredientsList, meal.strIngredient13, meal.strMeasure13);
        addIngredient(ingredientsList, meal.strIngredient14, meal.strMeasure14);
        addIngredient(ingredientsList, meal.strIngredient15, meal.strMeasure15);
        addIngredient(ingredientsList, meal.strIngredient16, meal.strMeasure16);
        addIngredient(ingredientsList, meal.strIngredient17, meal.strMeasure17);
        addIngredient(ingredientsList, meal.strIngredient18, meal.strMeasure18);
        addIngredient(ingredientsList, meal.strIngredient19, meal.strMeasure19);
        addIngredient(ingredientsList, meal.strIngredient20, meal.strMeasure20);

        return ingredientsList;
    }

    private void addIngredient(List<Ingredients> list, String ingredient, String measure) {
        if (ingredient != null && !ingredient.trim().isEmpty()) {
            Ingredients ing = new Ingredients();
            ing.setIngredient(ingredient);
            ing.setMeasurement(measure != null ? measure : "");
            list.add(ing);
        }
    }
}