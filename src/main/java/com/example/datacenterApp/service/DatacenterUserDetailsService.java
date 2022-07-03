package com.example.datacenterApp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.datacenterApp.models.UserModel;
import com.example.datacenterApp.repository.UserRepo;


@Service
public class DatacenterUserDetailsService implements UserDetailsService {

	private UserRepo userRepo;
	
	@Autowired
	public DatacenterUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user =  userRepo.findByUsername(username)
				.orElseThrow(() -> new IllegalStateException("Username Does Not Exist"));
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
		return userDetails;
	}
}
