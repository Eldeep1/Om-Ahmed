package com.depogramming.omahmed.presentation.planner.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.presentation.planner.presentation.PlannerPresenter;
import com.depogramming.omahmed.presentation.planner.presentation.PlannerPresenterImp;

import java.util.List;

public class PlannerFragment extends Fragment implements CalenderView,OnDayClickListener,OnPlannedMealClick {

    private RecyclerView mealsRecyclerView;
    private CalendarAdapter calendarAdapter;
    private TextView monthYearTextView;
    private ImageButton previousMonthButton;
    private ImageButton nextMonthButton;
    private PlannerPresenter plannerPresenter;
    private PlannedMealsAdapter plannedMealsAdapter;
    private TextView emptyMessageTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planner, container, false);
        RecyclerView calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        monthYearTextView = view.findViewById(R.id.monthYearTextView);
        previousMonthButton = view.findViewById(R.id.previousMonthButton);
        nextMonthButton = view.findViewById(R.id.nextMonthButton);
        emptyMessageTextView=view.findViewById(R.id.emptyMessageTextView);
        calendarAdapter = new CalendarAdapter(this);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 7));
        calendarRecyclerView.setAdapter(calendarAdapter);

        plannedMealsAdapter = new PlannedMealsAdapter(this);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        mealsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false)
        );
        mealsRecyclerView.setAdapter(plannedMealsAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plannerPresenter = new PlannerPresenterImp(this,this,getContext().getApplicationContext());
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
        System.out.println("we are here and we should actually remove the message");
        if (mealsPlanModels == null || mealsPlanModels.isEmpty()) {
            System.out.println("interesting");
            mealsRecyclerView.setVisibility(View.GONE);
            emptyMessageTextView.setVisibility(View.VISIBLE);
        } else {
            mealsRecyclerView.setVisibility(View.VISIBLE);
            emptyMessageTextView.setVisibility(View.GONE);
            plannedMealsAdapter.setPlannedMeals(mealsPlanModels);
        }
    }

    @Override
    public void updateCalenderMonth(List<CalendarDay> days) {
        calendarAdapter.setDaysNoSelection(days);
    }

    @Override
    public void onDayClick(CalendarDay day, int position) {
        plannerPresenter.loadMealsForDay(day,position);
    }

    @Override
    public void onRemoveButtonClicked(MealsPlanModel meal, int position) {
        plannerPresenter.removeFromPlanned(meal,position);
    }

    @Override
    public void onCardClicked(MealsPlanModel meal) {
        plannerPresenter.navigateToDetails(meal);
    }

    @Override
    public void onRemoveButtonAction(MealsPlanModel meal, int position) {
        plannedMealsAdapter.notifyItemChanged(position,meal);
        plannedMealsAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onCardClickedAction(Bundle bundle) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_plannerFragment_to_mealDetailsFragment, bundle);
    }
}