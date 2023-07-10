package com.example.nab;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    private User user;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);

        databaseHelper = new DatabaseHelper(this);

        user = databaseHelper.getUser();
        if (user == null) {
            // Se não houver usuário, crie um usuário inicial e salve no banco de dados
            user = new User("user", "Jefferson", "r.jefferson@mail.com", "pass", 1);
            databaseHelper.insertUser(user);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
    }
}

