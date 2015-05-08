package com.yangbang.beatboxteach.entity;

import java.io.Serializable;

public class Title implements Serializable {
	private static final String FIELD_ID = "title_id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_TYPE = "type";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int title_id;
	private String name;
	private int type;// 1代表文字教学、2代表视频教学

	public int getTitle_id() {
		return title_id;
	}

	public void setTitle_id(int title_id) {
		this.title_id = title_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static String getFieldId() {
		return FIELD_ID;
	}

	public static String getFieldName() {
		return FIELD_NAME;
	}

	public static String getFieldType() {
		return FIELD_TYPE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
