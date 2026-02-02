package com.depogramming.omahmed.onboarding.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.depogramming.omahmed.R;


public class ThirdOnBoarding extends Fragment {

    Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_on_boarding, container, false);
        nextButton=view.findViewById(R.id.third_on_boarding_next_button);
        nextButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_veiwPagerFragment_to_loginFragment);
            onBoardingFinished();
        });
        return view;
    }
    private void onBoardingFinished(){
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("onBoardingDone",true);
        editor.apply();
    }
}