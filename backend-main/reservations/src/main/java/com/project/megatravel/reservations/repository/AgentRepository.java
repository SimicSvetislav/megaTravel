package com.project.megatravel.reservations.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.reservations.ExistDB;

@Repository
public class AgentRepository implements ExistRepository {

	private static final String collectionName = "/agenti";
	private static Long currentId = ExistDB.determineId(collectionName);
	private static final String jaxbContext = Agent.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/users schemas/KorisnikSema.xsd";
	
	public AgentRepository() {
		
	}
	
	@Override
	public Agent save(Object entity) {
		
		Agent ag = (Agent) entity;
		
		if (ag.getId()==null) {
			// Dodeli id
			ag.setId(++currentId);
		}

		return ExistDB.save(ag, ag.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public Agent getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<Agent> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public Agent deleteById(Long id) {
		
		Agent ag = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return ag;
		
	}
	
	public Agent getByEmail(String email) {
		
		Collection<Agent> all = getAll();
		
		Agent user =  all.stream()
				.filter(u -> u.getEmail().equals(email))
				.findFirst()
				.orElse(null);
		
		return user;
	}
	
	public Agent getByUsername(String username) {
		
		Collection<Agent> all = getAll();
		
		Agent user =  all.stream()
				.filter(u -> u.getKorisnickoIme().equals(username))
				.findFirst()
				.orElse(null);
		
		return user;
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		AgentRepository.currentId = currentId;
	}

	public static String getCollectionname() {
		return collectionName;
	}

	public static String getJaxbcontext() {
		return jaxbContext;
	}

	public static String getSchemalocation() {
		return schemaLocation;
	}

	
	
}
