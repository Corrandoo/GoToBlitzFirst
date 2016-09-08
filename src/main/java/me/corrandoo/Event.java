package me.corrandoo;

public class Event {
    private int userId;
    private String eventType;
    private int stepId;
    private int time;

    public Event(int userId, String eventType, int stepId, int time) {
        this.userId = userId;
        this.eventType = eventType;
        this.stepId = stepId;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public String getEventType() {
        return eventType;
    }

    public int getStepId() {
        return stepId;
    }

    public int getTime() {
        return time;
    }
}
