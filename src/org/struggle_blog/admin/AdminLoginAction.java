package org.struggle_blog.admin;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.struggle_blog.entity.Admin;
import org.struggle_blog.entity.Page;

public class AdminLoginAction  {
	private AdminService service;
	
	public void setService(AdminService service) {
		this.service = service;
	}

	private Admin admin;
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public Admin getAdmin() {
		return admin;
	}
	/**
	 * ����Ա��¼
	 * @return
	 */
	public String login() {
		Admin ad = this.service.adminLogin(admin);
		admin = null;
		if(ad != null){
			/*��Ϊ�����¼�ɹ�*/
			// �����������
			ServletActionContext.getRequest().getSession().setAttribute("adInfo",ad.getAName());
			return "success";
		}else {
			return "login";
		}
	}
}
