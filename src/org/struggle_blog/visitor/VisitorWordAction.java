package org.struggle_blog.visitor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.struggle_blog.entity.Comment;
import org.struggle_blog.entity.LeaveWord;

public class VisitorWordAction {
	private VisitorWordService service;

	private List<LeaveWord> words;

	public void setService(VisitorWordService service) {
		this.service = service;
	}

	public List<LeaveWord> getWords() {
		return words;
	}

	public void setWords(List<LeaveWord> words) {
		this.words = words;
	}

	private LeaveWord lw;

	public LeaveWord getLw() {
		return lw;
	}

	public void setLw(LeaveWord lw) {
		this.lw = lw;
	}

	private int firstResult;
	private int maxResult;
	
	
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	
	public int getFirstResult() {
		return firstResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	private Comment com;
	
	public Comment getCom() {
		return com;
	}

	public void setCom(Comment com) {
		this.com = com;
	}

	/**
	 * �ύ����
	 * 
	 * @throws IOException
	 */
	public void postWord() throws IOException {
		// �����ַΪ���ַ����򽫽�����Ϊnull���������հ�����
		if ("".equals(this.lw.getLwUrl())) {
			this.lw.setLwUrl(null);
		}
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		int rs = service.doWord(lw);
		if (rs != 0) { 
			// ���Գɹ�
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies.length != 0) {  
		          for (int i = 0; i < cookies.length; i++)  {  
		              if (cookies[i].getName().equals("visitorInfo")) {  
		                  Cookie cookie = new Cookie("","");//��ߵ���"",������null  
		                  cookie.setPath("/");//���óɸ�д��cookiesһ����  
		                  cookie.setMaxAge(0);  
		                  ServletActionContext.getResponse().addCookie(cookie);  
		              }  
		          } 
			}
			//  ���ο͵���Ϣ������cookie��20��
			Cookie visitorInfoCookie = new Cookie("visitorInfo",this.lw.getLwName()+"|----|"+this.lw.getLwEmail()+"|----|"+this.lw.getLwUrl());
			//  ����cookie�������������Ϊ20��
			visitorInfoCookie.setPath("/");// ���Ҫ����  
			visitorInfoCookie.setMaxAge(60*60*24*20);
			//  ��cookie�����ͻ���
			/*ServletActionContext.getResponse().addCookie(visitorInfoCookie);*/
			ServletActionContext.getResponse().getWriter().print("���:" + this.lw.getLwName() + ",���Գɹ�");
		} else {
			ServletActionContext.getResponse().getWriter().print("��Ǹ:" + this.lw.getLwName() + ",����ʧ��");
		}
	}

	/**
	 * ��ȡȫ������
	 * 
	 * @throws IOException
	 */
	public String getAllWord() throws IOException {
		this.words = service.getAllWord(firstResult,maxResult);
		return "success";
	}
	/**
	 * ��ȡcookie
	 * @throws IOException 
	 */
	public void getCookies() throws IOException {
		//  ��ȡ�ͻ���cookie
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
	    for(Cookie cookie : cookies){
	    	String vInfo = "";
	    	System.out.println(cookie.getName());
	        if(cookie.getName().equals("visitorInfo")) {
	        	vInfo = cookie.getValue();
	        }
	        ServletActionContext.getResponse().getWriter().print(vInfo);
	     } 
		
	}
	/**
	 * �ύ����
	 * @throws IOException 
	 */
	public void postComments() throws IOException {
		// �����ַΪ���ַ����򽫽�����Ϊnull���������հ�����
		if ("".equals(this.com.getCUrl())) {
			this.com.setCUrl(null);
		}
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		int rs = service.doComments(com);
		if (rs != 0) { // ���۳ɹ�
			ServletActionContext.getResponse().getWriter().print("���:" + this.com.getCUname() + ",���۳ɹ�");
		} else {
			ServletActionContext.getResponse().getWriter().print("��Ǹ:" + this.com.getCUname() + ",����ʧ��");
		}
	}
	
}


