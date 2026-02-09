package com.depogramming.omahmed.presentation.mealdetails.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.presentation.mealdetails.view.tabs.ingredients.IngredientsFragment;
import com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.view.InstructionsFragment;

public class MealDetailsAdapter extends FragmentStateAdapter {
    private Meal meal;

    public MealDetailsAdapter(@NonNull FragmentActivity fragmentActivity,Meal meal) {
        super(fragmentActivity);
        this.meal=meal;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return InstructionsFragment.newInstance(meal);
        }
        return IngredientsFragment.newInstance(meal);
    }
    @Override
    public int getItemCount() {
        return 2;
    }

}
