package com.yangbang.beatboxteach.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yangbang.beatboxteach.entity.Teaching;
import com.yangbang.beatboxteach.entity.Title;
import com.yangbang.beatboxteach.entity.Voice;

public class SqliteHelper extends SQLiteOpenHelper {
	public static final String TB_NAME_TITLE = "Title";
	public static final String TB_NAME_VOICE = "Voice";
	public static final String TB_NAME_TEACHING = "Teaching";

	/**
	 * sqlite数据库帮助类
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            数据库名
	 * @param factory
	 * @param version
	 *            数据库版本
	 */
	public SqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_TITLE + "("
				+ Title.getFieldId() + " integer primary key autoincrement,"
				+ Title.getFieldName() + " varchar," + Title.getFieldType()
				+ " integer" + ")");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_VOICE + "("
				+ Voice.getFieldId() + " integer primary key autoincrement,"
				+ Voice.getFieldForeignId1() + " integer,"
				+ Voice.getFieldName() + " varchar," + Voice.getFieldType()
				+ " integer" + ")");
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_TEACHING + "("
				+ Teaching.getFieldId() + " integer primary key autoincrement,"
				+ Teaching.getFieldForeignId1() + " integer,"
				+ Teaching.getFieldForeignId2() + " integer,"
				+ Teaching.getFieldContent() + " varchar,"
				+ Teaching.getFieldType() + " integer" + ")");
		Log.e("Database", "onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_TITLE);
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_VOICE);
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_TEACHING);
		onCreate(db);
		Log.e("Database", "onUpgrade");
	}

}
