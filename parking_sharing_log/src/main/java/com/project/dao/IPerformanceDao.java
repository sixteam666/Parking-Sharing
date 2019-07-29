package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.Login_outBean;
import com.project.bean.PerformanceBean;

public interface IPerformanceDao {
	@Select("select COUNT(pe_requestTime) from t_performance_log where pe_requestTime between #{startTime} and #{stopTime}")
	public int findByRequestTime(@Param("startTime")int startTime,@Param("stopTime")int stopTime);
	
	@Insert("insert into t_performance_log(pe_requestion,pe_requestTime,pe_stopTime) "
			+ "values(#{pe_requestion},#{pe_requestTime},#{pe_stopTime})")
	public int add(PerformanceBean bean);
	
	@Delete("delete from _performance_log where pe_requestTime between #{startTime} and #{stopTime}")
	public void deleteByTime(@Param("startTime")String startTime,@Param("stopTime")String stopTime);
}
