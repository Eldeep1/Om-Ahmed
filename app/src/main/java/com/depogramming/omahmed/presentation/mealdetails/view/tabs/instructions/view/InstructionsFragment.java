package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.meal.Instructions;
import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.presenter.InstructionsPresenter;
import com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.presenter.InstructionsPresenterImp;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;
public class InstructionsFragment extends Fragment implements InstructionsView {

    private RecyclerView instructionsRecyclerView;
    private InstructionsRecyclerAdapter instructionsAdapter;
    private YouTubePlayerView youtubePlayerView;
    private InstructionsPresenter presenter;

    private static final String ARG_MEAL = "meal";

    public static InstructionsFragment newInstance(Meal meal) {
        InstructionsFragment fragment = new InstructionsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MEAL, meal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instructions, container, false);
        instructionsRecyclerView = view.findViewById(R.id.instructionsRecyclerView);
        youtubePlayerView = view.findViewById(R.id.youtubePlayerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new InstructionsPresenterImp(this);
        setupRecyclerView();

        if (getArguments() != null) {
            Meal meal = getArguments().getParcelable(ARG_MEAL);
            if (meal != null) {
                presenter.loadMealDetails(meal);
            }
        }
    }

    private void setupRecyclerView() {
        instructionsAdapter = new InstructionsRecyclerAdapter();
        instructionsRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );
        instructionsRecyclerView.setAdapter(instructionsAdapter);
    }


    @Override
    public void showInstructions(List<Instructions> instructions) {
        instructionsAdapter.setInstructions(instructions);
    }

    @Override
    public void showYoutubeVideo(String videoId) {
        youtubePlayerView.setVisibility(View.VISIBLE);
        getLifecycle().addObserver(youtubePlayerView);

        youtubePlayerView.addYouTubePlayerListener(
                new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.cueVideo(videoId, 0);
                    }
                }
        );
    }

    @Override
    public void hideYoutubeVideo() {
        youtubePlayerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.clear();
        youtubePlayerView.release();
    }
}
