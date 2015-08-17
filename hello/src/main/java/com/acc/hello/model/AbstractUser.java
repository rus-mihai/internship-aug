package com.acc.hello.model;

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
	
	@Column(name = "nume", nullable = false)
	private String nume;
	
	@Column(name = "prenume", nullable = false)
	private String prenume;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "idRole", nullable = false)
	private int idRole;
	
	public AbstractUser(int idRole, String nume, String prenume, String username, String password){
		this.nume = nume;
		this.prenume = prenume;
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
	
	
	
	public String getName(){
		return this.nume;
	}
	public void setName(String name){
		this.nume = name;
	}
	
	
	public String getPrenume(){
		return this.prenume;
	}
	public void setPrenume(String prenume){
		this.prenume = prenume;
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
