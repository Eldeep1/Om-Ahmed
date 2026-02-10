package com.depogramming.omahmed.presentation.planner.presentation;

import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;

public interface PlannerPresenter {
    void nextMonth();
    void previousMonth();
    void loadMealsForDay(CalendarDay day, int position);
}
