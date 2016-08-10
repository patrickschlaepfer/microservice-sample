package com.schlaepfer.pricecommand.aggregates;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.schlaepfer.pricecommand.commands.AddPriceCommand;
import com.schlaepfer.priceevents.events.PriceAddedEvent;

public class PriceAggregate extends AbstractAnnotatedAggregateRoot {

	private static final Logger LOG = LoggerFactory.getLogger(PriceAggregate.class);

	@AggregateIdentifier
	private String id;
	private String name;

	public PriceAggregate() {
	}

	@CommandHandler
	public PriceAggregate(AddPriceCommand command) {
		LOG.debug("Command: 'AddPriceCommand' received.");
		LOG.debug("Queuing up a new PriceAddedEvent for price '{}'", command.getId());
		apply(new PriceAddedEvent(command.getId(), command.getName()));
	}

	@EventSourcingHandler
	public void on(PriceAddedEvent event) {
		this.id = event.getId();
		this.name = event.getName();
		LOG.debug("Applied: 'PriceAddedEvent' [{}] '{}'", event.getId(), event.getName());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
