package com.acc.internship.controller;

public class DriverSchema implements RoleSchema {
	
	
	@Override
	public String getRoleSchema() {
		return "driver";
	}

	@Override
	public String getBasePage() {
		
		return "updatedriver";
	}

}
