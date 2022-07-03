package com.example.datacenterApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class TreeModel {

	@Id
	@SequenceGenerator(
				name = "tree_sequence",
				sequenceName = "tree_sequence",
				allocationSize = 1
			)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "tree_sequence"
			)
	private Long id;
	private Long parentId;
	private String path;
	
	@OneToOne()
	@JoinColumn(name = "rack_id")
	private RackModel rack;
	
	@OneToOne()
	@JoinColumn(name = "server_id")
	private ServerModel server;
	
	
	public TreeModel() {
	}

	public TreeModel(Long parentId) {
		this.parentId = parentId;
	}
	
	public TreeModel(Long parentId, RackModel rack) {
		this.parentId = parentId;
		this.rack = rack;
	}
	
	public TreeModel(Long parentId, ServerModel server) {
		this.parentId = parentId;
		this.server = server;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public RackModel getRack() {
		return rack;
	}

	public void setRack(RackModel rack) {
		this.rack = rack;
	}

	public ServerModel getServer() {
		return server;
	}

	public void setServer(ServerModel server) {
		this.server = server;
	}
	
}

