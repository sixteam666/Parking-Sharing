package com.project.dao;

import java.util.List;
import com.project.bean.CartPortBean;

/**
 * 车位持久层接口
 * 
 * @author YuChen
 *
 */
public interface ICarPortDao {
	/**
	 * 查询所有车位
	 * 
	 * @return
	 */
	List<CartPortBean> findAllCarPort();

	/**
	 * 通过审批状态查询车位
	 * 
	 * @param status
	 * @return
	 */
	List<CartPortBean> findCartPortByStatus(int status);

	/**
	 * 通过id查询车位
	 * 
	 * @return
	 */
	CartPortBean findCartPortById();

	/**
	 * 修改车位审批状态
	 * 
	 * @param status
	 * @return
	 */
	int updateCartPortStatus(int status);
}
