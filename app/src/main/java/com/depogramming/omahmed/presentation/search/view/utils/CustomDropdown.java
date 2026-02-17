package com.depogramming.omahmed.presentation.search.view.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.categories.Category;
import com.depogramming.omahmed.data.meals.model.countries.CountryModel;
import com.depogramming.omahmed.presentation.search.view.CategoriesDropDownAdapter;
import com.depogramming.omahmed.presentation.search.view.CountriesDropDownAdapter;
import com.depogramming.omahmed.presentation.search.view.OnDropDownItemClickListener;
import com.depogramming.omahmed.presentation.search.view.OnDropDownItemSelected;

import java.util.List;
public class CustomDropdown implements OnDropDownItemClickListener {
    OnDropDownItemSelected listener;
    public CustomDropdown(OnDropDownItemSelected listener){
        this.listener=listener;
    }
    PopupWindow popupWindow;
    public void showCategories(Context context, View anchorView, List<Category> items) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View dropdownView = inflater.inflate(R.layout.dropdown_menu_layout, null);

        RecyclerView recyclerView = dropdownView.findViewById(R.id.dropdownRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

       popupWindow = new PopupWindow(
                dropdownView,
                anchorView.getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );


        CategoriesDropDownAdapter adapter = new CategoriesDropDownAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setCategories(items);

        popupWindow.setElevation(8);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(anchorView, 0, 8);
    }

    public void showCountries(Context context, View anchorView, List<CountryModel> countries){


        LayoutInflater inflater = LayoutInflater.from(context);
        View dropdownView = inflater.inflate(R.layout.dropdown_menu_layout, null);

        RecyclerView recyclerView = dropdownView.findViewById(R.id.dropdownRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        popupWindow = new PopupWindow(
                dropdownView,
                anchorView.getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );


        CountriesDropDownAdapter adapter = new CountriesDropDownAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setCountries(countries);

        popupWindow.setElevation(8);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(anchorView, 0, 8);
    }
    @Override
    public void onDropdownItemClickListener(Category item) {
        listener.onDropDownItemSelected(item);
        popupWindow.dismiss();
    }

    @Override
    public void onDropdownItemClickListener(CountryModel item) {
        listener.onDropDownItemSelected(item);
        popupWindow.dismiss();
    }
}