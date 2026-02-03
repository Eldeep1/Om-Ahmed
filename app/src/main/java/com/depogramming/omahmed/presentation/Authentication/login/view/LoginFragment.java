package com.depogramming.omahmed.presentation.Authentication.login.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depogramming.omahmed.R;

public class LoginFragment extends Fragment {

    TextView singUpButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        singUpButton=view.findViewById(R.id.signUpTextAction);
        singUpButton.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));
        return view;
    }
}