package com.example.datacenterApp.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.datacenterApp.models.ServerModel;


@Component
public class SearchSpecifications {
	
	public Specification<ServerModel> brandContains(String brand) {
		return (server, cq, cb) -> cb.like(server.get("brand"), "%" + brand + "%");
	}
	
	public Specification<ServerModel> ramEquals(Integer ram) {
		return (server, cq, cb) -> cb.equal(server.get("ram"), ram);
	}
	
}
