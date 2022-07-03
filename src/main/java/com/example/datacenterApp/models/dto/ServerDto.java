package com.example.datacenterApp.models.dto;

import com.example.datacenterApp.models.ProviderModel;

public class ServerDto {

	private Long id;
	private Integer su; // start unit
	private Integer eu; // end unit
	private String brand;
	private String operatingSystem;
	private String cpu;
	private Integer ram;
	private Long rackId;
	private ProviderModel provider;
	private Long nodeParentId;
	
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

	public Long getRackId() {
		return rackId;
	}

	public void setRackId(Long rackId) {
		this.rackId = rackId;
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

	public Long getNodeParentId() {
		return nodeParentId;
	}

	public void setNodeParentId(Long nodeParentId) {
		this.nodeParentId = nodeParentId;
	}

}
