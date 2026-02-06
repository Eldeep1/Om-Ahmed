package com.depogramming.omahmed.presentation.home.presenter;

import com.depogramming.omahmed.data.home.repository.CategoriesRepo;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.home.view.HomeView;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter {

    HomeView homeView;
    CategoriesRepo categoriesRepo;
    MealsRepo mealsRepo;

    public HomePresenterImp(HomeView homeView) {
        this.homeView = homeView;
        this.mealsRepo = new MealsRepo();
        this.categoriesRepo = new CategoriesRepo();
    }

    @Override
    public void getAllCategories() {
        homeView.categoriesLoading();
        //TODO: same as the others, the global variable that holds all of that shits
        Disposable subscribe = categoriesRepo.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categories -> homeView.categoriesGotSuccessfully(categories.getCategories()),
                        throwable -> homeView.categoriesFailed(throwable.getMessage())
                );
    }

    public void getDailyRecommendations() {
        homeView.recommendationMealsLoading();
        List<Character> randomChars = getDailyChars();

        //TODO: another disposable here...
        Disposable subscribe = mealsRepo.getDailyRecommendations(randomChars).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.recommendationsMealsSuccessful(meals),
                        throwable -> homeView.recommendationsMealsFailed(throwable.getMessage())
                );
    }

    @Override
    public void getDailyMeal() {
        homeView.dailyMealLoading();

        Disposable subscribe = mealsRepo.getDailyMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meal -> homeView.dailyMealSuccessfully(meal),
                        throwable -> homeView.dailyMealFailed(throwable.getMessage())
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
}
