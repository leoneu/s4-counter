package s4app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.Immutable;

import org.apache.s4.base.Event;
import org.apache.s4.core.App;
import org.apache.s4.core.KeyFinder;
import org.apache.s4.core.ProcessingElement;
import org.apache.s4.core.Streamable;

public class ClockPE extends ProcessingElement {

    private Streamable<ClockEvent>[] targetStreams;

    public ClockPE(App app) {
        super(app);
    }

    /**
     * @param targetStreams
     *            the {@link UserEvent} streams.
     */
    public void setStreams(Streamable<ClockEvent>... targetStreams) {
        this.targetStreams = targetStreams;
    }

    public void onTrigger(Event event) {
        long time = System.currentTimeMillis();
        ClockEvent clockEvent = new ClockEvent(time);

        for (int i = 0; i < targetStreams.length; i++) {
            targetStreams[i].put(clockEvent);
        }
    }

    @Override
    protected void onCreate() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onRemove() {
        // TODO Auto-generated method stub

    }

    @Immutable
    public class ClockEvent extends Event {

        final private long time;

        public ClockEvent(long time) {
            super();
            this.time = time;
        }

        /**
         * @return the time
         */
        public long getTime() {
            return time;
        }
    }

    public class TimeKeyFinder implements KeyFinder<ClockEvent> {

        public List<String> get(ClockEvent event) {

            List<String> results = new ArrayList<String>();

            results.add(String.valueOf(event.getTime()));

            return results;
        }
    }
}
