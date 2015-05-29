package com.yangbang.beatboxteach.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.yangbang.beatboxteach.MainActivity;
import com.yangbang.beatboxteach.R;
import com.yangbang.beatboxteach.base.MyApplication;
import com.yangbang.beatboxteach.entity.Teaching;
import com.yangbang.beatboxteach.entity.Title;
import com.yangbang.beatboxteach.entity.Voice;
import com.yangbang.beatboxteach.view.RoundProgressBar;

public class InsertData {
	private static DbUtils dbUtils = MyApplication.dbUtils;
	private static SharedPreferences preferences = MyApplication.preferences;
	private static Resources resources = MyApplication.getApp().getResources();
	private static int available;

	public static void insertAllData(final Activity context,
			final RoundProgressBar progressBar) {
		// Log.i("YANGBANG", "DatabaseVersion-->"
		// + dbUtils.getDatabase().getVersion());
		// if (dbUtils.getDatabase().getVersion() != preferences.getInt(
		// "db_version", 0)) {

		final InputStream stream = resources
				.openRawResource(R.raw.data_factory);
		try {
			available = stream.available();
		} catch (IOException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		Log.i("YANGBANG", "stream.available()-->" + available);
		Log.i("YANGBANG",
				"preferences.available()-->"
						+ preferences.getInt("available", 0));
		try {
			if (available != preferences.getInt("available", 0)) {
				Log.i("SUMLEN", "stream.available()-->" + stream.available());
				progressBar.setMax(stream.available());
				try {
					dbUtils.dropDb();
					dbUtils.createTableIfNotExist(Title.class);
					dbUtils.createTableIfNotExist(Voice.class);
					dbUtils.createTableIfNotExist(Teaching.class);
				} catch (DbException e2) {
					e2.printStackTrace();
				}

				new AsyncTask<String, Integer, String>() {

					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						Log.i("onPostExecute", "Result-->" + result);
						Intent intent = new Intent(context, MainActivity.class);
						context.startActivity(intent);
						context.finish();
						preferences.edit().putInt("available", available)
								.commit();
						Log.i("YANGBANG", "数据插入完毕..." + available);
					}

					@Override
					protected void onProgressUpdate(Integer... values) {
						int len = values[0];
						Log.i("Progress", "ProgressValues-->" + len);
						progressBar.setProgress(len);
					}

					@Override
					protected String doInBackground(String... params) {
						try {
							InputStreamReader reader = new InputStreamReader(
									stream, "utf-8");
							BufferedReader bReader = new BufferedReader(reader);
							String line;
							StringBuffer sumLen = new StringBuffer();
							try {
								while ((line = bReader.readLine()) != null) {
									sumLen.append(line);
									try {
										dbUtils.execNonQuery(line);
										Log.i("sumLen.toString().getBytes().length",
												"sumLen.toString().getBytes().length-->"
														+ sumLen.toString()
																.getBytes().length
														+ ",,WelcomeActivityIsFinish-->"
														+ context.isFinishing());
										this.publishProgress(sumLen.toString()
												.getBytes().length);
										if (context.isFinishing()) {
											dbUtils.dropDb();
											android.os.Process
													.killProcess(android.os.Process
															.myPid());
											System.exit(0);
											break;
										}
									} catch (DbException e) {
										e.printStackTrace();
									}
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
						return "数据库加载成功";
					}
				}.execute("");

			} else {
				Intent intent = new Intent(context, MainActivity.class);
				context.startActivity(intent);
				context.finish();
				Log.i("YANGBANG", "data_factory文件没有任何改变");
			}
		} catch (IOException e3) {
			e3.printStackTrace();
		}

	}
}
