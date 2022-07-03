package com.example.datacenterApp.models;

public enum ProviderModel {
	BLUEHOST(0, "BLUEHOST"),
	HOSTGATOR(1, "HOSTGATOR"),
	DREAMHOST(2, "DREAMHOST");

	private final Integer index;
	private final String name;

	ProviderModel(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public Integer getIndex() {
		return this.index;
	}
	
	public String getName() {
		return this.name;
	}
	
}
