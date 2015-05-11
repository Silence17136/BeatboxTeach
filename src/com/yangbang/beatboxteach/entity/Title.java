package com.yangbang.beatboxteach.entity;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "title")
public class Title extends EntityBase implements Serializable {
	private static final String FIELD_ID = "title_id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_TYPE = "type";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private int title_id;
	@Column(column = "name")
	private String name;

	@Column(column = "type")
	private int type;// 1�������ֽ�ѧ��2������Ƶ��ѧ

	// public int getTitle_id() {
	// return title_id;
	// }
	//
	// public void setTitle_id(int title_id) {
	// this.title_id = title_id;
	// }

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

	@Override
	public String toString() {
		return "title{id=" + getId() + ",name=" + getName() + ",type="
				+ getType() + "}";
	}

}
