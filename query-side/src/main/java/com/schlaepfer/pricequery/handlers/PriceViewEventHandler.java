package com.schlaepfer.pricequery.handlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.schlaepfer.priceevents.events.PriceAddedEvent;
import com.schlaepfer.pricequery.domain.Price;
import com.schlaepfer.pricequery.repository.PriceRepository;

public class PriceViewEventHandler implements ReplayAware {

	private static final Logger LOG = LoggerFactory.getLogger(PriceViewEventHandler.class);

	@Autowired
	private PriceRepository priceRepository;

	@EventHandler
	public void handle(PriceAddedEvent event) {
		LOG.info("PriceAddedEvent: [{}] '{}'", event.getId(), event.getName());
		priceRepository.save(new Price(event.getId(), event.getName()));
	}

	public void beforeReplay() {
		LOG.info("Event Replay is about to START. Clearing the View...");
	}

	public void afterReplay() {
		LOG.info("Event Replay has FINISHED.");
	}

	public void onReplayFailed(Throwable cause) {
		LOG.error("Event Replay has FAILED.");
	}
}
