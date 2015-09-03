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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "routes")
public class Route {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "duration")
	private int duration;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idStart", nullable=false)
	@JsonManagedReference
	private Station start;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idEnd", nullable=false)
	@JsonManagedReference
	private Station end;
	
	@OneToMany(mappedBy="route")
	private Set<Assignment> assigments;

	@OneToMany(mappedBy="routerecord")
	private Set<Record> records;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration/60;
	}

	public void setDuration(int duration) {
		this.duration = duration*60;
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


	public Set<Assignment> getAssigments() {
		return assigments;
	}

	

	public void setAssigments(Set<Assignment> assigments) {
		this.assigments = assigments;
	}
	
	
}
