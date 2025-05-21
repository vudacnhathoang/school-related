import static org.junit.jupiter.api.Assertions.*;

import cvut.fel.TimeManager;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;


public class TimeManagerTest {

    @Test
    public void testStartTimer()  {
        TimeManager timeManager = new TimeManager();
        timeManager.startTimer();
        LocalTime initialTime = timeManager.getCurrentTime();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Thread was interrupted");
        }

        LocalTime newTime = timeManager.getCurrentTime();
        assertNotEquals(initialTime, newTime, "The time should have changed after 1 second");
    }


    @Test
    public void testStopTimer() {
        TimeManager timeManager = new TimeManager();
        timeManager.startTimer();
        LocalTime initialTime = timeManager.getCurrentTime();

        timeManager.stopTimer();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Thread was interrupted");
        }

        LocalTime newTime = timeManager.getCurrentTime();
        assertEquals(initialTime, newTime, "The time should not have changed after stopping the timer");
    }


}
