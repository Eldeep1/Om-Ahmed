package com.depogramming.omahmed.presentation.planner.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.presentation.planner.presentation.PlannerPresenter;
import com.depogramming.omahmed.presentation.planner.presentation.PlannerPresenterImp;
import com.depogramming.omahmed.utils.UserAlerts;

import java.util.List;

public class PlannerFragment extends Fragment implements CalenderView,OnDayClickListener,OnPlannedMealClick {

    private RecyclerView mealsRecyclerView;
    private CalendarAdapter calendarAdapter;
    private TextView monthYearTextView;
    private PlannerPresenter plannerPresenter;
    private PlannedMealsAdapter plannedMealsAdapter;
    private TextView emptyMessageTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plannerPresenter = new PlannerPresenterImp(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planner, container, false);
        RecyclerView calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        monthYearTextView = view.findViewById(R.id.monthYearTextView);
        view.findViewById(R.id.previousMonthButton).setOnClickListener(v -> plannerPresenter.nextMonth());
        view.findViewById(R.id.nextMonthButton).setOnClickListener(v -> plannerPresenter.previousMonth());
        emptyMessageTextView=view.findViewById(R.id.emptyMessageTextView);
        calendarAdapter = new CalendarAdapter(this);
        calendarRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        calendarRecyclerView.setAdapter(calendarAdapter);

        plannedMealsAdapter = new PlannedMealsAdapter(this);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        mealsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
        mealsRecyclerView.setAdapter(plannedMealsAdapter);
        return view;
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
        if (mealsPlanModels == null || mealsPlanModels.isEmpty()) {
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
        plannerPresenter.removeFromPlanned(getContext(),meal,position);
    }

    @Override
    public void onCardClicked(MealsPlanModel meal) {
        plannerPresenter.navigateToDetails(meal);
    }

    @Override
    public void onRemoveButtonAction(MealsPlanModel meal, int position, String message) {
        plannedMealsAdapter.notifyItemChanged(position,meal);
        UserAlerts.showSnackBar(getView(),message);
    }

    @Override
    public void onCardClickedAction(Bundle bundle) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_plannerFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void onStop() {
        super.onStop();
        plannerPresenter.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        plannerPresenter.init(this,this);
    }
}