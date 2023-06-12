package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa componentes de texto da UI
        usernameEditText = findViewById(R.id.usernameEditText);
        usernameEditText.setTextColor(Color.BLACK);
        usernameEditText.setHintTextColor(Color.BLACK);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText.setTextColor(Color.BLACK);
        passwordEditText.setHintTextColor(Color.BLACK);
        loginButton = findViewById(R.id.loginButton);

        // Cria o test user
        this.user = new User("user", "Jefferson", "r.jefferson@mail.com", "pass", 1);

        // Define o click listener para o login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recebe username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                // Valida o username e password
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    // Toast de login bem sucedido
                    Toast.makeText(MainActivity.this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show();

                // Redireciona para o HomeActivity passando o user como extra
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    // Toast de falha no login
                    Toast.makeText(MainActivity.this, "Senha ou usuário inválidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
