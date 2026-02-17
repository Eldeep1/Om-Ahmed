package com.depogramming.omahmed.presentation.favourites.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;
import com.depogramming.omahmed.presentation.favourites.presentation.FavouritesPresenter;
import com.depogramming.omahmed.presentation.favourites.presentation.FavouritesPresenterImp;
import com.depogramming.omahmed.utils.UserAlerts;

import java.util.List;

public class FavouritesFragment extends Fragment implements FavouritesView, OnHeartClicked, OnCardClicked {
    RecyclerView favouritesListView;
    FavouritesAdapter favouritesAdapter;
    FavouritesPresenter favouritesPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favouritesPresenter = new FavouritesPresenterImp(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        favouritesListView = view.findViewById(R.id.favouritesListView);
        favouritesAdapter = new FavouritesAdapter(this, this);
        favouritesListView.setAdapter(favouritesAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        favouritesListView.setLayoutManager(layoutManager);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        favouritesPresenter.setView(this,this,this);
        favouritesPresenter.getAllFavourites();
    }

    @Override
    public void favouritesLoading() {

    }

    @Override
    public void favouritesGotSuccessfully(List<FavouriteMeals> favouriteMeals) {
        favouritesAdapter.setFavouriteMeals(favouriteMeals);
    }

    @Override
    public void favouritesFailed(String errorMessage) {

    }

    @Override
    public void removeFavouriteLogic(FavouriteMeals favouriteMeals, int position) {
        favouritesPresenter.changeFavouritesFavState(getContext(),favouriteMeals, position);
    }

    @Override
    public void removeFavouriteUI(int position,String message) {
        favouritesAdapter.notifyItemChanged(position);
        UserAlerts.showSnackBar(getView(),message);
    }

    @Override
    public void onCardClicked(FavouriteMeals meal) {
        favouritesPresenter.onCardClicked(meal);
    }

    @Override
    public void onCardClickedAction(Bundle bundle) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_favouritesFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void onStop() {
        super.onStop();
        favouritesPresenter.clear();
    }
}