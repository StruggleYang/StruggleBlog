package org.struggle_blog.entity;

/**
 * BlogInfo entity. @author MyEclipse Persistence Tools
 */

public class BlogInfo implements java.io.Serializable {

	// Fields

	private String biId;
	private String biName;
	private String biHeader;
	private String biAutograph;
	private String biAbout;

	// Constructors

	/** default constructor */
	public BlogInfo() {
	}

	/** minimal constructor */
	public BlogInfo(String biName, String biHeader, String biAbout) {
		this.biName = biName;
		this.biHeader = biHeader;
		this.biAbout = biAbout;
	}

	/** full constructor */
	public BlogInfo(String biName, String biHeader, String biAutograph,
			String biAbout) {
		this.biName = biName;
		this.biHeader = biHeader;
		this.biAutograph = biAutograph;
		this.biAbout = biAbout;
	}

	// Property accessors

	public String getBiId() {
		return this.biId;
	}

	public void setBiId(String biId) {
		this.biId = biId;
	}

	public String getBiName() {
		return this.biName;
	}

	public void setBiName(String biName) {
		this.biName = biName;
	}

	public String getBiHeader() {
		return this.biHeader;
	}

	public void setBiHeader(String biHeader) {
		this.biHeader = biHeader;
	}

	public String getBiAutograph() {
		return this.biAutograph;
	}

	public void setBiAutograph(String biAutograph) {
		this.biAutograph = biAutograph;
	}

	public String getBiAbout() {
		return this.biAbout;
	}

	public void setBiAbout(String biAbout) {
		this.biAbout = biAbout;
	}

}