package org.struggle_blog.entity;

/**
 * Link entity. @author MyEclipse Persistence Tools
 */

public class Link implements java.io.Serializable {

	// Fields

	private Integer linkId;
	private String linkName;
	private String linkUrl;
	private String linkState;

	// Constructors

	/** default constructor */
	public Link() {
	}

	/** full constructor */
	public Link(String linkName, String linkUrl, String linkState) {
		this.linkName = linkName;
		this.linkUrl = linkUrl;
		this.linkState = linkState;
	}

	// Property accessors

	public Integer getLinkId() {
		return this.linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public String getLinkName() {
		return this.linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkState() {
		return this.linkState;
	}

	public void setLinkState(String linkState) {
		this.linkState = linkState;
	}

}