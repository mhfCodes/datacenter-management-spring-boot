package com.example.datacenterApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datacenterApp.models.RoleModel;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Long>{

}
