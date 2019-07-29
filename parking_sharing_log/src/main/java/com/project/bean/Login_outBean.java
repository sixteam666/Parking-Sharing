package com.project.bean;

public class Login_outBean {
	private int l_id;
	private String l_userName;
	private String l_logTime;
	private String l_ipAddress;
	private String l_login_out;
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public String getL_userName() {
		return l_userName;
	}
	public void setL_userName(String l_userName) {
		this.l_userName = l_userName;
	}
	public String getL_logTime() {
		return l_logTime;
	}
	public void setL_logTime(String l_logTime) {
		this.l_logTime = l_logTime;
	}
	public String getL_ipAddress() {
		return l_ipAddress;
	}
	public void setL_ipAddress(String l_ipAddress) {
		this.l_ipAddress = l_ipAddress;
	}
	public String getL_login_out() {
		return l_login_out;
	}
	public void setL_login_out(String l_login_out) {
		this.l_login_out = l_login_out;
	}
	@Override
	public String toString() {
		return "Login_outBean [l_id=" + l_id + ", l_username=" + l_userName + ", l_logTime=" + l_logTime
				+ ", l_ipAddress=" + l_ipAddress + ", l_login_out=" + l_login_out + "]";
	}
	

}
