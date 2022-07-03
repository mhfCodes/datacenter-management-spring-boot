package com.example.datacenterApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datacenterApp.models.dto.RackDto;
import com.example.datacenterApp.models.dto.ServerDto;
import com.example.datacenterApp.models.views.TreeView;
import com.example.datacenterApp.service.DatacenterService;


@RestController
@RequestMapping("/api/v1")
public class DatacenterController {
	
	@Autowired
	private DatacenterService datacenterService;
	
	@GetMapping("/racks")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public List<RackDto> getRacks() {
		return datacenterService.getAllRacks();
	}
	
	@PostMapping("/racks")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addRack(@RequestBody RackDto rackDto) {
		datacenterService.addRack(rackDto);
		return "Rack Added Successfully";
	}
	
	@GetMapping("/servers")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public List<ServerDto> getServers() {
		return datacenterService.getAllServers();
	}
	
	@PostMapping("/servers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addServer(@RequestBody ServerDto serverDto) {
		datacenterService.addServer(serverDto);
		return "Server Added Successfully";
	}
	
	@GetMapping("/tree")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public List<TreeView> getTree(@RequestParam("parentId") Long parentId) {
		return datacenterService.getAllNodes(parentId);
	}
	
	@GetMapping("/searchServers")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public List<ServerDto> searchServers(@RequestParam(value="brand",required=false) String brand,
			@RequestParam(value="ram",required=false) Integer ram) {
		return datacenterService.searchServers(brand, ram);
	}
	
}
