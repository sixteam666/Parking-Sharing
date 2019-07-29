package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.PerformanceBean;
import com.project.dao.IPerformanceDao;
import com.project.service.IPerformanceService;

@Service
public class PerformanceServiceImpl implements IPerformanceService {
	
	@Autowired
	private IPerformanceDao perDao;

	@Override
	public Integer[] findByRequestTime() {
		Integer count1 = perDao.findByRequestTime(0, 3000);
		Integer count2 = perDao.findByRequestTime(3001, 5000);
		Integer count3 = perDao.findByRequestTime(5001, 10000);
		Integer count4 = perDao.findByRequestTime(10000, 100000);
		/*List<Integer> list = new ArrayList<Integer>();
		list.add(count1);
		list.add(count2);
		list.add(count3);
		list.add(count4);*/
		Integer[] arr = new Integer[4];
		arr[0] = count1;
		arr[1] = count2;
		arr[2] = count3;
		arr[3] = count4;
		
		return arr;
	}

	@Override
	public int add(PerformanceBean bean) {
		int num = perDao.add(bean);
		return num;
	}

	@Override
	public void deleteByTime(String startTime, String stopTime) {
		perDao.deleteByTime(startTime, stopTime);
		
	}

	

}
