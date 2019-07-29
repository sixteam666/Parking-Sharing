package com.project.bean;

public class DataOperationBean {
	private int da_id;
	private String da_userName;
	private String da_logTime;
	private String da_module;
	private String da_data;
	private String da_operation;
	public int getDa_id() {
		return da_id;
	}
	public void setDa_id(int da_id) {
		this.da_id = da_id;
	}
	public String getDa_userName() {
		return da_userName;
	}
	public void setDa_userName(String da_userName) {
		this.da_userName = da_userName;
	}
	public String getDa_logTime() {
		return da_logTime;
	}
	public void setDa_logTime(String da_logTime) {
		this.da_logTime = da_logTime;
	}
	public String getDa_module() {
		return da_module;
	}
	public void setDa_module(String da_module) {
		this.da_module = da_module;
	}
	public String getDa_data() {
		return da_data;
	}
	public void setDa_data(String da_data) {
		this.da_data = da_data;
	}
	public String getDa_operation() {
		return da_operation;
	}
	public void setDa_operation(String da_operation) {
		this.da_operation = da_operation;
	}
	@Override
	public String toString() {
		return "DataOperationBean [da_id=" + da_id + ", da_userName=" + da_userName + ", da_logTime=" + da_logTime
				+ ", da_module=" + da_module + ", da_data=" + da_data + ", da_operation=" + da_operation + "]";
	}
	

}
