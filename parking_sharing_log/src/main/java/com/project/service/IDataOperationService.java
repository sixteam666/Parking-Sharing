package com.project.service;


import java.util.List;

import com.project.bean.DataOperationBean;

public interface IDataOperationService {
	public List<DataOperationBean> findThreeDays();
	public List<DataOperationBean> findByTime(String startTime,String stopTime);
	public int add(DataOperationBean bean);
	public void deleteByTime(String startTime,String stopTime);
}
