package com.example.multimobileapps;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private static final int PICK_AUDIO = 10;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.galleryButton).setOnClickListener(v -> openGallery());
        findViewById(R.id.musicButton).setOnClickListener(v -> chooseMusic());
        findViewById(R.id.cutterButton).setOnClickListener(v ->
            Toast.makeText(this, "Music Cutter: select an audio file and choose start/end.", Toast.LENGTH_LONG).show());
        findViewById(R.id.puzzleButton).setOnClickListener(v ->
            Toast.makeText(this, "Kids Puzzle: simple puzzle module ready for expansion.", Toast.LENGTH_LONG).show());
        findViewById(R.id.namesButton).setOnClickListener(v ->
            Toast.makeText(this, "Names: Aarav, Arjun, Aditya, Ananya, Diya, Riya and more.", Toast.LENGTH_LONG).show());
    }

    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, 20);
    }

    private void chooseMusic() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.setType("audio/*");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(i, PICK_AUDIO);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) return;
        if (requestCode == PICK_AUDIO) {
            Uri uri = data.getData();
            try {
                if (player != null) player.release();
                player = MediaPlayer.create(this, uri);
                if (player != null) {
                    player.start();
                    Toast.makeText(this, "Playing selected music", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Could not play this audio file.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override protected void onDestroy() {
        if (player != null) player.release();
        super.onDestroy();
    }
}
