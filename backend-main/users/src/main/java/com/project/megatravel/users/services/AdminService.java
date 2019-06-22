package com.project.megatravel.users.services;

import java.util.List;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.users.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repo;

	public Administrator save(Administrator korisnik) {
		
		Administrator admin = repo.save(korisnik);
		
		return admin;
	}

	public Administrator getOneById(Long id) {
		
		Administrator admin = repo.getOneById(id);
		
		return admin;
	}

	public Administrator deleteById(Long id) {
		
		Administrator admin = repo.deleteById(id);
		
		return admin;
	}
	
	public List<Administrator> getAll() {
		
		List<Administrator> all = (List<Administrator>)repo.getAll();
		
		return all;
		
	}
	
	public String generatePassword() {
	    PasswordGenerator gen = new PasswordGenerator();
	    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);
	 
	    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);
	 
	    CharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);
	 
	    CharacterData specialChars = new CharacterData() {
	        public String getErrorCode() {
	            return "ERROR_CODE";
	        }
	 
	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
	    };
	    CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(2);
	 
	    String password = gen.generatePassword(10, splCharRule, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}

}
