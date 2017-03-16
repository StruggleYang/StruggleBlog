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
	 * 游客留言
	 * @param lw 留言对象
	 * @return 插入成功后的对应主键
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
	 * 获取所有的留言
	 * @param firstResult 开始记录
	 * @param maxResult 一次查询几条数据
	 * @return 留言对象集合
	 */
	public List<LeaveWord> getAllWord(int firstResult,int maxResult ){
		Session session = this.factory.getCurrentSession();
		Query query =  (Query) session.createQuery("from LeaveWord lw order by lw.lwTime desc");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}
	/**
	 * 查询所有留言总的页数
	 * @return 总的页数
	 */
	public int getAllRowCount(){
		Session session = this.factory.getCurrentSession();
		return session.createQuery("from LeaveWord lw order by lw.lwTime desc").list().size();
	}
	/**
	 * 评论提交
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
