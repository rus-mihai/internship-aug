package com.acc.internship.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@Table(name = "stations")
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "start")
	private Set<Route> startRoutes;
	
	@OneToMany(mappedBy = "end")
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

	public void setEndRoutes(Set<Route> r) {
		this.endRoutes = r;
	}
	
	
}
