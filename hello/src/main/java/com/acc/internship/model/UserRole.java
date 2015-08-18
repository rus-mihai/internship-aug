package com.acc.internship.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "type", nullable = false)
	private String role;
	
	@OneToMany(mappedBy="userRole")
	private Set<User> user;
	
	public UserRole(){
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getRole(){
		return this.role;
	}
	
	public void setRole(String newRole){
		this.role = newRole;
	}
	
	public Set<User> getUser(){
		return this.user;
	}
	
	public void setUser(Set<User> user){
		this.user = user;
	}
}
