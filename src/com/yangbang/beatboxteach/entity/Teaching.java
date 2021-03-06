package com.yangbang.beatboxteach.entity;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "teaching")
public class Teaching extends EntityBase implements Serializable {
	private static final String FIELD_ID = "teach_id";
	private static final String FIELD_FOREIGN_ID1 = "title_id";
	private static final String FIELD_FOREIGN_ID2 = "voice_id";
	private static final String FIELD_CONTENT = "content";
	private static final String FIELD_TYPE = "type";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private int teach_id;
	// private int title_id;
	// private int voice_id;
	@Column(column = "content")
	private String content;

	@Column(column = "type")
	private int type;

	// @Foreign(column = "titleId", foreign = "id")
	// public Title title;
	//
	// @Foreign(column = "voiceId", foreign = "id")
	// public Voice voice;

	@Column(column = "titleName")
	private String titleName;// 外标题名

	@Column(column = "voiceName")
	private String voiceName;// 音名

	@Column(column = "tidyName")
	private String tidyName;// 精简标题名

	public String getTidyName() {
		return tidyName;
	}

	public void setTidyName(String tidyName) {
		this.tidyName = tidyName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getVoiceName() {
		return voiceName;
	}

	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}

	// public Title getTitle() {
	// return title;
	// }
	//
	// public void setTitle(Title title) {
	// this.title = title;
	// }
	//
	// public Voice getVoice() {
	// return voice;
	// }
	//
	// public void setVoice(Voice voice) {
	// this.voice = voice;
	// }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public static String getFieldForeignId2() {
		return FIELD_FOREIGN_ID2;
	}

	public static String getFieldContent() {
		return FIELD_CONTENT;
	}

	public static String getFieldType() {
		return FIELD_TYPE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
