package com.depogramming.omahmed.presentation.planner.presentation;

import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.presentation.planner.view.CalenderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PlannerPresenterImp implements PlannerPresenter {
    private final Calendar currentCalendar;

    CalenderView calenderView;
    public PlannerPresenterImp(CalenderView calenderView) {
        this.calenderView=calenderView;
        currentCalendar = Calendar.getInstance();
        updateCalendar();
    }

    private List<CalendarDay> generateCalendarDays(Calendar calendar) {
        List<CalendarDay> days = new ArrayList<>();

        Calendar cal = (Calendar) calendar.clone();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

        for (int i = 0; i < firstDayOfWeek; i++) {
            days.add(new CalendarDay());
        }

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day <= daysInMonth; day++) {
            days.add(new CalendarDay(day, month, year, true));
        }
        while (days.size() % 7 != 0) {
            days.add(new CalendarDay());
        }

        return days;
    }

    private void updateCalendar() {
        calenderView.updateCalendar(generateCalendarDays(currentCalendar));
        updateMonthYear();
    }

    private void updateMonthYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String monthYear=sdf.format(currentCalendar.getTime());
        calenderView.updateMonthYear(monthYear);
    }

    @Override
    public void nextMonth() {
        currentCalendar.add(Calendar.MONTH, 1);
        updateCalendar();
    }

    @Override
    public void previousMonth() {
        currentCalendar.add(Calendar.MONTH, -1);
        updateCalendar();
    }

    @Override
    public void loadMealsForDay(CalendarDay day, int position) {


        //1. get the meals from the database
        //2. sent the planned meals list to the view
//        calenderView.loadDayMeals();
    }
}
