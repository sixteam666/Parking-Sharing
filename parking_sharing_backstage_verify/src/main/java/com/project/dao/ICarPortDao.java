package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.project.bean.CarPortBean;

public interface ICarPortDao {

	CarPortBean findCarPortById(int id);
	/**
	 * 
	 * @param status
	 * @return
	 */
	List<CarPortBean> findCarPortByStatus(int status);

	/**
	 * 更新车位审核状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("UPDATE t_carport SET cp_status=#{status} WHERE cp_id=#{id}")
	int updateCarPortStatus(@Param("id") int id, @Param("status") int status);

}
