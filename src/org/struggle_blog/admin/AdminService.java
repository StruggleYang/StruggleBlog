package org.struggle_blog.admin;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.struggle_blog.entity.Admin;
import org.struggle_blog.entity.ImgAdds;
import org.struggle_blog.entity.Page;

@Transactional
public class AdminService {
	/*springע��*/
	private AdminDao dao;

	public void setDao(AdminDao dao) {
		this.dao = dao;
	}
	
	/**
	 * ��½
	 * @param admin
	 * @return
	 */
	public Admin adminLogin(Admin admin) {
		List<Admin> list = dao.adminLogin(admin);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	
	private Page getLastPage() {
		return  dao.getLastPage().get(0);
	}
	/**
	 * ���·���
	 * @param page
	 * @return
	 */
	public int savePage(Page page,ImgAdds img) {
		return dao.savePage(page,img);
	}
	
	/**
	 * �����޸�
	 * @param page
	 * @return
	 */
	public int updatePage(Page page) {
		return dao.updatePage(page);
	}
	
	/**
	 * ����ɾ����״̬����
	 * @param pageId
	 * @return
	 */
	public boolean deletePage(Integer pageId) {
		return dao.deletePage(pageId);
	}
	
}


































