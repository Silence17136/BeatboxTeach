package com.yangbang.beatboxteach.base;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.lidroid.xutils.DbUtils;

public class MyApplication extends Application {
	public static final String IS_FIRST_INSERT = "isFirstInsert";
	protected static MyApplication application;
	// public static DataHelper dataHelper;
	public static SharedPreferences preferences;
	public static DbUtils dbUtils;
	private List<Activity> mList = new LinkedList<Activity>();
	private static MyApplication instance;

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

	// public synchronized static MyApplication getInstance() {
	// if (null == instance) {
	// instance = new MyApplication();
	// }
	// return instance;
	// }

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

}
