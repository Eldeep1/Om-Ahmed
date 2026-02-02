package com.depogramming.omahmed.presentation.onboarding.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depogramming.omahmed.R;

import java.util.ArrayList;
import java.util.List;

public class VeiwPagerFragment extends Fragment {

    List<Fragment> fragmentList;
    ViewPagerAdapter adapter;
    ViewPager2 viewPager2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_veiw_pager, container, false);
        fragmentList=new ArrayList<>();
        fragmentList.add(new FirstOnBoarding());
        fragmentList.add(new SecondOnBoarding());
        fragmentList.add(new ThirdOnBoarding());
        viewPager2=view.findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(FragmentManager.findFragmentManager(view),getLifecycle(),fragmentList);
        viewPager2.setAdapter(adapter);

        return view;
    }
}