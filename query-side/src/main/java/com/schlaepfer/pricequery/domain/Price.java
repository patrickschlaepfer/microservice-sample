package com.schlaepfer.pricequery.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Price {

	@Id
	private String id;

	private String name;

	public Price() {
	}

	public Price(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
