package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.Login_outBean;
import com.project.dao.ILogin_outDao;
import com.project.service.ILogin_outService;

@Service
public class Login_outServiceImpl implements ILogin_outService {
	
	@Autowired
	private ILogin_outDao logDao;

	@Override
	public List<Login_outBean> findByTime(String startTime, String stopTime) {
		List<Login_outBean> list = logDao.findByTime(startTime, stopTime);
		return list;
	}

	@Override
	public int add(Login_outBean bean) {
		int num = logDao.add(bean);
		return num;
	}

	@Override
	public List<Login_outBean> findThreeDays() {
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
		//System.out.println(startTime+"1111 "+stopTime);
		List<Login_outBean> list = logDao.findByTime(startTime, stopTime);
		return list;
	}

	@Override
	public int findOnlineNum() {
		Date date=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Calendar calendar =new GregorianCalendar();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MINUTE, -5);
		date = calendar.getTime();
		String startTime = dateFormat.format(date);
		calendar.add(calendar.MINUTE, 6);
		date = calendar.getTime();
		String stopTime = dateFormat.format(date);
		//System.out.println(startTime+"1111 "+stopTime);
		int onlineNum = logDao.findOnlineNum(startTime, stopTime);
		return onlineNum;
	}

	

}
