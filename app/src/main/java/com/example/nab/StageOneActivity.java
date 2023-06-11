package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class StageOneActivity extends AppCompatActivity {

    private LinearLayout gameLayout;
    private TextView counterTextView;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the root layout
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.WHITE);
        setContentView(rootLayout);

        // Create the game layout
        gameLayout = new LinearLayout(this);
        gameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1.0f
        ));
        gameLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.addView(gameLayout);

        // Create the counter text view
        counterTextView = new TextView(this);
        counterTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        counterTextView.setText("Counter: 0");
        counterTextView.setPadding(16, 16, 16, 16);
        rootLayout.addView(counterTextView);

        // Initialize counter
        counter = 0;

        // Set click listener for the game layout
        gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Spawn a circle at the clicked position
                spawnCircle();
            }
        });
    }

    private void spawnCircle() {
        Random random = new Random();

        // Generate random coordinates for the circle
        int x = random.nextInt(gameLayout.getWidth() - 200);
        int y = random.nextInt(gameLayout.getHeight() - 200);

        // Create a new button for the circle
        Button circle = new Button(this);
        circle.setBackgroundResource(R.drawable.circle_shape); // Set the background drawable to a circle shape
        circle.setElevation(8);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the clicked circle from the game layout
                gameLayout.removeView(v);
                spawnCircle(); // Spawn the next circle immediately
                counter++;
                updateCounterText();
            }
        });

        // Set the position of the circle
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
        params.leftMargin = x;
        params.topMargin = y;
        circle.setLayoutParams(params);

        // Add the circle to the game layout
        gameLayout.addView(circle);

        // Increase the counter when the circle is added
        counter++;
        updateCounterText();
    }

    private void updateCounterText() {
        counterTextView.setText("Counter: " + counter);
    }
}
