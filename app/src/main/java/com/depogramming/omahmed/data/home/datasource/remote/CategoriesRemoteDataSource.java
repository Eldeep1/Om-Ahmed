package com.depogramming.omahmed.data.home.datasource.remote;


import com.depogramming.omahmed.data.home.models.CategoriesResponse;
import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.network.Network;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class CategoriesRemoteDataSource {
    CategoriesService categoriesService;
    public CategoriesRemoteDataSource(){
        categoriesService= Network.getInstance().getCategoryService();
    }
    public Observable<CategoriesResponse> getAllCategories(){
        return categoriesService.getAllCategories();
    }

}
