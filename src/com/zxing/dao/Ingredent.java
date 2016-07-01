package com.zxing.dao;

import java.io.Serializable;

public class Ingredent implements Serializable {
	private String id;
	private String menu_id;
	private String main_ingredent;
	private String other_ingredent;
	
	
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
	public String getMain_ingredent() {
		return main_ingredent;
	}
	public void setMain_ingredent(String main_ingredent) {
		this.main_ingredent = main_ingredent;
	}
	public String getOther_ingredent() {
		return other_ingredent;
	}
	public void setOther_ingredent(String other_ingredent) {
		this.other_ingredent = other_ingredent;
	}
	
}
