package com.acc.internship.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade=CascadeType.ALL)
	private UserRole userRole;
	
	public User(UserRole role, String lastName, String firstName, String username, String password){
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.userRole = role;
	}
	
	public User(){
		
	}
	

	public UserRole getRole(){
		return this.userRole;
	}
	public void setRole(UserRole role){
		this.userRole = role;
	}
	
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	
	
	public String getLastName(){
		return this.lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	
	public String getfirstName(){
		return this.firstName;
	}
	public void setfirstName(String firstName){
		this.firstName = firstName;
	}
	

	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}


	
	
	
	
}
