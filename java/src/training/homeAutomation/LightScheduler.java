package training.homeAutomation;

public class LightScheduler {
    public static final int EVERYDAY = 99;
    public static final int UNUSED = -1;

    private int eventId;
    private int eventDay;
    private int eventMinuteOfDay;

    private LightController lightContoller;
    private TimeService timeService;

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
        lightContoller.on(eventId);
    }

    public void scheduleTurnOn(int id, int day, int minuteOfDay) {
        eventId = id;
        eventMinuteOfDay = minuteOfDay;

    }

}
