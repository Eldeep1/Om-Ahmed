package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.Category;

import java.util.List;

public interface CategoriesView {
    void categoriesGotSuccessfully(List<Category> categories);
    void categoriesFailed(String errorMessage);
    void onCategoryClick(String category);
    void onCategoryClickAction(Bundle result);
}
