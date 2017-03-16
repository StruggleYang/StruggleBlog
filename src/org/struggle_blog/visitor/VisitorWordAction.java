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
	 * 提交留言
	 * 
	 * @throws IOException
	 */
	public void postWord() throws IOException {
		// 如果网址为空字符串则将将其置为null，避免插入空白数据
		if ("".equals(this.lw.getLwUrl())) {
			this.lw.setLwUrl(null);
		}
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		int rs = service.doWord(lw);
		if (rs != 0) { 
			// 留言成功
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies.length != 0) {  
		          for (int i = 0; i < cookies.length; i++)  {  
		              if (cookies[i].getName().equals("visitorInfo")) {  
		                  Cookie cookie = new Cookie("","");//这边得用"",不能用null  
		                  cookie.setPath("/");//设置成跟写入cookies一样的  
		                  cookie.setMaxAge(0);  
		                  ServletActionContext.getResponse().addCookie(cookie);  
		              }  
		          } 
			}
			//  将游客的信息保存在cookie中20天
			Cookie visitorInfoCookie = new Cookie("visitorInfo",this.lw.getLwName()+"|----|"+this.lw.getLwEmail()+"|----|"+this.lw.getLwUrl());
			//  设置cookie的最大生命周期为20天
			visitorInfoCookie.setPath("/");// 这个要设置  
			visitorInfoCookie.setMaxAge(60*60*24*20);
			//  将cookie发给客户端
			/*ServletActionContext.getResponse().addCookie(visitorInfoCookie);*/
			ServletActionContext.getResponse().getWriter().print("你好:" + this.lw.getLwName() + ",留言成功");
		} else {
			ServletActionContext.getResponse().getWriter().print("抱歉:" + this.lw.getLwName() + ",留言失败");
		}
	}

	/**
	 * 读取全部留言
	 * 
	 * @throws IOException
	 */
	public String getAllWord() throws IOException {
		this.words = service.getAllWord(firstResult,maxResult);
		return "success";
	}
	/**
	 * 获取cookie
	 * @throws IOException 
	 */
	public void getCookies() throws IOException {
		//  获取客户端cookie
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
	 * 提交评论
	 * @throws IOException 
	 */
	public void postComments() throws IOException {
		// 如果网址为空字符串则将将其置为null，避免插入空白数据
		if ("".equals(this.com.getCUrl())) {
			this.com.setCUrl(null);
		}
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		int rs = service.doComments(com);
		if (rs != 0) { // 评论成功
			ServletActionContext.getResponse().getWriter().print("你好:" + this.com.getCUname() + ",评论成功");
		} else {
			ServletActionContext.getResponse().getWriter().print("抱歉:" + this.com.getCUname() + ",评论失败");
		}
	}
	
}


