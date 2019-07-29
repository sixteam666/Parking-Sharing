package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.UserBean;
import com.project.dao.IUserDao;
import com.project.service.IUserService;

@Service(value="userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userdao;
	
	@Override
	public List<UserBean> findAll() {
		// TODO Auto-generated method stub
		return userdao.findAll();
	}

	@Override
	public UserBean findByName(String name) {
		// TODO Auto-generated method stub
		return userdao.findByName(name);
	}

	@Override
	public UserBean findById(int id) {
		// TODO Auto-generated method stub
		return userdao.findById(id);
	}

	@Override
	public void register(UserBean bean) {
		userdao.add(bean);
	}

}
