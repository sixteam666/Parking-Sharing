package com.project.bean;

public class PerformanceBean {
	private int pe_id;
	private String pe_requestion;
	private int pe_requestTime;
	private String pe_stopTime;
	public int getPe_id() {
		return pe_id;
	}
	public void setPe_id(int pe_id) {
		this.pe_id = pe_id;
	}
	public String getPe_requestion() {
		return pe_requestion;
	}
	public void setPe_requestion(String pe_requestion) {
		this.pe_requestion = pe_requestion;
	}
	public int getPe_requestTime() {
		return pe_requestTime;
	}
	public void setPe_requestTime(int pe_requestTime) {
		this.pe_requestTime = pe_requestTime;
	}
	public String getPe_stopTime() {
		return pe_stopTime;
	}
	public void setPe_stopTime(String pe_stopTime) {
		this.pe_stopTime = pe_stopTime;
	}
	@Override
	public String toString() {
		return "PerformanceBean [pe_id=" + pe_id + ", pe_requestion=" + pe_requestion + ", pe_requestTime="
				+ pe_requestTime + ", pe_stopTime=" + pe_stopTime + "]";
	}
	

}
