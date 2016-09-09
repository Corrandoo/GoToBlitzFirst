package me.corrandoo;

public class User {
    private int id;
    private int firstTime = 0;
    private int lastTime = 0;
    private int courseTime;
    private int score = 0;

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



    public void setScore(int score) {
        this.score = score;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public int getCourseTime() {
        if((lastTime == 0) && (firstTime == 0)){
        }
        else{
            int courseTime = lastTime - firstTime;
        }
        return courseTime;
    }

    public void initializeCourseTime(){
        if((lastTime == 0) && (firstTime == 0)){
        }
        else{
            int courseTime = lastTime - firstTime;
        }
    }
}
