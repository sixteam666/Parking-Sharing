package com.project.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bean.DataOperationBean;
import com.project.bean.Login_outBean;
import com.project.bean.PerformanceBean;
import com.project.bean.UserBean;
import com.project.service.IDataOperationService;
import com.project.service.ILogin_outService;
import com.project.service.IPerformanceService;
import com.project.util.IpAddressUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class ControllerInterceptor {
	@Autowired
	private IpAddressUtil ipAddressUtil;
	
	@Autowired
	private ILogin_outService loginService;
	
	@Autowired
	private IPerformanceService perService;
	
	@Autowired
	private IDataOperationService dataService;
	
	private static final Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);
    private static ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private static ThreadLocal<String> key = new ThreadLocal<String>();
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 定义拦截规则：拦截com.project.controller..*(..))包下面的所有类中，有@RequestMapping注解的方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    /**
     * 请求方法前打印内容
     *
     * @param joinPoint
     */
    @Before("controllerMethodPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 请求开始时间
        startTime.set(System.currentTimeMillis());
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 如果有session则返回session如果没有则返回null(避免创建过多的session浪费内存)
        //HttpSession session = request.getSession(false);
        //获取请求URI（requestmapping中的内容）
        String uri = request.getRequestURI();
        key.set(uri);
    }

    /**
     * 在方法执行后打印返回内容
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "controllerMethodPointcut()")
    public void doAfterReturing(Object obj) {
    	//请求响应时间
        long costTime = System.currentTimeMillis() - startTime.get();
        String uri = key.get();
        //去掉jsessionid
        if (uri.indexOf(";")!=-1) {
        	uri = uri.substring(0,uri.indexOf(";")).trim();
		}
        startTime.remove();
        key.remove();
        String result= null;
        if(obj instanceof Serializable){
        	//返回数据的页面
            result = obj.toString();
        }else {
            if(obj != null) {
                try {
                    result = objectMapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    log.error("doAfterReturing obj to json exception obj={},msg={}",obj,e);
                }
            }
        }
        //得到当前时间
  		Date da = new Date();
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		String time = dateFormat.format(da);
  		
  		
  		//登录日志
        if (uri.indexOf("login")!=-1 || uri.indexOf("Login")!=-1) {
        	//产生一个用户（门面对象）
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(false);
            UserBean userBean = (UserBean) session.getAttribute("user");
            //得到当前电脑的ip
      		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
      		String ipAddress = ipAddressUtil.getIpAddr(request);
            Login_outBean bean = new Login_outBean();
      		bean.setL_userName(userBean.getU_username());
      		bean.setL_logTime(time);
      		bean.setL_ipAddress(ipAddress);
      		bean.setL_login_out("登录");
      		loginService.add(bean);
		}
        
        //操作增删改日志
        if (uri.indexOf("login")==-1 && uri.indexOf("find")==-1 && uri.indexOf("loginout")==-1 && uri.indexOf("logout")==-1) {
        	Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession(false);
            UserBean userBean = (UserBean) session.getAttribute("user");
        	String[] arrayURI = uri.split("/");
        	DataOperationBean dataBean = new DataOperationBean();
        	dataBean.setDa_logTime(time);
        	dataBean.setDa_module(arrayURI[1]);
        	dataBean.setDa_userName(userBean.getU_username());
        	if (arrayURI.length>=3) {
        		if (arrayURI[2].indexOf("insert")!=-1) {
            		String data = arrayURI[2].substring(6,arrayURI[2].length());
            		dataBean.setDa_operation("增加");
            		dataBean.setDa_data(data);
    			}else if (arrayURI[2].indexOf("update")!=-1) {
    				String data = arrayURI[2].substring(6,arrayURI[2].length());
            		dataBean.setDa_operation("修改");
            		dataBean.setDa_data(data);
    			}else if (arrayURI[2].indexOf("delete")!=-1) {
    				String data = arrayURI[2].substring(6,arrayURI[2].length());
            		dataBean.setDa_operation("删除");
            		dataBean.setDa_data(data);
    			}
			}
        	
        	
        	dataService.add(dataBean);
		}
        
       //请求响应日志
        if (uri.indexOf("loginout")==-1 && uri.indexOf("logout")==-1) {
	        PerformanceBean perBean = new PerformanceBean();
	        perBean.setPe_requestion(uri);
	        perBean.setPe_requestTime(Integer.parseInt(costTime+""));
	        perBean.setPe_stopTime(time);
	        perService.add(perBean);
        }
        log.info("response result<<<<<<uri={},result={},costTime={}ms", uri, result, costTime);
    }
}
