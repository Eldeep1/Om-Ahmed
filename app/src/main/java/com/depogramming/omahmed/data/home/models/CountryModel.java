package com.depogramming.omahmed.data.home.models;

public class CountryModel {
    private String flagLink;
    private String countryName;

    public CountryModel(String flagLink, String countryName) {
        this.flagLink = flagLink;
        this.countryName = countryName;
    }
    public String getFlagLink() {
        return flagLink;
    }
    public String getCountryName() {
        return countryName;
    }

}
