package com.example.fragmetapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        Button btnForLogin = view.findViewById(R.id.buttonForLogin);
        btnForLogin.setOnClickListener(view1 ->
                Navigation.findNavController(view1).navigate(R.id.action_fragmentOne_to_fragmentLogin));

        Button btnForRegister = view.findViewById(R.id.buttonForRegister);
        btnForRegister.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_fragmentOne_to_fragmentRegister));
        return view;
    }
}