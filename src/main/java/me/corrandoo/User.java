package me.corrandoo;

public class User {
    private int id;
    private int score = 0;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
