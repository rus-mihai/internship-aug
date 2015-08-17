package com.acc.internship.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class AbstractUser {
	
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
	
	@Column(name = "idRole", nullable = false)
	private int idRole;
	
	public AbstractUser(int idRole, String lastName, String firstName, String username, String password){
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.idRole = idRole;
	}
	
	public AbstractUser(){
		
	}
	

	public int getIdRole(){
		return this.idRole;
	}
	public void setIdRole(int idRole){
		this.idRole = idRole;
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
