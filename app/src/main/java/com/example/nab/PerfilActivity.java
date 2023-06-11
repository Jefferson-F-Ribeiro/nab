package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Get the User object passed from the HomeActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            this.user = (User) intent.getSerializableExtra("user");
        }

        // Create a button dynamically
        Button backButton = new Button(this);
        backButton.setText("Voltar");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 0);
        backButton.setLayoutParams(params);

        // Set click listener for the button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the HomeActivity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", user);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        // Add the button to the layout
        LinearLayout layout = findViewById(R.id.perfilLayout);
        layout.addView(backButton);
    }
}