package com.depogramming.omahmed.presentation.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.depogramming.omahmed.MainActivity;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.profile.presentation.ProfilePresenter;
import com.depogramming.omahmed.presentation.profile.presentation.ProfilePresenterImp;
import com.depogramming.omahmed.utils.UserAlerts;

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
        logoutDataCard.setOnClickListener(v->onLogoutClick());
        uploadDataCard.setOnClickListener(v->onUploadClick());
        downloadDataCard.setOnClickListener(v->onDownloadClick());
        nameTextView= view.findViewById(R.id.nameTextView);
        nameCharTextView= view.findViewById(R.id.nameCharTextView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePresenter= new ProfilePresenterImp(this,getActivity().getApplicationContext());
    }

    @Override
    public void onUploadClick() {
        profilePresenter.onUploadClick();
    }

    @Override
    public void onUploadClickAction() {
        UserAlerts.showSnackBar(getView(),"uploaded Successfully");
    }

    @Override
    public void onDownloadClick() {
        profilePresenter.onDownloadClick();
    }

    @Override
    public void onDownloadClickAction() {
        UserAlerts.showSnackBar(getView(),"Downloaded Successfully");
    }

    @Override
    public void onLogoutClick() {
        profilePresenter.onLogoutClick();
    }

    @Override
    public void onLogoutClickAction() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("SKIP_SPLASH", true);
        startActivity(intent);
    }

    @Override
    public void setName(String name) {
        nameTextView.setText(name);
        nameCharTextView.setText(name.toUpperCase().charAt(0));
    }
}