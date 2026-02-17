package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.view;

import com.depogramming.omahmed.data.meals.model.meal.Instructions;

import java.util.List;

public interface InstructionsView {
    void showInstructions(List<Instructions> instructions);
    void showYoutubeVideo(String videoId);
    void hideYoutubeVideo();
    void showError(String message);
}
