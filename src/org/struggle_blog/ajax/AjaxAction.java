package org.struggle_blog.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.struggle_blog.entity.BlogInfo;
import org.struggle_blog.entity.Link;
import org.struggle_blog.entity.Page;
import org.struggle_blog.entity.Type;

import com.opensymphony.xwork2.ActionContext;

public class AjaxAction {
	private AjaxService service;

	public void setService(AjaxService service) {
		this.service = service;
	}
	
	private BlogInfo bloginfo;

	
	public BlogInfo getBloginfo() {
		return bloginfo;
	}


	public void setBloginfo(BlogInfo bloginfo) {
		this.bloginfo = bloginfo;
	}
	
	private List<Page> ps;
	
	

	public List<Page> getPs() {
		return ps;
	}


	public void setPs(List<Page> ps) {
		this.ps = ps;
	}
	private Page po;
	
	
	public Page getPo() {
		return po;
	}


	public void setPo(Page po) {
		this.po = po;
	}

	private int firstResult;
	private int maxResult;
	
	

	public int getFirstResult() {
		return firstResult;
	}


	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}


	public int getMaxResult() {
		return maxResult;
	}


	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	private List<Type> types;
	
	

	public List<Type> getTypes() {
		return types;
	}


	public void setTypes(List<Type> types) {
		this.types = types;
	}
	
	
	private String getType;
	private int thisPid;
	
	

	public String getGetType() {
		return getType;
	}


	public void setGetType(String getType) {
		this.getType = getType;
	}


	public int getThisPid() {
		return thisPid;
	}


	public void setThisPid(int thisPid) {
		this.thisPid = thisPid;
	}
	
	private String oper;  // 阅读或者修改文章的操作类型
	
	

	public String getOper() {
		return oper;
	}


	public void setOper(String oper) {
		this.oper = oper;
	}
	
	private String state;
	
	

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	private int tyId;
	
	public int getTyId() {
		return tyId;
	}

	public void setTyId(int tyId) {
		this.tyId = tyId;
	}

	private String like;
	
	

	public String getLike() {
		return like;
	}


	public void setLike(String like) {
		this.like = like;
	}
	
	private Type newT ;
	
	public Type getNewT() {
		return newT;
	}

	public void setNewT(Type newT) {
		this.newT = newT;
	}
	
	private Type tp;
	
	public Type getTp() {
		return tp;
	}

	public void setTp(Type tp) {
		this.tp = tp;
	}
	
	private List<Link> links;
	

	public List<Link> getLinks(){
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	private Link newLink;
	
	public Link getNewLink() {
		return newLink;
	}

	public void setNewLink(Link newLink) {
		this.newLink = newLink;
	}
	
	private  Link lk;
	
	public Link getLk() {
		return lk;
	}

	public void setLk(Link lk) {
		this.lk = lk;
	}
	
	private int cuLinkId;
	
	public int getCuLinkId() {
		return cuLinkId;
	}


	public void setCuLinkId(int cuLinkId) {
		this.cuLinkId = cuLinkId;
	}


	/**
	 * 获取博客信息
	 * @return
	 */
	public String getInfo() {
		this.bloginfo = service.getInfo();
		ActionContext.getContext().getSession().put("state", "vistior");
		return "getInfo";
	}
	/**
	 * 获得所有文章-分页
	 * @return
	 */
	public String getPages() {
		this.ps = service.getPages(firstResult, maxResult);
		return "getPages";
	}
	/**
	 * 获得所有文章-不分页
	 * @return
	 */
	public String getAllPages() {
		if(null==state) {
			state = "do";
		}
		this.ps = service.getPages(state, like);
		return "getPages";
	}
	
	/**
	 * 获取当前文章
	 * @return
	 */
	public String getPageOne() {
		this.po = service.getPageOne(po.getPageId());
		String re = "";
		if("view".equals(this.oper)){
			service.updateReadCount(this.po);
			re = "getPageOne";
		} else if ("update".equals(this.oper)){
			re = "updatePageOne";
		}
		this.po = service.getPageOne(po.getPageId());
		// 将文章内容转发给文章阅读界面/或者修改界面
		if(null!=this.po){
			ServletActionContext.getRequest().setAttribute("pageOne", this.po);
		} else {
			this.po.setPageTitle("-_-鬼知道这篇文章去哪儿了");
			ServletActionContext.getRequest().setAttribute("pageOne", this.po);
		}
		return re;
	}
	
	/**
	 * 获得所有类型
	 */
	public String getAllType() {
		this.types = service.getAllType(state,like);
		return "getTypes";
	}
	
	/**
	 * 添加类型
	 * @throws IOException 
	 */
	public void insertType() throws IOException {
		boolean rs = service.insertType(this.newT);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(rs) {
			ServletActionContext.getResponse().getWriter().print("添加成功！");
		} else{
			ServletActionContext.getResponse().getWriter().print("抱歉，添加失败");
		}
	}
	/**
	 * 修改类型
	 * @throws IOException
	 */
	public void updateType() throws IOException {
		boolean rs = service.updateType(tp);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(rs) {
			ServletActionContext.getResponse().getWriter().print("修改成功！");
		} else{
			ServletActionContext.getResponse().getWriter().print("抱歉，修改失败");
		}
	}
	
	/**
	 * 获得上一篇或下一篇文章
	 * @throws IOException 
	 */
	public void getOtherPage() throws IOException {
		int other = service.getOtherPage(this.getType,this.thisPid,state);
		if(other != 0) {
			ServletActionContext.getResponse().getWriter().print("http://localhost:8080/StruggleBlog/ajax_getPageOne.go?po.pageId="+other+"&oper=view");
		}else {
			ServletActionContext.getResponse().getWriter().print("no");
		}
	}
	
	/**
	 * 删除类型-往回收站
	 * @throws IOException
	 */
	public void deleteType() throws IOException {
		if("delete".equals(oper)){
			boolean rs = service.deleteType(tyId);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			if(rs){
				ServletActionContext.getResponse().getWriter().print("操作成功！可前往回收站恢复！");
			} else {
				ServletActionContext.getResponse().getWriter().print("操作失败");
			}
		}
	}
	
	/**
	 * 获得所有链接
	 */
	public String getAllLinks() {
		this.links = service.getAllLinks(state,like);
		return "getAllLinks";
	}
	/**
	 * 添加链接
	 * @throws IOException
	 */
	public void insertLink() throws IOException {
		boolean rs = service.insertLink(this.newLink);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(rs) {
			ServletActionContext.getResponse().getWriter().print("添加成功！");
		} else{
			ServletActionContext.getResponse().getWriter().print("抱歉，添加失败");
		}
	}
	
	/**
	 * 修改链接
	 * @throws IOException 
	 */
	public void updateLink() throws IOException {
		boolean rs = service.updateLink(lk);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(rs) {
			ServletActionContext.getResponse().getWriter().print("修改成功！");
		} else{
			ServletActionContext.getResponse().getWriter().print("抱歉，修改失败");
		}
	}
	
	/**
	 * 删除链接
	 * @throws IOException 
	 */
	public void deleteLink() throws IOException {
		boolean rs = service.deleteLink(this.cuLinkId);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(rs) {
			ServletActionContext.getResponse().getWriter().print("删除成功！可前往回收站恢复！");
		} else{
			ServletActionContext.getResponse().getWriter().print("抱歉，修改失败");
		}
	}
	
	/**
	 * 彻底删除链接
	 * @throws IOException 
	 */
	public void cleanLink() throws IOException {
		boolean rs = service.cleanLink(cuLinkId);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		if(rs) {
			ServletActionContext.getResponse().getWriter().print("删除成功!");
		} else{
			ServletActionContext.getResponse().getWriter().print("抱歉，修改失败");
		}
	}
}
