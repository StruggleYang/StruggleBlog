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
	
	 	private File pageImg ; //具体上传文件的 引用 , 指向临时目录中的临时文件  
	    private String pageImgFileName ;  // 上传文件的名字 ,FileName 固定的写法  
	    private String pageImgContentType ; //上传文件的类型， ContentType 固定的写法  
	    
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
	 * 保存或修改文章
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("finally")
	public String savePage() {
		String res = "";
		try {
			int rs = 0; // 结果
			if(!"fileNull".equals(ifnull)){
				FileInputStream  fis=new FileInputStream(pageImg);
				String path=ServletActionContext.getServletContext().getRealPath("/")+"pageImg/"+pageImgFileName;
				FileOutputStream fos=new FileOutputStream(path);
				//定义一个保存、读取文件中字节的临时变量
				int  data=0;
				while((data=fis.read())!=-1){
					fos.write(data);
				}             
				fis.close();fos.close();
				// 取得图片路径
				String imgPath = "pageImg/"+pageImgFileName;
				// 将当前文章的标题作为图片的标题
				String imgTitle = this.page.getPageTitle();
				// 将图片路径和标题一并保存到图片地址对象
				imgAdds.setImgTitle(imgTitle);
				imgAdds.setImgAdd(imgPath);
				/*// 将图片地址对象保存到文章做为级联插入
				this.page.setImgAdds(imgAdds);*/
			} 
			if("save".equals(this.oper)) {
				 // 最后将文章保存到数据库
				  rs = service.savePage(this.page,imgAdds);
				  if(rs!=0) {
					  	ActionContext.getContext().getSession().put("msg", "文章《"+this.page.getPageTitle()+"》，发布成功！");
						res = "upLoadSuccess";
					} else {
						ActionContext.getContext().getSession().put("msg", "发布失败，图片格式错误或有可能重复发布了");
						res = "upLoadFail";
					}
			 } else if("update".equals(this.oper)) {
				// 最后将文章保存到数据库
				  rs = service.updatePage(this.page);
				  if(rs!=0) {
					  	ActionContext.getContext().getSession().put("msg","文章《"+this.page.getPageTitle()+"》，修改成功！");
						res = "upLoadSuccess";
					} else {
						ActionContext.getContext().getSession().put("msg", "修改失败");
						res = "updateFail";
					}
			 } else {
				 res = "upLoadFail";
			 }
		} catch (Exception e) {
			System.out.println("action-----"+e);
			 e.printStackTrace();
			ActionContext.getContext().getSession().put("msg", "发布失败，图片格式错误或有可能重复发布了");
			 res = "upLoadFail";
		} finally {
			return res;
		}
	}
	
	
	/**
	 * 删除文章操作，将状态置为da
	 * @throws IOException 
	 */
	public void deletePage() throws IOException {
		if("delete".equals(oper)) {
			boolean rs = service.deletePage(page.getPageId());
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			if(rs){
				ServletActionContext.getResponse().getWriter().print("操作成功！可前往回收站恢复！");
			} else {
				ServletActionContext.getResponse().getWriter().print("操作失败");
			}
		}
	}
}




















