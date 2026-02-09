package com.depogramming.omahmed;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.depogramming.omahmed.databinding.ActivityHomeActiivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeActiivityBinding binding;
    private BottomNavigationView bottomNavigationView;
    private TextView appbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeActiivityBinding.inflate(getLayoutInflater());

        bottomNavigationView = binding.bottomNavigationView;
        appbarTitle = binding.appbarTitle;
        setContentView(binding.getRoot());

        Window window = getWindow();
        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(systemBars.left,systemBars.top, systemBars.right, 0);

            return insets;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.bottomNavigationFragments);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(
                binding.bottomNavigationView,
                navController
        );
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {

            if (destination.getId() == R.id.mealDetailsFragment) {
                appbarTitle.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
            } else if(destination.getId() == R.id.searchFragment) {
                appbarTitle.setVisibility(View.GONE);
            }
            else {
                    appbarTitle.setVisibility(View.VISIBLE);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }

        });

    }
}