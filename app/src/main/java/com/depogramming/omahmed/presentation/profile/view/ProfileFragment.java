package com.depogramming.omahmed.presentation.profile.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.profile.presentation.ProfilePresenter;
import com.depogramming.omahmed.presentation.profile.presentation.ProfilePresenterImp;

public class ProfileFragment extends Fragment implements ProfileView{

    LinearLayout uploadDataCard;
    LinearLayout downloadDataCard;
    LinearLayout logoutDataCard;
    TextView nameTextView;
    TextView nameCharTextView;
    ProfilePresenter profilePresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        uploadDataCard = view.findViewById(R.id.uploadDataCard);
        downloadDataCard = view.findViewById(R.id.downloadDataCard);
        logoutDataCard = view.findViewById(R.id.logoutDataCard);
        nameTextView= view.findViewById(R.id.nameTextView);
        nameCharTextView= view.findViewById(R.id.nameCharTextView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePresenter= new ProfilePresenterImp();
    }

    @Override
    public void onUploadClick() {
        profilePresenter.onUploadClick();
    }

    @Override
    public void onUploadClickAction() {

    }

    @Override
    public void onDownloadClick() {
        profilePresenter.onDownloadClick();
    }

    @Override
    public void onDownloadClickAction() {

    }

    @Override
    public void onLogoutClick() {
        profilePresenter.onDownloadClick();
    }

    @Override
    public void onLogoutClickAction() {

    }

    @Override
    public void setName(String name) {
        nameTextView.setText(name);
        nameCharTextView.setText(name.toUpperCase().charAt(0));
    }
}