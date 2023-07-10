package com.example.nab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.text.Html;

public class StageTwoActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button trueButton;
    private Button falseButton;
    private ProgressBar progressBar;
    private TextView scoreTextView;

    private User user;
    private int score;
    private int currentQuestionIndex;
    private JSONArray questionsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);

        questionTextView = findViewById(R.id.questionTextView);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        progressBar = findViewById(R.id.progressBar);
        scoreTextView = findViewById(R.id.scoreTextView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            user = (User) intent.getSerializableExtra("user");
            score = 0;
            currentQuestionIndex = 0;

            progressBar.setMax(10);
            progressBar.setProgress(currentQuestionIndex);
            scoreTextView.setText("Score: " + score);

            loadQuestions();
        }

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    private void loadQuestions() {
        new FetchQuestionsTask().execute();
    }

    private void displayQuestion() {
        try {
            if (currentQuestionIndex < questionsArray.length()) {
                JSONObject questionObject = questionsArray.getJSONObject(currentQuestionIndex);
                String question = questionObject.getString("question");
                String decodedQuestion = Html.fromHtml(question).toString();
                questionTextView.setText(decodedQuestion);
            } else {
                finishGame();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkAnswer(boolean answer) {
        try {
            JSONObject questionObject = questionsArray.getJSONObject(currentQuestionIndex);
            String correctAnswer = questionObject.getString("correct_answer");

            if (answer == Boolean.parseBoolean(correctAnswer.toLowerCase())) {
                score++;
            }

            currentQuestionIndex++;
            progressBar.setProgress(currentQuestionIndex);
            scoreTextView.setText("Score: " + score);

            displayQuestion();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void finishGame() {
        Toast.makeText(this, "Placar final: " + score, Toast.LENGTH_SHORT).show();
        user.addScoreStageTwo(score);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.updateUser(user);

        Intent intent = new Intent(StageTwoActivity.this, StageSelectActivity.class);
        intent.putExtra("user", user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private class FetchQuestionsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String questionsJson = null;

            try {
                URL url = new URL("https://opentdb.com/api.php?amount=10&difficulty=easy&type=boolean");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                questionsJson = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return questionsJson;
        }

        @Override
        protected void onPostExecute(String questionsJson) {
            if (questionsJson != null) {
                try {
                    JSONObject responseJson = new JSONObject(questionsJson);
                    questionsArray = responseJson.getJSONArray("results");
                    displayQuestion();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
