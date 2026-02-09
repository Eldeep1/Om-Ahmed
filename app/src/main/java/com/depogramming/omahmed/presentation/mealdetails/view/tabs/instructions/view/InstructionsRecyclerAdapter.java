package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.home.models.Instructions;

import java.util.ArrayList;
import java.util.List;

public class InstructionsRecyclerAdapter extends RecyclerView.Adapter<InstructionsRecyclerAdapter.ViewHolder> {

    private List<Instructions> instructionsList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instructions_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Instructions instruction = instructionsList.get(position);
        holder.bind(instruction, position + 1);
    }

    public void setInstructions(List<Instructions> instructions) {
        this.instructionsList.clear();
        this.instructionsList.addAll(instructions);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return instructionsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView stepNumberTextView;
        private TextView titleTextView;
        private TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepNumberTextView = itemView.findViewById(R.id.stepNumberTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void bind(Instructions instruction, int stepNumber) {
            stepNumberTextView.setText(String.format("%02d", stepNumber));
            titleTextView.setText(instruction.getTitle());
            descriptionTextView.setText(instruction.getDescription());
        }
    }
}