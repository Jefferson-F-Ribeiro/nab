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

    // Define a constant to identify the request code
    private static final int NEW_SCREEN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the activity_home.xml layout file
        setContentView(R.layout.activity_home);

        // Initialize the TextView for displaying the username
        usernameTextView = findViewById(R.id.usernameTextView);

        // Get the username passed from the MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            User user = (User) intent.getSerializableExtra("user");

            // Display the username at the top of the screen
            usernameTextView.setText("Bem vindo, " + user.getName() + "!");

            // Initialize the Buttons
            button1 = new Button(this);
            button2 = new Button(this);
            button3 = new Button(this);

            // Set text and click listeners for the Buttons
            button1.setText("JOGAR");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, StageSelectActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, NEW_SCREEN_REQUEST_CODE);
                }
            });

            button2.setText("RANKING");
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, RankingActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, NEW_SCREEN_REQUEST_CODE);
                }
            });

            button3.setText("PERFIL");
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, NEW_SCREEN_REQUEST_CODE);
                }
            });

            // Add the Buttons to the layout
            LinearLayout buttonLayout = findViewById(R.id.buttonLayout);
            buttonLayout.addView(button1);
            buttonLayout.addView(button2);
            buttonLayout.addView(button3);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_SCREEN_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("user")) {
                User user = (User) data.getSerializableExtra("user");
                // Use the updated user object here
            }
        }
    }

}
