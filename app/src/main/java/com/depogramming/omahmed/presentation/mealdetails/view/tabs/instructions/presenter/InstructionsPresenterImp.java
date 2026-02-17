package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.presenter;

import com.depogramming.omahmed.data.meals.model.meal.Instructions;
import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.data.meals.model.utils.MealMapper;
import com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.view.InstructionsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InstructionsPresenterImp implements InstructionsPresenter{
    private InstructionsView view;
    private CompositeDisposable disposables = new CompositeDisposable();

    public InstructionsPresenterImp(InstructionsView view) {
        this.view = view;
    }

    public void loadMealDetails(Meal meal) {
        Disposable d = Single.fromCallable(() -> {
                    List<Instructions> instructions =
                            MealMapper.mapMealToInstructions(meal);
                    String videoId =
                            MealMapper.getYoutubeVideoId(meal);
                    return new Result(instructions, videoId);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            view.showInstructions(result.instructions);

                            if (result.videoId != null && !result.videoId.isEmpty()) {
                                view.showYoutubeVideo(result.videoId);
                            } else {
                                view.hideYoutubeVideo();
                            }
                        },
                        throwable -> view.showError(throwable.getMessage())
                );

        disposables.add(d);
    }

    public void clear() {
        disposables.clear();
    }

    private static class Result {
        List<Instructions> instructions;
        String videoId;

        Result(List<Instructions> instructions, String videoId) {
            this.instructions = instructions;
            this.videoId = videoId;
        }
    }
}