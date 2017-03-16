package org.struggle_blog.entity;

import java.sql.Timestamp;

/**
 * LeaveWord entity. @author MyEclipse Persistence Tools
 */

public class LeaveWord implements java.io.Serializable {

	// Fields

	private Integer lwId;
	private String lwName;
	private String lwEmail;
	private String lwUrl;
	private String lwWord;
	private Timestamp lwTime;
	private String lwReply;

	// Constructors

	/** default constructor */
	public LeaveWord() {
	}

	/** minimal constructor */
	public LeaveWord(String lwName, String lwEmail, String lwWord,
			Timestamp lwTime) {
		this.lwName = lwName;
		this.lwEmail = lwEmail;
		this.lwWord = lwWord;
		this.lwTime = lwTime;
	}

	/** full constructor */
	public LeaveWord(String lwName, String lwEmail, String lwUrl,
			String lwWord, Timestamp lwTime, String lwReply) {
		this.lwName = lwName;
		this.lwEmail = lwEmail;
		this.lwUrl = lwUrl;
		this.lwWord = lwWord;
		this.lwTime = lwTime;
		this.lwReply = lwReply;
	}

	// Property accessors

	public Integer getLwId() {
		return this.lwId;
	}

	public void setLwId(Integer lwId) {
		this.lwId = lwId;
	}

	public String getLwName() {
		return this.lwName;
	}

	public void setLwName(String lwName) {
		this.lwName = lwName;
	}

	public String getLwEmail() {
		return this.lwEmail;
	}

	public void setLwEmail(String lwEmail) {
		this.lwEmail = lwEmail;
	}

	public String getLwUrl() {
		return this.lwUrl;
	}

	public void setLwUrl(String lwUrl) {
		this.lwUrl = lwUrl;
	}

	public String getLwWord() {
		return this.lwWord;
	}

	public void setLwWord(String lwWord) {
		this.lwWord = lwWord;
	}

	public Timestamp getLwTime() {
		return this.lwTime;
	}

	public void setLwTime(Timestamp lwTime) {
		this.lwTime = lwTime;
	}

	public String getLwReply() {
		return this.lwReply;
	}

	public void setLwReply(String lwReply) {
		this.lwReply = lwReply;
	}

}