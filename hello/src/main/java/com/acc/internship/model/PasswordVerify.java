package com.acc.internship.model;

public class PasswordVerify {
	private String oldpassword;
	private String confirm;
	private String newpassword;
	private int id;
	
	public PasswordVerify(){
	}
	public PasswordVerify(String oldpassword,String confirm,String newpassword,int id){
		super();
		this.oldpassword=oldpassword;
		this.confirm=confirm;
		this.newpassword=newpassword;
		this.id=id;
	}
	
	
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
