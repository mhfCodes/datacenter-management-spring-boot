package com.example.datacenterApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datacenterApp.models.views.TreeView;

@Repository
public interface TreeViewRepo extends JpaRepository<TreeView, Long>{
	
	Optional<List<TreeView>> findAllByNodeParentId(Long id);
}
