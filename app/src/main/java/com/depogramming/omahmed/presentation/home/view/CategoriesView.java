package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.meals.model.categories.Category;

import java.util.List;

public interface CategoriesView {
    void categoriesGotSuccessfully(List<Category> categories);
    void onCategoryClick(String category);
    void onCategoryClickAction(Bundle result);
}
