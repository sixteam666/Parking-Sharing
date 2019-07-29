package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.DataOperationBean;
import com.project.dao.IDataOperationDao;
import com.project.service.IDataOperationService;

@Service
public class DataOperationServiceImpl implements IDataOperationService {
	
	@Autowired
	private IDataOperationDao dataDao;

	@Override
	public List<DataOperationBean> findByTime(String startTime, String stopTime) {
		List<DataOperationBean> list = dataDao.findByTime(startTime, stopTime);
		return list;
	}

	@Override
	public int add(DataOperationBean bean) {
		int num = dataDao.add(bean);
		return num;
	}

	@Override
	public void deleteByTime(String startTime, String stopTime) {
		dataDao.deleteByTime(startTime, stopTime);
		
	}

	@Override
	public List<DataOperationBean> findThreeDays() {
		Date date=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Calendar calendar =new GregorianCalendar();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -3);
		date = calendar.getTime();
		String startTime = dateFormat.format(date);
		calendar.add(calendar.DATE, 4);
		date = calendar.getTime();
		String stopTime = dateFormat.format(date);
		List<DataOperationBean> list = dataDao.findByTime(startTime, stopTime);
		return list;
	}

}
