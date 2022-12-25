package com.example.recyclerviewapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class charListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<DataModel> dataset;
    private LinearLayoutManager layoutManager;

    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_char_list, container, false);
        recyclerView = view.findViewById(R.id.recycleViewCon);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataset = new ArrayList<DataModel>();

        // Get Data from Firebase
        dbRef.child("characters").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {

                final long ONE_MEGABYTE = 1024 * 1024;
                ArrayList<HashMap<String, String>> arrayList = (ArrayList<HashMap<String, String>>) task.getResult().getValue();

                for (int i = 0; i < Objects.requireNonNull(arrayList).size(); i++) {
                    String name = arrayList.get(i).get("name");
                    String description = arrayList.get(i).get("description");

                    Integer index = i;
                    StorageReference islandRef = storage.getReference().child((index + 1) + ".png");
                    islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                        dataset.add(new DataModel(name, description, index, bytes));
                        if (index == arrayList.size() - 1) {
                            recyclerView.setAdapter(new CustomAdapter(dataset, this::cardClicked));
                        }
                    }).addOnFailureListener(exception -> {
                        Log.d("e", exception.getMessage());
                    });
                }
            }});

        return view;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void cardClicked(DataModel item){
        View view = requireView();
        NavController navController = Navigation.findNavController(view);
        Bundle bundle = new Bundle();

        bundle.putSerializable("character", (Serializable) item);

        navController.navigate(R.id.action_charListFragment_to_characterFragment, bundle);
    }

}