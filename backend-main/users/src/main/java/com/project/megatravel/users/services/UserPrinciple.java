package com.project.megatravel.users.services;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.megatravel.model.users.Administrator;
import com.project.megatravel.model.users.Agent;
import com.project.megatravel.model.users.KrajnjiKorisnik;
import com.project.megatravel.model.users.TKorisnik;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;


    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id,
			    		String email, String password,
			    		Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;

    }

    public static UserPrinciple build(Object obj) {
    	
    	List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
    
    	
    	if(obj instanceof KrajnjiKorisnik) {
    		auth.add(new SimpleGrantedAuthority("USER"));
    		 return new UserPrinciple(
    	                ((KrajnjiKorisnik) obj).getId(),
    	                ((KrajnjiKorisnik) obj).getEmail(),
    	                ((KrajnjiKorisnik) obj).getSifra(),
    	                auth
    	        );
    	} else if(obj instanceof Agent) {
    		auth.add(new SimpleGrantedAuthority("AGENT"));
    		return new UserPrinciple(
	                ((Agent) obj).getId(),
	                ((Agent) obj).getEmail(),
	                ((Agent) obj).getSifra(),
	                auth
	        );
    	} else {
    		auth.add(new SimpleGrantedAuthority("ADMIN"));
    		return new UserPrinciple(
	                ((Administrator) obj).getId(),
	                ((Administrator) obj).getEmail(),
	                ((Administrator) obj).getSifra(),
	                auth
	        );
    	}
    	
    	/*List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
    	auth.add(new SimpleGrantedAuthority("USER"));*/
    	
     /*   List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());*/

       
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}