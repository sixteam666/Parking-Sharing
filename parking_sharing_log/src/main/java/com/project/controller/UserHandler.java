package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.ReportAsSingleViolation;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.UserBean;
import com.project.service.IUserService;

@Controller
public class UserHandler {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@RequestMapping("/user/login")
	@ResponseBody
	public String login(UserBean userBean,HttpSession session,Integer remember){
		String name = userBean.getU_username();
		String pwd = userBean.getU_password(); 
		//产生一个用户（门面对象）
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
	        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
	        //记住我
        	if (remember==1) {
				token.setRememberMe(true);
			}/*else if (remember==2 || remember==null){
				token.setRememberMe(false);
			}*/
	        try {
	        	//调用login进行认证
	            currentUser.login(token);
	            System.out.println("认证成功");
	            UserBean user = userService.findByName(name);
	            session.setAttribute("user", user);
	            Session session2 = currentUser.getSession();
	            session2.setAttribute("user", user);
	            return "ok";
	            //return "redirect:/user/updatepassword";
	        } 
	        // 父异常。认证失败异常
	        catch (AuthenticationException ae) {
	            //unexpected condition?  error?
	        	System.out.println("异常不详：自己解决");
	        	//return "/goods";
	        }
	     }
		 return "redirect:/user/updatepassword";
	}
	
	@RequestMapping("/user/updatepassword")
	public String update(){
		System.out.println("修改密码");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/register.html";
	}
	
}
