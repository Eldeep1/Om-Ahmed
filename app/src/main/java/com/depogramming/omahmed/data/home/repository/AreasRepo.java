package com.depogramming.omahmed.data.home.repository;

import com.depogramming.omahmed.data.home.datasource.remote.AreasRemoteDataSource;
import com.depogramming.omahmed.data.home.models.Areas;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class AreasRepo {
    AreasRemoteDataSource areasRemoteDataSource;

    public AreasRepo() {
        areasRemoteDataSource = new AreasRemoteDataSource();
    }
    public Observable<List<Areas>> getAreas(){
        return areasRemoteDataSource.getAreas().map(areasResponse -> areasResponse.areas);
    }
}
