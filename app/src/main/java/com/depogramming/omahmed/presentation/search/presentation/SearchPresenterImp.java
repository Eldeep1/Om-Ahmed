package com.depogramming.omahmed.presentation.search.presentation;

import android.content.Context;
import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;
import com.depogramming.omahmed.data.home.models.CountryUtils;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.repository.AreasRepo;
import com.depogramming.omahmed.data.home.repository.CategoriesRepo;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.search.view.OnSearchItemClick;
import com.depogramming.omahmed.presentation.search.view.SearchViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {
    CategoriesRepo categoriesRepo;
    SearchViewInterface searchView;
    OnSearchItemClick onSearchItemClick;
    AreasRepo areasRepo;
    MealsRepo mealsRepo;

    public SearchPresenterImp(SearchViewInterface searchView, Context context,OnSearchItemClick onSearchItemClick) {
        this.searchView = searchView;
        categoriesRepo = new CategoriesRepo();
        areasRepo = new AreasRepo();
        mealsRepo = new MealsRepo(context);
        this.onSearchItemClick=onSearchItemClick;
    }

    @Override
    public void getSearchMeals(String selectedCountry, String selectedCategory) {
        //call view method that shows list of meals
        //like passing the meals as a parameter
        Disposable subscribe = mealsRepo.getAllMeals().subscribeOn(Schedulers.io())
                .onErrorResumeNext(throwable -> observer -> {}).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchView.showMeals(meals)
                );
        if (selectedCategory.equals("All Categories")) {

        }

    }

    @Override
    public void getAreas() {
        Disposable disposable =
                areasRepo.getAreas()
                        .subscribeOn(Schedulers.io())
                        .map(areas ->
                                areas.stream()
                                        .filter(a -> a != null && a.strArea != null)
                                        .map(a -> new CountryModel(
                                                CountryUtils.getFlagUrl(a.strArea),
                                                a.strArea
                                        ))
                                        .collect(Collectors.toList())
                        )
                        .map(countries -> {
                            List<CountryModel> result = new ArrayList<>(countries.size() + 1);
                            result.add(new CountryModel("", "All Countries"));
                            result.addAll(countries);
                            return result;
                        })

                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                searchView::showCountries,
                                Throwable::printStackTrace
                        );
    }


    @Override
    public void getCategories() {
        Disposable subscribe = categoriesRepo.getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(
                        categoriesResponse -> {
                            List<Category> result = new ArrayList<>(categoriesResponse.getCategories().size() + 1);

                            result.add(new Category("All Categories", ""));
                            result.addAll(categoriesResponse.getCategories());

                            return result;
                        }
                )
                .subscribe(categoriesResponse -> {
                    searchView.showCategories(categoriesResponse);
                }, throwable -> {
                });
    }

    @Override
    public void navigateToMealDetails(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("meal", meal);
        onSearchItemClick.navigateToMealDetails(bundle);
    }

    @Override
    public void toggleFavourite(Meal meal, int position) {
        Disposable subscribe = mealsRepo.toggleFavourite(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            meal.isFav=!meal.isFav;
                            onSearchItemClick.onHeartClickedAction(meal, position);
                        }
                );
    }
}
