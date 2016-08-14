package com.schlaepfer.pricecommand.handlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.schlaepfer.priceevents.events.PriceAddedEvent;

@Component
public class EventLoggingHandler {

    private static final Logger LOG = LoggerFactory.getLogger(EventLoggingHandler.class);
    private static final String IID = String.valueOf(Double.valueOf(Math.random() * 1000).intValue());

    @EventHandler
    public void handle(PriceAddedEvent event) {
        LOG.debug("Instance:{} EventType:{} EventId:[{}] '{}'", IID, event.getClass().getSimpleName(), event.getId(), event.getName());
    }

   
}

