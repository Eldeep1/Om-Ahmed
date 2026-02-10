package com.depogramming.omahmed.presentation.planner.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.presentation.planner.presentation.PlannerPresenter;
import com.depogramming.omahmed.presentation.planner.presentation.PlannerPresenterImp;

import java.util.List;

public class PlannerFragment extends Fragment implements CalenderView,OnDayClickListener {

    private RecyclerView calendarRecyclerView;
    private CalendarAdapter calendarAdapter;
    private TextView monthYearTextView;
    private ImageButton previousMonthButton;
    private ImageButton nextMonthButton;
    PlannerPresenter plannerPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planner, container, false);
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearTextView = view.findViewById(R.id.monthYearTextView);
        previousMonthButton = view.findViewById(R.id.previousMonthButton);
        nextMonthButton = view.findViewById(R.id.nextMonthButton);

        calendarAdapter = new CalendarAdapter(this);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 7));
        calendarRecyclerView.setAdapter(calendarAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plannerPresenter = new PlannerPresenterImp(this);

        previousMonthButton.setOnClickListener(v -> plannerPresenter.previousMonth());

        nextMonthButton.setOnClickListener(v -> plannerPresenter.nextMonth());
    }

        @Override
    public void updateCalendar(List<CalendarDay> days) {
        calendarAdapter.setDays(days);
    }

    @Override
    public void updateMonthYear(String monthYear) {
        monthYearTextView.setText(monthYear);
    }

    @Override
    public void loadDayMeals(List<MealsPlanModel> mealsPlanModels) {
        //set there value to the adapter
    }

    @Override
    public void onDayClick(CalendarDay day, int position) {
        plannerPresenter.loadMealsForDay(day,position);
    }
}