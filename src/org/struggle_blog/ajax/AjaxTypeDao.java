package org.struggle_blog.ajax;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.struggle_blog.entity.Type;

public class AjaxTypeDao {
	
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Type t;
	
	
	public Type getT() {
		return t;
	}

	public void setT(Type t) {
		this.t = t;
	}

	/**
	 * ��ȡ������������
	 * @return
	 */
	public List<Type> getAllType(String state,String like){
		Session session = this.factory.getCurrentSession();
		Query query = session.createQuery("from Type t where t.TState=? and t.TName like ?");
		query.setString(0,state);
		/***ģ����ѯ****/
		if("".equals(like)||null==like) {
			like = "";
			query.setString(1, "%"+like+"%");
		}else {
			query.setString(1, "%"+like+"%");
		}
		return query.list();
	}
	
	/**
	 * ɾ��ָ�������ͣ�����������Ϊda
	 */
	public boolean deleteType(int typeId) {
		Session session = this.factory.getCurrentSession();
		t = (Type) session.get(Type.class, typeId);
		t.setTState("da");
		try {
			session.update(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * �������
	 * @param newT
	 * @return
	 */
	public boolean insertType(Type newT) {
		Session session = factory.getCurrentSession();
		try {
			session.save(newT);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * �����޸�
	 * @param tp
	 * @return
	 */
	public boolean updateType(Type tp) {
		Session session = factory.getCurrentSession();
		Type t =  (Type)session.get(Type.class, tp.getTId());
		t.setTName(tp.getTName());
		try {
			session.update(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
