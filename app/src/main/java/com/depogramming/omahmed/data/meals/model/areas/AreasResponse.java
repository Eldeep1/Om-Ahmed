package com.depogramming.omahmed.data.meals.model.areas;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreasResponse {
    @SerializedName("meals")
    public ArrayList<Areas> areas;
}
