package org.struggle_blog.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.struggle_blog.entity.ImgAdds;
import org.struggle_blog.entity.Page;

import com.opensymphony.xwork2.ActionContext;

public class SavePageAction {
	private AdminService service;
	
	 	private File pageImg ; //�����ϴ��ļ��� ���� , ָ����ʱĿ¼�е���ʱ�ļ�  
	    private String pageImgFileName ;  // �ϴ��ļ������� ,FileName �̶���д��  
	    private String pageImgContentType ; //�ϴ��ļ������ͣ� ContentType �̶���д��  
	    
	    private ImgAdds imgAdds;
	    
	    public ImgAdds getImgAdds() {
			return imgAdds;
		}

		public void setImgAdds(ImgAdds imgAdds) {
			this.imgAdds = imgAdds;
		}

		
	    public File getPageImg() {
			return pageImg;
		}

		public void setPageImg(File pageImg) {
			this.pageImg = pageImg;
		}

		
	      
	    public String getPageImgFileName() {
			return pageImgFileName;
		}

		public void setPageImgFileName(String pageImgFileName) {
			this.pageImgFileName = pageImgFileName;
		}

		public String getPageImgContentType() {
			return pageImgContentType;
		}

		public void setPageImgContentType(String pageImgContentType) {
			this.pageImgContentType = pageImgContentType;
		}

	
	public void setService(AdminService service) {
		this.service = service;
	}
	
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	private String oper;
	
	
	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}
	
	private String ifnull;
	
	
	public String getIfnull() {
		return ifnull;
	}

	public void setIfnull(String ifnull) {
		this.ifnull = ifnull;
	}

	/**
	 * ������޸�����
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("finally")
	public String savePage() {
		String res = "";
		try {
			int rs = 0; // ���
			if(!"fileNull".equals(ifnull)){
				FileInputStream  fis=new FileInputStream(pageImg);
				String path=ServletActionContext.getServletContext().getRealPath("/")+"pageImg/"+pageImgFileName;
				FileOutputStream fos=new FileOutputStream(path);
				//����һ�����桢��ȡ�ļ����ֽڵ���ʱ����
				int  data=0;
				while((data=fis.read())!=-1){
					fos.write(data);
				}             
				fis.close();fos.close();
				// ȡ��ͼƬ·��
				String imgPath = "pageImg/"+pageImgFileName;
				// ����ǰ���µı�����ΪͼƬ�ı���
				String imgTitle = this.page.getPageTitle();
				// ��ͼƬ·���ͱ���һ�����浽ͼƬ��ַ����
				imgAdds.setImgTitle(imgTitle);
				imgAdds.setImgAdd(imgPath);
				/*// ��ͼƬ��ַ���󱣴浽������Ϊ��������
				this.page.setImgAdds(imgAdds);*/
			} 
			if("save".equals(this.oper)) {
				 // ������±��浽���ݿ�
				  rs = service.savePage(this.page,imgAdds);
				  if(rs!=0) {
					  	ActionContext.getContext().getSession().put("msg", "���¡�"+this.page.getPageTitle()+"���������ɹ���");
						res = "upLoadSuccess";
					} else {
						ActionContext.getContext().getSession().put("msg", "����ʧ�ܣ�ͼƬ��ʽ������п����ظ�������");
						res = "upLoadFail";
					}
			 } else if("update".equals(this.oper)) {
				// ������±��浽���ݿ�
				  rs = service.updatePage(this.page);
				  if(rs!=0) {
					  	ActionContext.getContext().getSession().put("msg","���¡�"+this.page.getPageTitle()+"�����޸ĳɹ���");
						res = "upLoadSuccess";
					} else {
						ActionContext.getContext().getSession().put("msg", "�޸�ʧ��");
						res = "updateFail";
					}
			 } else {
				 res = "upLoadFail";
			 }
		} catch (Exception e) {
			System.out.println("action-----"+e);
			 e.printStackTrace();
			ActionContext.getContext().getSession().put("msg", "����ʧ�ܣ�ͼƬ��ʽ������п����ظ�������");
			 res = "upLoadFail";
		} finally {
			return res;
		}
	}
	
	
	/**
	 * ɾ�����²�������״̬��Ϊda
	 * @throws IOException 
	 */
	public void deletePage() throws IOException {
		if("delete".equals(oper)) {
			boolean rs = service.deletePage(page.getPageId());
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			if(rs){
				ServletActionContext.getResponse().getWriter().print("�����ɹ�����ǰ������վ�ָ���");
			} else {
				ServletActionContext.getResponse().getWriter().print("����ʧ��");
			}
		}
	}
}




















