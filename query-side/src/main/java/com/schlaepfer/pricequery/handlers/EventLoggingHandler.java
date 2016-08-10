package com.schlaepfer.pricequery.handlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.schlaepfer.priceevents.events.PriceAddedEvent;

/**
 * Handler's (a.k.a. Listeners) can be used to react to events and perform
 * associated actions, such as updating a 'materialised-view' for example.
 */
@Component
public class EventLoggingHandler {

	private static final Logger LOG = LoggerFactory.getLogger(EventLoggingHandler.class);

	@Value("${spring.application.index}")
	private Integer indexId;

	@EventHandler
	public void handle(PriceAddedEvent event) {
		LOG.debug("Instance:[{}] Event:{} Id:{} Name:'{}'", indexId, event.getClass().getSimpleName(), event.getId(),
				event.getName());
	}

}
