package com.example.fragmetapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentLogin extends Fragment {
    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnForApp = view.findViewById(R.id.buttonFromLoginToApp);
        email = view.findViewById(R.id.LoginEmailAddress);
        password = view.findViewById(R.id.LoginPassword);

        btnForApp.setOnClickListener(v -> {
            login(email.getText().toString(), password.getText().toString());
        });
    }

    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Navigation.findNavController(requireView()).navigate(R.id.fragmentApp);
            } else {
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}