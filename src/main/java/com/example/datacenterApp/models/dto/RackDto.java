package com.example.datacenterApp.models.dto;

import java.util.List;

public class RackDto {
	private Long id;
	private Integer units;
	private List<ServerDto> servers;
	private Long nodeParentId;

	
	public Long getNodeParentId() {
		return nodeParentId;
	}

	public void setNodeParentId(Long nodeParentId) {
		this.nodeParentId = nodeParentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ServerDto> getServers() {
		return servers;
	}

	public void setServers(List<ServerDto> servers) {
		this.servers = servers;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

}
