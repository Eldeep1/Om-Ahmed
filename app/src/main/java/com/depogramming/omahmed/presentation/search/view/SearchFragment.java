package com.depogramming.omahmed.presentation.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.presentation.search.presentation.SearchPresenter;
import com.depogramming.omahmed.presentation.search.presentation.SearchPresenterImp;
import com.depogramming.omahmed.presentation.search.view.utils.CustomDropdown;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class SearchFragment extends Fragment implements OnDropDownItemSelected, SearchViewInterface, OnSearchItemClick {
    private MaterialButton categoriesButton;
    private MaterialButton countriesButton;
    private CustomDropdown customDropdown;
    private SearchPresenter presenter;
    private ImageButton backButton;
    private EditText searchEditText;
    SearchedMealsAdapter searchedMealsAdapter;

    private String selectedCategory = "All Categories";
    private String selectedCountry = "All Countries";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        categoriesButton = view.findViewById(R.id.categoriesButton);
        countriesButton = view.findViewById(R.id.countriesButton);
        RecyclerView searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false));
        searchedMealsAdapter = new SearchedMealsAdapter(this);
        searchResultsRecyclerView.setAdapter(searchedMealsAdapter);
        searchEditText = view.findViewById(R.id.searchEditText);
        backButton = view.findViewById(R.id.backButton);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, bundle) -> {
            if (bundle.getString("category") != null) {
                selectedCategory = bundle.getString("category");
                System.out.println("yaaaaay");
                categoriesButton.setText(selectedCategory);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customDropdown = new CustomDropdown(this);

        presenter = new SearchPresenterImp(this, requireContext(), this);
        setupSearchBar();
        //if bundle is not null, then get meals where category equals bundle's category

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("category")) {
            System.out.println("interestinggggg");
            selectedCategory = bundle.getString("category");
            categoriesButton.setText(selectedCategory);
        }
        presenter.getSearchMeals(selectedCountry, selectedCategory);
        presenter.getAreas();
        presenter.getCategories();

    }

    private void setupSearchBar() {
        backButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        // Search text change listener
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Show/hide clear button with animation
                if (s.length() > 0) {


                    // Perform search
//                performSearch(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Search action from keyboard
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                performSearch(searchEditText.getText().toString());
                hideKeyboard();
                return true;
            }
            return false;
        });

    }

    private void hideKeyboard() {


        android.view.inputmethod.InputMethodManager imm =
                (android.view.inputmethod.InputMethodManager)
                        requireActivity().getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        }
    }

    @Override
    public void showMeals(List<Meal> meals) {
        searchedMealsAdapter.addToList(meals);
    }

    public void showCategories(List<Category> dropdownItems) {
        categoriesButton.setOnClickListener(view -> {
            customDropdown.showCategories(requireContext(), view, dropdownItems);
        });
    }

    public void showCountries(List<CountryModel> countries) {
        countriesButton.setOnClickListener(view -> customDropdown.showCountries(requireContext(), view, countries));
    }

    @Override
    public void setMeals(List<Meal> meals) {
        searchedMealsAdapter.setMeals(meals);
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

    @Override
    public void onCardClicked(Meal meal) {
        presenter.navigateToMealDetails(meal);
    }

    @Override
    public void navigateToMealDetails(Bundle bundle) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_searchFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void onHeartClicked(Meal meal, int position) {
        presenter.toggleFavourite(meal, position);
    }

    @Override
    public void onHeartClickedAction(Meal meal, int position) {
        searchedMealsAdapter.notifyItemChanged(position);
    }


}