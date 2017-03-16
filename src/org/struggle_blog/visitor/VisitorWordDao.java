package org.struggle_blog.visitor;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.struggle_blog.entity.Comment;
import org.struggle_blog.entity.LeaveWord;

public class VisitorWordDao {
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	/**
	 * �ο�����
	 * @param lw ���Զ���
	 * @return ����ɹ���Ķ�Ӧ����
	 */
	public int doWord(LeaveWord lw) {
		Session session = this.factory.getCurrentSession();
		int rs = 0;
		try {
			session.save(lw);
			rs = 1;
		} catch (Exception e) {
			rs =  0;
		} finally {
			return rs;
		}
	}
	/**
	 * ��ȡ���е�����
	 * @param firstResult ��ʼ��¼
	 * @param maxResult һ�β�ѯ��������
	 * @return ���Զ��󼯺�
	 */
	public List<LeaveWord> getAllWord(int firstResult,int maxResult ){
		Session session = this.factory.getCurrentSession();
		Query query =  (Query) session.createQuery("from LeaveWord lw order by lw.lwTime desc");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}
	/**
	 * ��ѯ���������ܵ�ҳ��
	 * @return �ܵ�ҳ��
	 */
	public int getAllRowCount(){
		Session session = this.factory.getCurrentSession();
		return session.createQuery("from LeaveWord lw order by lw.lwTime desc").list().size();
	}
	/**
	 * �����ύ
	 * @param com
	 * @return
	 */
	public int doComments(Comment com) {
		Session session = this.factory.getCurrentSession();
		int rs = 0;
		try {
			session.save(com);
			rs = 1;
		} catch (Exception e) {
			rs =  0;
		} finally {
			return rs;
		}
	}
}
