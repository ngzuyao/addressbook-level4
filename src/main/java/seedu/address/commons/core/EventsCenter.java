package seedu.address.commons.core;

import java.util.logging.Logger;

import com.google.common.eventbus.EventBus;

import seedu.address.commons.events.BaseEvent;


/**
 * Manages the event dispatching of the app.
 */
public class EventsCenter {
    private static final Logger logger = LogsCenter.getLogger(seedu.address.commons.core.EventsCenter.class);
    private static seedu.address.commons.core.EventsCenter instance;
    private final EventBus eventBus;

    private EventsCenter() {
        eventBus = new EventBus();
    }

    public static seedu.address.commons.core.EventsCenter getInstance() {
        if (instance == null) {
            instance = new seedu.address.commons.core.EventsCenter();
        }
        return instance;
    }

    public static void clearSubscribers() {
        instance = null;
    }

    public void registerHandler(Object handler) {
        eventBus.register(handler);
    }

    /**
     * Posts an event to the event bus.
     */
    public <E extends BaseEvent> seedu.address.commons.core.EventsCenter post(E event) {
        logger.info("------[Event Posted] " + event.getClass().getCanonicalName() + ": " + event.toString());
        eventBus.post(event);
        return this;
    }

}
