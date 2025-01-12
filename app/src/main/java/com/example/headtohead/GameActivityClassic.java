package com.example.headtohead;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.headtohead.question.GameCollections;

public class GameActivityClassic extends AppCompatActivity {
    private GameCollections collections;
    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_classic);
        collections = new GameCollections();

    }
}