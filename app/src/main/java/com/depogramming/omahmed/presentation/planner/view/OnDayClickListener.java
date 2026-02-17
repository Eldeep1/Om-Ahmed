package com.depogramming.omahmed.presentation.planner.view;


import com.depogramming.omahmed.data.meals.model.utils.CalendarDay;

public interface OnDayClickListener {
    void onDayClick(CalendarDay day, int position);
}