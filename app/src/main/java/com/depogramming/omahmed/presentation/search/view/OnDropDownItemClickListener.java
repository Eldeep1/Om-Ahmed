package com.depogramming.omahmed.presentation.search.view;

import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;

public interface OnDropDownItemClickListener {
    void onDropdownItemClickListener(Category item);
    void onDropdownItemClickListener(CountryModel item);
}