package org.struggle_blog.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ImgAdds entity. @author MyEclipse Persistence Tools
 */

public class ImgAdds implements java.io.Serializable {

	// Fields

	private Integer imgId;
	private String imgTitle;
	private String imgAdd;
	private Set pages = new HashSet(0);

	// Constructors

	/** default constructor */
	public ImgAdds() {
	}

	/** minimal constructor */
	public ImgAdds(String imgTitle, String imgAdd) {
		this.imgTitle = imgTitle;
		this.imgAdd = imgAdd;
	}

	/** full constructor */
	public ImgAdds(String imgTitle, String imgAdd, Set pages) {
		this.imgTitle = imgTitle;
		this.imgAdd = imgAdd;
		this.pages = pages;
	}

	// Property accessors

	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public String getImgTitle() {
		return this.imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getImgAdd() {
		return this.imgAdd;
	}

	public void setImgAdd(String imgAdd) {
		this.imgAdd = imgAdd;
	}

	public Set getPages() {
		return this.pages;
	}

	public void setPages(Set pages) {
		this.pages = pages;
	}

}