package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.presentation.home.presenter.HomePresenter;
import com.depogramming.omahmed.presentation.home.presenter.HomePresenterImp;

import java.util.List;

public class HomeFragment extends Fragment implements HomeCategoriesView {

    HomePresenter homePresenter;
    RecyclerView categoriesRecyclerView;
    HorizontalCategoriesAdapter horizontalCategoriesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenterImp(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoriesRecyclerView = view.findViewById(R.id.homeCategoriesHorizontalList);
        horizontalCategoriesAdapter = new HorizontalCategoriesAdapter();
        categoriesRecyclerView.setAdapter(horizontalCategoriesAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(layoutManager);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homePresenter.getAllCategories();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void categoriesLoading() {
    }

    @Override
    public void categoriesGotSuccessfully(List<Category> categories) {
        horizontalCategoriesAdapter.setCategories(categories);
    }


    @Override
    public void categoriesFailed(String errorMessage) {
        System.out.println(errorMessage);
    }
}