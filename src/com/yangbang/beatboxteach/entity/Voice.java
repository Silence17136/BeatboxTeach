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
	// private int voice_id;
	// private int title_id;
	@Column(column = "name")
	private String name;

	@Column(column = "type")
	private int type;

	@Foreign(column = "titleId", foreign = "id")
	public Title title;

	// public int getVoice_id() {
	// return voice_id;
	// }
	//
	// public void setVoice_id(int voice_id) {
	// this.voice_id = voice_id;
	// }
	//
	// public int getTitle_id() {
	// return title_id;
	// }
	//
	// public void setTitle_id(int title_id) {
	// this.title_id = title_id;
	// }

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
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
		return "title{id=" + getId() + ",titleId=" + getTitle().getId()
				+ ",name=" + getName() + ",type=" + getType() + "}";
	}

}
