package com.zxing.dao;

import java.io.Serializable;

public class Menu implements Serializable {
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
