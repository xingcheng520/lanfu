package com.shengtian.lanfu.model;






public class User {
	
	
	private long id;
	private  String userName;
	private  String phone;
	private  String passWord;
	private  String massgeTime;
	private  String invitationCode;
	private  double balance;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMassgeTime() {
		return massgeTime;
	}
	public void setMassgeTime(String massgeTime) {
		this.massgeTime = massgeTime;
	}
	
	
	
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", phone=" + phone
				+ ", passWord=" + passWord + ", massgeTime=" + massgeTime + "]";
	}
	

	
	
}
