package org.struggle_blog.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInter extends MethodFilterInterceptor {
	/**
	 * ���ʹ���Ա���أ��鿴�Ƿ��Ѿ���¼
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		Object obj = ServletActionContext.getRequest().getSession().getAttribute("adInfo");
		if(obj == null){
			System.out.println("�����������жϵ�infoΪ�գ��������Ҫ��¼");
			return "login";
		}else{
			System.out.println("����info:"+obj.toString());
			System.out.println("����ܲ���Ҫ��¼��");
			return arg0.invoke();
		}
	}

}
