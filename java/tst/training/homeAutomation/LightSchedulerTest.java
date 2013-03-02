package training.homeAutomation;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import org.mockito.*;
import org.junit.*;
import static org.mockito.Mockito.*;

/**
 * example from James Grenning's book "TDD for embedded C", Chpt 8
 * 
 * @author mebusw
 * 
 */
public class LightSchedulerTest {
    @Mock
    private LightController contollerSpy;
    @Mock
    private TimeService timeFake;

    private LightScheduler lightScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        lightScheduler = new LightScheduler(contollerSpy, timeFake);
    }

    protected void givenTime(Day day, int minuteOfDay) {
        when(timeFake.getTime()).thenReturn(new Time(day, minuteOfDay));
    }

    @Ignore
    public void testNoScheduleNothingHappens() {
        givenTime(Day.MONDAY, 6);

        lightScheduler.wakeUp();

        verify(contollerSpy, never()).on(anyInt());
    }

    @Test
    public void testScheduleOnEverydayNotTimeYet() {
        givenTime(Day.MONDAY, 6);

        lightScheduler.scheduleTurnOn(3, Day.EVERYDAY, 7);
        lightScheduler.wakeUp();

        verify(contollerSpy, never()).on(anyInt());
    }

    @Test
    public void testScheduleOnEverydayItsTime() {
        givenTime(Day.MONDAY, 6);

        lightScheduler.scheduleTurnOn(3, Day.EVERYDAY, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy, times(1)).on(3);
    }

    @Test
    public void testScheduleOffEverydayItsTime() {
        givenTime(Day.MONDAY, 6);

        lightScheduler.scheduleTurnOff(3, Day.EVERYDAY, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy, times(1)).off(3);
    }

    @Test
    public void testScheduleTuesdayButItsMonday() {
        givenTime(Day.MONDAY, 6);

        lightScheduler.scheduleTurnOff(3, Day.TUESDAY, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy, never()).off(anyInt());
    }

    @Test
    public void testScheduleTuesdayAndItsTuesday() {
        givenTime(Day.TUESDAY, 6);

        lightScheduler.scheduleTurnOn(3, Day.TUESDAY, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy).on(3);
    }

    
    @Test
    public void testScheduleWeekendButItsFriday() {
        givenTime(Day.FRIDAY, 6);

        lightScheduler.scheduleTurnOn(3, Day.WEEKEND, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy, never()).on(anyInt());
    }

    @Test
    public void testScheduleWeekendButItsSaturday() {
        givenTime(Day.SATURDAY, 6);

        lightScheduler.scheduleTurnOn(3, Day.WEEKEND, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy).on(3);
    }
    
    @Test
    public void testScheduleWeekendButItsSunday() {
        givenTime(Day.SUNDAY, 6);

        lightScheduler.scheduleTurnOn(3, Day.WEEKEND, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy).on(3);
    }
}
