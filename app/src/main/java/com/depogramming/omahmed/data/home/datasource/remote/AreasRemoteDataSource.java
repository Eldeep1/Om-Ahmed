package com.depogramming.omahmed.data.home.datasource.remote;

import com.depogramming.omahmed.data.home.models.AreasResponse;
import com.depogramming.omahmed.data.network.Network;

import io.reactivex.rxjava3.core.Observable;

public class AreasRemoteDataSource {
    AreasService areasService;
    public AreasRemoteDataSource(){
        areasService= Network.getInstance().getAreasService();
    }
    public Observable<AreasResponse> getAreas(){
        return areasService.getAreasNames();
    }
}
