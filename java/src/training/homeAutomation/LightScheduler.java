package training.homeAutomation;

enum Day {
    EVERYDAY, MONDAY
}

public class LightScheduler {
    public static final int EVERYDAY = 99;
    public static final int UNUSED = -1;
    private static final int TURN_ON = 1;
    private static final int TURN_OFF = 0;

    private LightController lightContoller;
    private TimeService timeService;
    private ScheduledEvent scheduledEvent;

    public LightScheduler(LightController lightContoller, TimeService timeService) {
        this.lightContoller = lightContoller;
        this.scheduledEvent = new ScheduledEvent();
        this.timeService = timeService;
        scheduledEvent.setEventId(UNUSED);
    }

    public void wakeUp() {

        Time time = this.timeService.getTime();
        processEventDueNow(time, scheduledEvent);
    }

    protected void processEventDueNow(Time time, ScheduledEvent scheduledEvent) {
        if (scheduledEvent.getEventId() == UNUSED)
            return;
        if (scheduledEvent.getEventMinuteOfDay() != time.getMinuteOfDay()) {
            return;
        }
        operateLight(scheduledEvent);
    }

    protected void operateLight(ScheduledEvent lightEvent) {
        if (lightEvent.getEventType() == TURN_ON)
            lightContoller.on(lightEvent.getEventId());
        else if (lightEvent.getEventType() == TURN_OFF)
            lightContoller.off(lightEvent.getEventId());
    }

    public void scheduleTurnOn(int id, int day, int minuteOfDay) {
        scheduleEvent(id, minuteOfDay, TURN_ON);
    }

    public void scheduleTurnOff(int id, int day, int minuteOfDay) {
        scheduleEvent(id, minuteOfDay, TURN_OFF);

    }

    private void scheduleEvent(int id, int minuteOfDay, int event) {
        scheduledEvent.setEventId(id);
        scheduledEvent.setEventMinuteOfDay(minuteOfDay);
        scheduledEvent.setEventType(event);
    }

}
