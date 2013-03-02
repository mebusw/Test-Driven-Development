package training.homeAutomation;

public class Time {
    public static final int MONDAY = 1;
    private int day;
    private int minuteOfDay;

    public Time(int day, int minuteOfDay) {
        this.day = day;
        this.minuteOfDay = minuteOfDay;
    }

    public Time() {
        // TODO Auto-generated constructor stub
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMinuteOfDay() {
        return minuteOfDay;
    }

    public void setMinuteOfDay(int minuteOfDay) {
        this.minuteOfDay = minuteOfDay;
    }

}
