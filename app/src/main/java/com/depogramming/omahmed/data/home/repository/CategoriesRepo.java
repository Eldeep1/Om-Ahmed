package com.depogramming.omahmed.data.home.repository;


import com.depogramming.omahmed.data.home.datasource.remote.CategoriesRemoteDataSource;
import com.depogramming.omahmed.data.home.models.CategoriesResponse;


import io.reactivex.rxjava3.core.Observable;

public class CategoriesRepo {
    CategoriesRemoteDataSource categoriesRemoteDataSource;

    public CategoriesRepo() {
        categoriesRemoteDataSource = new CategoriesRemoteDataSource();
    }

    public Observable<CategoriesResponse> getAllCategories(){
        return categoriesRemoteDataSource.getAllCategories();
    }
}
