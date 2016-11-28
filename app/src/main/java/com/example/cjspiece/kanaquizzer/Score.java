package com.example.cjspiece.kanaquizzer;

/**
 * Created by Chris on 11/27/2016.
 */

public class Score {
    private int _id;
    private String name;
    private int score;

    public Score() {}

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return _id;
    }

    public void setName(String name)  {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
