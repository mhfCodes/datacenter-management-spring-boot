package com.example.datacenterApp.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SERVER_MODEL")
public class ServerModel {

	@Id
	@SequenceGenerator(
				name = "server_sequence",
				sequenceName = "server_sequence",
				allocationSize = 1
			)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "server_sequence"
			)
	private Long id;
	private Integer su; // start unit
	private Integer eu; // end unit
	private String brand;
	private String operatingSystem;
	private String cpu;
	private Integer ram;
	@Enumerated(EnumType.ORDINAL)
	private ProviderModel provider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rack_id")
	private RackModel rack;
	
	public ServerModel() {
	}

	public ServerModel(String brand, String operatingSystem, String cpu,
			Integer ram, Integer su, Integer eu, ProviderModel provider) {
		this.brand = brand;
		this.operatingSystem = operatingSystem;
		this.cpu = cpu;
		this.ram = ram;
		this.su = su;
		this.eu = eu;
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getOperatingSystem() {
		return operatingSystem;
	}
	
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	public String getCpu() {
		return cpu;
	}
	
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
	public Integer getRam() {
		return ram;
	}
	
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public RackModel getRack() {
		return rack;
	}

	public void setRack(RackModel rack) {
		this.rack = rack;
	}

	public Integer getSu() {
		return su;
	}

	public void setSu(Integer su) {
		this.su = su;
	}

	public Integer getEu() {
		return eu;
	}

	public void setEu(Integer eu) {
		this.eu = eu;
	}

	public ProviderModel getProvider() {
		return provider;
	}

	public void setProvider(ProviderModel provider) {
		this.provider = provider;
	}
	
}

