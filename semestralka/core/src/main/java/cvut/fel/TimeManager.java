package cvut.fel;

import java.time.LocalTime;
import java.util.concurrent.ScheduledExecutorService;

public class TimeManager {
    private final ScheduledExecutorService executor;
    private LocalTime currentTime;

    public TimeManager(){
        this.executor = java.util.concurrent.Executors.newScheduledThreadPool(1);
        this.currentTime = LocalTime.now();
    }

    /**
     * Starts a timer that updates the current time every second.
     */
    public void startTimer() {
        executor.scheduleAtFixedRate(() -> {
            currentTime = LocalTime.now();
        }, 0, 1, java.util.concurrent.TimeUnit.SECONDS);
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Stops the timer.
     */
    public void stopTimer() {
        executor.shutdown();
    }
}
