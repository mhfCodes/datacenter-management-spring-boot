package com.example.datacenterApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.datacenterApp.models.dto.AuthReq;
import com.example.datacenterApp.models.dto.AuthRes;
import com.example.datacenterApp.security.JwtTokenUtil;
import com.example.datacenterApp.service.DatacenterUserDetailsService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	private JwtTokenUtil jwtTokenUtil;
	private DatacenterUserDetailsService datacenterUserDetailsService;
	
	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager,
			JwtTokenUtil jwtTokenUtil, DatacenterUserDetailsService datacenterUserDetailsService) {
		
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.datacenterUserDetailsService = datacenterUserDetailsService;
	}
	
	@PostMapping()
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthReq authReq) throws Exception {
		authenticate(authReq.getUsername(), authReq.getPassword());
		final UserDetails userDetails = datacenterUserDetailsService.loadUserByUsername(authReq.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthRes(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e); 
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_EXCEPTION", e);
		}
	}
	
	
}
