package com.depogramming.omahmed.data.meals.model.utils;


import java.util.Calendar;

public class CalendarDay {
    public int dayOfMonth;
    public int month;
    public int year;
    public boolean isCurrentMonth;
    public boolean isToday;
    public boolean isEmpty;
    public Calendar calendar;

    public CalendarDay(int dayOfMonth, int month, int year, boolean isCurrentMonth) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.isCurrentMonth = isCurrentMonth;
        this.isEmpty = false;

        calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        // Check if today
        Calendar today = Calendar.getInstance();
        isToday = calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH);
    }

    public CalendarDay() {
        this.isEmpty = true;
    }
}