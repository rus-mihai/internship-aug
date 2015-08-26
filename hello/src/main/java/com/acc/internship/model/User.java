package com.acc.internship.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idRole", nullable=false)
	private UserRole userRole;
	
	@OneToMany(mappedBy="driver")
	private Set<Assignment> assigments;

	
	public User(UserRole role, String lastName, String firstName, String username, String password){
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.userRole = role;
	}
	
	public User(){
		
	}
	


	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public Set<Assignment> getAssigments() {
		return assigments;
	}


	public void setAssigments(Set<Assignment> assigments) {
		this.assigments = assigments;
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
	
	
	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String firstName){
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
