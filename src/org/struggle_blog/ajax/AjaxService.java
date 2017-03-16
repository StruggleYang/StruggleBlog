package org.struggle_blog.ajax;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.struggle_blog.entity.BlogInfo;
import org.struggle_blog.entity.Link;
import org.struggle_blog.entity.Page;
import org.struggle_blog.entity.Type;

@Transactional
public class AjaxService {
	private AjaxDao dao;

	public void setDao(AjaxDao dao) {
		this.dao = dao;
	}
	
	private AjaxTypeDao typedao;
	
	
	
	public void setTypedao(AjaxTypeDao typedao) {
		this.typedao = typedao;
	}
	
	private AjaxPageDao pagedao;
	
	

	public void setPagedao(AjaxPageDao pagedao) {
		this.pagedao = pagedao;
	}

	/**
	 * 获取博客个人信息
	 * @return 博客信息对象
	 */
	public BlogInfo getInfo() {
		return dao.getInfo();
	}
	
	/**
	 * 获取博客文章
	 * @return 
	 */
	public List<Page> getPages(int firstResult,int maxResult) {
		return pagedao.getPages(firstResult,maxResult);
	}
	
	/**
	 * 获取所有文章，不分页
	 * @param state
	 * @return
	 */
	public List<Page> getPages(String state,String like) {
		return pagedao.getPages(state,like);
	}
	/**
	 * 获取所有文章类型
	 * @return
	 */
	public List<Type> getAllType(String state,String like){
		return typedao.getAllType(state,like);
	}

	public Page getPageOne(int pageId) {
		return pagedao.getPageOne(pageId).get(0);
	}
	
	/**
	 * 每查询一次对应文章则将文章阅读量加1
	 * @param pageRead
	 * @return
	 */

	public void updateReadCount(Page page) {
		int readCount = page.getPageRead()+1;
		page.setPageRead(readCount);
		pagedao.updateReadCount(page);
	}
	
	/*判断文章的上下篇*/
	public int getOtherPage(String getType, int thisPid,String state) {
		/*查询所有的有效文章*/
		List<Page> allPages =  pagedao.getPages(state,"");
		int rs = 0;
		for(int i=0;i < allPages.size();i++) {
			//  判断点击类型及文章的id ！！=0防止越界
			if(("up".equals(getType) && allPages.get(i).getPageId() == thisPid) && i != 0 ){
				rs = allPages.get(i-1).getPageId();
				break;
			} else if (("next".equals(getType) && allPages.get(i).getPageId() == thisPid) && (i != allPages.size()-1)){
				rs = allPages.get(i+1).getPageId();
				break;
			} 
		}
		// 将查询到的文章id传给前端
		return rs;
	}
	
	/**
	 * 删除指定类型，将其状态置为da
	 */
	public boolean deleteType(int typeId) {
		return typedao.deleteType(typeId);
	}
	
	/**
	 * 添加类型
	 * @param newT
	 * @return
	 */
	public boolean insertType(Type newT) {
		return typedao.insertType(newT);
	}
	/**
	 * 修改类型
	 * @param tp
	 * @return
	 */
	public boolean updateType(Type tp) {
		return typedao.updateType(tp);
	}
	/**
	 * 获得链接
	 * @param state
	 * @return
	 */
	public List<Link> getAllLinks(String state,String like) {
		return dao.getAllLinks(state,like);
	}
	
	/**
	 * 添加链接
	 * @param newLink
	 * @return
	 */
	public boolean insertLink(Link newLink) {
		
		return dao.insertLink(newLink);
	}
	
	/**
	 * 修改链接
	 */
	public  boolean updateLink(Link lk) {
		return dao.updateLink(lk);
	}
	/**
	 * 删除链接
	 * @param lId
	 * @return
	 */
	public boolean deleteLink(int cuLinkId) {
		return dao.deleteLink(cuLinkId);
	}
	
	/**
	 * 彻底删除链接
	 */
	public boolean cleanLink(int lId) {
		return dao.cleanLink(lId);
	}
}
