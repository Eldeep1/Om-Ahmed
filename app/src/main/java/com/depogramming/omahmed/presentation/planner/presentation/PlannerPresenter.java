package com.depogramming.omahmed.presentation.planner.presentation;

import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;

public interface PlannerPresenter {
    void nextMonth();
    void previousMonth();
    void loadMealsForDay(CalendarDay day, int position);
    void removeFromPlanned(MealsPlanModel meal, int position);
    void clear();
    void navigateToDetails(MealsPlanModel meal);
}
