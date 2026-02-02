package com.depogramming.omahmed.presentation.onboarding.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.onboarding.presenter.OnBoardingPresenter;
import com.depogramming.omahmed.presentation.onboarding.presenter.OnBoardingScreenPresenterImp;

public class FirstOnBoarding extends Fragment implements OnBoardingView {

    Button nextButton;
    ViewPager2 viewPager2;
    TextView skipText;
    OnBoardingPresenter onBoardingPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_on_boarding, container, false);
        onBoardingPresenter = new OnBoardingScreenPresenterImp(this);
        initUI(view);

        return view;
    }

    private void initUI(View view) {
        assert getActivity() != null;
        viewPager2 = getActivity().findViewById(R.id.view_pager);
        nextButton = view.findViewById(R.id.first_on_boarding_next_button);
        skipText = view.findViewById(R.id.first_on_boarding_skip_button);

        skipText.setOnClickListener((view1) -> onBoardingPresenter.endButtonClick(view1, requireActivity().getApplication()));
        nextButton.setOnClickListener((view1) -> onBoardingPresenter.nextButtonClick());
    }

    @Override
    public void nextButton() {
        viewPager2.setCurrentItem(1);
    }
}