package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.view;

import com.depogramming.omahmed.data.home.models.Instructions;

import java.util.List;

public interface InstructionsView {
    void showInstructions(List<Instructions> instructions);
    void showYoutubeVideo(String videoId);
    void hideYoutubeVideo();
    void showError(String message);
}
