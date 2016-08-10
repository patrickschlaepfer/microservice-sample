package com.schlaepfer.priceevents.events;

public class PriceAddedEvent extends AbstractEvent {

	private String name;

	public PriceAddedEvent() {
	}

	public PriceAddedEvent(String id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
