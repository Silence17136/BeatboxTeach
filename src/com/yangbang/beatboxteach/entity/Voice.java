package com.yangbang.beatboxteach.entity;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "voice")
public class Voice extends EntityBase implements Serializable {

	private static final String FIELD_ID = "voice_id";
	private static final String FIELD_FOREIGN_ID1 = "title_id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_TYPE = "type";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(column = "name")
	private String name;

	@Column(column = "type")
	private int type;

//	@Foreign(column = "titleId", foreign = "id")
//	public Title title;

	@Column(column = "titleName")
	private String titleName;


	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

//	public Title getTitle() {
//		return title;
//	}
//
//	public void setTitle(Title title) {
//		this.title = title;
//	}

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

	public static String getFieldForeignId1() {
		return FIELD_FOREIGN_ID1;
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
		return "title{id=" + getId() + ",titleId="
				+ ",name=" + getName() + ",type=" + getType() + "}";
	}

}
