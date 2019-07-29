package com.project.listener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.bean.Login_outBean;
import com.project.bean.UserBean;
import com.project.service.ILogin_outService;
import com.project.util.IpAddressUtil;

/**
 * SessionListener
 * SessionListenerAdapter
 * @author Mac Book Pro
 *
 */
public class MySessionListener implements SessionListener{
	@Autowired
	private ILogin_outService service;
	
	@Autowired
	private IpAddressUtil ipAddressUtil;
	
	private int onlineNumber;

	@Override
	public void onStart(Session session) {
		System.out.println("session创建监听");
		onlineNumber++;
	}
	@Override
	public void onStop(Session session) {
		System.out.println("session销毁监听");
		UserBean userBean = (UserBean) session.getAttribute("user");
		System.out.println(userBean);
		//得到当前时间
		Date da = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(da);
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
		//得到当前电脑的ip
		String ipAddress = ipAddressUtil.getIpAddr(request);
		Login_outBean bean = new Login_outBean();
		bean.setL_userName(userBean.getU_username());
		bean.setL_logTime(time);
		bean.setL_ipAddress(ipAddress);
		bean.setL_login_out("注销");
		service.add(bean);
		System.out.println("============================================");
		onlineNumber--;
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("session过期监听");
		UserBean userBean = (UserBean) session.getAttribute("user");
		//得到当前时间
		Date da = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(da);
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
		//得到当前电脑的ip
		String ipAddress = ipAddressUtil.getIpAddr(request);
		Login_outBean bean = new Login_outBean();
		bean.setL_userName(userBean.getU_username());
		bean.setL_logTime(time);
		bean.setL_ipAddress(ipAddress);
		bean.setL_login_out("注销");
		service.add(bean);
		System.out.println("============================================");
		onlineNumber--;
	}
	
}
