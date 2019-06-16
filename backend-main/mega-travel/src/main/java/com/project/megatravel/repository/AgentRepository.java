package com.project.megatravel.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.ExistDB;
import com.project.megatravel.model.users.Agent;

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
