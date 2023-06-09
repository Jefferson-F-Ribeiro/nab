package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI components
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if the entered username and password are valid
                if (username.equals("admin") && password.equals("password")) {
                    // Login successful, show a toast message
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                // Redirect to the HomeActivity and pass the username as an extra
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    // Login failed, show an error toast message
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
