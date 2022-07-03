package com.example.datacenterApp.models.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.datacenterApp.models.RackModel;
import com.example.datacenterApp.models.ServerModel;

@Component
public class EntityDtoConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public RackModel convertRackDtoToRackEntity(RackDto rackDto) {
		RackModel rackModel = modelMapper.map(rackDto, RackModel.class);
		return rackModel;
	}
	
	public ServerModel convertServerDtoToServerEntity(ServerDto serverDto) {
		ServerModel serverModel = modelMapper.map(serverDto, ServerModel.class);
		return serverModel;
	}
	
	public RackDto convertRackEntityToRackDto(RackModel rackModel) {
		RackDto rackDto = modelMapper.map(rackModel, RackDto.class);
		return rackDto;
	}
	
	public ServerDto convertServerEntityToServerDto(ServerModel serverModel) {
		ServerDto serverDto = modelMapper.map(serverModel, ServerDto.class);
		return serverDto;
	}
}
