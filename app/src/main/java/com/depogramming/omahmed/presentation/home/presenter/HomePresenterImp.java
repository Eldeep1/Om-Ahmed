package com.depogramming.omahmed.presentation.home.presenter;

import com.depogramming.omahmed.data.home.repository.CategoriesRepo;
import com.depogramming.omahmed.presentation.home.view.HomeCategoriesView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter{

    HomeCategoriesView homeCategoriesView;
    CategoriesRepo categoriesRepo;
    public HomePresenterImp(HomeCategoriesView homeCategoriesView) {
        this.homeCategoriesView = homeCategoriesView;
        this.categoriesRepo = new CategoriesRepo();
    }

    @Override
    public void getAllCategories() {
        homeCategoriesView.categoriesLoading();
        //TODO: same as the others, the global variable that holds all of that shits
        Disposable subscribe = categoriesRepo.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categories -> homeCategoriesView.categoriesGotSuccessfully(categories.getCategories()),
                        throwable -> homeCategoriesView.categoriesFailed(throwable.getMessage())
                );
    }
}
