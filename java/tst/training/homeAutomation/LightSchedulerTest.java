package training.homeAutomation;

import static org.junit.Assert.*;

import java.util.Date;

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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNoChangeToLightsDuringInitialization() {
        verify(contollerSpy, never()).on(anyInt());
        when(timeFake.getTime()).thenReturn(new Date());
    }

}
