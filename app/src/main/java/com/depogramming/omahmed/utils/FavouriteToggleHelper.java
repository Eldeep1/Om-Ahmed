package com.depogramming.omahmed.utils;


import android.content.Context;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.data.meals.repository.MealsRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouriteToggleHelper {

    public interface OnFavouriteToggled {
        void onToggled(boolean isFav, String message);
    }

    public static void toggle(
            Context context,
            Meal meal,
            MealsRepo mealsRepo,
            CompositeDisposable disposables,
            OnFavouriteToggled onToggled
    ) {
        if (UserData.isGuest) {
            GuestModeDialog.show(context);
            return;
        }

        if (meal.isFav) {
            ActionCheckingDialogue.show(
                    context,
                    context.getString(R.string.remove_from_favorites),
                    context.getString(R.string.are_you_sure_you_want_to_remove_this_delicious_recipe_from_your_favorites),
                    R.drawable.broken_heart,
                    result -> {
                        if (result) {
                            performToggle(meal, mealsRepo, disposables, onToggled);
                        }
                    }
            );
        } else {
            performToggle(meal, mealsRepo, disposables, onToggled);
        }
    }

    private static void performToggle(Meal meal,
                                      MealsRepo mealsRepo,
                                      CompositeDisposable disposables,
                                      OnFavouriteToggled onToggled
    ) {
        String successMessage = meal.isFav ? "Removed Successfully" : "Added Successfully";

        disposables.add(
                mealsRepo.toggleFavourite(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    meal.isFav = !meal.isFav;
                                    if (onToggled != null) {
                                        onToggled.onToggled(meal.isFav, successMessage);
                                    }
                                },
                                throwable -> System.out.println("Error toggling favourite: " + throwable)
                        )
        );
    }
}