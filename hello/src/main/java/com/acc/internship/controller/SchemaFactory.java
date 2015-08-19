package com.acc.internship.controller;

public class SchemaFactory {
	public RoleSchema getSchema(String authority){
		if(authority.contains("admin")){
			return new AdminSchema();
		}else if(authority.contains("driver")){
			return new DriverSchema();
		}else{
			return new LoginSchema();
		}
	}
}
