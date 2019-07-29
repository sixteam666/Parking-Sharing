package com.project.service;


import java.util.List;

import com.project.bean.Login_outBean;

public interface ILogin_outService {
	public List<Login_outBean> findThreeDays();
	public List<Login_outBean> findByTime(String startTime,String stopTime);
	public int add(Login_outBean bean);
	//public void deleteByTime(String startTime,String stopTime);
	public int findOnlineNum();
}
