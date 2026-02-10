package com.depogramming.omahmed.data.home.datasource.remote;

import com.depogramming.omahmed.data.home.models.AreasResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface AreasService {
    @GET("list.php?a=list/")
    Observable<AreasResponse> getAreasNames();
}
