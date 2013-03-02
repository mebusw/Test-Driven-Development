package training.homeAutomation;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LightSchedulerInitAndCleanup {

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
    public void testCreateStartsOneMinuteAlarm() {
        verify(timeFake, times(1)).setPeriodicAlarmInSeconds(eq(60), any(TimeServiceCallback.class));
    }

}
