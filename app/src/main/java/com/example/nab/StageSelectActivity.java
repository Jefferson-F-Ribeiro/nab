package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StageSelectActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_select);

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

        LinearLayout layout = findViewById(R.id.stageSelectLayout);
        layout.setBackgroundColor(Color.rgb(255, 204, 255));

        // Add 4 clickable image miniatures
        for (int i = 1; i <= 4; i++) {
            final int imageIndex = i;

            ImageView imageView = new ImageView(this);
            if(i==1){
                imageView.setImageResource(R.drawable.stage_one);
            }
            else{
                imageView.setImageResource(R.drawable.image_miniature);
            }
            imageView.setClickable(true);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageIndex == 1) {
                        // Open a new screen for the first image
                        Intent intent = new Intent(StageSelectActivity.this, StageOneActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                }
            });

            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(450, 450);
            imageParams.gravity = Gravity.CENTER;
            if (i != 1) {
                // Apply a greyed-out filter to the miniature
                imageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                // Make the miniature non-clickable
                imageView.setClickable(false);
            }
            layout.addView(imageView, imageParams);
        }

        // Add the button to the layout
        layout.addView(backButton);
    }
}