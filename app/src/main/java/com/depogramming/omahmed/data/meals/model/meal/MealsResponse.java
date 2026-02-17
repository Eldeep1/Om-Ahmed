package com.depogramming.omahmed.data.meals.model.meal;

import java.util.List;

public class MealsResponse {
    private List<Meal> meals;

    public MealsResponse(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
