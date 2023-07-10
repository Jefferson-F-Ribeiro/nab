package com.example.nab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "nab.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_LEVEL = "level";
    private static final String COLUMN_SCORES_STAGE_ONE = "scores_stage_one";
    private static final String COLUMN_FIRST_LOGIN = "first_login";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTableQuery = "CREATE TABLE " + User.TABLE_NAME + " (" +
                User.COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                User.COLUMN_NAME + " TEXT, " +
                User.COLUMN_EMAIL + " TEXT, " +
                User.COLUMN_PASSWORD + " TEXT, " +
                User.COLUMN_LEVEL + " INTEGER, " +
                User.COLUMN_SCORES_STAGE_ONE + " TEXT DEFAULT '', " +
                User.COLUMN_FIRST_LOGIN + " INTEGER DEFAULT 0)";

        db.execSQL(createUsersTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_LEVEL, user.getLevel());
        values.put(COLUMN_SCORES_STAGE_ONE, convertScoresToString(user.getScoresStageOne()));
        values.put(COLUMN_FIRST_LOGIN, user.getFirstLogin() ? 1 : 0);

        long id = db.insert(TABLE_USERS, null, values);
        db.close();

        return id;
    }

    public User getUser() {
        SQLiteDatabase db = getReadableDatabase();
        User user = null;

        String[] columns = {
                User.COLUMN_USERNAME,
                User.COLUMN_NAME,
                User.COLUMN_EMAIL,
                User.COLUMN_PASSWORD,
                User.COLUMN_LEVEL,
                User.COLUMN_SCORES_STAGE_ONE,
                User.COLUMN_FIRST_LOGIN
        };

        Cursor cursor = db.query(
                User.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null,
                "1"
        );

        if (cursor != null && cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex(User.COLUMN_USERNAME);
            int nameIndex = cursor.getColumnIndex(User.COLUMN_NAME);
            int emailIndex = cursor.getColumnIndex(User.COLUMN_EMAIL);
            int passwordIndex = cursor.getColumnIndex(User.COLUMN_PASSWORD);
            int levelIndex = cursor.getColumnIndex(User.COLUMN_LEVEL);
            int scoresStageOneIndex = cursor.getColumnIndex(User.COLUMN_SCORES_STAGE_ONE);
            int firstLoginIndex = cursor.getColumnIndex(User.COLUMN_FIRST_LOGIN);

            String username = cursor.getString(usernameIndex);
            String name = cursor.getString(nameIndex);
            String email = cursor.getString(emailIndex);
            String password = cursor.getString(passwordIndex);
            int level = cursor.getInt(levelIndex);
            String scoresStageOneString = cursor.getString(scoresStageOneIndex);
            boolean firstLogin = cursor.getInt(firstLoginIndex) == 1;

            List<Integer> scoresStageOne = new ArrayList<>();
            if (scoresStageOneString != null && !scoresStageOneString.isEmpty()) {
                String[] scoresStageOneArray = scoresStageOneString.split(",");
                for (String score : scoresStageOneArray) {
                    scoresStageOne.add(Integer.parseInt(score));
                }
            }

            user = new User(username, name, email, password, level);
            user.setScoresStageOne(scoresStageOne);
            user.setFirstLogin(firstLogin);
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return user;
    }



    private String convertScoresToString(List<Integer> scores) {
        StringBuilder sb = new StringBuilder();
        for (int score : scores) {
            sb.append(score).append(",");
        }
        return sb.toString();
    }

    private List<Integer> convertStringToScores(String scoresString) {
        List<Integer> scores = new ArrayList<>();
        String[] scoreStrings = scoresString.split(",");
        for (String scoreString : scoreStrings) {
            if (!scoreString.isEmpty()) {
                int score = Integer.parseInt(scoreString);
                scores.add(score);
            }
        }
        return scores;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_LEVEL, user.getLevel());
        values.put(COLUMN_SCORES_STAGE_ONE, convertScoresToString(user.getScoresStageOne()));
        values.put(COLUMN_FIRST_LOGIN, user.getFirstLogin() ? 1 : 0);

        db.update(TABLE_USERS, values, COLUMN_USERNAME + " = ?", new String[]{user.getUsername()});
        db.close();
    }

}
