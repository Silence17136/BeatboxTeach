package com.yangbang.beatboxteach.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.yangbang.beatboxteach.R;
import com.yangbang.beatboxteach.db.DataHelper;
import com.yangbang.beatboxteach.entity.Title;
import com.yangbang.beatboxteach.entity.Voice;

public class MyApplication extends Application {
	public static final String IS_FIRST_INSERT = "isFirstInsert";
	protected static MyApplication application;
	// public static DataHelper dataHelper;
	SharedPreferences preferences;
	public static DbUtils dbUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		dbUtils = DbUtils.create(this);
		initPreferences();
		initDatabase();
	}

	private void initPreferences() {
		preferences = getSharedPreferences("beatbox_shared", MODE_PRIVATE);
	}

	public static MyApplication getApp() {
		return application;
	}

	private void initDatabase() {
		// dataHelper = new DataHelper(this);
		// if (preferences.getBoolean(IS_FIRST_INSERT, true)) {
		// dataHelper.insertAllData();
		// preferences.edit().putBoolean(IS_FIRST_INSERT, false).commit();
		// }
		Log.i("YANGBANG", "DatabaseVersion-->"
				+ dbUtils.getDatabase().getVersion());
		if (dbUtils.getDatabase().getVersion() != preferences.getInt(
				"db_version", 0)) {
			dbUtils.configAllowTransaction(true);
			dbUtils.configDebug(true);
			String[] textTeachs = this.getResources().getStringArray(
					R.array.text_teach);
			for (int i = 0; i < textTeachs.length; i++) {
				Title title = new Title();
				title.setName(textTeachs[i]);
				title.setType(1);
				try {
					dbUtils.save(title);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String[] videoTeachs = this.getResources().getStringArray(
					R.array.video_teach);
			for (int i = 0; i < videoTeachs.length; i++) {
				Title title = new Title();
				title.setName(videoTeachs[i]);
				title.setType(2);
				try {
					dbUtils.save(title);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String[] voices1 = this.getResources().getStringArray(
					R.array.text_base_voice_teach);
			for (int i = 0; i < voices1.length; i++) {
				Voice voice = new Voice();
				voice.setName(voices1[i]);
				voice.setType(1);
				Title title = new Title();
				title.setId(1);
				voice.setTitle(title);
				try {
					dbUtils.save(voice);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String[] voices2 = this.getResources().getStringArray(
					R.array.text_special_voice_teach);
			for (int i = 0; i < voices2.length; i++) {
				Voice voice = new Voice();
				voice.setName(voices2[i]);
				voice.setType(1);
				Title title = new Title();
				title.setId(2);
				voice.setTitle(title);
				try {
					dbUtils.save(voice);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String[] voices3 = this.getResources().getStringArray(
					R.array.text_segment_teach);
			for (int i = 0; i < voices3.length; i++) {
				Voice voice = new Voice();
				voice.setName(voices3[i]);
				voice.setType(1);
				Title title = new Title();
				title.setId(3);
				voice.setTitle(title);
				try {
					dbUtils.save(voice);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			String[] voices4 = this.getResources().getStringArray(
					R.array.video_double_teach);
			for (int i = 0; i < voices4.length; i++) {
				Voice voice = new Voice();
				voice.setName(voices4[i]);
				voice.setType(2);
				Title title = new Title();
				title.setId(4);
				voice.setTitle(title);
				try {
					dbUtils.save(voice);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// dataHelper.insertAllData();
			preferences.edit()
					.putInt("db_version", dbUtils.getDatabase().getVersion())
					.commit();
		}
	}

}
