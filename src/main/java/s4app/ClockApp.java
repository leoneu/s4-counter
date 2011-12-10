package s4app;

import java.util.concurrent.TimeUnit;

import org.apache.s4.core.App;
import org.apache.s4.core.EventSource;

public class ClockApp extends App {

    private EventSource eventSource;
    private ClockPE clockPE;

    @Override
    protected void start() {
        System.out.println("Starting CounterApp...");
        clockPE.getInstanceForKey("single");
    }

    // generic array due to varargs generates a warning.
    @Override
    protected void init() {
        System.out.println("Initing CounterApp...");

        clockPE = new ClockPE(this);
        clockPE.setTimerInterval(1, TimeUnit.SECONDS);

        eventSource = new EventSource(this, "I can give you the time!");
        clockPE.setStreams(eventSource);
    }

    @Override
    protected void close() {
        System.out.println("Closing CounterApp...");
        eventSource.close();
    }
}
