package com.project.megatravel.rbm.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.rbm.ExistDB;

@Repository
public class AdminRepository implements ExistRepository {

	private static final String collectionName = "/admini";
	private static Long currentId = ExistDB.determineIdUser();
	private static final String jaxbContext = Administrator.class.getPackage().toString().substring(8);
	private static final String schemaLocation = "https://www.model.megatravel.project.com/users schemas/KorisnikSema.xsd";
	
	public AdminRepository() {
			
	}
	
	@Override
	public Administrator save(Object entity) {
		
		Administrator admin = (Administrator) entity;
		
		if (admin.getId()==null) {
			// Dodeli id
			admin.setId(++currentId);
		}

		return ExistDB.save(admin, admin.getId(), collectionName, schemaLocation, jaxbContext);		
	}

	@Override
	public Administrator getOneById(Long id) {
		
		return ExistDB.getOneById(id, collectionName, jaxbContext);
	}

	@Override
	public Collection<Administrator> getAll() {
		
		return ExistDB.getAll(collectionName, jaxbContext);
	
	}

	@Override
	public Administrator deleteById(Long id) {
		
		Administrator admin = ExistDB.deleteById(id, collectionName, jaxbContext);
		
		return admin;
		
	}

	public static Long getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(Long currentId) {
		AdminRepository.currentId = currentId;
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
