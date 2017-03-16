package org.struggle_blog.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Type entity. @author MyEclipse Persistence Tools
 */

public class Type implements java.io.Serializable {

	// Fields

	private Integer TId;
	private String TName;
	private String TState;
	private Set pages = new HashSet(0);

	// Constructors

	/** default constructor */
	public Type() {
	}

	/** minimal constructor */
	public Type(String TName, String TState) {
		this.TName = TName;
		this.TState = TState;
	}

	/** full constructor */
	public Type(String TName, String TState, Set pages) {
		this.TName = TName;
		this.TState = TState;
		this.pages = pages;
	}

	// Property accessors

	public Integer getTId() {
		return this.TId;
	}

	public void setTId(Integer TId) {
		this.TId = TId;
	}

	public String getTName() {
		return this.TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}

	public String getTState() {
		return this.TState;
	}

	public void setTState(String TState) {
		this.TState = TState;
	}

	public Set getPages() {
		return this.pages;
	}

	public void setPages(Set pages) {
		this.pages = pages;
	}

}