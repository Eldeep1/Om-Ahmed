package com.depogramming.omahmed.presentation.onboarding.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.onboarding.presenter.OnBoardingPresenter;
import com.depogramming.omahmed.presentation.onboarding.presenter.OnBoardingScreenPresenterImp;


public class ThirdOnBoarding extends Fragment implements OnBoardingView {

    Button nextButton;
    OnBoardingPresenter onBoardingPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_on_boarding, container, false);
        onBoardingPresenter = new OnBoardingScreenPresenterImp(this, requireActivity().getApplicationContext());
        nextButton = view.findViewById(R.id.third_on_boarding_next_button);

        nextButton.setOnClickListener((view1) -> onBoardingPresenter.endButtonClick(view, requireActivity().getApplication()));
        return view;
    }

    @Override
    public void nextButton() {

    }
}