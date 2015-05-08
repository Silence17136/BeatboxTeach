package com.yangbang.beatboxteach.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.yangbang.beatboxteach.db.DataHelper;

public class MyApplication extends Application {
	public static final String IS_FIRST_INSERT = "isFirstInsert";
	protected static MyApplication application;
	public static DataHelper dataHelper;
	SharedPreferences preferences;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
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
		dataHelper = new DataHelper(this);
		// if (preferences.getBoolean(IS_FIRST_INSERT, true)) {
		// dataHelper.insertAllData();
		// preferences.edit().putBoolean(IS_FIRST_INSERT, false).commit();
		// }
		if (DataHelper.DB_VERSION != preferences.getInt("db_version", 1)) {
			dataHelper.insertAllData();
			preferences.edit().putInt("db_version", DataHelper.DB_VERSION)
					.commit();
		}
	}

}
