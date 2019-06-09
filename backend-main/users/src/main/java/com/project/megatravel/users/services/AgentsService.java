package com.project.megatravel.users.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.users.Agent;

@Service
@CrossOrigin
public class AgentsService {

	public Agent getById(Long id) {
		
		Agent a = new Agent();
		a.setId(1L);
		
		return a;
		
	}

	
	public List<Agent> getAll() {
		
		return new ArrayList<>();
		
	}
	
}
