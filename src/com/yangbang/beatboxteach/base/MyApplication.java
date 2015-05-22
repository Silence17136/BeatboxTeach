package com.yangbang.beatboxteach.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.lidroid.xutils.DbUtils;
import com.yangbang.beatboxteach.util.InsertData;

public class MyApplication extends Application {
	public static final String IS_FIRST_INSERT = "isFirstInsert";
	protected static MyApplication application;
	// public static DataHelper dataHelper;
	public static SharedPreferences preferences;
	public static DbUtils dbUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		dbUtils = DbUtils.create(this);
		dbUtils.configAllowTransaction(true);
		dbUtils.configDebug(true);
		initPreferences();
		// initDatabase();
	}

	private void initPreferences() {
		preferences = getSharedPreferences("beatbox_shared", MODE_PRIVATE);
	}

	public static MyApplication getApp() {
		return application;
	}

	// private void initDatabase() {
	// InsertData.insertAllData();
	// }

}
