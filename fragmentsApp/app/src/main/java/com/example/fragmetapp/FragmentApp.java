package com.example.fragmetapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentApp extends Fragment implements View.OnClickListener {
    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private TextView editText;
    private EditText userName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, container, false);

        editText = (TextView) view.findViewById(R.id.textViewForUser);
        userName = (EditText) view.findViewById(R.id.userIdText);
        Button btn = (Button) view.findViewById(R.id.searchUserBtn);
        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbRef.child("users").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                // TODO
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
            }
        });
    }


    @Override
    public void onClick(View view) {
        String id = userName.getText().toString();
        try {
            dbRef.child("users").child(id).addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Person person = snapshot.getValue(Person.class);
                    if (person != null) {
                        editText.setText("Hi " + person.name);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e){
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}