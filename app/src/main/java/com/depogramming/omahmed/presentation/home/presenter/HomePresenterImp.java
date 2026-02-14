package com.depogramming.omahmed.presentation.home.presenter;

import android.content.Context;
import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.CategoriesResponse;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.repository.CategoriesRepo;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.home.view.HomeView;
import com.depogramming.omahmed.utils.GuestModeDialog;
import com.depogramming.omahmed.utils.UserData;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter {

    HomeView homeView;
    CategoriesRepo categoriesRepo;
    MealsRepo mealsRepo;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public HomePresenterImp(Context context) {
        this.mealsRepo = new MealsRepo(context);
        this.categoriesRepo = new CategoriesRepo();
    }

    @Override
    public void retryAllButton() {
        initData();
    }

    @Override
    public void changeDailyMealFavState(Meal meal, Context context) {
        if (UserData.isGuest) {
            GuestModeDialog.show(context);
        } else {
            disposables.add(mealsRepo.toggleFavourite(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                meal.isFav = !meal.isFav;
                homeView.updateDailyMealFavState(meal.isFav);
            }));
        }
    }

    @Override
    public void changeRecommendationsFavState(Meal meal, int position, Context context) {
        if (UserData.isGuest) {
            GuestModeDialog.show(context);
        } else {
            disposables.add(mealsRepo.toggleFavourite(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
                meal.isFav = !meal.isFav;
                homeView.updateListViewHeart(position, meal.isFav);
            }, throwable -> System.out.println("lol, we got an error" + throwable)));
        }
    }

    @Override
    public void navigateToMealDetails(Meal meal) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("meal", meal);
        homeView.navigateToMealDetails(bundle);
    }

    @Override
    public void navigateToSearch(String category) {
        Bundle result = new Bundle();
        result.putString("category", category);
        homeView.onCategoryClickAction(result);
    }

    @Override
    public void setView(HomeView homeView) {
        this.homeView = homeView;
        initData();
    }

    @Override
    public void initData() {
        homeView.allMealsLoading();
        disposables.add(
                Single.zip(
                                categoriesRepo.getAllCategories().firstOrError(),
                                mealsRepo.getDailyRecommendations(getDailyChars()).firstOrError(),
                                mealsRepo.getDailyMeal(generateRandomValidChar()).firstOrError(),
                                (categories, recommendations, dailyMeal) -> {
                                    Object[] objects = {categories, recommendations, dailyMeal};

                                    return objects;
                                }
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                objects -> {

                                    CategoriesResponse categoriesResponse =
                                            (CategoriesResponse) objects[0];

                                    List<Meal> recommendations =
                                            (List<Meal>) objects[1];

                                    Meal dailyMeal =
                                            (Meal) objects[2];

                                    homeView.categoriesGotSuccessfully(
                                            categoriesResponse.getCategories()
                                    );

                                    homeView.recommendationsMealsSuccessful(recommendations);
                                    homeView.dailyMealSuccessfully(dailyMeal);
                                    homeView.allMealsSuccessfully();
                                },
                                throwable -> {
                                     homeView.allMealsError(throwable.getMessage());
                                }
                        )
        );


    }
    private List<Character> getDailyChars() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        long seed = System.currentTimeMillis() / (1000 * 60 * 60 * 24);
        Random random = new Random(seed);

        char char1 = alphabet.charAt(random.nextInt(alphabet.length()));
        char char2 = alphabet.charAt(random.nextInt(alphabet.length()));

        while (char1 == char2) {
            char2 = alphabet.charAt(random.nextInt(alphabet.length()));
        }

        return Arrays.asList(char1, char2);
    }

    private char generateRandomValidChar() {
        String alphabet = "abcdefghijklmnopqrstuvwyz";
        long seed = getTodaySeed();
        Random r = new Random(seed);
        return alphabet.charAt(r.nextInt(alphabet.length()));
    }

    private long getTodaySeed() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String dateString = sdf.format(new Date());
        return Long.parseLong(dateString) + 3;
    }

    @Override
    public void clear() {
        disposables.clear();
        homeView = null;
    }
}
