package com.depogramming.omahmed.presentation.search.view;

import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;

public interface OnDropDownItemSelected {
    void onDropDownItemSelected(Category item);
    void onDropDownItemSelected(CountryModel item);
}
