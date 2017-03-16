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
	 * ��ȡ�������²���ָ����ѯ������
	 * @return
	 */
	public List<Page> getPages(int firstResult,int maxResult) {
		Session session = this.factory.getCurrentSession();
		/*��ѯ���е�do(����״̬)������*/
		Query query =  (Query) session.createQuery("from Page p where p.pageState=?  order by p.pageTime desc");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		query.setString(0,"do");
		return query.list();
	}
	/**
	 * ��ȡһƪ���µ�����
	 * @param pageId
	 * @return
	 */
	public List<Page>  getPageOne(int pageId) {
		Session session = this.factory.getCurrentSession();
		/*��ѯ���е�do(����״̬)������*/
		Query query =  (Query) session.createQuery("from Page p where p.pageId=?");
		query.setInteger(0,pageId);
		return query.list();
	}
	/**
	 * �����Ķ���
	 * @param page
	 */
	public void updateReadCount(Page page) {
		Session session = this.factory.getCurrentSession();
		session.update(page);
	}
	/**
	 * ��ѯ�������£��ҵ�������
	 * @return
	 */
	public List<Page> getPages(String state,String like) {
		Session session = this.factory.getCurrentSession();
		/*��ѯ���е�do(����״̬)������*/
		Query query =  (Query) session.createQuery("from Page p where p.pageState=? and (p.pageTitle like ? or p.type.TName like ? or p.pageRemark like ? or p.pageWord like ?) order by p.pageTime desc");
		query.setString(0,state);
		/***ģ����ѯ****/
		query.setString(1, "%"+like+"%");
		query.setString(2, "%"+like+"%");
		query.setString(3, "%"+like+"%");
		query.setString(4, "%"+like+"%");
		
		return query.list();
	}
	
}
