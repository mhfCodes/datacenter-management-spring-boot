package com.example.datacenterApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datacenterApp.models.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
	
	Optional<UserModel> findByUsername(String username);
}
