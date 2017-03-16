package org.struggle_blog.ajax;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.struggle_blog.entity.BlogInfo;
import org.struggle_blog.entity.Link;
import org.struggle_blog.entity.Page;
import org.struggle_blog.entity.Type;

public class AjaxDao {
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	/**
	 * 
	 * @return 博客信息对象
	 */
	public BlogInfo getInfo() {
		Session session =  factory.getCurrentSession();
		List<BlogInfo> list = session.createQuery("from BlogInfo").list();
		return list.get(0);
	}
	
	/**
	 * 获取所有的链接
	 * @param state
	 * @return
	 */
	public List<Link> getAllLinks(String state,String like) {
		Session session =  factory.getCurrentSession();
		Query query = session.createQuery("from Link l where l.linkState = ? and (l.linkName like ? or l.linkUrl like ?)");
		query.setString(0, state);
		query.setString(1, "%"+like+"%");
		query.setString(2, "%"+like+"%");
		return query.list();
	}
	/**
	 * 添加链接
	 * @param newLink
	 * @return
	 */
	public boolean insertLink(Link newLink) {
		Session session =  factory.getCurrentSession();
		System.out.println("哈哈"+newLink.getLinkName()+newLink.getLinkUrl());
		try {
			session.save(newLink);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 修改链接
	 */
	public  boolean updateLink(Link lk) {
		Session session = factory.getCurrentSession();
		Link l = (Link) session.get(Link.class, lk.getLinkId());
		l.setLinkName(lk.getLinkName());
		l.setLinkUrl(lk.getLinkUrl());
		try {
			session.update(l);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 删除链接,置状态为da
	 */
	public boolean deleteLink(int cuLinkId) {
		Session session = factory.getCurrentSession();
		System.out.println(cuLinkId);
		Link l = (Link) session.get(Link.class, cuLinkId);
		l.setLinkState("da");
		try {
			session.update(l);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 彻底删除链接
	 */
	public boolean cleanLink(int lId) {
		Session session = factory.getCurrentSession();
		Link l = (Link) session.get(Link.class, lId);
		try {
			session.delete(l);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
