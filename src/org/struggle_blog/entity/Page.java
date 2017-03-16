package org.struggle_blog.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Page entity. @author MyEclipse Persistence Tools
 */

public class Page implements java.io.Serializable {

	// Fields

	private Integer pageId;
	private Type type;
	private ImgAdds imgAdds;
	private String pageAuthor;
	private String pageTitle;
	private Timestamp pageTime;
	private String pageRemark;
	private String pageWord;
	private Integer pageRead;
	private String pageState;
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Page() {
	}

	/** minimal constructor */
	public Page(Type type, ImgAdds imgAdds, String pageAuthor,
			String pageTitle, String pageRemark, String pageWord,
			Integer pageRead, String pageState) {
		this.type = type;
		this.imgAdds = imgAdds;
		this.pageAuthor = pageAuthor;
		this.pageTitle = pageTitle;
		this.pageRemark = pageRemark;
		this.pageWord = pageWord;
		this.pageRead = pageRead;
		this.pageState = pageState;
	}

	/** full constructor */
	public Page(Type type, ImgAdds imgAdds, String pageAuthor,
			String pageTitle, Timestamp pageTime, String pageRemark,
			String pageWord, Integer pageRead, String pageState, Set comments) {
		this.type = type;
		this.imgAdds = imgAdds;
		this.pageAuthor = pageAuthor;
		this.pageTitle = pageTitle;
		this.pageTime = pageTime;
		this.pageRemark = pageRemark;
		this.pageWord = pageWord;
		this.pageRead = pageRead;
		this.pageState = pageState;
		this.comments = comments;
	}

	// Property accessors

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public ImgAdds getImgAdds() {
		return this.imgAdds;
	}

	public void setImgAdds(ImgAdds imgAdds) {
		this.imgAdds = imgAdds;
	}

	public String getPageAuthor() {
		return this.pageAuthor;
	}

	public void setPageAuthor(String pageAuthor) {
		this.pageAuthor = pageAuthor;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Timestamp getPageTime() {
		return this.pageTime;
	}

	public void setPageTime(Timestamp pageTime) {
		this.pageTime = pageTime;
	}

	public String getPageRemark() {
		return this.pageRemark;
	}

	public void setPageRemark(String pageRemark) {
		this.pageRemark = pageRemark;
	}

	public String getPageWord() {
		return this.pageWord;
	}

	public void setPageWord(String pageWord) {
		this.pageWord = pageWord;
	}

	public Integer getPageRead() {
		return this.pageRead;
	}

	public void setPageRead(Integer pageRead) {
		this.pageRead = pageRead;
	}

	public String getPageState() {
		return this.pageState;
	}

	public void setPageState(String pageState) {
		this.pageState = pageState;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pageRemark == null) ? 0 : pageRemark.hashCode());
		result = prime * result + ((pageTitle == null) ? 0 : pageTitle.hashCode());
		result = prime * result + ((pageWord == null) ? 0 : pageWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (pageRemark == null) {
			if (other.pageRemark != null)
				return false;
		} else if (!pageRemark.equals(other.pageRemark))
			return false;
		if (pageTitle == null) {
			if (other.pageTitle != null)
				return false;
		} else if (!pageTitle.equals(other.pageTitle))
			return false;
		if (pageWord == null) {
			if (other.pageWord != null)
				return false;
		} else if (!pageWord.equals(other.pageWord))
			return false;
		return true;
	}

	
	
	
}