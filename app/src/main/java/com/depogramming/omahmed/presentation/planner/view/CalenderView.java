package com.depogramming.omahmed.presentation.planner.view;

import com.depogramming.omahmed.data.meals.model.utils.CalendarDay;
import com.depogramming.omahmed.data.meals.model.meal.MealsPlanModel;

import java.util.List;

public interface CalenderView {
    void updateCalendar(List<CalendarDay> days);
    void updateMonthYear(String monthYear);
    void loadDayMeals(List<MealsPlanModel> mealsPlanModels);
    void updateCalenderMonth(List<CalendarDay> days);
}
