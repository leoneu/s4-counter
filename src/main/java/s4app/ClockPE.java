package s4app;

import javax.annotation.concurrent.Immutable;

import org.apache.s4.base.Event;
import org.apache.s4.core.App;
import org.apache.s4.core.SingletonPE;
import org.apache.s4.core.Streamable;

public class ClockPE extends SingletonPE {

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
}
