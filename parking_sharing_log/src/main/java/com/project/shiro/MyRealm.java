package com.project.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.project.bean.UserBean;
import com.project.service.IUserService;

/**
 * 自定义的Realm 
 * @author Mac Book Pro
 *
 */
public class MyRealm extends AuthorizingRealm{
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	/*
	 * 权限校验
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("进入到授权操作方法");
		//根据用户的名字从数据库获取当前用户的角色或者权限信息 返回一个Set集合
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取到用户名
		Object username = principals.getPrimaryPrincipal();
		
		//获取到角色信息
		Set<String> set = new HashSet<String>();
		/*if("tiger".equals(username)){
			set.add("superAdmin");
		}else if("root".equals(username)){
			set.add("admin");
		}
		info.addRoles(set);
		
		// 获取到数据库的权限信息
		Set<String> set2 = new HashSet<String>();
		if("tiger".equals(username)){
			set2.add("user:create");
			set2.add("user:find");
		}
		info.addStringPermissions(set2);*/
		
		/*AuthorizationInfo info = new SimpleAuthorizationInfo(set);*/
		return info;
	}
	
	/**
	 * 认证校验
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = token.getPrincipal().toString();
		UserBean user = userService.findByName(name);
		System.out.println(user);
		ByteSource by = ByteSource.Util.bytes(token.getPrincipal());
		AuthenticationInfo info  = null;
		
		if(user!=null){
			info = new SimpleAuthenticationInfo(user.getU_username(), user.getU_password(),by,getName());
		}
		return info;
	}
	
}
