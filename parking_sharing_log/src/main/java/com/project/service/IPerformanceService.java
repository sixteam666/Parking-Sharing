package com.project.service;


import java.util.List;

import com.project.bean.PerformanceBean;

public interface IPerformanceService {
	public Integer[] findByRequestTime();
	public int add(PerformanceBean bean);
	public void deleteByTime(String startTime,String stopTime);
}
