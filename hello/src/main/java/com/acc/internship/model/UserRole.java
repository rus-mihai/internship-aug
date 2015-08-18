package com.acc.internship.model;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "type", nullable = false)
	private String role;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
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
	
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
}
