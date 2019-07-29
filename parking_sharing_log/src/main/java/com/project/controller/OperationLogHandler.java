package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.DataOperationBean;
import com.project.service.IDataOperationService;

@RestController
public class OperationLogHandler {
	@Autowired
	private IDataOperationService dataService;
	
	@RequestMapping("/dataOperationLog/findThreeDays")
	public List<DataOperationBean> findThreeDays(){
		List<DataOperationBean> list = dataService.findThreeDays();
		return list;
	}
	
	@RequestMapping("/dataOperationLog/findByTime")
	public List<DataOperationBean> findByTime(String startTime,String stopTime){
		List<DataOperationBean> list = dataService.findByTime(startTime, stopTime);
		return list;
	}

}
