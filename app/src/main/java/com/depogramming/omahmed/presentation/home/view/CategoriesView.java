package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.home.models.Category;

import java.util.List;

public interface CategoriesView {
    void categoriesLoading();
    void categoriesGotSuccessfully(List<Category> categories);
    void categoriesFailed(String errorMessage);
}
