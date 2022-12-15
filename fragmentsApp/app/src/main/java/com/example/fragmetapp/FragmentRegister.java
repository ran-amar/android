package com.example.fragmetapp;

import android.os.Bundle;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentRegister extends Fragment {
    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;
    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnForApp = view.findViewById(R.id.buttonFromRegisterToApp);

        btnForApp.setOnClickListener(v -> {
            this.email = view.findViewById(R.id.RegisterEmailAddress);
            this.password = view.findViewById(R.id.RegisterPassword);
            EditText phone = view.findViewById(R.id.RegisterPhoneNumber);
            EditText name = view.findViewById(R.id.RegisterPersonName);

            Person person = new Person(
                    email.getText().toString(),
                    password.getText().toString(),
                    phone.getText().toString(),
                    name.getText().toString());

            register(email.getText().toString(), password.getText().toString(), person);
        });
    }

    public void register(String email, String password, Person person) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dbRef.child("users").child(person.password).setValue(person);
                Navigation.findNavController(requireView()).navigate(R.id.fragmentApp);
            } else {
                Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
