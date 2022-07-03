package com.example.datacenterApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.datacenterApp.models.TreeModel;


@Repository
public interface TreeRepo extends JpaRepository<TreeModel, Long> {
	
	Optional<TreeModel> findById(Long id);
	
	@Query(value = "CALL NODE_RACK_PROCEDURE(:node_parent_id, :node_path, :node_rack_id)", nativeQuery = true)
	void saveRackNode(@Param("node_parent_id") Long nodeParentId, @Param("node_path") String nodePath,
			@Param("node_rack_id") Long nodeRackId);
	
	@Query(value = "CALL NODE_SERVER_PROCEDURE(:node_parent_id, :node_path, :node_server_id)", nativeQuery = true)
	void saveServerNode(@Param("node_parent_id") Long nodeParentId, @Param("node_path") String nodePath, 
			@Param("node_server_id") Long nodeServerId);
	
}
