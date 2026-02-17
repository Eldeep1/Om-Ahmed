package com.depogramming.omahmed.presentation.search.view;

import com.depogramming.omahmed.data.meals.model.categories.Category;
import com.depogramming.omahmed.data.meals.model.countries.CountryModel;

public interface OnDropDownItemSelected {
    void onDropDownItemSelected(Category item);
    void onDropDownItemSelected(CountryModel item);
}
