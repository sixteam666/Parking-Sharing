package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.Login_outBean;
import com.project.service.ILogin_outService;

@RestController
public class LoginLogHandler {
	@Autowired
	private ILogin_outService loginService;
	
	@RequestMapping("/in_outLog/findThreeDays")
	public List<Login_outBean> findThreeDays(){
		List<Login_outBean> list = loginService.findThreeDays();
		return list;
	}
	
	@RequestMapping("/in_outLog/findByTime")
	public List<Login_outBean> findByTime(String startTime,String stopTime){
		List<Login_outBean> list = loginService.findByTime(startTime,stopTime);
		return list;
	}
	
	@RequestMapping("in_outLog/findOnlineNum")
	public Integer findOnlineNum(){
		Integer onlineNum = loginService.findOnlineNum();
		return onlineNum;
	}

}
