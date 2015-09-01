package com.acc.internship.model;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "records")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "startTime")
	private Date start;

	@Column(name = "pauseTime")
	private Date pause;

	@Column(name = "stopTime")
	private Date stop;

	@Column(name = "tour")
	private int tour;

	@Column(name = "retour")
	private int retour;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idRoute", nullable = false)
	private Route routerecord;

	@Transient
	private String startstring;
	
	public String getStartstring() {
		return startstring;
	}

	public void setStartstring(String startstring) {
		this.startstring = startstring;
	}

	public String getPausestring() {
		return pausestring;
	}

	public void setPausestring(String pausestring) {
		this.pausestring = pausestring;
	}

	public String getStopstring() {
		return stopstring;
	}

	public void setStopstring(String stopstring) {
		this.stopstring = stopstring;
	}

	@Transient
	private String pausestring;
	
	@Transient
	private String stopstring;
	
	public Route getRouterecord() {
		return routerecord;
	}

	public void setRouterecord(Route routerecord) {
		this.routerecord = routerecord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date date) {
		this.start = date;
	}

	public Date getPause() {
		return pause;
	}

	public void setPause(Date pause) {
		this.pause = pause;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public int getRetour() {
		return retour;
	}

	public void setRetour(int retour) {
		this.retour = retour;
	}

	public void startFormat()
	{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			start=format.parse(startstring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void pauseFormat()
	{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			pause=format.parse(pausestring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void stopFormat()
	{
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			stop=format.parse(stopstring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
}
