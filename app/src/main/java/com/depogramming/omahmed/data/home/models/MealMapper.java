package com.depogramming.omahmed.data.home.models;

import java.util.ArrayList;
import java.util.List;

public final class MealMapper {

    private MealMapper() {
    }

    public static FavouriteMeals toFavourite(Meal meal) {
        FavouriteMeals fav = new FavouriteMeals();

        fav.favouriteFlag = true;

        fav.idMeal = meal.idMeal;
        fav.strMeal = meal.strMeal;
        fav.strCategory = meal.strCategory;
        fav.strArea = meal.strArea;
        fav.strInstructions = meal.strInstructions;
        fav.strMealThumb = meal.strMealThumb;
        fav.strTags = meal.strTags;
        fav.strYoutube = meal.strYoutube;

        fav.strIngredient1 = meal.strIngredient1;
        fav.strIngredient2 = meal.strIngredient2;
        fav.strIngredient3 = meal.strIngredient3;
        fav.strIngredient4 = meal.strIngredient4;
        fav.strIngredient5 = meal.strIngredient5;
        fav.strIngredient6 = meal.strIngredient6;
        fav.strIngredient7 = meal.strIngredient7;
        fav.strIngredient8 = meal.strIngredient8;
        fav.strIngredient9 = meal.strIngredient9;
        fav.strIngredient10 = meal.strIngredient10;
        fav.strIngredient11 = meal.strIngredient11;
        fav.strIngredient12 = meal.strIngredient12;
        fav.strIngredient13 = meal.strIngredient13;
        fav.strIngredient14 = meal.strIngredient14;
        fav.strIngredient15 = meal.strIngredient15;
        fav.strIngredient16 = meal.strIngredient16;
        fav.strIngredient17 = meal.strIngredient17;
        fav.strIngredient18 = meal.strIngredient18;
        fav.strIngredient19 = meal.strIngredient19;
        fav.strIngredient20 = meal.strIngredient20;

        fav.strMeasure1 = meal.strMeasure1;
        fav.strMeasure2 = meal.strMeasure2;
        fav.strMeasure3 = meal.strMeasure3;
        fav.strMeasure4 = meal.strMeasure4;
        fav.strMeasure5 = meal.strMeasure5;
        fav.strMeasure6 = meal.strMeasure6;
        fav.strMeasure7 = meal.strMeasure7;
        fav.strMeasure8 = meal.strMeasure8;
        fav.strMeasure9 = meal.strMeasure9;
        fav.strMeasure10 = meal.strMeasure10;
        fav.strMeasure11 = meal.strMeasure11;
        fav.strMeasure12 = meal.strMeasure12;
        fav.strMeasure13 = meal.strMeasure13;
        fav.strMeasure14 = meal.strMeasure14;
        fav.strMeasure15 = meal.strMeasure15;
        fav.strMeasure16 = meal.strMeasure16;
        fav.strMeasure17 = meal.strMeasure17;
        fav.strMeasure18 = meal.strMeasure18;
        fav.strMeasure19 = meal.strMeasure19;
        fav.strMeasure20 = meal.strMeasure20;

        fav.strSource = meal.strSource;
        fav.dateModified = meal.dateModified;

        return fav;
    }

    public static Meal toMeal(FavouriteMeals fav) {
        Meal meal = new Meal();

        meal.isFav = true;

        meal.idMeal = fav.idMeal;
        meal.strMeal = fav.strMeal;
        meal.strCategory = fav.strCategory;
        meal.strArea = fav.strArea;
        meal.strInstructions = fav.strInstructions;
        meal.strMealThumb = fav.strMealThumb;
        meal.strTags = fav.strTags;
        meal.strYoutube = fav.strYoutube;

        meal.strIngredient1 = fav.strIngredient1;
        meal.strIngredient2 = fav.strIngredient2;
        meal.strIngredient3 = fav.strIngredient3;
        meal.strIngredient4 = fav.strIngredient4;
        meal.strIngredient5 = fav.strIngredient5;
        meal.strIngredient6 = fav.strIngredient6;
        meal.strIngredient7 = fav.strIngredient7;
        meal.strIngredient8 = fav.strIngredient8;
        meal.strIngredient9 = fav.strIngredient9;
        meal.strIngredient10 = fav.strIngredient10;
        meal.strIngredient11 = fav.strIngredient11;
        meal.strIngredient12 = fav.strIngredient12;
        meal.strIngredient13 = fav.strIngredient13;
        meal.strIngredient14 = fav.strIngredient14;
        meal.strIngredient15 = fav.strIngredient15;
        meal.strIngredient16 = fav.strIngredient16;
        meal.strIngredient17 = fav.strIngredient17;
        meal.strIngredient18 = fav.strIngredient18;
        meal.strIngredient19 = fav.strIngredient19;
        meal.strIngredient20 = fav.strIngredient20;

        meal.strMeasure1 = fav.strMeasure1;
        meal.strMeasure2 = fav.strMeasure2;
        meal.strMeasure3 = fav.strMeasure3;
        meal.strMeasure4 = fav.strMeasure4;
        meal.strMeasure5 = fav.strMeasure5;
        meal.strMeasure6 = fav.strMeasure6;
        meal.strMeasure7 = fav.strMeasure7;
        meal.strMeasure8 = fav.strMeasure8;
        meal.strMeasure9 = fav.strMeasure9;
        meal.strMeasure10 = fav.strMeasure10;
        meal.strMeasure11 = fav.strMeasure11;
        meal.strMeasure12 = fav.strMeasure12;
        meal.strMeasure13 = fav.strMeasure13;
        meal.strMeasure14 = fav.strMeasure14;
        meal.strMeasure15 = fav.strMeasure15;
        meal.strMeasure16 = fav.strMeasure16;
        meal.strMeasure17 = fav.strMeasure17;
        meal.strMeasure18 = fav.strMeasure18;
        meal.strMeasure19 = fav.strMeasure19;
        meal.strMeasure20 = fav.strMeasure20;

        meal.strSource = fav.strSource;
        meal.dateModified = fav.dateModified;

        return meal;
    }

    public static List<Ingredients> mapMealToIngredients(Meal meal) {
        if (meal == null) {
            return new ArrayList<>();
        }

        List<Ingredients> ingredientsList = new ArrayList<>();

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
    private static void addIngredient(List<Ingredients> list, String ingredient, String measure) {
        if (ingredient != null && !ingredient.trim().isEmpty()) {
            Ingredients ing = new Ingredients();
            ing.setIngredient(ingredient);
            ing.setMeasurement(measure != null ? measure.trim() : "");
            list.add(ing);
        }
    }

    public static String getInstructions(Meal meal) {
        return meal != null ? meal.strInstructions : "";
    }

    public static String getYoutubeUrl(Meal meal) {
        return meal != null ? meal.strYoutube : "";
    }
}