package com.example.datacenterApp.models.views;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "TREE_VIEW")
@Immutable
public class TreeView {

	@Id
	private Long nodeId;
	private Long nodeParentId;
	private String nodePath;
	private Long nodeRackId;
	private Long nodeServerId;
	
	public Long getNodeId() {
		return nodeId;
	}
	
	public Long getNodeParentId() {
		return nodeParentId;
	}
	
	public String getNodePath() {
		return nodePath;
	}
	
	public Long getNodeRackId() {
		return nodeRackId;
	}
	
	public Long getNodeServerId() {
		return nodeServerId;
	}
	
}
