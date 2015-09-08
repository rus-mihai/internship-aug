package com.acc.internship.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "stations")

public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "start")
	@JsonManagedReference
	private Set<Route> startRoutes;
	
	@OneToMany(mappedBy = "end")
	@JsonManagedReference
	private Set<Route> endRoutes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Route> getStartRoutes() {
		return startRoutes;
	}

	public void setStartRoutes(Set<Route> startRoutes) {
		this.startRoutes = startRoutes;
	}

	public Set<Route> getEndRoutes() {
		return endRoutes;
	}

	public void setEndRoutes(Set<Route> endRoutes) {
		this.endRoutes = endRoutes;
	}
	
	@Override
	public String toString(){
		return id + ","+ name;			
	}
	
}
