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
	 * ��ȡ���͸�����Ϣ
	 * @return ������Ϣ����
	 */
	public BlogInfo getInfo() {
		return dao.getInfo();
	}
	
	/**
	 * ��ȡ��������
	 * @return 
	 */
	public List<Page> getPages(int firstResult,int maxResult) {
		return pagedao.getPages(firstResult,maxResult);
	}
	
	/**
	 * ��ȡ�������£�����ҳ
	 * @param state
	 * @return
	 */
	public List<Page> getPages(String state,String like) {
		return pagedao.getPages(state,like);
	}
	/**
	 * ��ȡ������������
	 * @return
	 */
	public List<Type> getAllType(String state,String like){
		return typedao.getAllType(state,like);
	}

	public Page getPageOne(int pageId) {
		return pagedao.getPageOne(pageId).get(0);
	}
	
	/**
	 * ÿ��ѯһ�ζ�Ӧ�����������Ķ�����1
	 * @param pageRead
	 * @return
	 */

	public void updateReadCount(Page page) {
		int readCount = page.getPageRead()+1;
		page.setPageRead(readCount);
		pagedao.updateReadCount(page);
	}
	
	/*�ж����µ�����ƪ*/
	public int getOtherPage(String getType, int thisPid,String state) {
		/*��ѯ���е���Ч����*/
		List<Page> allPages =  pagedao.getPages(state,"");
		int rs = 0;
		for(int i=0;i < allPages.size();i++) {
			//  �жϵ�����ͼ����µ�id ����=0��ֹԽ��
			if(("up".equals(getType) && allPages.get(i).getPageId() == thisPid) && i != 0 ){
				rs = allPages.get(i-1).getPageId();
				break;
			} else if (("next".equals(getType) && allPages.get(i).getPageId() == thisPid) && (i != allPages.size()-1)){
				rs = allPages.get(i+1).getPageId();
				break;
			} 
		}
		// ����ѯ��������id����ǰ��
		return rs;
	}
	
	/**
	 * ɾ��ָ�����ͣ�����״̬��Ϊda
	 */
	public boolean deleteType(int typeId) {
		return typedao.deleteType(typeId);
	}
	
	/**
	 * �������
	 * @param newT
	 * @return
	 */
	public boolean insertType(Type newT) {
		return typedao.insertType(newT);
	}
	/**
	 * �޸�����
	 * @param tp
	 * @return
	 */
	public boolean updateType(Type tp) {
		return typedao.updateType(tp);
	}
	/**
	 * �������
	 * @param state
	 * @return
	 */
	public List<Link> getAllLinks(String state,String like) {
		return dao.getAllLinks(state,like);
	}
	
	/**
	 * �������
	 * @param newLink
	 * @return
	 */
	public boolean insertLink(Link newLink) {
		
		return dao.insertLink(newLink);
	}
	
	/**
	 * �޸�����
	 */
	public  boolean updateLink(Link lk) {
		return dao.updateLink(lk);
	}
	/**
	 * ɾ������
	 * @param lId
	 * @return
	 */
	public boolean deleteLink(int cuLinkId) {
		return dao.deleteLink(cuLinkId);
	}
	
	/**
	 * ����ɾ������
	 */
	public boolean cleanLink(int lId) {
		return dao.cleanLink(lId);
	}
}
