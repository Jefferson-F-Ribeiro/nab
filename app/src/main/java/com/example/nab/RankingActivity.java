package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            this.user = (User) intent.getSerializableExtra("user");
        }
        List<Integer> scores = user.getScoresStageOne();

        Button backButton = new Button(this);
        backButton.setText("Voltar");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 16, 0, 0);
        backButton.setLayoutParams(params);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", user);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        LinearLayout layout = findViewById(R.id.rankingLayout);

        // Limpa o layout antes de adicionar os scores atualizados
        layout.removeAllViews();

        // Obtém a lista de scores atualizada do objeto User


        for (int i = 0; i < scores.size(); i++) {
            TextView textView = new TextView(this);
            textView.setTextAppearance(this, android.R.style.TextAppearance_Large);
            textView.setText("Stage " + (i + 1) + ": " + scores.get(i) + " pontos");
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            textView.setGravity(Gravity.CENTER);
            layout.addView(textView);
        }

        layout.addView(backButton);
    }
}