package com.example.datacenterApp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RACK_MODEL")
public class RackModel {
	
	@Id
	@SequenceGenerator(
				name = "rack_sequence",
				sequenceName = "rack_sequence",
				allocationSize = 1
			)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "rack_sequence"
			)
	private Long id;
	private Integer units;
	
	@OneToMany(mappedBy="rack", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ServerModel> servers;
	
	public RackModel() {
	}
	
	public RackModel(Integer units) {
		this.units = units;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getUnits() {
		return units;
	}
	
	public void setUnits(Integer units) {
		this.units = units;
	}

	public List<ServerModel> getServers() {
		return servers;
	}

	public void setServers(List<ServerModel> servers) {
		this.servers = servers;
	}
	
}