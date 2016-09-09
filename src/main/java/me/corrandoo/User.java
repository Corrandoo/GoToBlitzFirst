package me.corrandoo;

public class User {
    private int id;
    private int firstTime;
    private int lastTime;
    private int finalTime;
    private int courseTime;
    private int score = 0;
    private boolean isCompleted = false;

    public User(int id, int firstTime) {
        this.id = id;
        this.firstTime = firstTime;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(int finalTime) {
        this.finalTime = finalTime;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public int getCourseTime() {
        return finalTime - firstTime;
    }
}

