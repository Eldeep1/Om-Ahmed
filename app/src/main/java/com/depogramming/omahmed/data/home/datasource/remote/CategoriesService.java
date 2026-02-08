package com.depogramming.omahmed.data.home.datasource.remote;


import com.depogramming.omahmed.data.home.models.CategoriesResponse;
import com.depogramming.omahmed.data.home.models.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface CategoriesService {
    @GET("categories.php/")
    Observable<CategoriesResponse> getAllCategories();
}
