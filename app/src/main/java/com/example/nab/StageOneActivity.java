package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class StageOneActivity extends AppCompatActivity {

    private LinearLayout gameLayout;
    private TextView counterTextView;
    private TextView timerTextView;
    private int counter;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setBackgroundColor(Color.WHITE);
        setContentView(rootLayout);

        gameLayout = new LinearLayout(this);
        gameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1.0f
        ));
        gameLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.addView(gameLayout);

        counterTextView = new TextView(this);
        counterTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        counterTextView.setText("Placar: 0 pontos");
        counterTextView.setPadding(16, 16, 16, 16);
        counterTextView.setTextAppearance(this, android.R.style.TextAppearance_Large);
        counterTextView.setTextColor(Color.BLACK);
        rootLayout.addView(counterTextView);

        timerTextView = new TextView(this);
        timerTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        timerTextView.setText("Tempo: 30");
        timerTextView.setPadding(16, 16, 16, 16);
        timerTextView.setTextAppearance(this, android.R.style.TextAppearance_Large);
        timerTextView.setTextColor(Color.BLACK);
        rootLayout.addView(timerTextView);

        counter = 0;

        gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spawnCircle();
            }
        });

        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer text
                timerTextView.setText("Tempo: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                showScoreToast();
                finish();
            }
        }.start();
    }

    private void spawnCircle() {
        Random random = new Random();

        int x = random.nextInt(gameLayout.getWidth() - 200);
        int y = random.nextInt(gameLayout.getHeight() - 200);

        Button circle = new Button(this);
        circle.setBackgroundResource(R.drawable.circle_shape); // Set the background drawable to a circle shape
        circle.setElevation(8);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameLayout.removeView(v);
                spawnCircle();
                counter++;
                updateCounterText();
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
        params.leftMargin = x;
        params.topMargin = y;
        circle.setLayoutParams(params);

        gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameLayout.removeView(circle);
                spawnCircle();
                counter--;
                updateCounterText();
            }
        });

        gameLayout.addView(circle);

        updateCounterText();
    }

    private void updateCounterText() {
        counterTextView.setText("Placar: " + counter + " pontos");
    }

    private void showScoreToast() {
        Toast toast = Toast.makeText(this, "Placar final: " + counter + " pontos", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
