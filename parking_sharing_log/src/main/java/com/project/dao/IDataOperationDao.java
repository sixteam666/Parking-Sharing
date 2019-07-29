package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.bean.DataOperationBean;

public interface IDataOperationDao {
	@Select("select * from t_dataOperation_log where da_logTime between #{startTime} and #{stopTime}")
	public List<DataOperationBean> findByTime(@Param("startTime")String startTime,@Param("stopTime")String stopTime);
	
	@Insert("insert into t_dataOperation_log(da_userName,da_logTime,da_module,da_data,da_operation) "
			+ "values(#{da_userName},#{da_logTime},#{da_module},#{da_data},#{da_operation})")
	public int add(DataOperationBean bean);
	
	@Delete("delete from t_dataOperation_log where da_logTime between #{startTime} and #{stopTime}")
	public void deleteByTime(@Param("startTime")String startTime,@Param("stopTime")String stopTime);
}
