package training.homeAutomation;

enum Day {
    EVERYDAY, WEEKEND, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class LightScheduler implements TimeServiceCallback{
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
        timeService.setPeriodicAlarmInSeconds(60, this);
    }

    public void wakeUp() {

        Time time = this.timeService.getTime();
        processEventDueNow(time, scheduledEvent);
    }

    protected void processEventDueNow(Time time, ScheduledEvent scheduledEvent) {
        if (scheduledEvent.getEventId() == UNUSED)
            return;
        if (!doesLightRespondToday(time.getDay(), scheduledEvent.getEventDay()))
            return;
        if (scheduledEvent.getEventMinuteOfDay() != time.getMinuteOfDay())
            return;

        operateLight(scheduledEvent);
    }

    protected boolean doesLightRespondToday(Day today, Day reactionDay) {
        if (reactionDay == Day.EVERYDAY)
            return true;
        if (reactionDay == Day.WEEKEND && (today == Day.SATURDAY || today == Day.SUNDAY))
            return true;
        if (reactionDay == today)
            return true;
        return false;

    }

    protected void operateLight(ScheduledEvent lightEvent) {
        if (lightEvent.getEventType() == TURN_ON)
            lightContoller.on(lightEvent.getEventId());
        else if (lightEvent.getEventType() == TURN_OFF)
            lightContoller.off(lightEvent.getEventId());
    }

    public void scheduleTurnOn(int id, Day day, int minuteOfDay) {
        scheduleEvent(id, day, minuteOfDay, TURN_ON);
    }

    public void scheduleTurnOff(int id, Day day, int minuteOfDay) {
        scheduleEvent(id, day, minuteOfDay, TURN_OFF);

    }

    private void scheduleEvent(int id, Day day, int minuteOfDay, int event) {
        scheduledEvent.setEventId(id);
        scheduledEvent.setEventMinuteOfDay(minuteOfDay);
        scheduledEvent.setEventType(event);
        scheduledEvent.setEventDay(day);

    }

    @Override
    public void callback() {
        // TODO Auto-generated method stub
        
    }

}
