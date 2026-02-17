package com.depogramming.omahmed.presentation.search.presentation;

import android.content.Context;
import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;
import com.depogramming.omahmed.data.home.models.CountryUtils;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.search.view.OnSearchItemClick;
import com.depogramming.omahmed.presentation.search.view.SearchViewInterface;
import com.depogramming.omahmed.utils.GuestModeDialog;
import com.depogramming.omahmed.utils.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchPresenterImp implements SearchPresenter {
    SearchViewInterface searchView;
    OnSearchItemClick onSearchItemClick;
    MealsRepo mealsRepo;
    private final CompositeDisposable disposables = new CompositeDisposable();
    List<Meal> allMeals;
    private final PublishSubject<Object[]> searchSubject = PublishSubject.create();

    public SearchPresenterImp( Context context) {
        mealsRepo = new MealsRepo(context);
        allMeals = new ArrayList<>();


    }

    @Override
    public void getSearchMeals(String selectedCountry, String selectedCategory) {

        searchView.setMeals(new ArrayList<>());

        disposables.add(mealsRepo.getAllMeals()
                .subscribeOn(Schedulers.io())
                .map(meals -> meals.stream()
                        .filter(meal -> {
                            boolean matchesCategory = selectedCategory.equals("All Categories")
                                    || meal.strCategory.equals(selectedCategory);
                            boolean matchesCountry = selectedCountry.equals("All Countries")
                                    || meal.strArea.equals(selectedCountry);
                            return matchesCategory && matchesCountry;
                        })
                        .collect(Collectors.toList()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> searchView.setMeals(new ArrayList<>()))
                .subscribe(
                        filteredMeals -> {
                            allMeals.addAll(filteredMeals);
                            searchView.showMeals(filteredMeals);
                        },
                        throwable -> {
                            //show error page?
                        }
                ));
    }

    @Override
    public void getAreas() {
        disposables.add(mealsRepo.getAreas()
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
                        ));
    }


    @Override
    public void getCategories() {
        disposables.add(mealsRepo.getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(
                        categoriesResponse -> {
                            List<Category> result = new ArrayList<>(categoriesResponse.getCategories().size() + 1);

                            result.add(new Category("All Categories", ""));
                            result.addAll(categoriesResponse.getCategories());

                            return result;
                        }
                )
                .subscribe(categoriesResponse -> searchView.showCategories(categoriesResponse), throwable -> {
                }));
    }

    @Override
    public void navigateToMealDetails(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("meal", meal);
        onSearchItemClick.navigateToMealDetails(bundle);
    }

    @Override
    public void toggleFavourite(Meal meal, int position, Context context) {
        if (UserData.isGuest) {
            GuestModeDialog.show(context);
        } else {
            disposables.add(mealsRepo.toggleFavourite(meal).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                                meal.isFav = !meal.isFav;
                                onSearchItemClick.onHeartClickedAction(meal, position);
                            }
                    ));
        }
    }

    @Override
    public void searchBySpecificMeal(String query, String selectedCountry, String selectedCategory) {
        searchSubject.onNext(new Object[]{query, selectedCountry, selectedCategory});
    }

    @Override
    public void clear() {
        disposables.clear();
        searchView = null;
        onSearchItemClick = null;
    }
    @Override
    public void setViews(OnSearchItemClick onSearchItemClick, SearchViewInterface searchView){
        this.onSearchItemClick=onSearchItemClick;
        this.searchView=searchView;

        disposables.add(searchSubject
                .debounce(300, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .distinctUntilChanged((oldArr, newArr) ->
                        oldArr[0].equals(newArr[0]) &&
                                oldArr[1].equals(newArr[1]) &&
                                oldArr[2].equals(newArr[2])
                )
                .map(arr -> {

                    String query = (String) arr[0];
                    String country = (String) arr[1];
                    String category = (String) arr[2];

                    List<Meal> result = new ArrayList<>();

                    for (Meal meal : allMeals) {

                        boolean matchesQuery =
                                query == null || query.isEmpty()
                                        || meal.strMeal.toLowerCase()
                                        .contains(query.toLowerCase());

                        boolean matchesCategory =
                                category.equals("All Categories")
                                        || meal.strCategory.equals(category);

                        boolean matchesCountry =
                                country.equals("All Countries")
                                        || meal.strArea.equals(country);

                        if (matchesQuery && matchesCategory && matchesCountry) {
                            result.add(meal);
                        }
                    }

                    return result;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchView::setMeals));
    }

}