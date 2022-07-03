package com.example.datacenterApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.datacenterApp.models.ServerModel;

@Repository
public interface ServerRepo extends JpaRepository<ServerModel, Long>, JpaSpecificationExecutor<ServerModel> {

	Optional<List<ServerModel>> findAllByRackId(Long id);
}
