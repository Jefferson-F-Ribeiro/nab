package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

        // Recebe o user object da HomeActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            this.user = (User) intent.getSerializableExtra("user");
        }

        // Cria text fields para modificar os atributos
        nameEditText = new EditText(this);
        nameEditText.setText(user.getName());
        emailEditText = new EditText(this);
        emailEditText.setText(user.getEmail());

        // Cria o botão de retorno
        Button backButton = new Button(this);
        backButton.setText("Voltar");
        LinearLayout.LayoutParams backButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        backButtonParams.setMargins(0, 16, 0, 0);
        backButton.setLayoutParams(backButtonParams);

        // Define o click listener para o botão de retorno
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retorna para o HomeActivity sem salvar as mudanças
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        // Cria o botão de salvar
        Button saveButton = new Button(this);
        saveButton.setText("Salvar");
        LinearLayout.LayoutParams saveButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        saveButtonParams.setMargins(0, 16, 0, 0);
        saveButton.setLayoutParams(saveButtonParams);

        // Define o click listener para o botão de salvar
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Atualiza informações do user
                user.setName(nameEditText.getText().toString());
                user.setEmail(emailEditText.getText().toString());

                // Retorna ao HomeActivity com o user atualizado
                Intent returnIntent = new Intent();
                returnIntent.putExtra("user", user);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        // Adiciona os text fields e buttons ao layout
        LinearLayout layout = findViewById(R.id.perfilLayout);
        LinearLayout pfb = findViewById(R.id.profileButtonLayout);
        layout.addView(nameEditText);
        layout.addView(emailEditText);
        pfb.addView(backButton);
        pfb.addView(saveButton);
    }

    @Override
    public void onBackPressed() {
        // Retorna ao HomeActivity sem salvar as mudanças ao clicar no botão de retorno
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
