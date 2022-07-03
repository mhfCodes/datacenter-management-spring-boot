package com.example.datacenterApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datacenterApp.models.RackModel;

@Repository
public interface RackRepo extends JpaRepository<RackModel, Long> {
	
	Optional<RackModel> findById(Long id);
}
