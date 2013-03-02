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

    @Test
    public void testNoScheduleNothingHappens() {
        when(timeFake.getTime()).thenReturn(new Time());

        lightScheduler.wakeUp();

        verify(contollerSpy, never()).on(anyInt());
    }

    @Test
    public void testScheduleOnEverydayNotTimeYet() {
        when(timeFake.getTime()).thenReturn(new Time(Time.MONDAY, 5));

        lightScheduler.scheduleTurnOn(3, LightScheduler.EVERYDAY, 6);
        lightScheduler.wakeUp();

        verify(contollerSpy, never()).on(anyInt());
    }

    @Test
    public void testScheduleOnEverydayItsTime() {
        when(timeFake.getTime()).thenReturn(new Time(Time.MONDAY, 6));

        lightScheduler.scheduleTurnOn(3, LightScheduler.EVERYDAY, 6);
        lightScheduler.wakeUp();
        
        verify(contollerSpy, times(1)).on(3);
    }
}
