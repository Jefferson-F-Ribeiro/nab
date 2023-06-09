package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the TextView for displaying the username
        usernameTextView = findViewById(R.id.usernameTextView);

        // Get the username passed from the MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");

            // Display the username at the top of the screen
            usernameTextView.setText("Welcome, " + username + "!");
        }
    }
}
