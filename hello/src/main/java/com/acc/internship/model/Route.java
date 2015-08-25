package com.acc.internship.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "duration")
	private String duration;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idStart", nullable=false)
	private Station start;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idEnd", nullable=false)
	private Station end;
	
	@OneToMany(mappedBy="route")
<<<<<<< HEAD
	private Set<Assignment> assigments;
=======
	private Set<Assigment> assigments;
>>>>>>> master

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Station getStart() {
		return start;
	}

	public void setStart(Station start) {
		this.start = start;
	}

	public Station getEnd() {
		return end;
	}

	public void setEnd(Station end) {
		this.end = end;
	}

<<<<<<< HEAD
	public Set<Assignment> getAssigments() {
		return assigments;
	}

	public void setAssigments(Set<Assignment> assigments) {
=======
	public Set<Assigment> getAssigments() {
		return assigments;
	}

	public void setAssigments(Set<Assigment> assigments) {
>>>>>>> master
		this.assigments = assigments;
	}
	
	
}
