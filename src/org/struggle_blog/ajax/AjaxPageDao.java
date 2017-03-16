package org.struggle_blog.ajax;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.struggle_blog.entity.Page;

public class AjaxPageDao {
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	/**
	 * 获取所有文章并且指定查询的区间
	 * @return
	 */
	public List<Page> getPages(int firstResult,int maxResult) {
		Session session = this.factory.getCurrentSession();
		/*查询所有的do(可用状态)的文章*/
		Query query =  (Query) session.createQuery("from Page p where p.pageState=?  order by p.pageTime desc");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		query.setString(0,"do");
		return query.list();
	}
	/**
	 * 获取一篇文章的内容
	 * @param pageId
	 * @return
	 */
	public List<Page>  getPageOne(int pageId) {
		Session session = this.factory.getCurrentSession();
		/*查询所有的do(可用状态)的文章*/
		Query query =  (Query) session.createQuery("from Page p where p.pageId=?");
		query.setInteger(0,pageId);
		return query.list();
	}
	/**
	 * 更新阅读量
	 * @param page
	 */
	public void updateReadCount(Page page) {
		Session session = this.factory.getCurrentSession();
		session.update(page);
	}
	/**
	 * 查询所有文章，且倒序排列
	 * @return
	 */
	public List<Page> getPages(String state,String like) {
		Session session = this.factory.getCurrentSession();
		/*查询所有的do(可用状态)的文章*/
		Query query =  (Query) session.createQuery("from Page p where p.pageState=? and (p.pageTitle like ? or p.type.TName like ? or p.pageRemark like ? or p.pageWord like ?) order by p.pageTime desc");
		query.setString(0,state);
		/***模糊查询****/
		query.setString(1, "%"+like+"%");
		query.setString(2, "%"+like+"%");
		query.setString(3, "%"+like+"%");
		query.setString(4, "%"+like+"%");
		
		return query.list();
	}
	
}
