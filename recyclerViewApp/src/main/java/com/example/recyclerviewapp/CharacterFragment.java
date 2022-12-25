package com.example.recyclerviewapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CharacterFragment extends Fragment{
    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private TextView editText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_character, container, false);

        TextView detailsTextView = view.findViewById(R.id.characterTextView);
        ImageView charactersImage = view.findViewById(R.id.characterImageView);
        Button btnBackToList = view.findViewById(R.id.BtnBackToCharList);

        btnBackToList.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_characterFragment_to_charListFragment);
        });

        Bundle args = getArguments();
        assert args != null;
        DataModel character = (DataModel)  args.getSerializable("character");
        detailsTextView.setText(character.getDetails());

        Bitmap bitmap = BitmapFactory.decodeByteArray(character.getImage(), 0, character.getImage().length);
        charactersImage.setImageBitmap(bitmap);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}