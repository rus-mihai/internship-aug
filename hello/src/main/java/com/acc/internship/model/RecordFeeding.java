package com.acc.internship.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecordFeeding {
	
	private int MIN = 10;
	private int MAX = 40;
	private GregorianCalendar gc;
	
	public RecordFeeding(){

	}
	
	public Record getRecord(Route route){
		int duration = randBetween(MIN, MAX);
		Date start = randomDate();
		
		Date intermediar = getNext(start,duration);
		
		duration = randBetween(MIN,MAX);
		Date end = getNext(intermediar,duration);
		Record r = new Record();
		r.setStart(start);
		r.setPause(intermediar);
		r.setStop(end);
		r.setRouterecord(route);
		
		return r;
	}
	
	public Date randomDate(){
		int year = randBetween(2014, 2015);
		int month = randBetween(0,11);
		int hour = randBetween(0, 23);
		int min = randBetween(0, 59);
		int sec = randBetween(0, 59);
		
		gc = new GregorianCalendar(year, month, 1);
		@SuppressWarnings("static-access")
		int day = randBetween(1, gc.getActualMaximum(gc.DAY_OF_MONTH));
		
		gc.set(year, month, day, hour, min, sec);
		return gc.getTime();
	}
	
	
	@SuppressWarnings("static-access")
	public Date getNext(Date date, int duration){
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		
		gc.add(gc.MINUTE, duration);
		
		return gc.getTime();
		
	}
	
	public static int randBetween(int start, int end){
		return start + (int)Math.round(Math.random() * (end - start));
	}
	
	
}
