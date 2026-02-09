package com.depogramming.omahmed.data.home.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static String getYoutubeVideoId(Meal meal) {
        if (meal == null || meal.strYoutube == null || meal.strYoutube.isEmpty()) {
            return null;
        }

        // Extract video ID from YouTube URL
        // Format: https://www.youtube.com/watch?v=VIDEO_ID
        String url = meal.strYoutube;

        // Method 1: Standard YouTube URL
        if (url.contains("youtube.com/watch?v=")) {
            String[] parts = url.split("v=");
            if (parts.length > 1) {
                String videoId = parts[1];
                // Remove any additional parameters
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    videoId = videoId.substring(0, ampersandPosition);
                }
                return videoId;
            }
        }

        if (url.contains("youtu.be/")) {
            String[] parts = url.split("youtu.be/");
            if (parts.length > 1) {
                String videoId = parts[1];
                // Remove any additional parameters
                int questionPosition = videoId.indexOf('?');
                if (questionPosition != -1) {
                    videoId = videoId.substring(0, questionPosition);
                }
                return videoId;
            }
        }

        return null;
    }

    public static List<Instructions> mapMealToInstructions(Meal meal) {
        List<Instructions> instructionsList = new ArrayList<>();

        if (meal == null || meal.strInstructions == null || meal.strInstructions.isEmpty()) {
            return instructionsList;
        }

        String instructions = meal.strInstructions
                .replace("\\r\\n", "\n")
                .replace("\r\n", "\n")
                .replace("\\n", "\n")
                .trim();

        String[] steps = instructions.split("\n\n+");

        for (String step : steps) {
            step = step.trim();
            if (step.isEmpty()) {
                continue;
            }

            String title;
            String description;

            Pattern numberedPattern = Pattern.compile("^(\\d+)\\s+(.+)", Pattern.DOTALL);
            Matcher numberedMatcher = numberedPattern.matcher(step);

            if (numberedMatcher.matches()) {
                description = numberedMatcher.group(2).trim();
                title = extractTitle(description);
            } else {
                Pattern headerPattern = Pattern.compile("^(.+?):\\s*\n(.+)", Pattern.DOTALL);
                Matcher headerMatcher = headerPattern.matcher(step);

                if (headerMatcher.matches()) {
                    title = headerMatcher.group(1).trim();
                    description = headerMatcher.group(2).trim();
                } else {
                    Pattern stepPattern = Pattern.compile("^step\\s*(\\d+)\\s*\n(.+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                    Matcher stepMatcher = stepPattern.matcher(step);

                    if (stepMatcher.matches()) {
                        description = stepMatcher.group(2).trim();
                        title = extractTitle(description);
                    } else {
                        description = step;
                        title = extractTitle(description);
                    }
                }
            }

            instructionsList.add(new Instructions(title, description));
        }

        return instructionsList;
    }

    private static String extractTitle(String description) {
        if (description == null || description.isEmpty()) {
            return "";
        }

        description = description.replaceFirst("(?i)^(step\\s*)?\\d+\\s*[:\\-\\.]*\\s*", "").trim();

        int firstNewline = description.indexOf('\n');
        if (firstNewline > 0 && firstNewline < 80) {
            String firstLine = description.substring(0, firstNewline).trim();
            if (firstLine.endsWith(":")) {
                return firstLine.substring(0, firstLine.length() - 1).trim();
            }
        }

        int firstPeriod = description.indexOf('.');
        if (firstPeriod > 0 && firstPeriod < 120) {
            String title = description.substring(0, firstPeriod).trim();

            if (title.length() < 25 && firstPeriod < description.length() - 1) {
                int secondPeriod = description.indexOf('.', firstPeriod + 1);
                if (secondPeriod > 0 && secondPeriod < 180) {
                    return description.substring(0, secondPeriod).trim();
                }
            }

            return title;
        }

        if (description.length() > 75) {
            int lastSpace = description.lastIndexOf(' ', 75);
            if (lastSpace > 45) {
                return description.substring(0, lastSpace).trim() + "...";
            }
            return description.substring(0, 75).trim() + "...";
        }

        return description;
    }
}