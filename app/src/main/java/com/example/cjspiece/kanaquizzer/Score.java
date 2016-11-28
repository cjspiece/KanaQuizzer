package com.example.cjspiece.kanaquizzer;

public class Score {
    private int _id;
    private String name;
    private int score;

    public Score() {}

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Setter method for the ID associated with the object
    public void setID(int id) {
        this._id = id;
    }

    // Getter method for the ID associated with the object
    public int getID() {
        return _id;
    }

    // Setter for the name value
    public void setName(String name)  {
        this.name = name;
    }

    // Getter for the name stored in the object
    public String getName() {
        return name;
    }

    // Setter for the score value
    public void setScore(int score) {
        this.score = score;
    }

    // Getter for the score value stored
    public int getScore() {
        return score;
    }
}
