package training.homeAutomation;

public class Time {
    public static final int MONDAY = 1;
    private Day day;
    private int minuteOfDay;

    public Time(Day day, int minuteOfDay) {
        this.day = day;
        this.minuteOfDay = minuteOfDay;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getMinuteOfDay() {
        return minuteOfDay;
    }

    public void setMinuteOfDay(int minuteOfDay) {
        this.minuteOfDay = minuteOfDay;
    }

}
