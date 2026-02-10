package com.depogramming.omahmed.presentation.planner.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;

import java.util.ArrayList;
import java.util.List;

    public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.DayViewHolder> {

        private List<CalendarDay> days = new ArrayList<>();
        private int selectedPosition = -1;
        private OnDayClickListener listener;
        public CalendarAdapter(OnDayClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.calendar_day_item, parent, false);
            return new DayViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
            CalendarDay day = days.get(position);
            holder.bind(day, position == selectedPosition);
        }

        @Override
        public int getItemCount() {
            return days.size();
        }

        public void setDays(List<CalendarDay> days) {
            this.days = days;
            notifyDataSetChanged();
        }

        public void setSelectedPosition(int position) {
            int oldPosition = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(oldPosition);
            notifyItemChanged(selectedPosition);
        }

        class DayViewHolder extends RecyclerView.ViewHolder {
            TextView dayTextView;
            View selectedIndicator;
            View container;

            DayViewHolder(@NonNull View itemView) {
                super(itemView);
                dayTextView = itemView.findViewById(R.id.dayTextView);
                selectedIndicator = itemView.findViewById(R.id.selectedIndicator);
                container = itemView.findViewById(R.id.dayContainer);
            }

            void bind(CalendarDay day, boolean isSelected) {
                if (day.isEmpty) {
                    dayTextView.setText("");
                    container.setClickable(false);
                    dayTextView.setAlpha(0.3f);
                } else {
                    dayTextView.setText(String.valueOf(day.dayOfMonth));
                    container.setClickable(true);
                    dayTextView.setAlpha(day.isCurrentMonth ? 1.0f : 0.3f);

                    if (isSelected) {
                        selectedIndicator.setVisibility(View.VISIBLE);
                        dayTextView.setTextColor(Color.WHITE);
                    } else {
                        selectedIndicator.setVisibility(View.GONE);
                        dayTextView.setTextColor(day.isToday ?
                                Color.parseColor("#EA2A33") : Color.WHITE);
                    }

                    container.setOnClickListener(v -> {
                        if (listener != null && day.isCurrentMonth) {
                            listener.onDayClick(day, getAdapterPosition());
                            setSelectedPosition(getAdapterPosition());
                        }
                    });
                }
            }
        }
    }

