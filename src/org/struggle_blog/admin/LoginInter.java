package org.struggle_blog.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInter extends MethodFilterInterceptor {
	/**
	 * 访问管理员拦截，查看是否已经登录
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("adInfo");
		if(obj == null){
			System.out.println("这是拦截器判断到info为空，这可能需要登录");
			return "login";
		}else{
			System.out.println("这是info:"+obj.toString());
			System.out.println("这可能不需要登录了");
			return arg0.invoke();
		}
	}

}
