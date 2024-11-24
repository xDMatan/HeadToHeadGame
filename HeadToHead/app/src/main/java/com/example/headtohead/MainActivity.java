package com.example.headtohead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btnSignIn,btnSignUp;
private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        btnSignUp= findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);


    }

    @Override
    public void onClick(View v) {
        if(v==btnSignIn){

        }
        if(v==btnSignUp){

        }

    }
}