package training.homeAutomation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
