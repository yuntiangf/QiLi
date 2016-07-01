package com.zxing.dao;

import java.io.Serializable;

public class Food implements Serializable{

	private static final long serialVersionUID = 1157770412148539331L;
	private String id;
	private String name;
	private String tip;
	private String imageRelativePath;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getImageRelativePath() {
		return imageRelativePath;
	}
	public void setImageRelativePath(String imageRelativePath) {
		this.imageRelativePath = imageRelativePath;
	}

	
}
