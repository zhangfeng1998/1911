package com.jk.model;

import java.util.List;

public class TreeEntity {
	
	 private Integer id;
	 
	 private String text;
	 
	 private String path;
	 
	 private Integer pid;
	 
	 private List<TreeEntity> nodes;
	 
	 private String selectable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<TreeEntity> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeEntity> nodes) {
		this.nodes = nodes;
	}

	public String getSelectable() {
		return selectable;
	}

	public void setSelectable(String selectable) {
		this.selectable = selectable;
	}

}
