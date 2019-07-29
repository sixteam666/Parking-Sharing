package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.IPerformanceService;

@RestController
public class PerformanceLogHandler {
	@Autowired
	private IPerformanceService perService;
	
	@RequestMapping("/performanceLog/findByRequestTime")
	public Integer[] findByRequestTime(){
		Integer[] arr = perService.findByRequestTime();
		return arr;
	}

}
