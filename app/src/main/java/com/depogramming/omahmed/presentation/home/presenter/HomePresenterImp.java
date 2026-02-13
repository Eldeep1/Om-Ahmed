package com.depogramming.omahmed.presentation.home.presenter;

import android.content.Context;
import android.os.Bundle;

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
    public void getAllCategories() {
        homeView.categoriesLoading();
        disposables.add(categoriesRepo.getAllCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(categories -> homeView.categoriesGotSuccessfully(categories.getCategories()), throwable -> homeView.categoriesFailed(throwable.getMessage())));
    }

    @Override
    public void getDailyRecommendations() {
        homeView.recommendationMealsLoading();
        List<Character> randomChars = getDailyChars();

        disposables.add(mealsRepo.getDailyRecommendations(randomChars).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(meals -> homeView.recommendationsMealsSuccessful(meals), throwable -> homeView.recommendationsMealsFailed(throwable.getMessage())));
    }

    @Override
    public void getDailyMeal() {
        homeView.dailyMealLoading();

        disposables.add(mealsRepo.getDailyMeal(generateRandomValidChar()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(meal -> homeView.dailyMealSuccessfully(meal), throwable -> homeView.dailyMealFailed(throwable.getMessage())));
    }

    @Override
    public void retryAllButton() {
        getAllCategories();
        getDailyRecommendations();
        getDailyMeal();
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
