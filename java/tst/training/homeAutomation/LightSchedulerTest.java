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
        lightScheduler = new LightScheduler();
    }

    @Test
    public void testNoScheduleNothingHappens() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.MINUTE, 100);

        when(timeFake.getTime()).thenReturn(calendar.getTime());
        verify(contollerSpy, never()).on(anyInt());

        lightScheduler.wakeUp();

    }

}
