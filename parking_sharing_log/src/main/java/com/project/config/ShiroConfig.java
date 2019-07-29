package com.project.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.listener.MySessionListener;
import com.project.shiro.MyRealm;


/**
 * shiro的配置类
 * @author Mac Book Pro
 *
 */
@Configuration
public class ShiroConfig {
	/**
	 * shiro的核心过滤器,放在类的最前面
	 * @return
	 */
	@Bean(name="shiroFilterFactoryBean")//@Qualifier("defaultWebSecurityManager")可以不要
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager securityManager){
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//需要注入安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//认证失败需要跳转的地址
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		//授权失败需要跳转的地址
		shiroFilterFactoryBean.setUnauthorizedUrl("/fail.html");
		
		//设置访问路径的权限
		Map<String,String> fmap = new LinkedHashMap<String,String>();
		fmap.put("/**", "anon");
		fmap.put("/loginout", "logout");
		/*fmap.put("/register.html", "anon");
		fmap.put("/login.html", "anon");
		
		fmap.put("/user/login", "anon");
		fmap.put("/user", "anon");
		fmap.put("/user/updatepassword", "anon");
		//记住我
		fmap.put("/login.html", "user");
		//fmap.put("/evaluation/pages/register.html", "user");
		fmap.put("/goods", "user");
		fmap.put("/loginout", "logout");
		//fmap.put("/**", "anon");
		
		fmap.put("/index.html", "authc");
		fmap.put("/replyPost.html", "authc");
		fmap.put("/goods", "authc");*/
		//fmap.put("/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(fmap);
		
		
		return shiroFilterFactoryBean;
	}
	/**
	 * 定义安全管理器
	 * @Qualifier("myRealm1")可能存在多个realm，通过注解来区分，如果只有一个可以省略注解
	 */
	@Bean(name="defaultWebSecurityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm1")MyRealm realm,@Qualifier("sessionManager")SessionManager sessionManager){
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		securityManager.setSessionManager(sessionManager);
		return securityManager;
	}
	
	/**
	 * 使用md5来进行密码加密,自定义凭证匹配器
	 * @return
	 */
	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher getHashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//加密算法
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		//加密次数
		hashedCredentialsMatcher.setHashIterations(1024);
		//决定hex还是base64，可以不用设置
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}
	
	/**
	 * realm对象，对认证、权限进行校验
	 * @return
	 */
	@Bean(name="myRealm1")
	public MyRealm getMyRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher){
		MyRealm realm = new MyRealm();
		//传入自定义凭证匹配器
		realm.setCredentialsMatcher(hashedCredentialsMatcher);
		return realm;
	}

	/**
	 * 配置session监听
	 * @return
	 */
	@Bean("sessionListener")
	public MySessionListener sessionListener(){
		MySessionListener sessionListener = new MySessionListener();
	    return sessionListener;
	}
	/**
	 * 配置会话管理器，设定会话超时及保存
	 * @return
	 */
	@Bean("sessionManager")
	public DefaultWebSessionManager sessionManager(@Qualifier("sessionListener")MySessionListener sessionListener) {

		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
	    Collection<SessionListener> listeners = new ArrayList<SessionListener>();
	    //配置监听
	    listeners.add(sessionListener);
	    
	    sessionManager.setSessionListeners(listeners);
	    //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
	    sessionManager.setGlobalSessionTimeout(1800000);
	    //是否开启删除无效的session对象  默认为true
	    sessionManager.setDeleteInvalidSessions(true);
	    //是否开启定时调度器进行检测过期session 默认为true
	    sessionManager.setSessionValidationSchedulerEnabled(true);
	    //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
	    //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
	    //暂时设置为 5秒 用来测试
	    sessionManager.setSessionValidationInterval(3600000);
	    return sessionManager;

	}
	
	
}
