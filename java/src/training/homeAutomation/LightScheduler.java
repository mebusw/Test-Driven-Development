package training.homeAutomation;

enum Day {
    EVERYDAY, MONDAY
}

public class LightScheduler {
    public static final int EVERYDAY = 99;
    public static final int UNUSED = -1;
    private static final int TURN_ON = 1;
    private static final int TURN_OFF = 0;

    private int eventId;
    private int eventDay;
    private int eventMinuteOfDay;

    private LightController lightContoller;
    private TimeService timeService;
    private int event;

    public LightScheduler(LightController lightContoller, TimeService timeService) {
        this.lightContoller = lightContoller;
        this.timeService = timeService;
        eventId = UNUSED;
    }

    public void wakeUp() {
        if (eventId == UNUSED)
            return;
        if (eventMinuteOfDay != timeService.getTime().getMinuteOfDay()) {
            return;
        }
        if (event == TURN_ON)
            lightContoller.on(eventId);

        else if (event == TURN_OFF)
            lightContoller.off(eventId);
    }

    public void scheduleTurnOn(int id, int day, int minuteOfDay) {
        eventId = id;
        eventMinuteOfDay = minuteOfDay;
        event = TURN_ON;
    }

    public void scheduleTurnOff(int id, int day, int minuteOfDay) {
        eventId = id;
        eventMinuteOfDay = minuteOfDay;
        event = TURN_OFF;
    }

}
