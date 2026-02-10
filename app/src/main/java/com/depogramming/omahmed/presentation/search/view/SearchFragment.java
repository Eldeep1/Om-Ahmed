package com.depogramming.omahmed.presentation.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;
import com.depogramming.omahmed.presentation.search.presentation.SearchPresenter;
import com.depogramming.omahmed.presentation.search.view.utils.CustomDropdown;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements OnDropDownItemSelected {
    private MaterialButton categoriesButton;
    private MaterialButton countriesButton;
    private CustomDropdown customDropdown;
    SearchPresenter presenter;
    private String selectedCategory = "All Categories";
    private String selectedCountry = "All Countries";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        categoriesButton = view.findViewById(R.id.categoriesButton);
        countriesButton = view.findViewById(R.id.countriesButton);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        List<Category> categories = new ArrayList<>();
//        Category category = new Category();
//        category.setIdCategory("1");
//        category.setStrCategory("Meow");
//        category.setStrCategoryThumb("https://www.themealdb.com/images/category/beef.png");
//        categories.add(category);
//        categories.add(category);
//        categories.add(category);
//        categories.add(category);
//        categories.add(category);


//        showCategories(categories);
        //if bundle is not null, then get meals where category equals bundle's category
        presenter.getSearchMeals(selectedCountry, selectedCategory);
        presenter.getAreas();
        presenter.getCategories();

        List<CountryModel> countryModels = new ArrayList<>();
        countryModels.add(new CountryModel("", "All Countries"));
        countryModels.add(new CountryModel("https://www.themealdb.com/images/flags/ae.png", "United Arab Emirates"));
        countryModels.add(new CountryModel("https://www.themealdb.com/images/category/beef.png", "United Arab Emirates"));
        countryModels.add(new CountryModel("https://www.themealdb.com/images/flags/ae.png", "United Arab Emirates"));
        countryModels.add(new CountryModel("https://www.themealdb.com/images/flags/ae.png", "United Arab Emirates"));

        showCountries(countryModels);

        customDropdown= new CustomDropdown(this);
    }

    public void showCategories(List<Category> dropdownItems) {
        // Convert API categories to dropdown items
        Category all = new Category();
        all.setStrCategory("All Categories");

        dropdownItems.add(all);

        categoriesButton.setOnClickListener(view -> {
            customDropdown.showCategories(requireContext(), view, dropdownItems);
        });
    }

    public void showCountries(List<CountryModel> countries){

        countriesButton.setOnClickListener(view -> customDropdown.showCountries(requireContext(),view,countries));
    }
    @Override
    public void onDropDownItemSelected(Category item) {
        selectedCategory = item.getStrCategory();
        categoriesButton.setText(selectedCategory);

        if (item.getStrCategory().equals("All Categories")) {
            System.out.println("Selected: All Categories");

//                    presenter.searchMeals(null, selectedCountry.equals("All Countries") ? null : selectedCountry);
        } else {
            System.out.println("Selected category: " + item.getStrCategory());

//                    presenter.searchMeals(item.getName(), selectedCountry.equals("All Countries") ? null : selectedCountry);
        }
    }

    @Override
    public void onDropDownItemSelected(CountryModel item) {
        selectedCountry = item.getCountryName();
        countriesButton.setText(selectedCountry);

        if (selectedCountry.equals("All Countries")) {
            System.out.println("Selected: All Categories");

//                    presenter.searchMeals(null, selectedCountry.equals("All Countries") ? null : selectedCountry);
        } else {
            System.out.println("Selected category: " + selectedCountry);

//                    presenter.searchMeals(item.getName(), selectedCountry.equals("All Countries") ? null : selectedCountry);
        }
    }
}