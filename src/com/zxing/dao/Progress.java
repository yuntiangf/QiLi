package com.zxing.dao;

import java.io.Serializable;

public class Progress implements Serializable {
	
	private String id;
	private String menu_id;
	private String step;
	private String imageRelativePath;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getImageRelativePath() {
		return imageRelativePath;
	}
	public void setImageRelativePath(String imageRelativePath) {
		this.imageRelativePath = imageRelativePath;
	}
	
}
