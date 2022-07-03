package com.example.datacenterApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;

import com.example.datacenterApp.models.RackModel;
import com.example.datacenterApp.models.ServerModel;
import com.example.datacenterApp.models.TreeModel;
import com.example.datacenterApp.models.dto.EntityDtoConverter;
import com.example.datacenterApp.models.dto.RackDto;
import com.example.datacenterApp.models.dto.ServerDto;
import com.example.datacenterApp.models.views.TreeView;
import com.example.datacenterApp.repository.RackRepo;
import com.example.datacenterApp.repository.SearchSpecifications;
import com.example.datacenterApp.repository.ServerRepo;
import com.example.datacenterApp.repository.TreeRepo;
import com.example.datacenterApp.repository.TreeViewRepo;

@Service
public class DatacenterService {

	private RackRepo rackRepo;
	private ServerRepo serverRepo;
	private EntityDtoConverter entityDtoConverter;
	private TreeRepo treeRepo;
	private TreeViewRepo treeViewRepo;
	private SearchSpecifications searchSpecifications;

	@Autowired
	public DatacenterService(RackRepo rackRepo,
							ServerRepo serverRepo, EntityDtoConverter entityDtoConverter,
							TreeRepo treeRepo, TreeViewRepo treeViewRepo,
							SearchSpecifications searchSpecifications) {
		this.rackRepo = rackRepo;
		this.serverRepo = serverRepo;
		this.entityDtoConverter = entityDtoConverter;
		this.treeRepo = treeRepo;
		this.treeViewRepo = treeViewRepo;
		this.searchSpecifications = searchSpecifications;
	}
	
	public List<RackDto> getAllRacks() {
		List<RackDto> rackDtos = new ArrayList<>(); 
		List<RackModel> racks = rackRepo.findAll();
	
		racks.forEach(rack -> {
			RackDto rackDto = entityDtoConverter.convertRackEntityToRackDto(rack);
			rackDtos.add(rackDto);
		});
		
		return rackDtos;
	}
	
	@Transactional
	public void addRack(RackDto rackDto) {
		RackModel rack = rackRepo.save(entityDtoConverter.convertRackDtoToRackEntity(rackDto));
		String path = generatePath(treeRepo.findById(rackDto.getNodeParentId()).orElseThrow(), treeRepo);
		this.addRackNode(rackDto.getNodeParentId(), path, rack.getId());
	}
	
	public List<ServerDto> getAllServers() {
		List<ServerDto> serverDtos = new ArrayList<>();
		List<ServerModel> servers = serverRepo.findAll();
		
		servers.forEach(server -> {
			ServerDto serverDto = entityDtoConverter.convertServerEntityToServerDto(server);
			serverDtos.add(serverDto);
		});
		
		return serverDtos;
	}
	
	@Transactional
	public void addServer(ServerDto serverDto) {
		ServerModel serverModel = entityDtoConverter.convertServerDtoToServerEntity(serverDto);
		RackModel rack = rackRepo.findById(serverModel.getRack().getId())
			.orElseThrow(() -> new IllegalStateException("Rack With Id " + serverModel.getId() + " Does Not Exist"));
		List<ServerModel> servers = serverRepo.findAllByRackId(serverModel.getRack().getId())
				.orElseThrow(() -> new IllegalStateException("Rack With Id " + serverModel.getId() + " Does Not Exist"));
		
		if (serverModel.getSu().equals(serverModel.getEu())) {
			throw new IllegalStateException("Server Start Unit And End Unit Can Not Be Equal");
		}
		
		if (serverModel.getSu() > serverModel.getEu()) {
			throw new IllegalStateException("Server Start Unit Can Not Be More Than End Unit");
		}
		
		if (serverModel.getSu() > rack.getUnits() || serverModel.getEu() > rack.getUnits()) {
			throw new IllegalStateException("Start Unit Or End Unit Can not Be More Than Rack Total Units");
		}
		
		servers.forEach(server -> {
			if ((serverModel.getSu() >= server.getSu() && serverModel.getSu() <= server.getEu())
				|| (serverModel.getEu() >= server.getSu() && serverModel.getEu() <= server.getEu())
				|| (server.getSu() > serverModel.getSu() && server.getSu() < serverModel.getEu())) {
				throw new IllegalStateException("Server With Specified Start Unit Or End Unit Already Exists");
			}
		});
		
		ServerModel savedModel = serverRepo.save(serverModel);
		String path = generatePath(treeRepo.findById(serverDto.getNodeParentId()).orElseThrow(), treeRepo);
		this.addServerNode(serverDto.getNodeParentId(), path, savedModel.getId());		
	}
	
	public List<TreeView> getAllNodes(Long parentId) {
		
		if (parentId.equals(0L)) return treeViewRepo.findAll();
		
		return treeViewRepo.findAllByNodeParentId(parentId)
				.orElseThrow(() -> new IllegalStateException("Parent Id " + parentId + " Does Not Exist"));
	}

	@Procedure("NODE_RACK_PROCEDURE")
	public void addRackNode(Long parentId, String path, Long rackId) {
		try {
			treeRepo.saveRackNode(parentId, path, rackId);
		} catch (NegativeArraySizeException e) {
			System.out.println(e);
		}
	}
	
	@Procedure("NODE_SERVER_PROCEDURE")
	public void addServerNode(Long parentId, String path, Long serverId) {
		try {
			treeRepo.saveServerNode(parentId, path, serverId);
		} catch (NegativeArraySizeException e) {
			System.out.println(e);
		}
	}
	
	public List<ServerDto> searchServers(String brand, Integer ram) {
		List<ServerModel> serverModels = new ArrayList<>();
		
		if (ram == null && brand != null) {
			serverModels = serverRepo.findAll(searchSpecifications.brandContains(brand));
		} else if (brand == null && ram != null) {
			serverModels = serverRepo.findAll(searchSpecifications.ramEquals(ram));
		} else {
			serverModels = serverRepo.findAll(Specification.where(searchSpecifications.brandContains(brand))
							.and(searchSpecifications.ramEquals(ram)));
		}
		
		return serverModels.stream().map(server -> entityDtoConverter.convertServerEntityToServerDto(server))
						.collect(Collectors.toList());
	}
	
	public String generatePath(TreeModel node, TreeRepo treeRepo){
		
		if(node.getParentId() == null) {
			return "1";
		} else {
			return generatePath(treeRepo.findById(node.getParentId())
					.orElseThrow(() -> new IllegalStateException("Id Not Found")), treeRepo) + "/" + node.getId();
		}
		
	}
	
}
