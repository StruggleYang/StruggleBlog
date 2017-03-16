package org.struggle_blog.entity;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer AId;
	private String AName;
	private String APwd;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String AName, String APwd) {
		this.AName = AName;
		this.APwd = APwd;
	}

	// Property accessors

	public Integer getAId() {
		return this.AId;
	}

	public void setAId(Integer AId) {
		this.AId = AId;
	}

	public String getAName() {
		return this.AName;
	}

	public void setAName(String AName) {
		this.AName = AName;
	}

	public String getAPwd() {
		return this.APwd;
	}

	public void setAPwd(String APwd) {
		this.APwd = APwd;
	}

}