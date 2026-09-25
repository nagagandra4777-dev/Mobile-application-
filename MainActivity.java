package com.example.mymobileapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButton(R.id.galleryButton, "Gallery");
        setupButton(R.id.musicButton, "Music Player");
        setupButton(R.id.cutterButton, "Music Cutter");
        setupButton(R.id.gamesButton, "Kids Games");
        setupButton(R.id.namesButton, "Names & Meanings");
    }

    private void setupButton(int id, String feature) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(v ->
                Toast.makeText(this, feature + " module coming next", Toast.LENGTH_SHORT).show()
        );
    }
}
