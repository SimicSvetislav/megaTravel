package com.project.megatravel.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.users.repository.AgentRepository;

@Service
@CrossOrigin
public class AgentsService {

	@Autowired
	private AgentRepository repo;
	
	public Agent getById(Long id) {
		
		Agent a = repo.getOneById(id);
		
		return a;
		
	}
	
	public List<Agent> getAll() {
		
		List<Agent> agents = (List<Agent>) repo.getAll();
		
		return agents;
		
	}

	public Agent save(Agent korisnik) {
		
		Agent agent = repo.save(korisnik);
		
		return agent;
	}

	public Agent deleteById(Long id) {
		
		Agent a = repo.deleteById(id);
		
		return a;
	}
	
}
