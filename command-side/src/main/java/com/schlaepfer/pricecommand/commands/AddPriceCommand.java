package com.schlaepfer.pricecommand.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class AddPriceCommand {

	@TargetAggregateIdentifier
	private final String id;
	private final String name;

	public AddPriceCommand(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
