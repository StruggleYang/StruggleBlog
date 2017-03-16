package org.struggle_blog.entity;

import java.sql.Timestamp;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer CId;
	private Page page;
	private String CUname;
	private String CEmail;
	private String CUrl;
	private String CComment;
	private Timestamp CTime;
	private String CReply;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(Page page, String CUname, String CEmail, String CComment,
			Timestamp CTime) {
		this.page = page;
		this.CUname = CUname;
		this.CEmail = CEmail;
		this.CComment = CComment;
		this.CTime = CTime;
	}
	
	

	public Comment(Page page, String cUname, String cEmail, String cUrl, String cComment, Timestamp cTime,
			String cReply) {
		super();
		this.page = page;
		CUname = cUname;
		CEmail = cEmail;
		CUrl = cUrl;
		CComment = cComment;
		CTime = cTime;
		CReply = cReply;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getCUname() {
		return this.CUname;
	}

	public void setCUname(String CUname) {
		this.CUname = CUname;
	}

	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}
	
	public String getCUrl() {
		return CUrl;
	}

	public void setCUrl(String cUrl) {
		CUrl = cUrl;
	}

	public String getCComment() {
		return this.CComment;
	}

	public void setCComment(String CComment) {
		this.CComment = CComment;
	}

	public Timestamp getCTime() {
		return this.CTime;
	}

	public void setCTime(Timestamp CTime) {
		this.CTime = CTime;
	}

	public String getCReply() {
		return CReply;
	}

	public void setCReply(String cReply) {
		CReply = cReply;
	}
	
	

}