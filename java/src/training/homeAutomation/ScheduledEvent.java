package training.homeAutomation;

public class ScheduledEvent {
    private int eventId;
    private Day eventDay;
    private int eventMinuteOfDay;
    private int eventType;


    public ScheduledEvent() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Day getEventDay() {
        return eventDay;
    }

    public void setEventDay(Day eventDay) {
        this.eventDay = eventDay;
    }

    public int getEventMinuteOfDay() {
        return eventMinuteOfDay;
    }

    public void setEventMinuteOfDay(int eventMinuteOfDay) {
        this.eventMinuteOfDay = eventMinuteOfDay;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

}