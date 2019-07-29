package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.UserBean;

public interface IUserDao {
	@Select("select * from t_user")
	public List<UserBean> findAll();
	
	@Select("select * from t_user where u_username=#{u_username}")
	public UserBean findByName(String name);
	
	@Select("select * from t_user where u_id=#{u_id}")
	public UserBean findById(int u_id);
	
	@Insert("insert into t_user(u_username,u_password) values(#{u_username},#{u_password})")
	public void add(UserBean bean);
}
