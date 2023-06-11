package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PerfilActivity extends AppCompatActivity {

    private User user;
    private EditText nameEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Get the User object passed from the HomeActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            this.user = (User) intent.getSerializableExtra("user");
        }

        // Create text fields for changing user info
        nameEditText = new EditText(this);
        nameEditText.setText(user.getName());
        emailEditText = new EditText(this);
        emailEditText.setText(user.getEmail());

        // Create the back button
        Button backButton = new Button(this);
        backButton.setText("Voltar");
        LinearLayout.LayoutParams backButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        backButtonParams.setMargins(0, 16, 0, 0);
        backButton.setLayoutParams(backButtonParams);

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the HomeActivity without saving changes
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        // Create the save button
        Button saveButton = new Button(this);
        saveButton.setText("Salvar");
        LinearLayout.LayoutParams saveButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        saveButtonParams.setMargins(0, 16, 0, 0);
        saveButton.setLayoutParams(saveButtonParams);

        // Set click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the user information
                user.setName(nameEditText.getText().toString());
                user.setEmail(emailEditText.getText().toString());

                // Return to the HomeActivity with the updated user
                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", user);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        // Add the text fields and buttons to the layout
        LinearLayout layout = findViewById(R.id.perfilLayout);
        LinearLayout pfb = findViewById(R.id.profileButtonLayout);
        layout.addView(nameEditText);
        layout.addView(emailEditText);
        pfb.addView(backButton);
        pfb.addView(saveButton);
    }

    @Override
    public void onBackPressed() {
        // Return to the HomeActivity without saving changes when the back button is pressed
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
