package org.struggle_blog.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.struggle_blog.entity.Admin;
import org.struggle_blog.entity.ImgAdds;
import org.struggle_blog.entity.Page;

public class AdminDao {
	/*springע��*/
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	/**
	 * ����Ա��½
	 * @param admin
	 * @return
	 */
	public List<Admin> adminLogin(Admin admin) {
		Session session = this.factory.getCurrentSession();
		Criteria c = session.createCriteria(Admin.class);
		c.add(Restrictions.eq("AName", admin.getAName()));
		c.add(Restrictions.eq("APwd", admin.getAPwd()));
		return c.list();
	}
	/**
	 * ������������
	 * @param page
	 * @return
	 */
	public int savePage(Page page,ImgAdds img){
		Session session = this.factory.getCurrentSession();
		try {
			page.setImgAdds(img);
			img.getPages().add(page);
			session.save(img);
			session.save(page);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	/*
	 * ��ȡ���������ݿ����������
	 */
	public List<Page> getLastPage() {
		Session session = this.factory.getCurrentSession();
		/*��ѯ���е�do(����״̬)������*/
		Query query =  (Query) session.createQuery("from Page p where p.pageState=?  order by p.pageId desc");
		query.setFirstResult(0);
		query.setMaxResults(1);
		query.setString(0,"do");
		return query.list();
	}
	
	/**
	 * �����޸�
	 * @param page
	 * @return
	 */
	public int updatePage(Page page) {
		Session session = this.factory.getCurrentSession();
		Page p = (Page) session.get(Page.class, page.getPageId());
		p.setPageTitle(page.getPageTitle());
		p.setPageRemark(page.getPageRemark());
		p.setType(page.getType());
		p.setPageWord(page.getPageWord());
		try {
		// �����ַΪ���򲻸���ͼƬ
		if(null != page.getImgAdds().getImgAdd()) {
			ImgAdds img = page.getImgAdds();
			p.setImgAdds(img);
			img.getPages().add(p);
			session.save(img);
		}
			session.update(p);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	/**
	 * ����ɾ��-״̬����
	 * @param pageId
	 * @return
	 */
	public boolean deletePage(Integer pageId) {
		Session session = this.factory.getCurrentSession();
		Page p = (Page) session.get(Page.class,pageId);
		p.setPageState("da");
		try {
			session.update(p);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}














