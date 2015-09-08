package com.acc.internship.model;

public class Message {
	private int line;
	private String startTime;
	private String endTime;
	private String startStation;
	private String endStation;

	public Message(int line, String startTime, String endTime, String startStation, String endStation) {
		this.line = line;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startStation = startStation;
		this.endStation = endStation;
	}

	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	
}
