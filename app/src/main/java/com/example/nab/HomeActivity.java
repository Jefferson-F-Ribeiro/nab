package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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

    private static final int NEW_SCREEN_REQUEST_CODE = 1;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernameTextView = findViewById(R.id.usernameTextView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            user = (User) intent.getSerializableExtra("user");
            usernameTextView.setText("Bem vindo, " + user.getName() + ". Você está atualmente no nível " + user.getLevel());
            usernameTextView.setGravity(Gravity.CENTER);

            button1 = new Button(this);
            button2 = new Button(this);
            button3 = new Button(this);

            button1.setText("JOGAR");
            button1.setTextAppearance(this, android.R.style.TextAppearance_Large);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, StageSelectActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, NEW_SCREEN_REQUEST_CODE);
                }
            });

            button2.setText("RANKING");
            button2.setTextAppearance(this, android.R.style.TextAppearance_Large);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, RankingActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, NEW_SCREEN_REQUEST_CODE);
                }
            });

            button3.setText("PERFIL");
            button3.setTextAppearance(this, android.R.style.TextAppearance_Large);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, NEW_SCREEN_REQUEST_CODE);
                }
            });

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
                user = (User) data.getSerializableExtra("user");
                usernameTextView.setText("Bem vindo, " + user.getName() + ". Você está atualmente no nível " + user.getLevel());
            }
        }
    }
}
