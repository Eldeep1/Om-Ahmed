package com.depogramming.omahmed.onboarding.views;

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


public class SecondOnBoarding extends Fragment {
    ViewPager2 viewPager2;
    TextView skipText;
    Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_on_boarding, container, false);
        viewPager2=getActivity().findViewById(R.id.view_pager);
        nextButton=view.findViewById(R.id.second_on_boarding_next_button);
        skipText=view.findViewById(R.id.second_on_boarding_skip_button);
        skipText.setOnClickListener((view1)-> Navigation.findNavController(view).navigate(R.id.action_veiwPagerFragment_to_loginFragment));
        nextButton.setOnClickListener((view1)-> viewPager2.setCurrentItem(2));
        return view;
    }
}