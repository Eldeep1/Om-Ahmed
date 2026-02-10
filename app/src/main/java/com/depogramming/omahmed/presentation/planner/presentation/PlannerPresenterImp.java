package com.depogramming.omahmed.presentation.planner.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.data.mealsplan.repo.MealsPlanRepo;
import com.depogramming.omahmed.presentation.planner.view.CalenderView;
import com.depogramming.omahmed.presentation.planner.view.OnPlannedMealClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenterImp implements PlannerPresenter {
    private final Calendar currentCalendar;
    MealsPlanRepo mealsPlanRepo;
    CalenderView calenderView;
    OnPlannedMealClick onPlannedMealClick;

    public PlannerPresenterImp(CalenderView calenderView, OnPlannedMealClick onPlannedMealClick, Context context) {
        this.calenderView = calenderView;
        currentCalendar = Calendar.getInstance();
        mealsPlanRepo = new MealsPlanRepo(context);
        this.onPlannedMealClick=onPlannedMealClick;
        loadMealsForToday();
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
    private void updateCalendarMonth() {
        calenderView.updateCalenderMonth(generateCalendarDays(currentCalendar));
        updateMonthYear();
    }

    private void updateMonthYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String monthYear = sdf.format(currentCalendar.getTime());
        calenderView.updateMonthYear(monthYear);
    }

    @Override
    public void nextMonth() {
        currentCalendar.add(Calendar.MONTH, 1);
        updateCalendarMonth();
    }

    @Override
    public void previousMonth() {
        currentCalendar.add(Calendar.MONTH, -1);
        updateCalendarMonth();
    }

    @Override
    public void loadMealsForDay(CalendarDay day, int position) {
        Disposable subscribe = mealsPlanRepo.getAllPlannedMeals(day.calendar.getTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealsPlanModels -> {
                            for (MealsPlanModel meal:mealsPlanModels) {
                                System.out.println(meal.isFav);
                            }
                            calenderView.loadDayMeals(mealsPlanModels);
                        },
                        throwable -> System.out.println("interesting" + throwable)
                );

        //1. get the meals from the database
        //2. sent the planned meals list to the view
//        calenderView.loadDayMeals();
    }

    @Override
    public void removeFromPlanned(MealsPlanModel plannedMeal, int position) {
        Disposable subscribe = mealsPlanRepo.deletePlannedMeal(plannedMeal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        () -> {
                            plannedMeal.isFav=!plannedMeal.isFav;
                            onPlannedMealClick.onRemoveButtonAction(plannedMeal, position);
                        }, throwable -> System.out.println("ما علينا من النقطه دي")
                );

    }

    private void loadMealsForToday() {
        Calendar today = Calendar.getInstance();
        CalendarDay todayCalendarDay = new CalendarDay(
                today.get(Calendar.DAY_OF_MONTH),
                today.get(Calendar.MONTH),
                today.get(Calendar.YEAR),
                true
        );
        loadMealsForDay(todayCalendarDay, -1);
    }
}
