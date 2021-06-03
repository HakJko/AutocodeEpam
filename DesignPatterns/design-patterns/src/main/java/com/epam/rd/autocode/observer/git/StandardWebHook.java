package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class StandardWebHook implements WebHook
{
    private final String branch;
    private final Event.Type type;
    private final List<Event> caughtEvents = new ArrayList<>();

    public StandardWebHook(final Event.Type type, final String branch) {
        this.branch = branch;
        this.type = type;
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return caughtEvents;
    }

    @Override
    public void onEvent(final Event event) {
        if (event.type() == type && event.branch().equalsIgnoreCase(branch)) {
            caughtEvents.add(event);
        }
    }
}
