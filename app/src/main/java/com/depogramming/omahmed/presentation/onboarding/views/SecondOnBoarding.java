package com.depogramming.omahmed.presentation.onboarding.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.onboarding.presenter.OnBoardingPresenter;
import com.depogramming.omahmed.presentation.onboarding.presenter.OnBoardingScreenPresenterImp;


public class SecondOnBoarding extends Fragment implements OnBoardingView{
    ViewPager2 viewPager2;
    TextView skipText;
    Button nextButton;
    OnBoardingPresenter onBoardingPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_on_boarding, container, false);
        onBoardingPresenter = new OnBoardingScreenPresenterImp(this);
        intiUI(view);
        return view;
    }

    private void intiUI(View view) {
        assert getActivity() != null;
        viewPager2=getActivity().findViewById(R.id.view_pager);
        nextButton= view.findViewById(R.id.second_on_boarding_next_button);
        skipText= view.findViewById(R.id.second_on_boarding_skip_button);
        skipText.setOnClickListener((view1)-> onBoardingPresenter.nextButtonClick());
        nextButton.setOnClickListener((view1)-> onBoardingPresenter.endButtonClick(view,requireActivity().getApplication()));
    }

    @Override
    public void nextButton() {
        viewPager2.setCurrentItem(2);
    }
}