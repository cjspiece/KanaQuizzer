package com.example.cjspiece.kanaquizzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


public class KanaQuizzerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "kanaquizzer";
    private static final int DB_VERSION = 1;
    private static final String TABLE_SCORES = "SCORE";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    public KanaQuizzerDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the Score table in the databse if it doesn't already exist
        db.execSQL("CREATE TABLE SCORE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "NAME TEXT, "
        + "SCORE INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implementation will be added when the prospect of upgrading occurs
    }

    //CRUD operations for the database
    public void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName());
        values.put(KEY_SCORE, score.getScore());

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    // Retrieves a list of scores and orders them in descending order
    public List<Score> getAllScores() {
        List<Score> scores = new LinkedList<Score>();
        String query = "SELECT * FROM " + TABLE_SCORES + " ORDER BY SCORE DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Score score = null;

        if (cursor.moveToFirst()) {
            do {    // Loop as long as there are entries in the database
                score = new Score();
                score.setID(Integer.parseInt(cursor.getString(0)));
                score.setName(cursor.getString(1));
                score.setScore(cursor.getInt(2));

                scores.add(score);
            } while (cursor.moveToNext());
        }
        return scores;
    }

    // Update a score entry
    public int updateScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", score.getName());
        values.put("score", score.getScore());

        int i = db.update(TABLE_SCORES, values, KEY_ID+" = ?",
                new String[] { String.valueOf(score.getID())});

        db.close();

        return i;
    }

    // Deleting a single score entry
    public void deleteScore(Score score) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORES,
                KEY_ID+" = ?",
                new String[] { String.valueOf(score.getID()) });

        db.close();
    }
}
