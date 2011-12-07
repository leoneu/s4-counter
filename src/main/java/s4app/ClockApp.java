package s4app;

import java.util.concurrent.TimeUnit;

import org.apache.s4.base.Event;
import org.apache.s4.core.App;
import org.apache.s4.core.EventSource;

import s4app.ClockPE.ClockEvent;

public class ClockApp extends App {

    private EventSource<ClockEvent> eventSource;

    @Override
    protected void start() {
        System.out.println("Starting CounterApp...");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init() {
        System.out.println("Initing CounterApp...");

        ClockPE clockPE = new ClockPE(this);
        clockPE.setTrigger(Event.class, 1, 1, TimeUnit.SECONDS);

        eventSource = new EventSource<ClockEvent>(this, "I can give you the time!");
        clockPE.setStreams(eventSource);
    }

    // this is the method consumer apps will use to get the event source.
    public EventSource<ClockEvent> getEventSource() {
        return eventSource;
    }

    @Override
    protected void close() {
        System.out.println("Closing CounterApp...");

    }
}