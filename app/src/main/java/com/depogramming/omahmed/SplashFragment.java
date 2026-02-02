package com.depogramming.omahmed;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SplashFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_splash, container, false);

        TextView text = view.findViewById(R.id.splash_text);
        text.setTranslationY(50f);
        text.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(800)
                .setStartDelay(1000) // Wait for logo to start first
                .start();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(onBoardingFinished()){
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment);
        }else {
            new Handler().postDelayed(() -> Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_veiwPagerFragment), 3500);
        }
    }

    private boolean onBoardingFinished(){
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("onBoardingDone",false);
    }
}