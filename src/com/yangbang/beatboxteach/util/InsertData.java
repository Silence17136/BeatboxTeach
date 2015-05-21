package com.yangbang.beatboxteach.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.yangbang.beatboxteach.R;
import com.yangbang.beatboxteach.base.MyApplication;
import com.yangbang.beatboxteach.entity.Teaching;
import com.yangbang.beatboxteach.entity.Title;
import com.yangbang.beatboxteach.entity.Voice;

public class InsertData {
	private static DbUtils dbUtils = MyApplication.dbUtils;
	private static SharedPreferences preferences = MyApplication.preferences;
	private static Resources resources = MyApplication.getApp().getResources();

	public static void insertAllData() {
		// Log.i("YANGBANG", "DatabaseVersion-->"
		// + dbUtils.getDatabase().getVersion());
		// if (dbUtils.getDatabase().getVersion() != preferences.getInt(
		// "db_version", 0)) {

		InputStream stream = resources.openRawResource(R.raw.data_factory);
		try {
			if (stream.available() != preferences.getInt("available", 0)) {
				try {
					dbUtils.dropDb();
					dbUtils.createTableIfNotExist(Title.class);
					dbUtils.createTableIfNotExist(Voice.class);
					dbUtils.createTableIfNotExist(Teaching.class);
				} catch (DbException e2) {
					e2.printStackTrace();
				}
				try {
					InputStreamReader reader = new InputStreamReader(stream,
							"utf-8");
					BufferedReader bReader = new BufferedReader(reader);
					String line;
					try {
						while ((line = bReader.readLine()) != null) {
							try {
								dbUtils.execNonQuery(line);
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
				try {
					preferences.edit().putInt("available", stream.available())
							.commit();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} else {
				Log.i("YANGBANG", "data_factory文件没有任何改变");
			}
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		//
		// String[] textTeachs =
		// resources.getStringArray(R.array.text_teach);
		// for (int i = 0; i < textTeachs.length; i++) {
		// Title title = new Title();
		// title.setName(textTeachs[i]);
		// title.setType(1);
		// try {
		// dbUtils.save(title);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// String[] videoTeachs = resources
		// .getStringArray(R.array.video_teach);
		// for (int i = 0; i < videoTeachs.length; i++) {
		// Title title = new Title();
		// title.setName(videoTeachs[i]);
		// title.setType(2);
		// try {
		// dbUtils.save(title);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// String[] voices1 = resources
		// .getStringArray(R.array.text_base_voice_teach);
		// for (int i = 0; i < voices1.length; i++) {
		// Voice voice = new Voice();
		// voice.setName(voices1[i]);
		// voice.setType(1);
		// Title title = new Title();
		// title.setId(1);
		// voice.setTitle(title);
		// try {
		// dbUtils.save(voice);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// String[] voices2 = resources
		// .getStringArray(R.array.text_special_voice_teach);
		// for (int i = 0; i < voices2.length; i++) {
		// Voice voice = new Voice();
		// voice.setName(voices2[i]);
		// voice.setType(1);
		// Title title = new Title();
		// title.setId(2);
		// voice.setTitle(title);
		// try {
		// dbUtils.save(voice);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// String[] voices3 = resources
		// .getStringArray(R.array.text_segment_teach);
		// for (int i = 0; i < voices3.length; i++) {
		// Voice voice = new Voice();
		// voice.setName(voices3[i]);
		// voice.setType(1);
		// Title title = new Title();
		// title.setId(3);
		// voice.setTitle(title);
		// try {
		// dbUtils.save(voice);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// String[] voices4 = resources
		// .getStringArray(R.array.video_double_teach);
		// for (int i = 0; i < voices4.length; i++) {
		// Voice voice = new Voice();
		// voice.setName(voices4[i]);
		// voice.setType(2);
		// Title title = new Title();
		// title.setId(4);
		// voice.setTitle(title);
		// try {
		// dbUtils.save(voice);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// String[] doubleDetail = resources
		// .getStringArray(R.array.video_double_teach_detail);
		// for (int i = 0; i < 1; i++) {
		// Teaching teaching = new Teaching();
		// teaching.setContent(doubleDetail[i]);
		// teaching.setType(2);
		// Title title = new Title();
		// title.setId(4);
		// teaching.setTitle(title);
		// Voice voice = new Voice();
		// voice.setId(8);
		// teaching.setVoice(voice);
		// try {
		// dbUtils.save(teaching);
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// preferences.edit()
		// .putInt("db_version", dbUtils.getDatabase().getVersion())
		// .commit();
		// }
	}
}
