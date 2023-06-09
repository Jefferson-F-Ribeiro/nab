package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the activity_home.xml layout file
        setContentView(R.layout.activity_home);

        // Initialize the TextView for displaying the username
        usernameTextView = findViewById(R.id.usernameTextView);

        // Get the username passed from the MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");

            // Display the username at the top of the screen
            usernameTextView.setText("Welcome, " + username + "!");

            // Initialize the Buttons
            button1 = new Button(this);
            button2 = new Button(this);
            button3 = new Button(this);

            // Set text and click listeners for the Buttons
            button1.setText("Button 1");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomeActivity.this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
                }
            });

            button2.setText("Button 2");
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomeActivity.this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
                }
            });

            button3.setText("Button 3");
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HomeActivity.this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
                }
            });

            // Add the Buttons to the layout
            LinearLayout buttonLayout = findViewById(R.id.buttonLayout);
            buttonLayout.addView(button1);
            buttonLayout.addView(button2);
            buttonLayout.addView(button3);
        }
    }
}
